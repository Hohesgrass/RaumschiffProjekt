import java.util.ArrayList;
import java.util.Random;

public class Raumschiff {
    private String name;
    private int PosX;
    private int PosY;
    private Kapitaen captain;
    private ArrayList<Ladung> cargoList = new ArrayList<>();
    private int capacity;
    private int HealthPoints;
    private int energySupply;
    private int energyShield;
    private int weaponStrength;
    private int maneuverability;


    public Raumschiff(String name, int PosX, int PosY, Kapitaen captain, int capacity) {
        this.name = name;
        this.PosX = PosX;
        this.PosY = PosY;
        this.captain = captain;
        this.HealthPoints = 100;
        this.capacity = capacity;
    }
    public void fly(char direction) {
        switch (direction) {
            case 'w' -> this.setPosY(this.getPosY() + 1);
            case 'a' -> this.setPosX(this.getPosX() - 1);
            case 's' -> this.setPosY(this.getPosY() - 1);
            case 'd' -> this.setPosX(this.getPosX() + 1);
        }
    }
    public void attack(Raumschiff gegner) {
        int diceResult = throwDice();
        int damage = switch (diceResult) {
            case 20 -> 50;
            case 1 -> 0;
            default -> diceResult + this.weaponStrength + this.energySupply;
        };
        gegner.defend(damage);

    }
    public void defend(int damage) {
        int diceResult = throwDice();
        int defendValue = switch (diceResult) {
          case 20 -> 50;  
          case 1 -> 0;
          default -> diceResult + this.energyShield + this.captain.getSkill();
        };
        int finalDamage = defendValue - damage;
        this.energyShield -= 2;
        
        if (finalDamage <= 0) {
            finalDamage = 0;
        }
        if (finalDamage >= this.HealthPoints) {
            finalDamage = 10000;
        }
        this.HealthPoints -= finalDamage;
    }
    public void rechargeShield() {
        this.energyShield =+ 2;
    }
    public boolean tryFleeFromEnemy(Raumschiff gegner) {
        return (this.throwDice() + this.getManeuverability()) > (gegner.throwDice() + gegner.getManeuverability());
    }
    public boolean validatePosition(int x, int y) {
        return x == this.getPosX() && y == this.getPosY();
    }
    public boolean deliverCargo(Ladung ladung) {
        this.cargoList.remove(ladung);
        return true;
    }
    public boolean receiveCargo(Ladung ladung) {
        if (calculateCargoWeight() + ladung.getWeight() * ladung.getEinheiten() > this.capacity) {
            return false;
        } else {
            this.cargoList.add(ladung);
            return true;
        }
    }
    public ArrayList<Ladung> getCargoList() {
        return cargoList;
    }
    public int calculateCargoWeight(){
        int currentWeight = 0;
        for (Ladung ladung : cargoList){
            currentWeight += ladung.getWeight() * ladung.getEinheiten();
        }   
        return currentWeight;
    }
    public int getManeuverability(){
        return this.maneuverability;
    }
    public String getName() {
        return name;
    }
    public int getPosX() {
        return PosX;
    }
    public int getPosY() {
        return PosY;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosX(int posX) {
        PosX = posX;
    }
    public void setPosY(int posY) {
        PosY = posY;
    }
    public Kapitaen getCaptain() {
        return captain;
    }
    private int throwDice(){
        Random rnd = new Random();
        return rnd.nextInt(20) + 1;
    }
    public int getHealthPoints(){
        return this.HealthPoints;
    }
    public void setHealthPoints(int value){
        this.HealthPoints = value;
    }
    public int getCapacity(){
        return this.capacity;
    }

}

//TODO
    /*
     * Erweiterte Kampffunktionen hinzufügen
     * Frontalangriff || gegner.Unversehrtheit = gegner.Unversehrtheit - (Waffenstärke x d20 / gegner.Schildstärke)
     * Waffensystem angreifen || gegner.Waffenstärke = gegner.Waffenstärke - (Waffenstärke x d20 / gegner.unversehrtheit)
     * Schilde Schwächen || 
     */
