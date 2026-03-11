package it.prova.manytomanysmartphoneappmaven.service;

import it.prova.manytomanysmartphoneappmaven.dao.EntityManagerUtily;
import it.prova.manytomanysmartphoneappmaven.dao.smartphone.SmartphoneDAO;
import it.prova.manytomanysmartphoneappmaven.model.App;
import it.prova.manytomanysmartphoneappmaven.model.Smartphone;

import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneServiceImpl implements SmartphoneService{

    private SmartphoneDAO smartphoneDAO;

    @Override
    public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO) {
        this.smartphoneDAO = smartphoneDAO;
    }

    @Override
    public List<Smartphone> listAll() throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{

            smartphoneDAO.setEntityManager(entityManager);

            return smartphoneDAO.listAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public Smartphone findById(Long smartphoneId) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            smartphoneDAO.setEntityManager(entityManager);

            return smartphoneDAO.findById(smartphoneId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void insert(Smartphone smartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            smartphoneDAO.setEntityManager(entityManager);

            smartphoneDAO.insert(smartphone);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }


    @Override
    public void update(Smartphone smartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            smartphoneDAO.setEntityManager(entityManager);

            smartphoneDAO.update(smartphone);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void delete(Smartphone smartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            smartphoneDAO.setEntityManager(entityManager);

            smartphoneDAO.delete(smartphone);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void deleteAndUnlinkApp(Long smartphoneId) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            smartphoneDAO.setEntityManager(entityManager);

            smartphoneDAO.deleteAndUnlinkApp(smartphoneId);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void aggiungiApp(Smartphone smartphone, App app) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            smartphoneDAO.setEntityManager(entityManager);

            Smartphone smartphoneAttached = entityManager.merge(smartphone);

            App appAttached = entityManager.merge(app);

            smartphoneAttached.addToApps(appAttached);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void disinstallaApp(App app, Smartphone smartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Smartphone smartphoneAttached = entityManager.merge(smartphone);
            App appAttached = entityManager.merge(app);

            smartphoneAttached.removeFromApps(appAttached);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    public Smartphone caricaSingoloElementoEInizializzaApps(Long id) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();
        try {
            smartphoneDAO.setEntityManager(entityManager);
            return smartphoneDAO.findByIdFetchingApps(id);
        } catch (Exception e) {
            throw e;
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }
}
