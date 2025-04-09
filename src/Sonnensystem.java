import java.util.ArrayList;
import java.util.Scanner;

public class Sonnensystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Captains
        Kapitaen alexiaNova = new Kapitaen("Alexia Nova", 7, 7);
        Kapitaen admiralZenithNightfall = new Kapitaen("Admiral Zenith Nightfall", 5, 10);

        //Ships
        ArrayList<Raumschiff> ships = new ArrayList<>();
        ships.add(new Raumschiff("Eos Nova", 0, 0, alexiaNova));
        ships.add(new Raumschiff("Aurora Quest", 0, 2, admiralZenithNightfall));

        //Planets
        ArrayList<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Auroria", true, 6, 1));
        planets.add(new Planet("Solara", false, 1, 0));
        planets.add(new Planet("Ktaris", true, 2, 0));

        //Cargo
        Ladung stones = new Ladung("Steine", 3);
        Ladung iron = new Ladung("Eisen", 1);
        Ladung gold = new Ladung("Gold", 2);
        Ladung aluminium = new Ladung("Aluminium", 1);

        //Add Cargo to planets
        for (Planet p : planets) {
            p.addCargo(stones);
            p.addCargo(iron);
            p.addCargo(gold);
            p.addCargo(aluminium);
        }

        Raumschiff playerShip = ships.getFirst();
        alexiaNova.setName("Alexia Starlight Nova");
        System.out.println("Sie fliegen das Raumschiff " + playerShip.getName() + ".");
        System.out.println("Gesteuert von " + playerShip.getCaptain().getName() + ".");

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
