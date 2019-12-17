CREATE TABLE IF NOT EXISTS house (
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    location_id  BIGINT NOT NULL REFERENCES ref_location (id) ON UPDATE CASCADE ON DELETE NO ACTION,
    title      VARCHAR(255),
    address    VARCHAR(255),
    dsc   VARCHAR(255),
    rooms smallint,
    market_type smallint,
    house_type smallint,
    house_number VARCHAR(255),
    area   VARCHAR(255),
    price   BIGINT,
    currency   VARCHAR(255),
    phone_number   VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS image (
     id         BIGSERIAL PRIMARY KEY,
     created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
     updated_at TIMESTAMP WITHOUT TIME ZONE,
     path VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS house_image (
    houses_id BIGINT REFERENCES house (id) ON UPDATE CASCADE ON DELETE NO ACTION,
    images_id BIGINT REFERENCES image (id) ON UPDATE CASCADE ON DELETE NO ACTION
);