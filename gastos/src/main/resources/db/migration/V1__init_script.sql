CREATE TABLE spent (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  card_number varchar(255) DEFAULT NULL,
  dat_creation datetime NOT NULL,
  description varchar(255) DEFAULT NULL,
  spent_date datetime DEFAULT NULL,
  spent_value double DEFAULT NULL,
  dat_update datetime NOT NULL,
  user_code bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;