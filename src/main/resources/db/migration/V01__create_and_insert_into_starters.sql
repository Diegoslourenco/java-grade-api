CREATE TABLE starters (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	username VARCHAR(4) NOT NULL,
	phone VARCHAR(11) not null,
	street VARCHAR(50),
	number VARCHAR(5),
	complement VARCHAR(50),
	district VARCHAR(50),
	cep VARCHAR(30),
	city VARCHAR(50),
	state VARCHAR(2),
	language_name VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# starters
INSERT INTO starters (`name`, `email`, `username`, `phone`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`, `language_name`) VALUES ('Paulo Oliveira', 'paulo.oliveira@gft.com', 'pooa', '15999999999', 'Rua Santos Delares', '25', 'Ap 12', 'Jardim Sandra', '18181818', 'Santos', 'SP', 'Java');
INSERT INTO starters (`name`, `email`, `username`, `phone`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`, `language_name`) VALUES ('Carlos Mancini', 'carlos.mancini@gft.com', 'csmi', '15999999998', 'Rua Pedro Sampaio', '75', 'Casa', 'Parque Mario Filho', '19035962', 'Manaus', 'AM', 'Java');
INSERT INTO starters (`name`, `email`, `username`, `phone`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`, `language_name`) VALUES ('Paulo Oliveira', 'paulo.oliveira@gft.com', 'pooa', '15999999997', 'Avenida André Marques', '15', null, 'Jardim Três Meninos', '36985214', 'Cariacica', 'ES', 'dotnet');