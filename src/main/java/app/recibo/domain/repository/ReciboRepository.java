package app.recibo.domain.repository;

import app.recibo.domain.entity.Recibo;
import java.sql.SQLException;
import java.util.List;

public interface ReciboRepository {
	public void create(Recibo recibo) throws SQLException;
	public void delete(Recibo recibo) throws SQLException;
	public Recibo read(long id) throws SQLException;
        public List<Recibo> getAll(long administradoId) throws SQLException;
        public Recibo findByCode(String code) throws SQLException;
}
