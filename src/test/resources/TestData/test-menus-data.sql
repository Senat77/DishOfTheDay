DELETE FROM MENUS;

INSERT INTO PUBLIC.MENUS (ID, VERSION, DATE, DISHES, RESTAURANT_ID) VALUES (201, 0, CURRENT_DATE(), '[{"name":"Dish1","price":3},{"name":"Dish2","price":4}]', 101);
INSERT INTO PUBLIC.MENUS (ID, VERSION, DATE, DISHES, RESTAURANT_ID) VALUES (202, 0, CURRENT_DATE() - 1, '[{"name":"Dish3","price":1},{"name":"Dish4","price":2}]', 101);
INSERT INTO PUBLIC.MENUS (ID, VERSION, DATE, DISHES, RESTAURANT_ID) VALUES (203, 0, CURRENT_DATE() - 2, '[{"name":"Dish5","price":10},{"name":"Dish6","price":11}]', 101);
INSERT INTO PUBLIC.MENUS (ID, VERSION, DATE, DISHES, RESTAURANT_ID) VALUES (204, 0, CURRENT_DATE(), '[{"name":"Dish7","price":8},{"name":"Dish8","price":9}]', 103);
INSERT INTO PUBLIC.MENUS (ID, VERSION, DATE, DISHES, RESTAURANT_ID) VALUES (205, 0, CURRENT_DATE(), '[{"name":"Dish9","price":5},{"name":"Dish10","price":6},{"name":"Dish11","price":7}]', 104);