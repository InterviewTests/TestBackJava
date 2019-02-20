CREATE TABLE oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(255),
  autoapprove VARCHAR(255)
 );

CREATE TABLE oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

CREATE TABLE oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);
CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);
CREATE TABLE oauth_code (
  code VARCHAR(255), authentication BLOB
);

CREATE TABLE `users` (
  `user_id` int(10) unsigned NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_63cf888pmqtt5tipcne79xsbm` (`mobile`)
);

CREATE TABLE `roles` (
  `role_id` smallint(5) unsigned NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK3cyq9kgtpbol1tlouij82oufa` (`role_id`,`role_name`),
  UNIQUE KEY `UK_716hgxp60ym1lifrdgp67xt5k` (`role_name`)
);

CREATE TABLE `user_roles` (
  `user_id` int(10) unsigned NOT NULL,
  `role_id` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
