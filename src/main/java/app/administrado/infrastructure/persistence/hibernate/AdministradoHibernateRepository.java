package app.administrado.infrastructure.persistence.hibernate;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.administrado.domain.entity.Administrado;
import app.administrado.domain.repository.AdministradoRepository;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class AdministradoHibernateRepository extends BaseHibernateRepository<Administrado> implements AdministradoRepository {

	public AdministradoHibernateRepository() {
		super(Administrado.class);
	}

        @Override
        public List<Administrado> getAll() throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Administrado.class);
            return criteria.list();
        }

        @Override
        public Administrado findByIdentityDocument(String identityDocument) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Administrado.class);
            criteria.add(Restrictions.eq("identityDocument", identityDocument));
            return (Administrado) criteria.uniqueResult();
        }
}
