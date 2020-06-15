$schema=@'

    CREATE SCHEMA IF NOT EXISTS CosacDB;
    USE CosacDB;

    DROP TABLE IF EXISTS Order_;
    DROP TABLE IF EXISTS User;
    DROP TABLE IF EXISTS Food;
    DROP TABLE IF EXISTS Restriction;
    DROP TABLE IF EXISTS Section;

    CREATE TABLE User (
        userID VARCHAR(11) NOT NULL,
        firstname VARCHAR(50) NULL,
        lastname VARCHAR(50) NULL,
        email VARCHAR(100) NULL,
        password BLOB NULL,
        role VARCHAR(45) NULL,
        locked INT NULL,
        PRIMARY KEY (userID)
    );

    CREATE TABLE Section (
        sectionID INT NOT NULL,
        name VARCHAR(45) NULL,
        PRIMARY KEY (sectionID)
    );

    CREATE TABLE Food (
        foodID INT NOT NULL,
        name VARCHAR(45) NULL,
        Section_sectionID INT NOT NULL,
        PRIMARY KEY (foodID),
        CONSTRAINT fk_Food_Section1
            FOREIGN KEY (Section_sectionID)
            REFERENCES Section (sectionID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
    );

    CREATE TABLE Restriction (
        restrictionID INT NOT NULL AUTO_INCREMENT,
        startTime VARCHAR(45) NULL,
        endTime VARCHAR(45) NULL,
        visitorLimit INT NULL,
        PRIMARY KEY (restrictionID)
    );

    CREATE TABLE Order_ (
        orderID INT NOT NULL,
        Restriction_restrictionID INT NOT NULL,
        Food_foodID INT NOT NULL,
        User_userID VARCHAR(11) NOT NULL,
        PRIMARY KEY (orderID),
        CONSTRAINT fk_Order_Restriction
            FOREIGN KEY (Restriction_restrictionID)
            REFERENCES Restriction (restrictionID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
        CONSTRAINT fk_Order_Food1
            FOREIGN KEY (Food_foodID)
            REFERENCES Food (foodID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
        CONSTRAINT fk_Order_User1
            FOREIGN KEY (User_userID)
            REFERENCES User (userID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
    );

'@

echo $schema | docker exec -i mysql mysql