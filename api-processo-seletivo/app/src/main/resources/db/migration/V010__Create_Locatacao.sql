CREATE TABLE lotacao (
    lot_id SERIAL PRIMARY KEY,
    pes_id INT NOT NULL,
    unid_id INT NOT NULL,
    lot_data_lotacao DATE  NOT NULL,
	lot_data_remocao DATE,
    lot_portaria VARCHAR(100) NOT NULL,
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id),
    FOREIGN KEY (unid_id) REFERENCES unidade(unid_id)
);