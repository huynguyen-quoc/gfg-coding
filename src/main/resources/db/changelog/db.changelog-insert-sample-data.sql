--liquibase formatted sql
--changeset huynguyen:insert-sample-table
INSERT INTO product (product_Id, title, description, brand, price, color) VALUES
('GAS1234567', 'Jeans', 'Slim fit jeans', 'GAS', 100, 'Blue'),
('REP7876543', 'Jeans', 'Straight fit jeans', 'REPLAY', 150, 'Light Blue'),
('BOS9987676', 'Shirt', 'Button Down Oxford', 'BOSS', 120, 'Navy Blue'),
('LEV8763928', 'T-Shirt', 'Color Block', 'LEVIS', 100, 'Pearl White'),
('GAS8745129', 'Joggers', 'Plain', 'GAS', 100, 'Dark Gray');
--rollback DELETE FROM
--rollback product