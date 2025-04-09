import java.util.ArrayList;
import java.util.Scanner;

public class Sonnensystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Kapitaen alexiaNova = new Kapitaen("Alexia Nova", 7, 7);
        Kapitaen admiralZenithNightfall = new Kapitaen("Admiral Zenith Nightfall", 5, 10);

        ArrayList<Raumschiff> ships = new ArrayList<>();
        ships.add(new Raumschiff("Eos Nova", 0, 0, alexiaNova));
        ships.add(new Raumschiff("Aurora Quest", 0, 2, admiralZenithNightfall));

        Raumschiff playerShip = ships.getFirst();
        alexiaNova.setName("Alexia Starlight Nova");
        System.out.println("Sie fliegen das Raumschiff " + ships.getFirst().getName() + ".");
        System.out.println("Gesteuert von " + ships.getFirst().getCaptain().getName() + ".");

        //Planets
        ArrayList<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Auroria", true, 6, 1));
        planets.add(new Planet("Solara", false, 1, 0));
        planets.add(new Planet("Ktaris", true, 2, 0));

        boolean gameOver = false;

        while (!gameOver) {

            playerShip.fly(scan.next().charAt(0));
            //check for ship encounter
            for (Raumschiff r : ships) {
                if (playerShip != r && playerShip.validatePosition(r.getPosX(), r.getPosY())) {
                    System.out.println("Hier ist das Raumschiff " + r.getName());
                    break;
                }
            }
            //check planet encounter
            for (Planet p : planets) {
                if (playerShip.validatePosition(p.getPosX(), p.getPosY())){
                    System.out.println("Hier ist der Planet " + p.getName() + ", Atmosphäre: " + p.getAtmosphere());
                    break;
                }
            }

        }
    }
}
