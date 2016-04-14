package itp341.sposto.lorraine.a9.model;

import java.io.Serializable;

/**
 * Created by LorraineSposto on 4/13/16.
 */
public class Hero implements Serializable {
    private long _id;
    private String name;
    private String power1;
    private String power2;
    private int health;
    private int numWins;
    private int numLosses;
    private int numTies;


    public Hero() {
        super();
    }

    public boolean isAlive() {
        return (health >= 0);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower1() {
        return power1;
    }

    public void setPower1(String power1) {
        this.power1 = power1;
    }

    public String getPower2() {
        return power2;
    }

    public void setPower2(String power2) {
        this.power2 = power2;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public int getNumLosses() {
        return numLosses;
    }

    public void setNumLosses(int numLosses) {
        this.numLosses = numLosses;
    }

    public int getNumTies() {
        return numTies;
    }

    public void setNumTies(int numTies) {
        this.numTies = numTies;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", power1='" + power1 + '\'' +
                ", power2='" + power2 + '\'' +
                ", health=" + health +
                ", numWins=" + numWins +
                ", numLosses=" + numLosses +
                ", numTies=" + numTies +
                '}';
    }
}
