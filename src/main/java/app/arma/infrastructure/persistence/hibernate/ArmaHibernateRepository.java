package app.arma.infrastructure.persistence.hibernate;

import app.arma.infrastructure.persistence.hibernate.*;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.arma.domain.entity.Arma;
import app.arma.domain.repository.ArmaRepository;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class ArmaHibernateRepository extends BaseHibernateRepository<Arma> implements ArmaRepository {

	public ArmaHibernateRepository() {
		super(Arma.class);
	}

        @Override
        public List<Arma> getAll(long administradoId) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Arma.class);
            criteria.add(Restrictions.eq("administrado", administradoId));
            return criteria.list();
        }

        @Override
        public Arma findBySerie(String serie) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Arma.class);
            criteria.add(Restrictions.eq("serie", serie));
            return (Arma) criteria.uniqueResult();
        }
        
        @Override
        public Arma findByAdministrado(long administradoId) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Arma.class);
            criteria.add(Restrictions.eq("administrado", administradoId));
            return (Arma) criteria.uniqueResult();
        }
}
