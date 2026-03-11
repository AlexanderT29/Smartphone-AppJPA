package it.prova.manytomanysmartphoneappmaven.test;

import it.prova.manytomanysmartphoneappmaven.dao.EntityManagerUtily;
import it.prova.manytomanysmartphoneappmaven.model.App;
import it.prova.manytomanysmartphoneappmaven.model.Smartphone;
import it.prova.manytomanysmartphoneappmaven.service.AppService;
import it.prova.manytomanysmartphoneappmaven.service.MyServiceFactory;
import it.prova.manytomanysmartphoneappmaven.service.SmartphoneService;

import java.time.LocalDate;
import java.util.List;

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

            testAggiornaVersioneOSSmartphone(smartphoneService);

            testInserisciNuovaApp(appServiceInstance);

            testAggiornaAppConData(appServiceInstance);

            testInstallazioneAppSuSmartphone(appServiceInstance, smartphoneService);


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

    private static void testAggiornaVersioneOSSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception{
        System.out.println("..........testAggiornaVersioneOSSmartphone inizio..........");

        List<Smartphone> listaSmartphone = smartphoneServiceInstance.listAll();
        Smartphone smartphoneDaModificare = listaSmartphone.get(0);
        smartphoneDaModificare.setVersioneos("Android2024");
        smartphoneServiceInstance.update(smartphoneDaModificare);

        System.out.println("..........testAggiornaVersioneOSSmartphone fine PASSED..........");

    }

    private static void testInserisciNuovaApp(AppService appServiceInstance) throws Exception{
        System.out.println("..........testInserisciNuovaApp inizio..........");

        App app1 = new App("Instagrammo", LocalDate.now(), LocalDate.now(), "Versione 1.0.1");
        appServiceInstance.insert(app1);
        if(appServiceInstance.listAll().size() != 1) {
            throw new Exception("testInserisciNuovaApp FAILED");
        }

        System.out.println("..........testInserisciNuovaApp fine PASSED..........");
    }

    private  static void testAggiornaAppConData(AppService appServiceInstance) throws Exception{
        System.out.println("..........testAggiornaAppConData inizio..........");

        List<App> listaApp = appServiceInstance.listAll();
        App appDaModificare = listaApp.get(0);
        appDaModificare.setVersione("1.0.2");
        appDaModificare.setDataUltimoAggiornamento(LocalDate.of(2026, 3, 12));
        appServiceInstance.update(appDaModificare);

        System.out.println("..........testAggiornaAppConData fine PASSED..........");
    }

    private static void testInstallazioneAppSuSmartphone(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception{
        System.out.println("..........testInstallazioneAppSuSmartphone inizio..........");

        List<App> listaApp = appServiceInstance.listAll();
        List<Smartphone> listaSmart = smartphoneServiceInstance.listAll();
        App appDaInstallare = listaApp.get(0);
        Smartphone smartphoneDoveInstallare = listaSmart.get(0);
        smartphoneServiceInstance.aggiungiApp(smartphoneDoveInstallare, appDaInstallare);

        System.out.println("..........testInstallazioneAppSuSmartphone fine PASSED..........");
    }

}
