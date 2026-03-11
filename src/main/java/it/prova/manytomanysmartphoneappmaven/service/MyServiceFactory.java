package it.prova.manytomanysmartphoneappmaven.service;

import it.prova.manytomanysmartphoneappmaven.dao.MyDAOFactory;

public class MyServiceFactory {

    private static AppService appServiceInstance = null;
    private static SmartphoneService smartphoneServiceInstance = null;

    public static AppService getAppServiceInstance(){
        if(appServiceInstance == null){
            appServiceInstance = new AppServiceImpl();
        }
        appServiceInstance.setAppDAO(MyDAOFactory.getAppDAOInstance());
        return appServiceInstance;
    }

    public static SmartphoneService getSmartphoneServiceInstance(){
        if(smartphoneServiceInstance == null){
            smartphoneServiceInstance = new SmartphoneServiceImpl();
        }
        smartphoneServiceInstance.setSmartphoneDAO(MyDAOFactory.getSmartphoneDAOInstance());
        return smartphoneServiceInstance;
    }
}
