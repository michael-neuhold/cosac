$insertStatements=@'

    USE CosacDB;
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307094','Michael','Neuhold','michi.neuhold@gmail.com','pwd1', 'admin', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307095','Julian','Jany','julian.jany@gmail.com','pwd2', 'student', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307096','Maxi','Ranger','maxi.ranger@gmail.com','pwd3', 'student', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307097','Claudia','Wimmeder','claudia.wimmeder@gmail.com','student', 'role', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307098','Pia','Schaenzle','pia.schaenzle@gmail.com','pwd5', 'student', 1);

'@

echo $insertStatements | docker exec -i mysql mysql