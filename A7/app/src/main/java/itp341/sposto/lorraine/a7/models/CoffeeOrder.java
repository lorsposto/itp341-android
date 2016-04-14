package itp341.sposto.lorraine.a7.models;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by LorraineSposto on 3/18/16.
 */
public class CoffeeOrder implements Serializable {

    public static final String SIZE_SMALL = "Small";
    public static final String SIZE_MEDIUM = "Medium";
    public static final String SIZE_LARGE = "Large";

    private static final String[] brewArray = {
            "Kona",
            "Turkish"
    };
    public static final int BREW_KONA = 0;
    public static final int BREW_TURKISH = 1;

    private String name;
    private String size;
    private String brew;
    private boolean hasSugar;
    private boolean hasMilk;
    private String specialInstructions;

    public CoffeeOrder(String name, String size, String brew, boolean hasSugar, boolean hasMilk, String specialInstructions) {
        this.name = name;
        this.size = size;
        this.brew = brew;
        this.hasSugar = hasSugar;
        this.hasMilk = hasMilk;
        this.specialInstructions = specialInstructions;
    }

    public static String getBrew(int i) {
        if (i < brewArray.length && i > -1) {
            return brewArray[i];
        }
        return null;
    }

    public static int getBrewIndex(String brew) {
        return Arrays.asList(brewArray).indexOf(brew);
    }

    public static String[] getBrews() {
        return brewArray;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getBrew() {
        return brew;
    }

    public boolean isHasSugar() {
        return hasSugar;
    }

    public boolean isHasMilk() {
        return hasMilk;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBrew(String brew) {
        this.brew = brew;
    }

    public void setHasSugar(boolean hasSugar) {
        this.hasSugar = hasSugar;
    }

    public void setHasMilk(boolean hasMilk) {
        this.hasMilk = hasMilk;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", brew='" + brew + '\'' +
                ", hasSugar=" + hasSugar +
                ", hasMilk=" + hasMilk +
                '}';
    }


}
