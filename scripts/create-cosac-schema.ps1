$schema=@'

    CREATE SCHEMA IF NOT EXISTS CosacDB;
    USE CosacDB;
    DROP TABEL IF EXISTS User;

    CREATE TABLE User (
    userID VARCHAR(11) NOT NULL,
    firstname VARCHAR(50) NULL,
    lastname VARCHAR(50) NULL,
    email VARCHAR(100) NULL,
    password BLOB NULL,
    role VARCHAR(45) NULL,
    locked INT NULL,
    PRIMARY KEY (userID));

'@

echo $schema | docker exec -i mysql mysql