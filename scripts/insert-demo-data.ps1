$insertStatements=@'

    USE CosacDB;
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307094','Michael','Neuhold','michi.neuhold@gmail.com', '5e884898da2847151d0e56f8dc6292773603dd6aabbdd62a11ef721d1542d8', 'admin', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307095','Julian','Jany','julian.jany@gmail.com', '5e884898da2847151d0e56f8dc6292773603dd6aabbdd62a11ef721d1542d8', 'student', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307096','Maxi','Ranger','maxi.ranger@gmail.com', '5e884898da2847151d0e56f8dc6292773603dd6aabbdd62a11ef721d1542d8', 'student', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307097','Claudia','Wimmeder','claudia.wimmeder@gmail.com', '5e884898da2847151d0e56f8dc6292773603dd6aabbdd62a11ef721d1542d8', 'student', 1);
    INSERT INTO User (userID, firstname, lastname, email, password, role, locked) VALUES ('S1810307098','Pia','Schaenzle','pia.schaenzle@gmail.com', '5e884898da2847151d0e56f8dc6292773603dd6aabbdd62a11ef721d1542d8', 'student', 1);

    INSERT INTO Section (sectionID, name) VALUES (1, 'section 1');
    INSERT INTO Section (sectionID, name) VALUES (2, 'section 2');
    INSERT INTO Section (sectionID, name) VALUES (3, 'section 3');
    INSERT INTO Section (sectionID, name) VALUES (4, 'section 4');

    INSERT INTO Food (foodID, name, Section_sectionID) VALUES (1, 'Schnitzel', 2);
    INSERT INTO Food (foodID, name, Section_sectionID) VALUES (2, 'Nudeln', 3);
    INSERT INTO Food (foodID, name, Section_sectionID) VALUES (3, 'Gulasch', 1);
    INSERT INTO Food (foodID, name, Section_sectionID) VALUES (4, 'Pizza', 4);

    INSERT INTO Restriction (startTime, endTime, visitorLimit) VALUES ('12:00', '13:00', 20);
    INSERT INTO Restriction (startTime, endTime, visitorLimit) VALUES ('13:00', '14:00', 10);
    INSERT INTO Restriction (startTime, endTime, visitorLimit) VALUES ('14:00', '15:00', 50);
    INSERT INTO Restriction (startTime, endTime, visitorLimit) VALUES ('15:00', '16:00', 30);

    INSERT INTO Order_ (orderID, Restriction_restrictionID, Food_foodID, User_userID) VALUES (1, 1, 2, 'S1810307094');
    INSERT INTO Order_ (orderID, Restriction_restrictionID, Food_foodID, User_userID) VALUES (2, 2, 3, 'S1810307095');
    INSERT INTO Order_ (orderID, Restriction_restrictionID, Food_foodID, User_userID) VALUES (3, 1, 2, 'S1810307094');
    INSERT INTO Order_ (orderID, Restriction_restrictionID, Food_foodID, User_userID) VALUES (4, 1, 2, 'S1810307096');
'@

echo $insertStatements | docker exec -i mysql mysql