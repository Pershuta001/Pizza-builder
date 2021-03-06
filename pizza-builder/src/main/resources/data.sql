insert into users(uuid, email, hashed_password, name, role_id,phone)
values ('b210ad51-7558-48f6-b8b0-40e536e2eda7', 'admin@example.com', '$2a$10$fW131c/DHDePUXyHeaBz1OQZN6ERZxKK3UIT6iCnst8WsFiFu3sx.', 'Ivan', 2, '+380668897412'),
       ('9ff585cb-3865-4589-bd3d-749cd8872fa5', 'shutyak2001@gmail.com', '$2a$10$fW131c/DHDePUXyHeaBz1OQZN6ERZxKK3UIT6iCnst8WsFiFu3sx.', 'Taisiia', 1, '+380668898412');
insert into ingredient_group (uuid, name, label)
values ('9ff585cb-3865-4589-bd3d-749cd8872fa8','Meat','🥩'),
       ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777','Cheese','🧀'),
       ('4b5c8cc1-8773-43ee-a736-652b5a188893','Vegetable','🍈'),
       ('d655d24f-1051-4a3c-8bc8-3626c24c68b8','Sauce','🥫'),
       ('68540c80-ed3f-4510-a82d-d84a0d889036','Dough','🍞');
INSERT INTO ingredient (uuid, name, photo_url, price, spicy, vegan, vegetarian, group_uuid)
VALUES ('5e722dd1-dd82-41c3-8130-b4b6bbc8001a', 'Cheddar',
        'https://cdn.pixabay.com/photo/2013/07/13/12/31/cheese-159788_1280.png',
        20.5, false, false, false, '205ffc2c-2445-4ca3-8969-5d5e4bcb7777'),
       ('9c1e9397-5b01-4691-993f-c69c895193cf', 'Gouda',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Gouda.jpg/205px-Gouda.jpg',
        28, false, false, false, '205ffc2c-2445-4ca3-8969-5d5e4bcb7777'),
       ('71b216a3-b8e9-4064-abc1-24eb5501afcf', 'Dor Blue',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Dorblu_Cheese.jpg/548px-Dorblu_Cheese.jpg',
        15, false, false, false, '205ffc2c-2445-4ca3-8969-5d5e4bcb7777'),
       ('41a9379d-77b5-4692-b22d-d559b9ec7fe2', 'Mozzarella',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/440px-Three_mozzarellas_on_the_toast.jpg',
        30, false, false, false, '205ffc2c-2445-4ca3-8969-5d5e4bcb7777'),

       ('80266af4-1ea4-49ec-9881-586e3b6407f8', 'Sweet Italian Sausage',
        'https://dorismarket.com/wp-content/uploads/2018/09/mile-sausage-with-and-without-fennel.jpg',
        18.5, false, false, false, '9ff585cb-3865-4589-bd3d-749cd8872fa8'),
       ('d4bd2282-28a7-4a96-9c40-417feb30d603', 'Pork & Beef Pepperoni',
        'https://upload.wikimedia.org/wikipedia/commons/1/18/Salami_aka.jpg',
        44, false, false, false, '9ff585cb-3865-4589-bd3d-749cd8872fa8'),
        ('803b3475-8e3b-46df-93b7-eb1a1d31c84e', 'Black Forest Ham',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Ham_%284%29.jpg/320px-Ham_%284%29.jpg',
        35, false, false, false, '9ff585cb-3865-4589-bd3d-749cd8872fa8'),
        ('36c2ad28-855d-4486-b426-3e4961499aff', 'Chicken',
        'https://thehealthybutcher.com/images/P.cache.x1/The-Healthy-Butcher-Organic-Boneless-Skinless-Chicken-Breast.jpg',
        40, false, false, false, '9ff585cb-3865-4589-bd3d-749cd8872fa8'),


       ('2ce1ef95-2bf3-4ec8-8702-be205dc2cf74', 'Unleavened Dough',
        'https://www.akcrust.com/hs-fs/hubfs/Blog_Images/Dough-Proofing.jpg',
        25.5, false, false, false, '68540c80-ed3f-4510-a82d-d84a0d889036'),
       ('d2d1b18e-1e7f-4a75-82c0-5a85a82bad3f', 'Yeast Dough',
        'https://www.akcrust.com/hs-fs/hubfs/Blog_Images/Dough%20Ball%20Life%20Cycle.jpg',
        23, false, false, false, '68540c80-ed3f-4510-a82d-d84a0d889036'),
        ('6d0c882f-8168-4042-8685-0596320b76ca', 'Thin Dough',
        'https://assets.epicurious.com/photos/5776e756844278e739c4e278/master/pass/pastry-dough.jpg',
        30, false, false, false, '68540c80-ed3f-4510-a82d-d84a0d889036'),
        ('1983bdfc-399f-45b2-879b-1a21ae3eb826', 'Italian Pizza Dough',
        'https://img.freepik.com/free-photo/pizza-pieces-dough_23-2148570342.jpg',
        40, false, false, false, '68540c80-ed3f-4510-a82d-d84a0d889036'),


       ('5e722dd1-dd82-41c3-8130-b4b6bbc8001b', 'Onion',
        'https://basketbaba.com/wp-content/uploads/2017/06/onion-pyaj-pyaz.jpg',
        20, false, true, true, '4b5c8cc1-8773-43ee-a736-652b5a188893'),
       ('5e722dd1-dd82-41c3-8130-b4b6bac8001b', 'Pepper',
        'https://basketbaba.com/wp-content/uploads/2017/07/capsicum-shimla-mirch-160x130.jpg',
        25, false, true, true, '4b5c8cc1-8773-43ee-a736-652b5a188893'),
       ('afed9018-8a27-4cd1-a366-6a2a3e4a4aad', 'Tomato',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Pomidory_-_tomato.jpg/1200px-Pomidory_-_tomato.jpg',
        30, false, true, true, '4b5c8cc1-8773-43ee-a736-652b5a188893'),
       ('e77aca0b-936a-46d4-8699-6bd3f807c94d', 'Olive',
        'https://www.medolico.com/upfiles/Image/Olives/olive01.jpg',
        40, false, true, true, '4b5c8cc1-8773-43ee-a736-652b5a188893'),
       ('6fd5c4eb-ea62-4ca9-8e6f-e88df42b70ba', 'Chilly pepper',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Red_Chilli.jpg/320px-Red_Chilli.jpg',
        15, true, true, true, '4b5c8cc1-8773-43ee-a736-652b5a188893'),

       ('0b2c03ee-4436-4de5-a27b-d1f276fd8680', 'Pesto Sauce',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/BasilPesto.JPG/500px-BasilPesto.JPG',
        18, true, true, true, 'd655d24f-1051-4a3c-8bc8-3626c24c68b8'),
       ('ea7ad070-1cea-4177-a2fc-e0b518b4dbf6', 'BBQ Sauce',
        'https://i.pinimg.com/originals/88/2f/1b/882f1b524aba125e5a301ded7de3f0cb.jpg',
        19, true, true, true, 'd655d24f-1051-4a3c-8bc8-3626c24c68b8'),
       ('1e1bd08a-0c20-4239-840a-b09130fb2248', 'Peppery Red Sauce',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/BasilPesto.JPG/500px-BasilPesto.JPG',
        20.5, true, true, true, 'd655d24f-1051-4a3c-8bc8-3626c24c68b8'),
       ('9932307f-5e22-4ccd-ad63-89f47573eaa2', 'Spicy Red Sauce',
        'https://i.pinimg.com/originals/88/2f/1b/882f1b524aba125e5a301ded7de3f0cb.jpg',
        26, true, true, true, 'd655d24f-1051-4a3c-8bc8-3626c24c68b8');

