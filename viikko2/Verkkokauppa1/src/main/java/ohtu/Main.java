package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

// nämä napattu pois spring-context.xml:stä, niin lähti pelittämään
//<context:annotation-config />
//<context:component-scan base-package="ohtu.verkkokauppa" />  

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
        
        
        
        //Kirjanpito kirjanpito = new Kirjanpito();
        Kirjanpito kirjanpito = (Kirjanpito) ctx.getBean("kirjanpito");
        //Varasto varasto = new Varasto(kirjanpito);
        Varasto varasto = (Varasto) ctx.getBean("varasto");
      
        //Pankki pankki = new Pankki(kirjanpito);
        Pankki pankki = (Pankki) ctx.getBean("pankki");
        //Viitegeneraattori viitegen = new Viitegeneraattori();
        Viitegeneraattori viitegen = (Viitegeneraattori) ctx.getBean("viitegeneraattori");
        //Kauppa kauppa = new Kauppa (varasto, pankki, viitegen);
        Kauppa kauppa = (Kauppa) ctx.getBean("kauppa");
        
        
        
//        //Kauppa kauppa = new Kauppa();
//        Kauppa kauppa = new Kauppa(Varasto.getInstance(),Pankki.getInstance(),Viitegeneraattori.getInstance());

        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
//        for (String tapahtuma : Kirjanpito.getInstance().getTapahtumat()) {
        for(String tapahtuma: kirjanpito.getTapahtumat()){
            System.out.println(tapahtuma);
        }
    }
}
