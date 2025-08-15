INSERT INTO tb_role (name) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (name) VALUES ('ROLE_MODERATOR');
INSERT INTO tb_role (name) VALUES ('ROLE_USER');

INSERT INTO tb_user (email, password) VALUES ('admin@example.com', '$2a$10$x10ghvYds9Itx2kEsoiGP.ug2f2NAyimkJOcKUm4yuiyW9kSbKx/G');
INSERT INTO tb_user (email, password) VALUES ('moderator@example.com', '$2a$10$XcfdodwriZqj2r1azxYd7eLji6F2c7VNrDlPxtJGiyRy9ruURQ3bK');
INSERT INTO tb_user (email, password) VALUES ('user@example.com', '$2a$10$6mRxj1KvEFCfl.4nFsc/z.NrXaVel0FTqxP/US3cPTuHYczTZi9Zy');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
