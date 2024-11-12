INSERT INTO roles (id_role, name, description) VALUES ('44aa77eb-c9e9-40ec-8325-d281552bb7a7', 'CLIENT', 'Es la persona que realiza las compras en APP') ON CONFLICT DO NOTHING;
INSERT INTO roles (id_role, name, description) VALUES ('7da73dec-6bf2-4d2f-98d6-8b406fccfa5a', 'SELLER', 'Es la persona que realiza las ventas en el sistema') ON CONFLICT DO NOTHING;
INSERT INTO roles (id_role, name, description) VALUES ('e5f0a0b6-55d0-4e84-bc0d-af1be6b0d405', 'ADMIN', 'Es la persona que realiza tareas de administración en el sistema') ON CONFLICT DO NOTHING;

INSERT INTO order_statuses (type_status, description) VALUES ('IN_PROGRESS', 'Estado inicial de la orden') ON CONFLICT DO NOTHING;
INSERT INTO order_statuses (type_status, description) VALUES ('SENT', 'Productos enviados al cliente') ON CONFLICT DO NOTHING;
INSERT INTO order_statuses (type_status, description) VALUES ('DELIVERED', 'Productos entregados al cliente') ON CONFLICT DO NOTHING;
INSERT INTO order_statuses (type_status, description) VALUES ('REJECTED', 'Orden rechazada') ON CONFLICT DO NOTHING;

INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('78c11152-b4a7-4289-a646-9ef9458c5741', 'NATURE', 'NATURE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('e8aaf358-97bd-43d3-a855-2fd0fc4c9e99', 'BRESI', 'BRESI') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('4c2f8b30-b7db-40c4-8f8c-7b7c35554fba', 'ESPRESSO', 'ESPRESSO') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('1a96f74c-ea9e-4d50-9524-fd8a8e6dacc7', 'CHOCOLAT', 'CHOCOLAT') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('1158cd49-f368-414b-93af-5106fa6f4f09', 'COLOMBIE', 'COLOMBIE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('7d0be894-5987-4b1e-814b-91cec148a54e', 'FILTRE', 'FILTRE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('e9e322eb-49bc-49c4-acdd-656fdeda3e3b', 'FRUITÉ', 'FRUITÉ') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('a5439780-440b-4b82-a291-db6a1a7843e7', 'TRADITIONAL', 'TRADITIONAL') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('da18c6b1-e52f-45e1-b848-58ca57868fc4', 'NICARAGUA', 'NICARAGUA') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('bcef7ba6-bc54-47ea-b46e-26aca48911a9', 'EXOTIQUE', 'EXOTIQUE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('626359e1-6001-469a-9ddb-94cda73269a3', 'ANAEROBIQUE', 'ANAEROBIQUE') ON CONFLICT DO NOTHING;
INSERT INTO coffee_types (id_coffee_type, name, description) VALUES ('2eee9f36-e981-4d89-adc8-3a85cf2caae1', 'LAVÉ', 'LAVÉ') ON CONFLICT DO NOTHING;
