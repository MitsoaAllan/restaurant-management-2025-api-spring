CREATE TABLE IF NOT EXISTS dish_order_status(
    id_order int NOT NULL REFERENCES "order"(id),
    id_dish int NOT NULL REFERENCES dish(id),
    status status NOT NULL DEFAULT 'CREATED'::status,
    created_datetime timestamp DEFAULT now(),
    PRIMARY KEY (id_order,id_dish)
);