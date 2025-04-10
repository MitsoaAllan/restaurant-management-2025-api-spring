INSERT INTO move (id_ingredient,move_type,quantity,unit,update_move)
VALUES
    (4,'IN',50,'U','2025-02-01 08:00:00'),
    (3,'IN',100,'U','2025-02-01 08:00:00'),
    (2,'IN',20,'L','2025-02-01 08:00:00'),
    (1,'IN',10000,'G','2025-02-01 08:00:00') ON CONFLICT DO NOTHING ;


INSERT INTO move(id_ingredient, move_type, quantity, unit, update_move)
VALUES
    (3,'OUT',10,'U','2025-02-02 10:00:00'),
    (3,'OUT',10,'U','2025-02-03 15:00:00'),
    (4,'OUT',20,'U','2025-02-05 16:00:00')ON CONFLICT DO NOTHING;
