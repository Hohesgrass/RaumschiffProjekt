public class Kapitaen {
    private String name;
    private int charisma;
    private int skill;
    private double money;

    public Kapitaen(String name, int charisma, int skill, double money) {
        this.name = name;
        this.charisma = charisma;
        this.skill = skill;
        this.money = money;
    }
    public void diplomaticNegotiationsWith(Kapitaen otherKapitaen) {
        if (this.charisma > otherKapitaen.charisma) {
            System.out.println("Erfolgreich");
        } else {
            System.out.println("Unerfolgreich");
        }
    }
    public double getMoney(){
        return this.money;
    }
    public void setMoney(double money){
        this.money = money;
    }
    public void buyCargo(Ladung ladung){
        this.money -= ladung.getPrice();
    }
    public void sellCargo(Ladung ladung){
        this.money += ladung.getPrice();
    }
    public String getName() {
        return name;
    }
    public int getCharisma() {
        return charisma;
    }
    public int getSkill() {
        return skill;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    public void setSkill(int skill) {
        this.skill = skill;
    }
}
