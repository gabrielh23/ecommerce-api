CREATE TABLE inventories (
     id UUID PRIMARY KEY,
     quantity INTEGER NOT NULL DEFAULT 0,
     reserved_quantity INTEGER NOT NULL DEFAULT 0,
     product_variant_id UUID NOT NULL UNIQUE,
     created_at TIMESTAMP NOT NULL,
     updated_at TIMESTAMP NOT NULL,

     CONSTRAINT chk_inventories_quantity
         CHECK (quantity >= 0),

     CONSTRAINT chk_inventories_reserved_quantity
         CHECK (reserved_quantity >= 0),

     CONSTRAINT chk_inventories_reserved_not_greater_than_quantity
         CHECK (reserved_quantity <= quantity),

     CONSTRAINT fk_inventories_product_variant
         FOREIGN KEY (product_variant_id)
         REFERENCES product_variants(id)
);