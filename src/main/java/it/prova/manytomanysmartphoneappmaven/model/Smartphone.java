package it.prova.manytomanysmartphoneappmaven.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "smartphone")
public class Smartphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modello")
    private String modello;
    @Column(name = "prezzo")
    private double prezzo;
    @Column(name = "versioneos")
    private String versioneos;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "smartphone_app", joinColumns = @JoinColumn(name = "smartphone_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id"))
    private Set<App> apps = new HashSet<>();

    public Smartphone() {
    }

    public Smartphone(String marca, String modello, double prezzo, String versioneos) {
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
        this.versioneos = versioneos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getVersioneos() {
        return versioneos;
    }

    public void setVersioneos(String versioneos) {
        this.versioneos = versioneos;
    }

    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }

    public void addToApps(App appInstance) {
        this.apps.add(appInstance);
        appInstance.getSmartphones().add(this);
    }

    public void removeFromApps(App appInstance) {
        this.apps.remove(appInstance);
        appInstance.getSmartphones().remove(this);
    }
}
