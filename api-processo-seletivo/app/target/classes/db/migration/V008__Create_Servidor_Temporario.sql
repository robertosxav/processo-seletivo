CREATE TABLE servidor_temporario (
    pes_id INT NOT NULL,
    st_data_admissao DATE NOT NULL,
    st_data_demissao DATE,
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);