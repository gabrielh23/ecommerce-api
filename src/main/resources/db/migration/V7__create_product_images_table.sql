CREATE TABLE product_images (
    id UUID PRIMARY KEY,
    url VARCHAR(1000) NOT NULL,
    alt_text VARCHAR(255),
    position INTEGER NOT NULL DEFAULT 0,
    main_image BOOLEAN NOT NULL DEFAULT FALSE,
    product_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT chk_product_images_position
        CHECK (position >= 0),

    CONSTRAINT fk_product_images_product
        FOREIGN KEY (product_id)
            REFERENCES products(id)
            ON DELETE CASCADE
);

CREATE INDEX idx_product_images_product_id
    ON product_images(product_id);

CREATE UNIQUE INDEX idx_product_images_main_image
    ON product_images(product_id)
    WHERE main_image = TRUE;