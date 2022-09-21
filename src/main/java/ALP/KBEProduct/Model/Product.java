package ALP.KBEProduct.Model;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Representation of a product of a guitar
 */
public class Product implements Serializable {

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
    private String additionalInfo;
    private HashMap<String, Component> components = new HashMap<>();

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
            Component decke,
            String additionalInfo) {
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
        this.additionalInfo = additionalInfo;
        createMap();
    }

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
            Component decke) {
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
        this.setAdditionalInfo(name);
        createMap();
    }

    public Product(String name) {
        this.name = name;
        this.setAdditionalInfo(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component getHals() {
        return this.hals;
    }

    public void setHals(Component hals) {
        this.hals = hals;
        this.components.put("hals", hals);
    }

    public Component getSteg() {
        return this.steg;
    }

    public void setSteg(Component steg) {
        this.steg = steg;
        this.components.put("steg", steg);
    }

    public Component getKorpus() {
        return this.korpus;
    }

    public void setKorpus(Component korpus) {
        this.korpus = korpus;
        this.components.put("korpus", korpus);
    }

    public Component getGriffbrett() {
        return this.griffbrett;
    }

    public void setGriffbrett(Component griffbrett) {
        this.griffbrett = griffbrett;
        this.components.put("griffbrett", griffbrett);
    }

    public Component getTonabnehmer() {
        return this.tonabnehmer;
    }

    public void setTonabnehmer(Component tonabnehmer) {
        this.tonabnehmer = tonabnehmer;
        this.components.put("tonabnehmer", tonabnehmer);
    }

    public Component getSchallloch() {
        return this.schallloch;
    }

    public void setSchallloch(Component schallloch) {
        this.schallloch = schallloch;
        this.components.put("schallloch", schallloch);
    }

    public Component getKopf() {
        return this.kopf;
    }

    public void setKopf(Component kopf) {
        this.kopf = kopf;
        this.components.put("kopf", kopf);
    }

    public Component getWirbel() {
        return this.wirbel;
    }

    public void setWirbel(Component wirbel) {
        this.wirbel = wirbel;
        this.components.put("wirbel", wirbel);
    }

    public Component getSattel() {
        return this.sattel;
    }

    public void setSattel(Component sattel) {
        this.sattel = sattel;
        this.components.put("sattel", sattel);
    }

    public Component getDecke() {
        return this.decke;
    }

    public void setDecke(Component decke) {
        this.decke = decke;
        this.components.put("decke", decke);
    }

    public HashMap<String, Component> getComponents() {
        return this.components;
    }

    private void setAdditionalInfo(String name) {
        String pre = "The name " + name + " can be interpreted as a number which would be ";
        String post = ", which is ";
        String fact = "";
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < name.length(); i++) {
            int num = Character.getNumericValue(name.charAt(i)) - 10;
            if (num >= 0) {
                list.add(num);
            }
        }
        String number = "";
        for (Integer integer : list) {
            number = number + integer;
        }

        try {
            URL url = new URL("http://numbersapi.com/" + number + "/?fragment");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int response = conn.getResponseCode();

            if (response != 200) {
                throw new RuntimeException("HTTPRespone was " + response);
            } else {
                StringBuilder sb = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
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

    public void setComponent(Component component){
        switch (component.getKomponententyp()) {
            case "Schallloch":
                this.setSchallloch(component);
                break;
            case "Hals":
                this.setHals(component);
                break;
            case "Steg":
                this.setSteg(component);
                break;
            case "Korpus":
                this.setKorpus(component);
                break;
            case "Griffbrett":
                this.setGriffbrett(component);
                break;
            case "Tonabnehmer":
                this.setTonabnehmer(component);
                break;
            case "Kopf":
                this.setKopf(component);
                break;
            case "Wirbel":
                this.setWirbel(component);
                break;
            case "Sattel":
                this.setSattel(component);
                break;
            case "Decke":
                this.setDecke(component);
                break;
        }
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    private void createMap() {
        if (components == null) {
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

    public String Stringify() {
        return this.getName() + "; " +
                this.getHals().Stringify() + "; " +
                this.getSteg().Stringify() + "; " +
                this.getKorpus().Stringify() + "; " +
                this.getGriffbrett().Stringify() + "; " +
                this.getTonabnehmer().Stringify() + "; " +
                this.getSchallloch().Stringify() + "; " +
                this.getKopf().Stringify() + "; " +
                this.getWirbel().Stringify() + "; " +
                this.getSattel().Stringify() + "; " +
                this.getDecke().Stringify() + "; " +
                this.getAdditionalInfo();
    }

    public static Product Parse(String product) {
        String[] parts = product.split(", ");
        Product prod = new Product(parts[0]);
        if (parts.length == 11 || parts.length == 12) {
            prod.setHals(Component.Parse(parts[1]));
            prod.setSteg(Component.Parse(parts[2]));
            prod.setKorpus(Component.Parse(parts[3]));
            prod.setGriffbrett(Component.Parse(parts[4]));
            prod.setTonabnehmer(Component.Parse(parts[5]));
            prod.setSchallloch(Component.Parse(parts[6]));
            prod.setKopf(Component.Parse(parts[7]));
            prod.setWirbel(Component.Parse(parts[8]));
            prod.setSattel(Component.Parse(parts[9]));
            prod.setDecke(Component.Parse(parts[10]));
        }
        return prod;
    }

    @Override
    public String toString() {
        String s = this.getName() + ": ";
        for (Component component : components.values()) {
            s = s + "<-| ";
            s = s + component.toString();
            s = s + " |->";
        }
        return s;
    }
}