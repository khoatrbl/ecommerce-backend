package com.khoatrbl.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @Column(name = "product_name_at_purchase", nullable = false, updatable = false)
    private String productNameAtPurchase;

    @Column(name = "unit_price_at_purchase", nullable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal unitPriceAtPurchase;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
