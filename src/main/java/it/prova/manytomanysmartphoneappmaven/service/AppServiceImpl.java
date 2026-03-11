package it.prova.manytomanysmartphoneappmaven.service;

import it.prova.manytomanysmartphoneappmaven.dao.EntityManagerUtily;
import it.prova.manytomanysmartphoneappmaven.dao.app.AppDAO;
import it.prova.manytomanysmartphoneappmaven.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public class AppServiceImpl implements AppService{
    private AppDAO appDAO;

    public void setAppDAO(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @Override
    public List<App> listAll() throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            appDAO.setEntityManager(entityManager);

            return appDAO.listAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public App findById(Long appId) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            appDAO.setEntityManager(entityManager);

            return appDAO.findById(appId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void insert(App app) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            appDAO.setEntityManager(entityManager);

            appDAO.insert(app);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void update(App app) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            appDAO.setEntityManager(entityManager);

            appDAO.update(app);

            entityManager.getTransaction().begin();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void delete(App app) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            appDAO.setEntityManager(entityManager);

            appDAO.delete(app);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }

    @Override
    public void deleteAndUnlinkSmartphones(Long appId) throws Exception {
        EntityManager entityManager = EntityManagerUtily.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            appDAO.setEntityManager(entityManager);

            appDAO.deleteAndUnlinkSmartphones(appId);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.closeEntityManager(entityManager);
        }
    }
}
