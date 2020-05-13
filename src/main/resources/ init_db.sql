CREATE TABLE products(
	product_id BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL UNIQUE,
    price DECIMAL NOT NULL
);

CREATE TABLE roles(
	role_id BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL UNIQUE
);
INSERT INTO roles(role_name) VALUES
('ADMIN');
INSERT INTO roles(role_name) VALUES
('USER');

CREATE TABLE users(
	user_id BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE users_roles(
	user_id BIGINT(11) NOT NULL,
	role_id BIGINT(11) NOT NULL
);
ALTER TABLE users_roles ADD FOREIGN KEY (user_id)
	REFERENCES users(user_id);
ALTER TABLE users_roles ADD FOREIGN KEY (role_id)
	REFERENCES roles(role_id);

CREATE TABLE buckets(
	bucket_id BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT(11) NOT NULL
);
ALTER TABLE buckets ADD FOREIGN KEY (user_id)
	REFERENCES users(user_id) ON DELETE CASCADE;

CREATE TABLE buckets_products(
	bucket_id BIGINT(11) NOT NULL ,
	product_id BIGINT(11) NOT NULL
);
ALTER TABLE buckets_products ADD FOREIGN KEY (bucket_id)
	REFERENCES buckets(bucket_id)  ON DELETE CASCADE;
ALTER TABLE buckets_products ADD FOREIGN KEY (product_id)
	REFERENCES products(product_id) ON DELETE CASCADE;

CREATE TABLE orders(
	order_id BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT(11) NOT NULL
);
ALTER TABLE orders ADD FOREIGN KEY (user_id)
	REFERENCES users(user_id);

CREATE TABLE orders_products(
	order_id BIGINT(11) NOT NULL,
	product_id BIGINT(11) NOT NULL
);
ALTER TABLE orders_products ADD FOREIGN KEY (order_id)
	REFERENCES orders(order_id);
ALTER TABLE orders_products ADD FOREIGN KEY (product_id)
	REFERENCES products(product_id);