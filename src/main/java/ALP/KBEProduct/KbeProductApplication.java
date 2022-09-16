package ALP.KBEProduct;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KbeProductApplication {

	static List<Product> products = new LinkedList<Product>();

	public static void main(String[] args) throws Exception {
		SpringApplication.run(KbeProductApplication.class, args);
		// createSampleProducts();
	}

	private static void createSampleProducts() {

		Component Hals1 = new Component("Hals", "Ahornhals", "Ahorn", "Deutschland", "hellbraun", "Hausmarke", "D", "neu", "100.00", "bestellbar", "stark");
		Component Steg1 = new Component("Steg", "Rollensteg", "Glockenmessing", "Deutschland", "Chrom", "ABM", "V", "gebraucht", "90.20", "sofort lieferbar", "gering");
		Component Korpus1 = new Component("Korpus", "Erlenkorpus", "Erle", "Deutschland", "schwarz", "MLFactory", "Stratocaster", "gebraucht", "120.00", "sofort lieferbar", "stark");
		Component Griffbrett1 = new Component("Griffbrett", "Palisandergriffbrett", "Palisander", "Indien", "braun", "RallFactory", "LP", "neu", "47.70", "sofort lieferbar", "stark");
		Component Tonabnehmer1 = new Component("Tonabnehmer", "Strat Pick up Set", "Alnico V Magnet", "USA", "weiss", "Fender", "Single Coil", "neu", "21.50", "sofort lieferbar", "stark");
		Component Schallloch1 = new Component("Schallloch", "FLoch", "NA", "NA", "NA", "Hausmarke", "f", "neu", "20.00", "sofort lieferbar", "gering");
		Component Kopf1 = new Component("Kopf", "Mahagonikopfplatte", "Mahagoni", "Mexiko", "dunkelbraun", "MLFactory", "Rohling", "neu", "7.50", "sofort lieferbar", "gering");
		Component Wirbel1 = new Component("Wirbel", "Pegs Schluessel Wirbel", "Kunststoff", "China", "creme", "Yibui", "Bonbon", "neu", "12.90", "sofort lieferbar", "keine");
		Component Sattel1 = new Component("Sattel", "Knochensattel", "Knochen", "USA", "creme", "RallFactory", "flach", "neu", "30.00", "bestellbar", "gering");
		Component Decke1 = new Component("Decke", "Ahornfunier", "Ahorn", "Deutschland", "hellbraun", "ABM", "Funier", "neu", "90.90", "bestellbar", "gering");
		
		Component Hals2 = new Component("Hals", "Erlenhals", "Erle", "Deutschland", "hellbraun", "Hausmarke", "D", "neu", "80.00", "bestellbar", "stark");
		Component Korpus2 = new Component("Korpus", "Birkenkorpus", "Birke", "Deutschland", "schwarz", "MLFactory", "Stratocaster", "gebraucht", "150.00", "sofort lieferbar", "stark");
		Component Kopf2 = new Component("Kopf", "Eichenkopfplatte", "Eiche", "Deutschland", "dunkelbraun", "MLFactory", "Rohling", "neu", "2.50", "sofort lieferbar", "gering");
		Component Decke2 = new Component("Decke", "Fichtenfunier", "Fichte", "Deutschland", "hellbraun", "ABM", "Funier", "neu", "50.00", "bestellbar", "gering");

		products.add(new Product("a", Hals1, Steg1, Korpus1, Griffbrett1, Tonabnehmer1, Schallloch1, Kopf1, Wirbel1, Sattel1, Decke1));
		products.add(new Product("b", Hals2, Steg1, Korpus2, Griffbrett1, Tonabnehmer1, Schallloch1, Kopf2, Wirbel1, Sattel1, Decke2));
		products.add(new Product("c", Hals1, Steg1, Korpus2, Griffbrett1, Tonabnehmer1, Schallloch1, Kopf1, Wirbel1, Sattel1, Decke2));
		products.add(new Product("k", Hals2, Steg1, Korpus1, Griffbrett1, Tonabnehmer1, Schallloch1, Kopf2, Wirbel1, Sattel1, Decke1));
		products.add(new Product("q", Hals1, Steg1, Korpus2, Griffbrett1, Tonabnehmer1, Schallloch1, Kopf2, Wirbel1, Sattel1, Decke1));

		for (Product product : products) {
			System.out.println(product.getAdditionalInfo());
		}
	}
}
