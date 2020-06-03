INSERT INTO users (created_at, username, password, first_name, last_name, email, enabled)
values
    (now(),'nedvijimost9889@gmail.com', crypt('123456', gen_salt('bf', 8)), '-', '-','nedvijimost9889@gmail.com',true);

