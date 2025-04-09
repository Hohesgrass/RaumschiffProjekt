import java.lang.reflect.Array;
import java.util.ArrayList;

public class Planet {
    private String name;
    private boolean atmosphere;
    private int posX;
    private int posY;
    private ArrayList<Ladung> cargoList = new ArrayList<>();
    public Planet(String name, boolean atmosphere, int posX, int posY) {
        this.name = name;
        this.atmosphere = atmosphere;
        this.posX = posX;
        this.posY = posY;
    }
    public void addCargo(Ladung ladung) {
        this.cargoList.add(ladung);
    }
    public void removeCargo(Ladung ladung) {
        this.cargoList.remove(ladung);
    }
    public ArrayList<Ladung> getCargoList() {
        return cargoList;
    }
    public int[] getCoordinates() {
        return new int[]{posX, posY};
    }
    public String getName() {
        return name;
    }
    public boolean getAtmosphere() {
        return atmosphere;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAtmosphere(boolean atmosphere) {
        this.atmosphere = atmosphere;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
