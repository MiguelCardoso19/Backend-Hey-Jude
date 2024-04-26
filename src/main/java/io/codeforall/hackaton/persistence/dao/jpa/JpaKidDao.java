package io.codeforall.hackaton.persistence.dao.jpa;

import io.codeforall.hackaton.persistence.dao.KidDao;
import io.codeforall.hackaton.persistence.model.Kid;
import org.springframework.stereotype.Repository;
@Repository
public class JpaKidDao extends GenericJpaDao<Kid> implements KidDao {

        public JpaKidDao() {
            super(Kid.class);
        }


}
