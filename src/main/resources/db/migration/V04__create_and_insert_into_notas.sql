CREATE TABLE notas (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	submissao_id BIGINT(20) NOT NULL,
	grade_code_quality INT(11) NOT NULL,
	grade_quantity_delivered INT(11) NOT NULL,
	FOREIGN KEY (submissao_id) REFERENCES submissoes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# notas
INSERT INTO notas (`submissao_id`, `grade_code_quality`, `grade_quantity_delivered`) VALUES (1, 3, 3);
INSERT INTO notas (`submissao_id`, `grade_code_quality`, `grade_quantity_delivered`) VALUES (2, 2, 3);
INSERT INTO notas (`submissao_id`, `grade_code_quality`, `grade_quantity_delivered`) VALUES (3, 1, 1);
INSERT INTO notas (`submissao_id`, `grade_code_quality`, `grade_quantity_delivered`) VALUES (4, 2, 2);
INSERT INTO notas (`submissao_id`, `grade_code_quality`, `grade_quantity_delivered`) VALUES (5, 3, 2);
INSERT INTO notas (`submissao_id`, `grade_code_quality`, `grade_quantity_delivered`) VALUES (6, 2, 1);
