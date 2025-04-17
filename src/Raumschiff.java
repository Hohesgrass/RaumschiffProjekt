import java.util.ArrayList;

public class Raumschiff {
    private String name;
    private int PosX;
    private int PosY;
    private Kapitaen captain;
    private ArrayList<Ladung> cargoList = new ArrayList<>();

    public Raumschiff(String name, int PosX, int PosY, Kapitaen captain) {
        this.name = name;
        this.PosX = PosX;
        this.PosY = PosY;
        this.captain = captain;
    }
    public void fly(char direction) {
        switch (direction) {
            case 'w' -> this.setPosY(this.getPosY() + 1);
            case 'a' -> this.setPosX(this.getPosX() - 1);
            case 's' -> this.setPosY(this.getPosY() - 1);
            case 'd' -> this.setPosX(this.getPosX() + 1);
        }
    }
    public boolean validatePosition(int x, int y) {
        return x == this.getPosX() && y == this.getPosY();
    }
    public void deliverCargo(Ladung ladung) {
        this.cargoList.remove(ladung);
    }
    public void receiveCargo(Ladung ladung) {
        this.cargoList.add(ladung);
    }
    public ArrayList<Ladung> getCargoList() {
        return cargoList;
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
}
