INSERT INTO PUBLIC.POLLS (ID, VERSION) VALUES (CURRENT_DATE(), 0);
INSERT INTO PUBLIC.POLLS (ID, VERSION) VALUES (CURRENT_DATE() - 1, 0);

INSERT INTO PUBLIC.POLLS_MENUS (POLL_ID, MENUS_ID) VALUES (CURRENT_DATE(), 201);
INSERT INTO PUBLIC.POLLS_MENUS (POLL_ID, MENUS_ID) VALUES (CURRENT_DATE(), 204);
INSERT INTO PUBLIC.POLLS_MENUS (POLL_ID, MENUS_ID) VALUES (CURRENT_DATE(), 205);
INSERT INTO PUBLIC.POLLS_MENUS (POLL_ID, MENUS_ID) VALUES (CURRENT_DATE() - 1, 202);
INSERT INTO PUBLIC.POLLS_MENUS (POLL_ID, MENUS_ID) VALUES (CURRENT_DATE() - 1, 206);

