
CREATE TABLE APP_USERS (
     id INT NOT NULL AUTO_INCREMENT,
     first_name VARCHAR(50) NOT NULL,
     last_name VARCHAR(50) NOT NULL,
     email VARCHAR(50) NOT NULL,
     password VARCHAR(60) NOT NULL,
     date_created DATETIME NOT NULL,
     date_updated DATETIME,
     PRIMARY KEY (id)
);

INSERT INTO APP_USERS (first_name, last_name, email, password, date_created, date_updated) VALUES ('Seb', 'Kazun', 'Someem@gmail.com', '$2a$12$PhlN2dvxl.naeWn3VWRBjeQvYRVJy1jnT0jzwO8My0tiE.5auDBxO', NOW(), null);

---------FOR DEVELOPMENT PURPOSES ONLY---------
--Spring Security (Jdbc) generated tables
INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) VALUES ('user', '$2a$10$x9rfO4SjhECePg2YiN92xuTdtpapgEKcvBctcVyfctBSDZ9M0.cKa', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('user', 'ROLE_USER');



