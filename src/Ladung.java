public class Ladung {
    private String name;
    private int einheiten;
    private int weight;
    private double price;
    public Ladung() {
        this.name = "Leerladung";
        this.einheiten = 0;
        this.weight = 0;
        this.price = 0;
    }
    public Ladung(String name, int einheiten, int weight, double price) {
        this.name = name;
        this.einheiten = einheiten;
        this.weight = weight;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getEinheiten() {
        return einheiten;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEinheiten(int einheiten) {
        this.einheiten = einheiten;
    }
    public double getPrice(){
        return this.price;
    }
    public int getWeight(){
        return this.weight;
    }
    @Override
    public String toString() {
        return "Ladung{" +
                "name='" + name + '\'' +
                ", einheiten=" + einheiten +
                '}';
    }
}
