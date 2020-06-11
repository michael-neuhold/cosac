$select=@'

    USE CosacDB;
    SELECT * FROM User;

'@

echo $select | docker exec -i mysql mysql