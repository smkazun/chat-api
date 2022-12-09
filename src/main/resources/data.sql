CREATE TABLE APP_USERS (
     id INT NOT NULL AUTO_INCREMENT,
     first_name VARCHAR(50) NOT NULL,
     last_name VARCHAR(50) NOT NULL,
     email VARCHAR(50) NOT NULL,
     password VARCHAR(50) NOT NULL,
     date_created DATETIME NOT NULL,
     PRIMARY KEY (id)
);

INSERT INTO APP_USERS (first_name, last_name, email, password, date_created) VALUES ('Seb', 'Kazun', 'Someem@gmail.com', 'sldfjhsldkjfh', NOW());