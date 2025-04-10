CREATE TABLE IF NOT EXISTS dish_order(
    id_dish int NOT NULL REFERENCES dish(id_dish),
    id_order int NOT NULL REFERENCES "order"(id_order),
    quantity int NOT NULL,
    PRIMARY KEY (id_dish,id_order)
)