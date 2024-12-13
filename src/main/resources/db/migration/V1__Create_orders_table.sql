CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        order_number VARCHAR(50) NOT NULL,
                        items VARCHAR(255) NOT NULL,
                        table_number INT NOT NULL,
                        waiter_name VARCHAR(100) NOT NULL
);

