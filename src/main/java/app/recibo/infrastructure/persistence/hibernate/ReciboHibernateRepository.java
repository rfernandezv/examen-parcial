package app.recibo.infrastructure.persistence.hibernate;

import app.recibo.infrastructure.persistence.hibernate.*;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.recibo.domain.entity.Recibo;
import app.recibo.domain.repository.ReciboRepository;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class ReciboHibernateRepository extends BaseHibernateRepository<Recibo> implements ReciboRepository {

	public ReciboHibernateRepository() {
		super(Recibo.class);
	}

        @Override
        public List<Recibo> getAll(long administradoId) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Recibo.class);
            criteria.add(Restrictions.eq("administrado", administradoId));
            return criteria.list();
        }

        @Override
        public Recibo findByCode(String code) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Recibo.class);
            criteria.add(Restrictions.eq("code", code));
            return (Recibo) criteria.uniqueResult();
        }
}
