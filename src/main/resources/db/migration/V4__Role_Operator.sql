DELETE from roles where id = 2;

INSERT INTO roles (created_at, title, code)
VALUES (now(), 'Operator User', 'ROLE_OPERATOR');