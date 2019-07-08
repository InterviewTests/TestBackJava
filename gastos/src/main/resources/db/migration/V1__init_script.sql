CREATE TABLE card (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  card_number varchar(255) DEFAULT NULL,
  dat_creation datetime NOT NULL,
  dat_update datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY card_UK (card_number)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE spent (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dat_creation datetime NOT NULL,
  description varchar(255) DEFAULT NULL,
  spent_date datetime DEFAULT NULL,
  spent_value double DEFAULT NULL,
  dat_update datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE client (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dat_creation datetime NOT NULL,
  dat_update datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE classification (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  dat_creation datetime NOT NULL,
  dat_update datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE card_spent (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dat_creation datetime NOT NULL,
  dat_update datetime NOT NULL,
  card bigint(20) DEFAULT NULL,
  spent bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_card (card),
  KEY FK_spent (spent),
  CONSTRAINT FK_spent FOREIGN KEY (spent) REFERENCES spent (id),
  CONSTRAINT FK_card FOREIGN KEY (card) REFERENCES card (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE client_card (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dat_creation datetime NOT NULL,
  dat_update datetime NOT NULL,
  client bigint(20) DEFAULT NULL,
  card bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_client (client),
  KEY FK_card_2 (card),
  CONSTRAINT FK_card_2 FOREIGN KEY (card) REFERENCES card (id),
  CONSTRAINT FK_client FOREIGN KEY (client) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE client_classification (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dat_creation datetime NOT NULL,
  dat_update datetime NOT NULL,
  client bigint(20) DEFAULT NULL,
  classification bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_client (client),
  KEY FK_card_2 (card),
  CONSTRAINT FK_classification FOREIGN KEY (classification) REFERENCES classification (id),
  CONSTRAINT FK_client_2 FOREIGN KEY (client) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;