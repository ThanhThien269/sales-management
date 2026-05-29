package com.interview.demo;

import com.interview.demo.constant.database.InvoiceStatusEnum;
import com.interview.demo.constant.database.ProductStatusEnum;
import com.interview.demo.domain.entities.database.Invoice;
import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.infrastructure.persistence.converter.InvoiceStatusConverter;
import com.interview.demo.infrastructure.persistence.converter.ProductStatusConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StatusConverterTests {

    private final InvoiceStatusConverter invoiceStatusConverter = new InvoiceStatusConverter();
    private final ProductStatusConverter productStatusConverter = new ProductStatusConverter();

    @Test
    void invoiceStatusConverterShouldConvertBetweenEnumAndId() {
        assertThat(invoiceStatusConverter.convertToDatabaseColumn(InvoiceStatusEnum.PAID)).isEqualTo(2);
        assertThat(invoiceStatusConverter.convertToEntityAttribute(3)).isEqualTo(InvoiceStatusEnum.CANCELED);
        assertThat(invoiceStatusConverter.convertToDatabaseColumn(null)).isNull();
        assertThat(invoiceStatusConverter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void productStatusConverterShouldConvertBetweenEnumAndId() {
        assertThat(productStatusConverter.convertToDatabaseColumn(ProductStatusEnum.ACTIVE)).isEqualTo(1);
        assertThat(productStatusConverter.convertToEntityAttribute(3)).isEqualTo(ProductStatusEnum.OUT_OF_STOCK);
        assertThat(productStatusConverter.convertToDatabaseColumn(null)).isNull();
        assertThat(productStatusConverter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void entitiesShouldExposeStatusIdFromEnum() {
        Invoice invoice = Invoice.builder()
                .status(InvoiceStatusEnum.WAITING)
                .build();
        Product product = Product.builder()
                .status(ProductStatusEnum.INACTIVE)
                .build();

        assertThat(invoice.getStatusId()).isEqualTo(1);
        assertThat(product.getStatusId()).isEqualTo(2);
    }

    @Test
    void invalidDatabaseIdShouldFailFast() {
        assertThatThrownBy(() -> invoiceStatusConverter.convertToEntityAttribute(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid status ID: 99");

        assertThatThrownBy(() -> productStatusConverter.convertToEntityAttribute(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid status ID: 99");
    }
}

