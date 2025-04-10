BEGIN;
CREATE TYPE status as enum('CREATED','CONFIRMED','ON_GOING','FINISHED','DELIVERED');
COMMIT;

CREATE TABLE IF NOT EXISTS order_status(
    id_order_status BIGSERIAL PRIMARY KEY,
    id_order int NOT NULL REFERENCES "order"(id_order),
    status status NOT NULL DEFAULT 'CREATED'::status,
    updated_status_datetime timestamp
)