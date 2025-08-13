INSERT INTO tb_role (id, name) VALUES (3, 'ROLE_CLIENT');
INSERT INTO tb_role (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO tb_role (id, name) VALUES (1, 'ROLE_ADMIN');

INSERT INTO tb_user (id, email, password) VALUES (1, 'admin@example.com', '$2a$10$x10ghvYds9Itx2kEsoiGP.ug2f2NAyimkJOcKUm4yuiyW9kSbKx/G');
INSERT INTO tb_user (id, email, password) VALUES (2, 'moderator@example.com', '$2a$10$XcfdodwriZqj2r1azxYd7eLji6F2c7VNrDlPxtJGiyRy9ruURQ3bK');
INSERT INTO tb_user (id, email, password) VALUES (3, 'client@example.com', '$2a$10$6mRxj1KvEFCfl.4nFsc/z.NrXaVel0FTqxP/US3cPTuHYczTZi9Zy');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
