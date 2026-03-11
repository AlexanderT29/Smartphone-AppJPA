package it.prova.manytomanysmartphoneappmaven.service;

import it.prova.manytomanysmartphoneappmaven.dao.app.AppDAO;
import it.prova.manytomanysmartphoneappmaven.model.App;

import javax.persistence.EntityManager;
import java.util.List;

public interface AppService {

    public void setAppDAO(AppDAO appDAO);

    public List<App> listAll() throws Exception;

    public App findById(Long appId) throws Exception;

    public void update(App app) throws Exception;

    public void insert(App app) throws Exception;

    public void delete(App app) throws Exception;

    public void deleteAndUnlinkSmartphones(Long appId) throws Exception;
}
