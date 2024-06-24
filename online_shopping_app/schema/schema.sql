CREATE TABLE users
(
    user_id      SERIAL PRIMARY KEY,
    username     VARCHAR(255) UNIQUE,
    password     VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    phone_number VARCHAR(20)
);
-- Address field
ALTER TABLE users
    ADD COLUMN street  VARCHAR(255),
    ADD COLUMN city    VARCHAR(255),
    ADD COLUMN state   VARCHAR(255),
    ADD COLUMN zipCode VARCHAR(20),
    ADD COLUMN country VARCHAR(255);


CREATE TABLE products
(
    product_id  SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    price       NUMERIC(10, 2),
    quantity    INTEGER
);

ALTER TABLE products
    ADD COLUMN user_id INT;

ALTER TABLE products
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (user_id);

CREATE TABLE orders
(
    order_id     BIGSERIAL PRIMARY KEY,
    user_id      INT,
    order_date   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_amount NUMERIC
);

ALTER TABLE orders
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (user_id);


CREATE TABLE order_item
(
    order_item_id BIGSERIAL PRIMARY KEY,
    product_id    INT NOT NULL,
    order_id      INT NOT NULL,
    quantity      INT,
    unit_price    NUMERIC(10, 2)
);
ALTER TABLE order_item
    ADD CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES products (product_id);

ALTER TABLE order_item
    ADD CONSTRAINT fk_order_id
        FOREIGN KEY (order_id)
            REFERENCES orders (order_id);


CREATE TABLE payment_transactions
(
    transaction_id   BIGSERIAL PRIMARY KEY,
    order_id         INT,
    amount           NUMERIC(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method   VARCHAR(50)    NOT NULL,
    status           VARCHAR(20)
);

ALTER TABLE payment_transactions
    ADD CONSTRAINT fk_order_id
        FOREIGN KEY (order_id)
            REFERENCES orders (order_id);

CREATE TABLE shopping_cart
(
    cart_id    BIGSERIAL PRIMARY KEY,
    user_id    INT NOT NULL,
    product_id INT NOT NULL,
    quantity   INT NOT NULL DEFAULT 1
);

ALTER TABLE shopping_cart
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (user_id);

ALTER TABLE shopping_cart
    ADD CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES products (product_id);


