INSERT INTO users (created_at, username, password, first_name, last_name, email, enabled)
values
    (now(),'rys277@mail.ru', crypt('123456', gen_salt('bf', 8)), '-', '-','rys277@mail.ru',true);

