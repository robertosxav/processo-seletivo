CREATE TABLE pessoa_endereco (
    pes_id INT NOT NULL,
    end_id INT NOT NULL,
    PRIMARY KEY (pes_id, end_id),
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id),
    FOREIGN KEY (end_id) REFERENCES endereco(end_id)
);