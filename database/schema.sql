DROP DATABASE IF EXISTS chat_db;
CREATE DATABASE chat_db;
USE chat_db;

CREATE TABLE roles (
                id INT AUTO_INCREMENT NOT NULL,
                name VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE users (
                id INT AUTO_INCREMENT NOT NULL,
                username VARCHAR(255) NOT NULL,
                password VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE users_roles (
                id INT AUTO_INCREMENT NOT NULL,
                role_id INT NOT NULL,
                user_id INT NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE chats (
                id INT AUTO_INCREMENT NOT NULL,
                subject VARCHAR(255) NOT NULL,
                author_id INT NOT NULL,
                date DATETIME NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE messages (
                id INT AUTO_INCREMENT NOT NULL,
                content VARCHAR(255) NOT NULL,
                sender_id INT NOT NULL,
                chat_id INT NOT NULL,
                date DATETIME NOT NULL,
                PRIMARY KEY (id)
);


ALTER TABLE users_roles ADD CONSTRAINT roles_users_roles_fk
FOREIGN KEY (role_id)
REFERENCES roles (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE chats ADD CONSTRAINT users_chats_fk
FOREIGN KEY (author_id)
REFERENCES users (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE messages ADD CONSTRAINT users_messages_fk
FOREIGN KEY (sender_id)
REFERENCES users (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE users_roles ADD CONSTRAINT users_users_roles_fk
FOREIGN KEY (user_id)
REFERENCES users (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE messages ADD CONSTRAINT chats_messages_fk
FOREIGN KEY (chat_id)
REFERENCES chats (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO roles(id, name) VALUES 
(1, 'EMPLOYEE'), (2, 'CUSTOMER');

INSERT INTO users(id, username, password) VALUES
(1, 'employee', '$2a$12$dQdsCLKUK4wot9IlE7FMHOoiEFZh1878SXJ5Fc84qs/xM.bpX4KM.'),
(2, 'customer', '$2a$12$K79DKSmWbCFfs46v5MMFNuSlsqXPzblz.SH0INwyMwnVJPNErHsxa');

INSERT INTO users_roles(id, role_id, user_id) VALUES 
(1, 1, 1), (2, 2, 2);