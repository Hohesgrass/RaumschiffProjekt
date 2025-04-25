import java.util.ArrayList;

public class Handelsstation {
    private String name;
    private ArrayList<Ladung> cargoList = new ArrayList<>();

    public Handelsstation(String name){
        this.name = name;
    }
    public void addCargo(Ladung ladung){
        this.cargoList.add(ladung);
    }
    public void removeCargo(Ladung ladung) {
        if (ladung.getEinheiten() > 1) {
            ladung.setEinheiten(ladung.getEinheiten() - 1);
        } else {
            this.cargoList.remove(ladung);
        }
    }
    public ArrayList<Ladung> getCargoList(){
        return this.cargoList;
    }
    public String getName(){
        return this.name;
    }
}
