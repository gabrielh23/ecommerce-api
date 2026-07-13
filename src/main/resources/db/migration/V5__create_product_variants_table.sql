CREATE TABLE product_variants (
    id UUID PRIMARY KEY,
    sku VARCHAR(100) NOT NULL UNIQUE,
    color VARCHAR(80) NOT NULL,
    size VARCHAR(40) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    product_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_product_variants_product
      FOREIGN KEY (product_id)
      REFERENCES products(id)
);