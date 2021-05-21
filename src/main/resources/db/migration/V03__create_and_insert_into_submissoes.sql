CREATE TABLE submissoes (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	repository_url VARCHAR(50) NOT NULL,
	starter_id BIGINT(20) NOT NULL,
	desafio_id BIGINT(20) NOT NULL,
	FOREIGN KEY (starter_id) REFERENCES starters(id),
	FOREIGN KEY (desafio_id) REFERENCES desafios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# submissoes
INSERT INTO submissoes (`starter_id`, `desafio_id`, `repository_url`) VALUES (1, 1, 'www.exemplo.com');
INSERT INTO submissoes (`starter_id`, `desafio_id`, `repository_url`) VALUES (1, 2, 'www.exemplo.com');
INSERT INTO submissoes (`starter_id`, `desafio_id`, `repository_url`) VALUES (2, 3, 'www.exemplo.com');
INSERT INTO submissoes (`starter_id`, `desafio_id`, `repository_url`) VALUES (2, 2, 'www.exemplo.com');
INSERT INTO submissoes (`starter_id`, `desafio_id`, `repository_url`) VALUES (3, 1, 'www.exemplo.com');
INSERT INTO submissoes (`starter_id`, `desafio_id`, `repository_url`) VALUES (3, 2, 'www.exemplo.com');
