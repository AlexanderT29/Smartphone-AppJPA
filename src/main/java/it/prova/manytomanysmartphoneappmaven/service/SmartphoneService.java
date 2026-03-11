package it.prova.manytomanysmartphoneappmaven.service;

import it.prova.manytomanysmartphoneappmaven.dao.app.AppDAO;
import it.prova.manytomanysmartphoneappmaven.dao.smartphone.SmartphoneDAO;
import it.prova.manytomanysmartphoneappmaven.model.App;
import it.prova.manytomanysmartphoneappmaven.model.Smartphone;

import java.util.List;

public interface SmartphoneService {
    public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);

    public List<Smartphone> listAll() throws Exception;

    public Smartphone findById(Long smartphoneId) throws Exception;

    public void update(Smartphone smartphone) throws Exception;

    public void insert(Smartphone smartphone) throws Exception;

    public void delete(Smartphone smartphone) throws Exception;

    public void deleteAndUnlinkApp(Long smartphoneId) throws Exception;

    public void aggiungiApp(Smartphone smartphone, App app) throws Exception;

}
