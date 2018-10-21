package app.arma.domain.repository;

import app.arma.domain.entity.Arma;
import java.sql.SQLException;
import java.util.List;

public interface ArmaRepository {
	public void create(Arma arma) throws SQLException;
	public void update(Arma arma) throws SQLException;
        public void delete(Arma arma) throws SQLException;
	public Arma read(long id) throws SQLException;
        public List<Arma> getAll(long administradoId) throws SQLException;
        public Arma findBySerie(String serie) throws SQLException;
        public Arma findByAdministrado(long administradoId) throws SQLException;
}
