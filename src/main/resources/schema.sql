CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS activity (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS distance_unit (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS user_activity (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id),
    activity_id INTEGER NOT NULL REFERENCES activity(id),
    start_date_time TIMESTAMP,
    end_date_time TIMESTAMP,
    duration INTERVAL GENERATED ALWAYS AS (end_date_time - start_date_time) STORED,
    distance DOUBLE PRECISION,
    distance_unit_id INTEGER REFERENCES distance_unit(id),
    note text
);