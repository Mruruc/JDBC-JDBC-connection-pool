CREATE TABLE users(
    user_id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(30) NOT NULL,
    password VARCHAR(150) NOT NULL,
    UNIQUE (user_name)
);
