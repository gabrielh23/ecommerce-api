CREATE TABLE products (
      id UUID PRIMARY KEY,
      name VARCHAR(160) NOT NULL,
      slug VARCHAR(180) NOT NULL UNIQUE,
      description TEXT,
      price NUMERIC(10, 2) NOT NULL,
      active BOOLEAN NOT NULL DEFAULT TRUE,
      category_id UUID NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL,

      CONSTRAINT fk_products_category
          FOREIGN KEY (category_id)
          REFERENCES categories(id)
);