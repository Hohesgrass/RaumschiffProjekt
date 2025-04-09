public class Raumschiff {
    private String name;
    private int PosX;
    private int PosY;

    public Raumschiff(String name, int PosX, int PosY) {
        this.name = name;
        this.PosX = PosX;
        this.PosY = PosY;
    }
    public void fly(char direction) {
        switch (direction) {
            case 'w':
                this.setPosY(this.getPosY() + 1);
                break;
            case 'a':
                this.setPosX(this.getPosX() - 1);
                break;
            case 's':
                this.setPosY(this.getPosY() - 1);
                break;
            case 'd':
                this.setPosX(this.getPosX() + 1);
        }
    }
    public boolean validatePosition(Raumschiff raumschiff) {
        return raumschiff.getPosX() == this.getPosX() && raumschiff.getPosY() == this.getPosY();
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
}
