INSERT INTO users (created_at, username, password, first_name, last_name, email, enabled)
values
    (now(),'momukulov2014@gmail.com', crypt('123456', gen_salt('bf', 8)), 'Beknazar', 'Momukulov','momukulov2014@gmail.com',true),
    (now(),'Nicolay099010@mail.ru', crypt('123456', gen_salt('bf', 8)), 'Николай', 'Ким','Nicolay099010@mail.ru',true),
    (now(),'buylyashov@mail.ru', crypt('123456', gen_salt('bf', 8)), 'Бектемир', 'Буйляшов','buylyashov@mail.ru',true),
    (now(),'rys277@mail.ru', crypt('123456', gen_salt('bf', 8)), 'Рустам', 'Именов','rys277@mail.ru ',true),
    (now(),'Nedvijimost9889@gmail.com', crypt('123456', gen_salt('bf', 8)), 'Нурлан', 'Канатбек уулу','Nedvijimost9889@gmail.com',true),
    (now(),'kimsanbaevbg@gmail.com', crypt('123456', gen_salt('bf', 8)), 'Батыйхан', 'Кимсанбаев','kimsanbaevbg@gmail.com',true),
    (now(),'zama1310@mail.ru', crypt('123456', gen_salt('bf', 8)), 'Замирбек', 'Эркинбай уулу','zama1310@mail.ru',true),
    (now(),'Zhumagulov97.97@mail.ru', crypt('123456', gen_salt('bf', 8)), 'Медер', 'Джумагулов','Zhumagulov97.97@mail.ru',true),
    (now(),'abaktybekovich@gmail.com', crypt('123456', gen_salt('bf', 8)), 'Айбек', 'Жумашов','abaktybekovich@gmail.com',true),
    (now(),'taisalova1altynai@gmail.com', crypt('123456', gen_salt('bf', 8)), 'Алтынай', 'Тайсалова','taisalova1altynai@gmail.com',true);