INSERT INTO ingredient_group_ingredients (ingredient_group_uuid, ingredients_uuid)
 VALUES ('d655d24f-1051-4a3c-8bc8-3626c24c68b8',
         '0b2c03ee-4436-4de5-a27b-d1f276fd8680'),
        ('d655d24f-1051-4a3c-8bc8-3626c24c68b8',
         'ea7ad070-1cea-4177-a2fc-e0b518b4dbf6'),
        ('d655d24f-1051-4a3c-8bc8-3626c24c68b8',
         '1e1bd08a-0c20-4239-840a-b09130fb2248'),
        ('d655d24f-1051-4a3c-8bc8-3626c24c68b8',
         '9932307f-5e22-4ccd-ad63-89f47573eaa2'),

        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         '5e722dd1-dd82-41c3-8130-b4b6bbc8001a'),
        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         '9c1e9397-5b01-4691-993f-c69c895193cf'),
        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         '71b216a3-b8e9-4064-abc1-24eb5501afcf'),
        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         '41a9379d-77b5-4692-b22d-d559b9ec7fe2'),

        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         '2ce1ef95-2bf3-4ec8-8702-be205dc2cf74'),
        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         'd2d1b18e-1e7f-4a75-82c0-5a85a82bad3f'),
        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         '6d0c882f-8168-4042-8685-0596320b76ca'),
        ('205ffc2c-2445-4ca3-8969-5d5e4bcb7777',
         '1983bdfc-399f-45b2-879b-1a21ae3eb826'),

        ('9ff585cb-3865-4589-bd3d-749cd8872fa8',
         '80266af4-1ea4-49ec-9881-586e3b6407f8'),
        ('9ff585cb-3865-4589-bd3d-749cd8872fa8',
         'd4bd2282-28a7-4a96-9c40-417feb30d603'),
        ('9ff585cb-3865-4589-bd3d-749cd8872fa8',
         '803b3475-8e3b-46df-93b7-eb1a1d31c84e'),
        ('9ff585cb-3865-4589-bd3d-749cd8872fa8',
         '36c2ad28-855d-4486-b426-3e4961499aff'),

        ('4b5c8cc1-8773-43ee-a736-652b5a188893',
         '5e722dd1-dd82-41c3-8130-b4b6bbc8001b'),
        ('4b5c8cc1-8773-43ee-a736-652b5a188893',
         '5e722dd1-dd82-41c3-8130-b4b6bac8001b'),
        ('4b5c8cc1-8773-43ee-a736-652b5a188893',
         'afed9018-8a27-4cd1-a366-6a2a3e4a4aad'),
        ('4b5c8cc1-8773-43ee-a736-652b5a188893',
         'e77aca0b-936a-46d4-8699-6bd3f807c94d'),
        ('4b5c8cc1-8773-43ee-a736-652b5a188893',
         '6fd5c4eb-ea62-4ca9-8e6f-e88df42b70ba')
         ;

