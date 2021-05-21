CREATE TABLE usuarios (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	perfil_id BIGINT(20) NOT NULL,
	name VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(255) NOT NULL,
	FOREIGN KEY (perfil_id) REFERENCES perfis(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# usuarios
INSERT INTO usuarios (`name`, `username`, `email`, `password`, `perfil_id`) VALUES ('Instrutor', 'instrutor', 'instrutor@gft.com', '$2a$10$H794//0Hr1T1HwF.4JyzXerDldLeQpgZe6FeamuDbKz5gtIY2Kmgu', 1);
INSERT INTO usuarios (`name`, `username`, `email`, `password`, `perfil_id`) VALUES ('Diego', 'dodn', 'diego.lourenco@gft.com', '$2a$10$H794//0Hr1T1HwF.4JyzXerDldLeQpgZe6FeamuDbKz5gtIY2Kmgu', 2);
