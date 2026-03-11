package it.prova.manytomanysmartphoneappmaven.dao.smartphone;

import it.prova.manytomanysmartphoneappmaven.dao.IBaseDAO;
import it.prova.manytomanysmartphoneappmaven.model.App;
import it.prova.manytomanysmartphoneappmaven.model.Smartphone;

import javax.persistence.EntityManager;
import java.util.List;

public interface SmartphoneDAO extends IBaseDAO<Smartphone> {
    public List<Smartphone> listAll() throws Exception;

    public Smartphone findById(Long smartphoneId) throws Exception;

    public void update(Smartphone smartphone) throws Exception;

    public void insert(Smartphone smartphone) throws Exception;

    public void delete(Smartphone smartphone) throws Exception;

    public void setEntityManager(EntityManager entityManager);

    public void deleteAndUnlinkApp(Long smartphoneId) throws Exception;


    public Smartphone findByIdFetchingApps(Long id) throws Exception;
}
