SELECT id, name, birth_date
    FROM users
        WHERE LOWER(name) LIKE LOWER(CONCAT(:prefix, '%'))
            ORDER BY name