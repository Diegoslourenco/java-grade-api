CREATE TABLE desafios (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# desafios
INSERT INTO desafios (`name`) VALUES ('mvc');
INSERT INTO desafios (`name`) VALUES ('api');
INSERT INTO desafios (`name`) VALUES ('testes');