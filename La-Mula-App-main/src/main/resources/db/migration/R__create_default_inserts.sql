INSERT INTO roles (name, description) VALUES ('CLIENT', 'Es la persona que realiza las compras en APP') ON CONFLICT DO NOTHING;
INSERT INTO roles (name, description) VALUES ('SELLER', 'Es la persona que realiza las ventas en el sistema') ON CONFLICT DO NOTHING;
INSERT INTO roles (name, description) VALUES ('ADMIN', 'Es la persona que realiza tareas de administración en el sistema') ON CONFLICT DO NOTHING;

INSERT INTO order_statuses (name, description) VALUES ('IN_PROGRESS', 'Estado inicial de la orden') ON CONFLICT DO NOTHING;
INSERT INTO order_statuses (name, description) VALUES ('SENT', 'Productos enviados al cliente') ON CONFLICT DO NOTHING;
INSERT INTO order_statuses (name, description) VALUES ('DELIVERED', 'Productos entregados al cliente') ON CONFLICT DO NOTHING;
INSERT INTO order_statuses (name, description) VALUES ('REJECTED', 'Orden rechazada') ON CONFLICT DO NOTHING;

INSERT INTO coffee_types (name, description) VALUES ('NATURE', 'NATURE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('BRESI', 'BRESI') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('ESPRESSO', 'ESPRESSO') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('CHOCOLAT', 'CHOCOLAT') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('COLOMBIE', 'COLOMBIE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('FILTRE', 'FILTRE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('FRUITÉ', 'FRUITÉ') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('TRADITIONAL', 'TRADITIONAL') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('NICARAGUA', 'NICARAGUA') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('EXOTIQUE', 'EXOTIQUE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('ANAEROBIQUE', 'ANAEROBIQUE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (name, description) VALUES ('LAVÉ', 'LAVÉ') ON CONFLICT DO NOTHING;
