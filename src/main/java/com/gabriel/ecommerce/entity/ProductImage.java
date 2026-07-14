package com.gabriel.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    @Id
    private UUID id;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(name = "alt_text", length = 255)
    private String altText;

    @Column(nullable = false)
    private Integer position;

    @Column(name = "main_image", nullable = false)
    private Boolean mainImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();

        if (this.position == null) {
            this.position = 0;
        }

        if (this.mainImage == null) {
            this.mainImage = false;
        }

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}