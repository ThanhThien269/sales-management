package com.interview.demo.infrastructure.persistence.mybatis;

import com.interview.demo.domain.entities.request_dto.product.ProductFilterOption;
import com.interview.demo.domain.entities.response_dto.product.ProductResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMybatisRepository {
    List<ProductResponse> findByFilterOptions(
            @Param("f") ProductFilterOption filter,
            @Param("page") Integer page,
            @Param("size") Integer size
    );
}
