package io.codeforall.hackaton.persistence.dao.jpa;
import io.codeforall.hackaton.persistence.dao.ParentDao;
import io.codeforall.hackaton.persistence.model.Parent;
import org.springframework.stereotype.Repository;
@Repository
public class JpaParentDao extends GenericJpaDao<Parent> implements ParentDao {
        public JpaParentDao() {
            super(Parent.class);
        }
}
