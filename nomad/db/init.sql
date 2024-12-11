CREATE DATABASE nomad;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- hashed password
    email VARCHAR(100),
    full_name VARCHAR(100)
);

CREATE TABLE Countries (
    country_id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL,
    country_code VARCHAR(3) NOT NULL -- for easy reference
);

CREATE TABLE Accommodations (
    accommodation_id INT AUTO_INCREMENT PRIMARY KEY,
    accommodation_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    carbon_footprint DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Transportation (
    transport_id INT AUTO_INCREMENT PRIMARY KEY,
    transport_type VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    carbon_footprint DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Activities (
    activity_id INT AUTO_INCREMENT PRIMARY KEY,
    activity_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    carbon_footprint DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Trips (
    trip_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10, 2),
    total_carbon_footprint DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);


CREATE TABLE Trip_Countries (
    trip_id INT,
    country_id INT,
    PRIMARY KEY (trip_id, country_id),
    FOREIGN KEY (trip_id) REFERENCES Trips(trip_id),
    FOREIGN KEY (country_id) REFERENCES Countries(country_id)
);

CREATE TABLE Trip_Accommodations (
    trip_id INT,
    accommodation_id INT,
    PRIMARY KEY (trip_id, accommodation_id),
    FOREIGN KEY (trip_id) REFERENCES Trips(trip_id),
    FOREIGN KEY (accommodation_id) REFERENCES Accommodations(accommodation_id)
);

CREATE TABLE Trip_Transportation (
    trip_id INT,
    transport_id INT,
    PRIMARY KEY (trip_id, transport_id),
    FOREIGN KEY (trip_id) REFERENCES Trips(trip_id),
    FOREIGN KEY (transport_id) REFERENCES Transportation(transport_id)
);

CREATE TABLE Trip_Activities (
    trip_id INT,
    activity_id INT,
    PRIMARY KEY (trip_id, activity_id),
    FOREIGN KEY (trip_id) REFERENCES Trips(trip_id),
    FOREIGN KEY (activity_id) REFERENCES Activities(activity_id)
);

INSERT INTO Countries (country_name, country_code)
VALUES
    ('United States', 'USA'),
    ('Canada', 'CAN'),
    ('United Kingdom', 'GBR'),
    ('France', 'FRA'),
    ('Germany', 'DEU'),
    ('Japan', 'JPN'),
    ('Australia', 'AUS'),
    ('Italy', 'ITA'),
    ('Spain', 'ESP'),
    ('India', 'IND');

INSERT INTO Accommodations (accommodation_name, price, carbon_footprint)
VALUES
    ('Luxury Hotel', 200.00, 50.0),
    ('Budget Hostel', 50.00, 10.0),
    ('Beachfront Resort', 300.00, 75.0),
    ('Airbnb Apartment', 120.00, 25.0),
    ('Mountain Lodge', 150.00, 40.0),
    ('City Center Hotel', 180.00, 60.0);

INSERT INTO Transportation (transport_type, price, carbon_footprint)
VALUES
    ('Flight (Economy)', 500.00, 200.0),
    ('Flight (Business)', 1500.00, 400.0),
    ('Train', 300.00, 100.0),
    ('Bus', 50.00, 10.0),
    ('Car Rental', 200.00, 50.0),
    ('Ferry', 100.00, 30.0);

INSERT INTO Activities (activity_name, price, carbon_footprint)
VALUES
    ('City Tour', 50.00, 10.0),
    ('Nature Tour', 30.00, 5.0),
    ('Cultural Tour', 20.00, 2.0),
    ('Food Tour', 40.00, 5.0),
    ('Family Tour', 150.00, 20.0),
    ('Adventure Tour', 100.00, 15.0);




