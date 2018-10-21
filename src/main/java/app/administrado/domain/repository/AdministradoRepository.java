package app.administrado.domain.repository;

import app.administrado.domain.entity.Administrado;
import java.sql.SQLException;
import java.util.List;

public interface AdministradoRepository {
	public void create(Administrado administrado) throws SQLException;
	public void delete(Administrado administrado) throws SQLException;
	public Administrado read(long id) throws SQLException;
        public List<Administrado> getAll() throws SQLException;
        public Administrado findByIdentityDocument(String identityDocument) throws SQLException;
}
