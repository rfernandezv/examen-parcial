CREATE TABLE recibo (
  recibo_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  code VARCHAR(50) NOT NULL,
  process VARCHAR(200) NOT NULL,
  balance DECIMAL(10,2) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  locked BIT NOT NULL,
  administrado_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY(recibo_id),
  INDEX IX_recibo_administrado_id(recibo_id),
  UNIQUE INDEX UQ_recibo_code(code),
  CONSTRAINT FK_recibo_administrado_id FOREIGN KEY(administrado_id) REFERENCES administrado(administrado_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--INSERT INTO recibo(code, process, balance, currency, locked, administrado_id) VALUES('00001', 'NUEVO_REGISTRO_ARMA', 8.60, 'PEN', 0, 1);
--INSERT INTO recibo(code, process, balance, currency, locked, administrado_id) VALUES('00002', 'CESE_REGISTRO_ARMA', 5.50, 'PEN', 0, 1);

