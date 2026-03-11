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

            testDisinstallaAppDaSmartphone(appServiceInstance, smartphoneService);

            testRimuoviSmartphoneConDueApp(appServiceInstance, smartphoneService);

            System.out.println(
                    "****************************** fine batteria di test ********************************************");
            System.out.println(
                    "*************************************************************************************************");
            System.out.println("In tabella Smartphone ci sono " + smartphoneService.listAll().size() + " elementi.");
            System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi");

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
        System.out.println("..........testInstallazioneAppSuSmartphone INIZIO..........");

        // 1. Creazione e inserimento Smartphone
        Smartphone smartphoneNuovo = new Smartphone("Iphone", "17", 1450.99, "iOS 17");
        smartphoneServiceInstance.insert(smartphoneNuovo);

        // 2. Creazione e inserimento App
        App appNuova = new App("Facemagazine", LocalDate.now(), LocalDate.now(), "Versione 1.0.1");
        appServiceInstance.insert(appNuova);


        smartphoneServiceInstance.aggiungiApp(smartphoneNuovo, appNuova);
        smartphoneNuovo = smartphoneServiceInstance.caricaSingoloElementoEInizializzaApps(smartphoneNuovo.getId());

        System.out.println("App installate nello smartphone: " + smartphoneNuovo.getApps().size());
        for (App appItem : smartphoneNuovo.getApps()) {
            System.out.println(" - Nome App: " + appItem.getNome());
        }

        if (smartphoneNuovo.getApps().isEmpty()) {
            throw new RuntimeException("Test fallito: Lo smartphone non ha app collegate.");
        }

        System.out.println("..........testInstallazioneAppSuSmartphone fine PASSED..........");
    }

    private static void testDisinstallaAppDaSmartphone(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception{
        System.out.println("..........testDisinstallaAppDaSmartphone INIZIO..........");

        Smartphone smartphone = new Smartphone("Samsung", "S24", 900.0, "Android 14");
        smartphoneServiceInstance.insert(smartphone);
        App app = new App("Spotifi", LocalDate.now(), LocalDate.now(), "8.9.0");
        appServiceInstance.insert(app);
        smartphoneServiceInstance.aggiungiApp(smartphone, app);
        smartphone = smartphoneServiceInstance.caricaSingoloElementoEInizializzaApps(smartphone.getId());
        if (smartphone.getApps().size() != 1) {
            throw new RuntimeException("Errore: App non installata correttamente.");
        }
        System.out.println("App installata con successo.");
        smartphoneServiceInstance.disinstallaApp(app, smartphone);
        smartphone = smartphoneServiceInstance.caricaSingoloElementoEInizializzaApps(smartphone.getId());
        if (!smartphone.getApps().isEmpty()) {
            throw new RuntimeException("Test fallito: L'app è ancora presente nello smartphone.");
        }

        System.out.println("App disinstallata con successo. Numero app rimaste: " + smartphone.getApps().size());
        System.out.println("..........testDisinstallaAppDaSmartphone fine PASSED..........");
    }

    private static void testRimuoviSmartphoneConDueApp(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception{
        System.out.println("..........testRimuoviSmartphoneConDueApp INIZIO..........");

        Smartphone smartphone = new Smartphone("Samsung", "S22", 900.0, "Android 12");
        smartphoneServiceInstance.insert(smartphone);
        App app1 = new App("Spotifi", LocalDate.now(), LocalDate.now(), "9.9.0");
        appServiceInstance.insert(app1);
        App app2 = new App("Discordia", LocalDate.now(), LocalDate.now(), "5,7,2");
        appServiceInstance.insert(app2);
        smartphoneServiceInstance.aggiungiApp(smartphone, app1);
        smartphoneServiceInstance.aggiungiApp(smartphone, app2);
        smartphone = smartphoneServiceInstance.caricaSingoloElementoEInizializzaApps(smartphone.getId());
        for(App app: smartphone.getApps()){
            System.out.println(app.getNome());
        }
        smartphoneServiceInstance.deleteAndUnlinkApp(smartphone.getId());



        System.out.println("..........testRimuoviSmartphoneConDueApp fine PASSED..........");


    }



}
