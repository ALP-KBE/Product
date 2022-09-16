package ALP.KBEProduct;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Representation of a product of a guitar
 */
public class Product {
    
    private String name;
    private Component hals;
    private Component steg;
    private Component korpus;
    private Component griffbrett;
    private Component tonabnehmer;
    private Component schallloch;
    private Component kopf;
    private Component wirbel;
    private Component sattel;
    private Component decke;
    private Map<String, Component> components;
    private String additionalInfo;

    public Product(String name,
                    Component hals,
                    Component steg,
                    Component korpus,
                    Component griffbrett,
                    Component tonabnehmer,
                    Component schallloch,
                    Component kopf,
                    Component wirbel,
                    Component sattel,
                    Component decke){
                        this.name = name;
                        this.hals = hals;
                        this.steg = steg;
                        this.korpus = korpus;
                        this.griffbrett = griffbrett;
                        this.tonabnehmer = tonabnehmer;
                        this.schallloch = schallloch;
                        this.kopf = kopf;
                        this.wirbel = wirbel;
                        this.sattel = sattel;
                        this.decke = decke;
                        setAdditionalInfo(name);
                        createMap();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        setAdditionalInfo(name);
    }

    public Component getHals() {
        return this.hals;
    }

    public void setHals(Component hals) {
        this.hals = hals;
    }

    public Component getSteg() {
        return this.steg;
    }

    public void setSteg(Component steg) {
        this.steg = steg;
    }

    public Component getKorpus() {
        return this.korpus;
    }

    public void setKorpus(Component korpus) {
        this.korpus = korpus;
    }

    public Component getGriffbrett() {
        return this.griffbrett;
    }

    public void setGriffbrett(Component griffbrett) {
        this.griffbrett = griffbrett;
    }

    public Component getTonabnehmer() {
        return this.tonabnehmer;
    }

    public void setTonabnehmer(Component tonabnehmer) {
        this.tonabnehmer = tonabnehmer;
    }

    public Component getSchallloch() {
        return this.schallloch;
    }

    public void setSchallloch(Component schallloch) {
        this.schallloch = schallloch;
    }

    public Component getKopf() {
        return this.kopf;
    }

    public void setKopf(Component kopf) {
        this.kopf = kopf;
    }

    public Component getWirbel() {
        return this.wirbel;
    }

    public void setWirbel(Component wirbel) {
        this.wirbel = wirbel;
    }

    public Component getSattel() {
        return this.sattel;
    }

    public void setSattel(Component sattel) {
        this.sattel = sattel;
    }

    public Component getDecke() {
        return this.decke;
    }

    public void setDecke(Component decke) {
        this.decke = decke;
    }

    public Map<String, Component> getComponents() {
        return this.components;
    }

    private void createMap(){
        if(components == null){
            components = new HashMap<>();
        }
        components.put("hals", hals);
        components.put("steg", steg);
        components.put("korpus", korpus);
        components.put("griffbrett", griffbrett);
        components.put("tonabnehmer", tonabnehmer);
        components.put("schalloch", schallloch);
        components.put("kopf", kopf);
        components.put("wirbel", wirbel);
        components.put("sattel", sattel);
        components.put("decke", decke);
    }

    private void setAdditionalInfo(String name){
        String pre = "The name " + name + " can be interpreted as a number which would be ";
        String post = ", which is ";
        String fact = "";
		List<Integer> l = new LinkedList<>();
		for (int i = 0; i < name.length(); i++) {
			int num = Character.getNumericValue(name.charAt(i)) - 10;
			if(num >= 0) {
				l.add(num);
			}
		}
		String number = "";
		for (Integer integer : l) {
			number = number + integer;
		}

        try {
            URL url = new URL("http://numbersapi.com/" + number + "/?fragment");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int response = conn.getResponseCode();
            
            if(response != 200) {
                throw new RuntimeException("HTTPRespone was " + response);
            }
            else {
                StringBuilder sb = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
				fact = sb.toString();
                sc.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.additionalInfo = pre + number + post + fact;
    }

    public String getAdditionalInfo(){
        return this.additionalInfo;
    }
    
    @Override
    public String toString() {
        String s = "";
        for(Component component : components.values()){
            s = s + "<-| " + component.toString() + " |->";
        }
        s = s + "<-| " + this.additionalInfo + " |->";
        return s;
    }
}