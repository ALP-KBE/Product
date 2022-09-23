package ALP.KBEProduct.Model;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Representation of a product of a guitar
 */
public class Product implements Serializable {

    private String name;
    private String additionalInfo;
    private List<Component> components = new LinkedList<>();

    public Product(String name,
            String additionalInfo, Component... components) {
        this.name = name;
        this.additionalInfo = additionalInfo;
        for (Component comp : components) {
            this.components.add(comp);
        }
    }

    public Product(String name, Component... components) {
        this.name = name;
        for (Component comp : components) {
            this.components.add(comp);
        }
        this.setAdditionalInfo(name);
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

    public List<Component> getComponents() {
        return this.components;
    }

    public void addComponent(Component component){
        this.components.add(component);
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


    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    @Override
    public String toString() {
        String s = this.getName() + ": ";
        for (Component component : components) {
            s = s + "<-| ";
            s = s + component.toString();
            s = s + " |->";
        }
        return s;
    }
}