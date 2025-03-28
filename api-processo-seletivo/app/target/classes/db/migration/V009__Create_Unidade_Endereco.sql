CREATE TABLE unidade_endereco (
    unid_id INT NOT NULL,
    end_id INT NOT NULL,
    PRIMARY KEY (unid_id, end_id),
    FOREIGN KEY (unid_id) REFERENCES unidade(unid_id),
    FOREIGN KEY (end_id) REFERENCES endereco(end_id)
);