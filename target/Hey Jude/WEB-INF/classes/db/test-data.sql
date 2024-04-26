USE heyjude;

-- Insert data into the 'kid' table
TRUNCATE TABLE kid;
INSERT INTO kid (name, age, email, password, dateOfBirth)
VALUES ('Kid 1', 10, 'kid1@example.com', 'kidpassword1', '2014-02-11'),
       ('Kid 2', 8, 'kid2@example.com', 'kidpassword2', '2016-02-11'),
       ('Kid 3', 12, 'kid3@example.com', 'kidpassword3', '2012-02-11');

-- Insert data into the 'parent' table
TRUNCATE TABLE parent;
INSERT INTO parent (name, age, email, password, kid_id, dateOfBirth)
VALUES ('Parent 1', 24, 'parent1@example.com', 'password1', 1, '2000-02-11'),
       ('Parent 2', 44, 'parent2@example.com', 'password2', 1, '1980-02-11'),
       ('Parent 3', 34, 'parent3@example.com', 'password3', 2, '1990-02-11');


TRUNCATE TABLE events;
INSERT INTO events (description, startDate, endDate, kid_id)
    VALUES ('golfe', '2024-04-19', '2024-04-22',1);


