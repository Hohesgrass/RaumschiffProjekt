import java.util.Random;

public class Asteroidenfeld {
    private int PosX;
    private int PosY;
    private int dangerLevel; // 0-30

    public Asteroidenfeld(int PosX, int posY, int dangerLevel){
        this.PosX = PosX;
        this.PosY = posY;
        this.dangerLevel = dangerLevel > 30 ? 30 : dangerLevel;
    }
    public void crossField(Raumschiff playerShip) {
        int damage = throwDice() + playerShip.getManeuverability() + (playerShip.getCaptain().getSkill() / 2);
        if (damage < dangerLevel) {
            playerShip.setHealthPoints(playerShip.getHealthPoints() - damage);
        } else {
            
        }
    }
    public int getPosX() {
        return PosX;
    }
    public int getPosY() {
        return PosY;
    }
    public void setPosX(int posX) {
        this.PosX = posX;
    }
    public void setPosY(int posY) {
        this.PosY = posY;
    }

    private int throwDice() {
        Random rnd = new Random();
        return rnd.nextInt(20) + 1;
    }
}
