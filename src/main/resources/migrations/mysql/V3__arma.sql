CREATE TABLE arma (
  arma_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  serie VARCHAR(50) NOT NULL,  
  brand VARCHAR(50) NOT NULL,
  model VARCHAR(50) NOT NULL,
  locked BIT NOT NULL,
  administrado_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY(arma_id),
  INDEX IX_arma_administrado_id(administrado_id),
  UNIQUE INDEX UQ_arma_serie(serie),
  CONSTRAINT FK_arma_administrado_id FOREIGN KEY(administrado_id) REFERENCES administrado(administrado_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--INSERT INTO user(serie, brand, model, locked, administrado_id) VALUES('R224P0A319', 'TAURUS', 'PT99AF', 0, 1);