CREATE TABLE servidor_efetivo (
    pes_id INT PRIMARY KEY,
    se_matricula VARCHAR(20) NOT NULL,
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);
