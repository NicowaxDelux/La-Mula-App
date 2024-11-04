CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles (
    id_role UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name CHARACTER VARYING NOT NULL UNIQUE,
    description CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);

CREATE TABLE logins (
    id_login UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_role UUID NOT NULL REFERENCES roles(id_role),
    username CHARACTER VARYING NOT NULL,
    password CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);

CREATE TABLE users (
    id_user UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_login UUID NOT NULL REFERENCES logins(id_login),
    name CHARACTER VARYING NOT NULL,
    email CHARACTER VARYING NOT NULL,
    address CHARACTER VARYING NOT NULL,
    phone CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);

CREATE TABLE companies (
   id_company UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   id_login UUID NOT NULL REFERENCES logins(id_login),
   name CHARACTER VARYING NOT NULL,
   email CHARACTER VARYING NOT NULL,
   address CHARACTER VARYING,
   phone CHARACTER VARYING,
   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
   updated_at TIMESTAMP,
   updated_by CHARACTER VARYING
);

CREATE TABLE order_statuses (
    id_order_status UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type_status CHARACTER VARYING NOT NULL UNIQUE,
    description CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);

CREATE TABLE orders (
    id_order UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_user UUID NOT NULL REFERENCES users(id_user),
    id_order_status UUID NOT NULL REFERENCES order_statuses(id_order_status),
    order_code SERIAL UNIQUE NOT NULL,
    total_cost NUMERIC(8, 4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);

CREATE TABLE coffee_types (
    id_coffee_type UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name CHARACTER VARYING NOT NULL UNIQUE,
    description CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);

CREATE TABLE attachments (
     id_attachment UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     name CHARACTER VARYING NOT NULL,
     content BYTEA NOT NULL,
     content_type CHARACTER VARYING NOT NULL,
     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
     updated_at TIMESTAMP,
     updated_by CHARACTER VARYING
);

CREATE TABLE products (
    id_product UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_coffee_type UUID REFERENCES coffee_types(id_coffee_type),
    id_attachment UUID NOT NULL REFERENCES attachments(id_attachment),
    id_company UUID NOT NULL REFERENCES companies(id_company),
    name CHARACTER VARYING NOT NULL,
    description CHARACTER VARYING NOT NULL,
    unit_cost NUMERIC(8, 4) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0,
    package_size CHARACTER VARYING NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);

CREATE TABLE oder_details (
    id_order_detail UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_order UUID NOT NULL REFERENCES orders(id_order),
    id_product UUID NOT NULL REFERENCES products(id_product),
    quantity INTEGER NOT NULL,
    unit_cost NUMERIC(8, 4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHARACTER VARYING NOT NULL DEFAULT 'SYSTEM',
    updated_at TIMESTAMP,
    updated_by CHARACTER VARYING
);
