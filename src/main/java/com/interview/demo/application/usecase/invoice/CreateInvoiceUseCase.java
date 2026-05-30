package com.interview.demo.application.usecase.invoice;

import com.interview.demo.application.validation.ProductValidation;
import com.interview.demo.constant.database.InvoiceStatusEnum;
import com.interview.demo.constant.database.ProductStatusEnum;
import com.interview.demo.constant.enumuration.MessageCode;
import com.interview.demo.core.usecase.UseCase;
import com.interview.demo.core.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.FixedLocaleMessage;
import com.interview.demo.domain.entities.database.Invoice;
import com.interview.demo.domain.entities.database.InvoiceItem;
import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.domain.entities.request_dto.invoice.CreateInvoiceRequestBody;
import com.interview.demo.domain.entities.request_dto.invoice.InvoiceItemRequest;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceItemResponse;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceResponse;
import com.interview.demo.domain.repositories.InvoiceRepository;
import com.interview.demo.domain.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Component
@RequiredArgsConstructor
public class CreateInvoiceUseCase extends UseCase<CreateInvoiceUseCase.InputValue, ApiResponse, UseCaseContext> {

    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final ProductValidation productValidation;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        CreateInvoiceRequestBody body = input.requestBody();


        boolean hasDiscountAmount = body.getDiscountAmount() != null
                && body.getDiscountAmount().compareTo(BigDecimal.ZERO) > 0;
        boolean hasDiscountPercentage = body.getDiscountPercentage() != null
                && body.getDiscountPercentage().compareTo(BigDecimal.ZERO) > 0;
        if (hasDiscountAmount && hasDiscountPercentage) {
            throw new IllegalArgumentException(
                    MessageCode.CANNOT_APPLY_DISCOUNT_AMOUNT_AND_PERCENTAGE.name());
        }


        BigDecimal originalAmount = BigDecimal.ZERO;
        List<InvoiceItem> itemsToSave = new ArrayList<>();
        List<InvoiceItemResponse> itemResponses = new ArrayList<>();

        for (InvoiceItemRequest itemReq : body.getItems()) {
            Product product = productValidation.validateActiveProductThenReturn(itemReq.getProductId());

            int orderQty = itemReq.getQuantity();
            int stock = product.getQuantity() != null ? product.getQuantity() : 0;

            // Cap quantity tối đa theo stock hiện có
            int actualQty = Math.min(orderQty, stock);

            BigDecimal price = product.getPrice();
            BigDecimal subTotal = price.multiply(BigDecimal.valueOf(actualQty));
            originalAmount = originalAmount.add(subTotal);

            int newStock = stock - actualQty;
            product.setQuantity(newStock);
            int sold = product.getSoldQuantity() != null ? product.getSoldQuantity() : 0;
            product.setSoldQuantity(sold + actualQty);


            if (newStock == 0) {
                product.setStatus(ProductStatusEnum.OUT_OF_STOCK);
            }
            productRepository.save(product);

            itemsToSave.add(InvoiceItem.builder()
                    .productId(itemReq.getProductId())
                    .quantity(actualQty)
                    .price(price)
                    .build());

            itemResponses.add(InvoiceItemResponse.builder()
                    .productId(itemReq.getProductId().toString())
                    .quantity(actualQty)
                    .price(price)
                    .subTotal(subTotal)
                    .build());
        }

        // ── 3. Tính totalAmount ──────────────────────────────────────────
        BigDecimal extraFee = body.getExtraFee() != null ? body.getExtraFee() : BigDecimal.ZERO;
        BigDecimal discountAmount = body.getDiscountAmount() != null ? body.getDiscountAmount() : BigDecimal.ZERO;
        BigDecimal discountPercentage = body.getDiscountPercentage() != null ? body.getDiscountPercentage() : BigDecimal.ZERO;

        BigDecimal totalAmount;
        if (hasDiscountPercentage) {
            BigDecimal discountValue = originalAmount
                    .multiply(discountPercentage)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
            totalAmount = originalAmount.subtract(discountValue).add(extraFee);
        } else {
            totalAmount = originalAmount.subtract(discountAmount).add(extraFee);
        }

        // ── 4. Lưu Invoice ───────────────────────────────────────────────
        Invoice invoice = Invoice.builder()
                .invoiceNumber(generateInvoiceNumber())
                .invoiceNote(body.getNote())
                .customerName(body.getCustomerName())
                .customerPhone(body.getCustomerPhoneNumber())
                .originalAmount(originalAmount)
                .extraFee(extraFee)
                .discountAmount(discountAmount)
                .discountPercentage(discountPercentage)
                .totalAmount(totalAmount)
                .status(InvoiceStatusEnum.WAITING)
                .build();

        Invoice saved = invoiceRepository.save(invoice);

        // ── 5. Lưu InvoiceItems (gắn invoiceId) ─────────────────────────
        itemsToSave.forEach(item -> item.setInvoiceId(saved.getId()));
        invoiceRepository.saveAllItems(itemsToSave);

        // ── 6. Build response ────────────────────────────────────────────
        InvoiceResponse response = InvoiceResponse.builder()
                .id(saved.getId().toString())
                .invoiceNumber(saved.getInvoiceNumber())
                .invoiceNote(saved.getInvoiceNote())
                .customerName(saved.getCustomerName())
                .customerPhone(saved.getCustomerPhone())
                .items(itemResponses)
                .originalAmount(saved.getOriginalAmount())
                .extraFee(saved.getExtraFee())
                .discountAmount(saved.getDiscountAmount())
                .discountPercentage(saved.getDiscountPercentage())
                .totalAmount(saved.getTotalAmount())
                .statusId(saved.getStatusId())
                .statusName(saved.getStatus().name())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();

        return ApiResponse.builder()
                .result("success")
                .localeMessage(new FixedLocaleMessage(MessageCode.CREATE_SUCCESSFULLY))
                .content(response)
                .status(HttpStatus.CREATED)
                .build();
    }

    private String generateInvoiceNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }

    public record InputValue(
            CreateInvoiceRequestBody requestBody,
            HttpServletRequest httpServletRequest
    ) implements UseCase.InputValue {}
}

