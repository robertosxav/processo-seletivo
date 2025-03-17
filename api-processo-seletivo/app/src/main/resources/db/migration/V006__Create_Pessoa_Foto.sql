CREATE TABLE foto_pessoa (
    fp_id SERIAL PRIMARY KEY,
    pes_id INT NOT NULL,
	fp_data DATE NOT NULL,
    fp_bucket VARCHAR(255),
	fp_hash VARCHAR (50),
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);