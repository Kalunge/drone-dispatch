CREATE TYPE  drone_model AS ENUM ('LIGHTWEIGHT', 'MIDDLEWEIGHT', 'CRUISERWEIGHT', 'HEAVYWEIGHT');
CREATE TYPE drone_state AS ENUM ('IDLE', 'LOADING', 'LOADED', 'DELIVERING', 'DELIVERED', 'RETURNING');

CREATE TABLE IF NOT EXISTS drone (
                                     id BIGSERIAL PRIMARY KEY,
                                     serial_number VARCHAR(100) NOT NULL,
                                     model drone_model NOT NULL,
                                     weight_limit FLOAT NOT NULL CHECK (weight_limit <= 500),
                                     battery_capacity INT NOT NULL CHECK (battery_capacity >= 0 AND battery_capacity <= 100),
                                     state drone_state NOT NULL
);

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
VALUES
    ('DRONE1', 'LIGHTWEIGHT', 300, 100, 'IDLE'),
    ('DRONE2', 'MIDDLEWEIGHT', 400, 80, 'IDLE'),
    ('DRONE3', 'HEAVYWEIGHT', 500, 60, 'IDLE');