CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS watches (
                                       id SERIAL PRIMARY KEY,
                                       brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE PRECISION NOT NULL,
    stock_quantity INTEGER,
    image_url TEXT,
    type VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS cart_items (
                                          id SERIAL PRIMARY KEY,
                                          watch_id BIGINT NOT NULL,
                                          quantity INTEGER NOT NULL,
                                          session_id VARCHAR(255) NOT NULL,
    CONSTRAINT fk_watch FOREIGN KEY (watch_id) REFERENCES watches(id)
    );
