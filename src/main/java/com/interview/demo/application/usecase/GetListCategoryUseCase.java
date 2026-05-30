package com.interview.demo.application.usecase;

import com.interview.demo.common.usecase.UseCase;
import com.interview.demo.common.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.database.Category;
import com.interview.demo.domain.repositories.CategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetListCategoryUseCase extends UseCase<GetListCategoryUseCase.InputValue, ApiResponse, UseCaseContext> {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        List<Category> categories = categoryRepository.findAll();

        return ApiResponse.builder()
                .result("success")
                .content(categories)
                .status(HttpStatus.OK)
                .build();
    }

    public record InputValue(
            HttpServletRequest httpServletRequest
    ) implements UseCase.InputValue {}
}

