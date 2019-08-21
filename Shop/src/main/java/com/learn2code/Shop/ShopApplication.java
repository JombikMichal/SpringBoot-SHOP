package com.learn2code.Shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@anotacia  - povieme springu co ma robit
//@SpringBootApplication - anotovaná trieda predstavuje vstupný bod aplikácie založenej na Spring Boote. Okrem toho ešte nastaví automatické vyhľadávanie
// REST endpointov (kontrolérov) a ich registráciu v Springu
//anotacia je alias resp v sebe skryva @Configuration, @EnableAutoConfiguration a @ComponentScan pouzivane v spring frameworku
/*
* VELKA TEORIA:
* @Configuration dáva Springu informáciu o tom, že daná trieda môže
obsahovať konfiguračné nastavenia, ako napríklad správu bean. Tieto konfigurácie je možné evidovať v XML súbore, ale priamo vývojári Springu Boot odporúčajú používať anotáciu @Configuration . @EnableAutoConfiguration
slúži k nastaveniu Springu na základe závislostí, ktoré aplikácia obsahuje.
V prípade tejto aplikácie sleduje závislosti, ktoré boli pridané cez Maven.
@ComponentScan slúži k výhľadávaniu komponentov.
* */
// otazka: moze ich byt viac? kde vsade to mozem pouzit?
@SpringBootApplication
public class ShopApplication {



	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
