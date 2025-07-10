INSERT INTO tb_role (id, name) VALUES (3, 'ROLE_CLIENT');
INSERT INTO tb_role (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO tb_role (id, name) VALUES (1, 'ROLE_ADMIN');

INSERT INTO tb_user (id, email) VALUES (1, 'admin@example.com');
INSERT INTO tb_user (id, email) VALUES (2, 'moderator@example.com');
INSERT INTO tb_user (id, email) VALUES (3, 'client@example.com');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
