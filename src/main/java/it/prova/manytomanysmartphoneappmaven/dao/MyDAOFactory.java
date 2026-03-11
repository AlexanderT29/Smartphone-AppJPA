package it.prova.manytomanysmartphoneappmaven.dao;

import it.prova.manytomanysmartphoneappmaven.dao.app.AppDAO;
import it.prova.manytomanysmartphoneappmaven.dao.app.AppDAOImpl;
import it.prova.manytomanysmartphoneappmaven.dao.smartphone.SmartphoneDAO;
import it.prova.manytomanysmartphoneappmaven.dao.smartphone.SmartphoneDAOImpl;

public class MyDAOFactory {

    private static AppDAO appDAOInstance = null;
    private static SmartphoneDAO smartphoneDAOInstance = null;

    public static AppDAO getAppDAOInstance(){
        if(appDAOInstance == null){
            appDAOInstance = new AppDAOImpl();
        }
        return appDAOInstance;
    }

    public static SmartphoneDAO getSmartphoneDAOInstance(){
        if(smartphoneDAOInstance == null){
            smartphoneDAOInstance = new SmartphoneDAOImpl();
        }
        return smartphoneDAOInstance;
    }
}
