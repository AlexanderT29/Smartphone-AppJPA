package it.prova.manytomanysmartphoneappmaven.dao.app;

import it.prova.manytomanysmartphoneappmaven.dao.EntityManagerUtily;
import it.prova.manytomanysmartphoneappmaven.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Override
    public List<App> listAll() throws Exception {
        return entityManager.createQuery("from App", App.class).getResultList();
    }

    @Override
    public App findById(Long appId) throws Exception {
        return  entityManager.find(App.class, appId);
    }

    @Override
    public void insert(App app) throws Exception {
        if(app == null){
            throw new Exception("Input non valido");
        }
        entityManager.persist(app);
    }

    @Override
    public void update(App app) throws Exception {
        if(app == null){
            throw new Exception("Input non valido");
        }
        app = entityManager.merge(app);
    }

    @Override
    public void delete(App app) throws Exception {
        if(app == null){
            throw new Exception("Input non valido");
        }
        entityManager.remove(entityManager.merge(app));
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteAndUnlinkSmartphones(Long appId) throws Exception {
        entityManager.createNativeQuery("DELETE FROM smartphone_app WHERE app_id = ?1").setParameter(1, appId).executeUpdate();
        entityManager.createNativeQuery("DELETE FROM App a WHERE a.id = ?1").setParameter(1, appId).executeUpdate();
    }
}
