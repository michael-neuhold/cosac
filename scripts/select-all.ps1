$select=@'

    USE CosacDB;
    SELECT * FROM User;
    SELECT * FROM Restriction;
    SELECT * FROM Order_;
    SELECT * FROM Section;
    SELECT * FROM Food;

'@

echo $select | docker exec -i mysql mysql