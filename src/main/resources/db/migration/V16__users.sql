INSERT INTO users (created_at, username, password, first_name, last_name, email, enabled)
values
    (now(),'Stimirlan@gmail.com', crypt('123456', gen_salt('bf', 8)), 'Темирлан', 'Сейдахматов','Stimirlan@gmail.com',true),
    (now(),'Zhany959595@mail.ru', crypt('123456', gen_salt('bf', 8)), 'Айгерим', 'Жаныгулова','Zhany959595@mail.ru',true);

