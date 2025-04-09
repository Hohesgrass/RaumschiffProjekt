public class Kapitaen {
    private String name;
    private int charisma;
    private int skill;

    public Kapitaen(String name, int charisma, int skill) {
        this.name = name;
        this.charisma = charisma;
        this.skill = skill;
    }
    public void diplomaticNegotiationsWith(Kapitaen otherKapitaen) {
        if (this.charisma > otherKapitaen.charisma) {
            System.out.println("Erfolgreich");
        } else {
            System.out.println("Unerfolgreich");
        }
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
