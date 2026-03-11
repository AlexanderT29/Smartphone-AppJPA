package it.prova.manytomanysmartphoneappmaven.test;

import it.prova.manytomanysmartphoneappmaven.dao.EntityManagerUtily;
import it.prova.manytomanysmartphoneappmaven.model.Smartphone;
import it.prova.manytomanysmartphoneappmaven.service.AppService;
import it.prova.manytomanysmartphoneappmaven.service.MyServiceFactory;
import it.prova.manytomanysmartphoneappmaven.service.SmartphoneService;

public class TestSmartphoneApp {

    public static void main(String[] args){
        AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();
        SmartphoneService smartphoneService = MyServiceFactory.getSmartphoneServiceInstance();

        try{
            System.out.println("In tabella Smartphone ci sono " + smartphoneService.listAll().size() + " elementi.");
            System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi");
            System.out.println("********************************inizio batteria di test********************************");
            System.out.println("***************************************************************************************");

            testInserisciNuovoSmartphone(smartphoneService);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            EntityManagerUtily.shutdown();
        }

    }

    private static void testInserisciNuovoSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception{
        System.out.println("..........testInserisciNuovoSmartphone inizio..........");

        Smartphone smartphone1 = new Smartphone("Samsung", "Galaxy S24", 650.99, "Android2025");
        smartphoneServiceInstance.insert(smartphone1);
        if(smartphoneServiceInstance.listAll().size() != 1){
            throw new Exception("testInserisciNuovoSmartphone FAILED");
        }
        System.out.println("..........testInserisciNuovoSmartphone fine PASSED..........");

    }

}
