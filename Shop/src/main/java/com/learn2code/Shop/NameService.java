package com.learn2code.Shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

// vytvorenie beanu - po starte aplikacie sa vytvori dependency a pomocou anotacie @Service spring vie tejto triede
// nikde v aplikacii nevytvarame instanciu tejto triedy - spring vytvara tieto beany za nas
//@Service
public class NameService {

    //toto name nam berie z aplication.properties
    //pomocou anotacie @Value a nazvu premenej vieme k tejto premennej pristupit
    @Value("${name}")
    private String name;
    @Value("${age}")
    private int age;

    public NameService() {
    }

    //keby sme to chceli vypisat priamo v konstruktore tak nam vrati hodnotu null lebo sa este nenacitala hodnota @Value("${name}")
    // pomocou anotacie @PostConstruct povieme ze az po nacitani konfiguracie a beanov ju chceme spustit

    @PostConstruct
    private void sayMyName(){
        System.out.println(this.name + " " + this.age);
    }
}
