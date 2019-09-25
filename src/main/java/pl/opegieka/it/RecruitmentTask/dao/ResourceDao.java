package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Model.Resource;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ResourceDao {

    public static final Logger LOG = LogManager.getLogger(ResourceDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public long save(Resource r) {
        entityManager.persist(r);
        return r.getResourceId();
    }

    public Resource update(Resource r) {
        return entityManager.merge(r);
    }

    public void delete(long id) {
        final Resource r = entityManager.find(Resource.class, id);
        if (r != null) {
            entityManager.remove(r);
        }
    }

    public Resource findById(long id) {
        return entityManager.find(Resource.class, id);
    }

    public List<Resource> findAll() {
        final Query query = entityManager.createQuery("SELECT r FROM Resource r");

        return query.getResultList();
    }

    public int checkIfExist(String resourceName) {
        final Query query = entityManager.createQuery("SELECT p FROM Resource p where p.resourceName = :resourceName");
        query.setParameter("resourceName", resourceName);
        List<Resource> groupList = query.getResultList();
        if (groupList.size() <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public Resource findByName(String name) {
        final Query query = entityManager.createQuery("SELECT r FROM Resource r where resourceName = :name");
        query.setParameter("name", name);
        List<Resource> groupList = query.getResultList();
        if (groupList.size() <= 0) {
            throw new NotFoundException(String.format("Resource %s not exist", name));
        }

        Resource Resource = groupList.get(0);
        return Resource;

    }

}
