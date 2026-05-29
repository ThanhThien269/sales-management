package com.interview.demo.domain.entities.database;

import com.interview.demo.constant.database.ProductStatusEnum;
import com.interview.demo.domain.abstracts.DbEntity;
import com.interview.demo.infrastructure.persistence.converter.ProductStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "sold_quantity", nullable = false)
    private Integer soldQuantity = 0;

    @Column(name = "image")
    private String image;

    @Column(name = "status_id", nullable = false)
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatusEnum status;

    @Transient
    public Integer getStatusId() {
        return status != null ? status.getId() : null;
    }


}
