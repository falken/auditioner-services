
CREATE DATABASE auditioner;
USE auditioner;

CREATE USER 'auditioner'@'localhost' IDENTIFIED BY 'Password1!';

GRANT ALL PRIVILEGES ON *.* TO 'auditioner'@'localhost';

