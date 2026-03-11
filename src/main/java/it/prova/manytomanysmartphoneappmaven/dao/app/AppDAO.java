package it.prova.manytomanysmartphoneappmaven.dao.app;

import it.prova.manytomanysmartphoneappmaven.dao.IBaseDAO;
import it.prova.manytomanysmartphoneappmaven.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public interface AppDAO extends IBaseDAO<App> {
    public List<App> listAll() throws Exception;

    public App findById(Long appId) throws Exception;

    public void update(App app) throws Exception;

    public void insert(App app) throws Exception;

    public void delete(App app) throws Exception;

    public void setEntityManager(EntityManager entityManager);

    public void deleteAndUnlinkSmartphones(Long appId) throws Exception;
}
