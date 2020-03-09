INSERT INTO ref_location (created_at, title, parent_id,location_level)
VALUES
  (now(), 'Бишкек', null ,1);

INSERT INTO ref_location
  (created_at, title, parent_id,location_level)
VALUES
  (now(), 'Южный микрорайон',	(select id from ref_location where title='Бишкек'), 2),
  (now(), 'Центр',	(select id from ref_location where title='Бишкек'), 2),
  (now(), 'Другие',	(select id from ref_location where title='Бишкек'), 2);