insert into pizza_pattern(uuid, confirmed, name, photo_url, user_uuid)
values ('d56ef4ec-2f16-45e0-9e97-ae5236a06213', true, 'Margarita', 'https://static.1000.menu/img/content-v2/ef/27/10853/picca-margarita-v-domashnix-usloviyax_1608783820_32_max.jpg','9ff585cb-3865-4589-bd3d-749cd8872fa5' );

insert into ingredient_in_pizza (ingredient_uuid, pattern_uuid, quantity)
values ('2ce1ef95-2bf3-4ec8-8702-be205dc2cf74','d56ef4ec-2f16-45e0-9e97-ae5236a06213',1),
       ('e77aca0b-936a-46d4-8699-6bd3f807c94d','d56ef4ec-2f16-45e0-9e97-ae5236a06213',1),
       ('6fd5c4eb-ea62-4ca9-8e6f-e88df42b70ba','d56ef4ec-2f16-45e0-9e97-ae5236a06213',2),
       ('36c2ad28-855d-4486-b426-3e4961499aff','d56ef4ec-2f16-45e0-9e97-ae5236a06213',3),
       ('0b2c03ee-4436-4de5-a27b-d1f276fd8680','d56ef4ec-2f16-45e0-9e97-ae5236a06213',1),
       ('d2d1b18e-1e7f-4a75-82c0-5a85a82bad3f','d56ef4ec-2f16-45e0-9e97-ae5236a06213',1);
insert into pizza_pattern_ingredients(pizza_pattern_uuid, ingredients_ingredient_uuid, ingredients_pattern_uuid)
VALUES ('d56ef4ec-2f16-45e0-9e97-ae5236a06213','2ce1ef95-2bf3-4ec8-8702-be205dc2cf74','d56ef4ec-2f16-45e0-9e97-ae5236a06213'),
       ('d56ef4ec-2f16-45e0-9e97-ae5236a06213','e77aca0b-936a-46d4-8699-6bd3f807c94d','d56ef4ec-2f16-45e0-9e97-ae5236a06213'),
       ('d56ef4ec-2f16-45e0-9e97-ae5236a06213','6fd5c4eb-ea62-4ca9-8e6f-e88df42b70ba','d56ef4ec-2f16-45e0-9e97-ae5236a06213'),
       ('d56ef4ec-2f16-45e0-9e97-ae5236a06213','36c2ad28-855d-4486-b426-3e4961499aff','d56ef4ec-2f16-45e0-9e97-ae5236a06213'),
       ('d56ef4ec-2f16-45e0-9e97-ae5236a06213','0b2c03ee-4436-4de5-a27b-d1f276fd8680','d56ef4ec-2f16-45e0-9e97-ae5236a06213'),
       ('d56ef4ec-2f16-45e0-9e97-ae5236a06213','d2d1b18e-1e7f-4a75-82c0-5a85a82bad3f','d56ef4ec-2f16-45e0-9e97-ae5236a06213');

select ingredient_uuid from pizzabuilder.public.ingredient_in_pizza;