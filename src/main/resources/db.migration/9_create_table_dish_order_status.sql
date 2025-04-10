CREATE TABLE IF NOT EXISTS dish_order_status(
    id_order int REFERENCES "order"(id_order),
    id_dish int REFERENCES dish(id_dish),
    status status NOT NULL DEFAULT 'CREATED'::status,
    updated_status_datetime timestamp DEFAULT now()
)