INSERT INTO oauth_client_details(client_id, client_secret,  scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES ('trusted-app', '$2a$08$V0jF.bqEvkO9EhpSEfXi5OZ2JAygrAyz/8X3BLCKHgc2pPyBi4Spy', 'read,write,trust', 'client_credentials,authorization_code,implicit,password,refresh_token','ROLE_CLIENT,ROLE_TRUSTED_CLIENT','45000', '45000');
 
INSERT INTO `users` (`user_id`, `email`, `first_name`, `last_name`, `mobile`, `password`, `user_name`) 
VALUES(123456,'demo@example.com', 'Demo', 'Example', '1234578632', '$2a$08$V0jF.bqEvkO9EhpSEfXi5OZ2JAygrAyz/8X3BLCKHgc2pPyBi4Spy', 'demo');

INSERT INTO `roles` (`role_id`, `role_name`) 
VALUES(1234, 'ROLE_DEMO');

INSERT INTO `user_roles` (`role_id`, `user_id`) 
VALUES(1234, 123456);