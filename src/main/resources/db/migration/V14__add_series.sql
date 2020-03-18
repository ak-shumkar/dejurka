CREATE TABLE IF NOT EXISTS series (
     id         BIGSERIAL PRIMARY KEY,
     created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
     updated_at TIMESTAMP WITHOUT TIME ZONE,
     name VARCHAR(150)
);

ALTER TABLE house
ADD COLUMN IF NOT EXISTS series_id BIGINT;