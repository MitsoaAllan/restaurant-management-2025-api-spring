INSERT INTO ingredient
(name)
VALUES ('Saucisse', 20, 'G', '2025-03-15'),
       ('Huile', 10000, 'L', '2025-03-15'),
       ('Oeuf', 1000, 'U', '2025-03-15'),
       ('Pain', 1000, 'U', '2025-03-15')
on conflict do nothing