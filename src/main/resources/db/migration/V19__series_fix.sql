ALTER TABLE house DROP COLUMN IF EXISTS series_id;
ALTER TABLE house ADD COLUMN series_id BIGINT NOT NULL REFERENCES series(id) ON UPDATE CASCADE ON DELETE NO ACTION default null;

