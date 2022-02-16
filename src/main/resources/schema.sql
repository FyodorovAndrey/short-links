CREATE TABLE IF NOT EXISTS links
(
    id SERIAL PRIMARY KEY,
    long_link VARCHAR (100),
    short_link VARCHAR (20),
    created_at TIMESTAMP
);