CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles (
    id_role UUID PRIMARY KEY,
    name CHARACTER VARYING NOT NULL,
    description CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE users (
    id_user UUID PRIMARY KEY,
    id_role UUID NOT NULL REFERENCES roles(id_role),
    name CHARACTER VARYING NOT NULL,
    email CHARACTER VARYING NOT NULL,
    address CHARACTER VARYING NOT NULL,
    phone CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE logins (
    id_login UUID PRIMARY KEY,
    id_user UUID NOT NULL REFERENCES users(id_user),
    username CHARACTER VARYING NOT NULL,
    password CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE order_statuses (
    id_order_status UUID PRIMARY KEY,
    name CHARACTER VARYING NOT NULL,
    description CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE orders (
    id_order UUID PRIMARY KEY,
    id_user UUID NOT NULL REFERENCES users(id_user),
    id_order_status UUID NOT NULL REFERENCES order_statuses(id_order_status),
    order_code SERIAL NOT NULL,
    total_cost NUMERIC(8, 4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE coffee_types (
    id_coffee_type UUID PRIMARY KEY,
    name CHARACTER VARYING NOT NULL,
    description CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE products (
    id_product UUID PRIMARY KEY,
    id_coffee_type UUID REFERENCES coffee_types(id_coffee_type),
    name CHARACTER VARYING NOT NULL,
    description CHARACTER VARYING NOT NULL,
    unit_cost NUMERIC(8, 4) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE attachments (
    id_attachment UUID PRIMARY KEY,
    name CHARACTER VARYING NOT NULL,
    content BYTEA NOT NULL,
    content_type CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

CREATE TABLE product_details (
    id_product_detail UUID PRIMARY KEY,
    id_attachment UUID NOT NULL REFERENCES attachments(id_attachment),
    name CHARACTER VARYING NOT NULL,
    package_size CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);

COMMENT ON COLUMN product_details.package_size IS '10 Kg';

CREATE TABLE oder_details (
    id_order_detail UUID PRIMARY KEY,
    id_order UUID NOT NULL REFERENCES orders(id_order),
    id_product UUID NOT NULL REFERENCES products(id_product),
    quantity INTEGER NOT NULL,
    unit_cost NUMERIC(8, 4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by TIMESTAMP
);
