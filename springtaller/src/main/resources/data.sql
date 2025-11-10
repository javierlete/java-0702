insert or replace into users (username, password, enabled) VALUES ('javier@email.net', '{noop}javier', 1);
insert or replace into users (username, password, enabled) VALUES ('pepe@email.net', '{noop}pepe', 1);
insert or replace into users (username, password, enabled) VALUES ('juan@email.net', '{noop}juan', 1);

insert or replace into authorities (username, authority) VALUES ('javier@email.net', 'ROLE_ADMINISTRADOR');
insert or replace into authorities (username, authority) VALUES ('pepe@email.net', 'ROLE_USUARIO');
insert or replace into authorities (username, authority) VALUES ('juan@email.net', 'ROLE_USUARIO');
