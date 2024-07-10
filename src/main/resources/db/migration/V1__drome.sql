DROP type drone_model;
DROP type drone_state;
CREATE TYPE  drone_model AS ENUM ('LIGHTWEIGHT', 'MIDDLEWEIGHT', 'CRUISERWEIGHT', 'HEAVYWEIGHT');
CREATE TYPE drone_state AS ENUM ('IDLE', 'LOADING', 'LOADED', 'DELIVERING', 'DELIVERED', 'RETURNING');
CREATE TABLE IF NOT EXISTS drone (
                                     id SERIAL PRIMARY KEY,
                                     serial_number VARCHAR(100) NOT NULL,
                                     model VARCHAR(20) NOT NULL CHECK (model IN ('LIGHTWEIGHT', 'MIDDLEWEIGHT', 'CRUISERWEIGHT', 'HEAVYWEIGHT')),
                                     weight_limit FLOAT NOT NULL CHECK (weight_limit <= 500),
                                     battery_capacity INT NOT NULL CHECK (battery_capacity >= 0 AND battery_capacity <= 100),
                                     state VARCHAR(20) NOT NULL CHECK (state IN ('IDLE', 'LOADING', 'LOADED', 'DELIVERING', 'DELIVERED', 'RETURNING'))
);


CREATE TABLE IF NOT EXISTS medication (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(100) NOT NULL,
                                          weight FLOAT NOT NULL,
                                          code VARCHAR(100) NOT NULL CHECK (code ~* '^[A-Z0-9_]+$'),
                                          image VARCHAR(255) NOT NULL,
                                          drone_id INTEGER,
                                          CONSTRAINT fk_drone
                                              FOREIGN KEY (drone_id)
                                                  REFERENCES drone(id)
                                                  ON DELETE CASCADE
);

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state) VALUES
                                                                                    ('DRONE1', 'LIGHTWEIGHT', 300, 100, 'IDLE'),
                                                                                    ('DRONE2', 'MIDDLEWEIGHT', 400, 80, 'IDLE'),
                                                                                    ('DRONE3', 'HEAVYWEIGHT', 500, 60, 'IDLE');

INSERT INTO medication (name, weight, code, image, drone_id) VALUES
                                                                 ('Med1', 100, 'MED_001', 'med1.jpg', 1),
                                                                 ('Med2', 150, 'MED_002', 'med2.jpg', 1),
                                                                 ('Med3', 200, 'MED_003', 'med3.jpg', 2),
                                                                 ('Med4', 250, 'MED_004', 'med4.jpg', 3),
                                                                 ('Med5', 300, 'MED_005', 'med5.jpg', NULL); -- NULL means not assigned to any drone yet
