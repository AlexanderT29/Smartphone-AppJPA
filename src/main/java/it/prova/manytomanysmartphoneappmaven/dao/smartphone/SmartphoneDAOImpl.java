package it.prova.manytomanysmartphoneappmaven.dao.smartphone;

import it.prova.manytomanysmartphoneappmaven.dao.EntityManagerUtily;
import it.prova.manytomanysmartphoneappmaven.model.App;
import it.prova.manytomanysmartphoneappmaven.model.Smartphone;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SmartphoneDAOImpl implements SmartphoneDAO{

    private EntityManager entityManager;

    @Override
    public List<Smartphone> listAll() throws Exception {
        return entityManager.createQuery("FROM Smartphone ", Smartphone.class).getResultList();
    }

    @Override
    public Smartphone findById(Long smartphoneId) throws Exception {
        return entityManager.find(Smartphone.class, smartphoneId);
    }

    @Override
    public void insert(Smartphone smartphone) throws Exception {
        if(smartphone == null){
            throw new Exception("Input non valido");
        }
        entityManager.persist(smartphone);
    }

    @Override
    public void update(Smartphone smartphone) throws Exception {
        if(smartphone == null){
            throw new Exception("Input non valido");
        }
        entityManager.merge(smartphone);
    }

    @Override
    public void delete(Smartphone smartphone) throws Exception {
        if(smartphone == null){
            throw new Exception("Input non valido");
        }
        entityManager.remove(entityManager.merge(smartphone));
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteAndUnlinkApp(Long smartphoneId) throws Exception {
        entityManager.createNativeQuery("DELETE FROM smartphone_app AS sa WHERE smartphone_id = ?1")
                .setParameter(1, smartphoneId)
                .executeUpdate();
        entityManager.createNativeQuery("DELETE FROM smartphone WHERE id = ?1")
                .setParameter(1, smartphoneId)
                .executeUpdate();
    }



    public Smartphone findByIdFetchingApps(Long id) throws Exception{
        TypedQuery<Smartphone> query = entityManager.createQuery(
                "select s from Smartphone s left join fetch s.apps where s.id = :idSmartphone",
                Smartphone.class);
        query.setParameter("idSmartphone", id);
        return query.getSingleResult();
    }
}
