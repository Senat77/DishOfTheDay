DELETE FROM ROLES;
DELETE FROM USERS;

INSERT INTO PUBLIC.USERS (ID, VERSION, EMAIL, ENABLE, NAME, PASSWORD) VALUES (0, 0, 'admin1@site.com', true, 'admin', '{bcrypt}$2a$10$pYzqHaNTVSmsOZUV3IGwruiT3Cpk8UctkTc2izURDnwCq90IUQINK');
INSERT INTO PUBLIC.USERS (ID, VERSION, EMAIL, ENABLE, NAME, PASSWORD) VALUES (1, 0, 'user1@site.com', true, 'user1', '{bcrypt}$2a$10$lz/dOvdjrgfNzD7uvPPI3.rIT0QAw/SHD85pUSGjJg6EfGlAh/of.');
INSERT INTO PUBLIC.USERS (ID, VERSION, EMAIL, ENABLE, NAME, PASSWORD) VALUES (2, 0, 'user2@site.com', true, 'user2', '{bcrypt}$2a$10$zPIKb8KCpuClX.Yh5bcxhORJCUoNr1UcXHd3Y8kDTXV1aMU2s0OyO');
INSERT INTO PUBLIC.USERS (ID, VERSION, EMAIL, ENABLE, NAME, PASSWORD) VALUES (3, 0, 'user3@site.com', true, 'user3', '{bcrypt}$2a$10$50idlSmSOTt/c/gjANAsceeVepjdGdFIeB4kRgCtWQWfn9n2Qw5Xy');
INSERT INTO PUBLIC.USERS (ID, VERSION, EMAIL, ENABLE, NAME, PASSWORD) VALUES (4, 0, 'user4@site.com', true, 'user4', '{bcrypt}$2a$10$9CmwK3JW/GsJFdXuog/P6ebHyP5r0kN8sWpJGYTotD/CYjIGh2Fk2');
INSERT INTO PUBLIC.USERS (ID, VERSION, EMAIL, ENABLE, NAME, PASSWORD) VALUES (5, 0, 'user5@site.com', true, 'user5', '{bcrypt}$2a$10$FKXL40xuefMQio6bx.z0o.li5ZkBKq1BSXe61jRbHu32HLAEz./wG');

INSERT INTO PUBLIC.ROLES (ID, ROLES) VALUES (0, 1);
INSERT INTO PUBLIC.ROLES (ID, ROLES) VALUES (1, 0);
INSERT INTO PUBLIC.ROLES (ID, ROLES) VALUES (2, 0);
INSERT INTO PUBLIC.ROLES (ID, ROLES) VALUES (3, 0);
INSERT INTO PUBLIC.ROLES (ID, ROLES) VALUES (4, 0);
INSERT INTO PUBLIC.ROLES (ID, ROLES) VALUES (5, 0);