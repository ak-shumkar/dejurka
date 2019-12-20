CREATE TABLE IF NOT EXISTS ranklist (
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    username VARCHAR(255),
    solved smallint
);

INSERT INTO ranklist
  (created_at,username)
VALUES
  (now(), 'emli'),
  (now(), 'zubaidullo'),
  (now(), 'kalandar'),
  (now(), 'smilerik'),
  (now(), 'BekbolotovBolot'),
  (now(), 'wwormich'),
  (now(), 'ADJA'),
  (now(), 'mbek'),
  (now(), 'justlive'),
  (now(), 'mfv'),
  (now(), 'abdykaparkamilov');

