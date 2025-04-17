import java.io.Console;
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
            playerShip.receiveCargo(iron);
            playerShip.deliverCargo(iron);
            //check for ship encounter
            for (Raumschiff r : ships) {
                if (playerShip != r && playerShip.validatePosition(r.getPosX(), r.getPosY())) {
                    System.out.println("Hier ist das Raumschiff " + r.getName());
                    break;
                }
            }
            for (Planet p : planets) {
                if (playerShip.validatePosition(p.getPosX(), p.getPosY())) {
                    printPlanetMenu(playerShip, p);
                    break;
                }
            }

        }
    }
    private static void printPlanetMenu(Raumschiff playerShip, Planet p){
        boolean isDone = false;
        while (!isDone) { 
            ConsoleHelper.header("Hier ist der Planet " + p.getName() + ", Atmosphäre: " + p.getAtmosphere());
            ConsoleHelper.printMenuElement(1, "Ladung Abholen");
            ConsoleHelper.printMenuElement(2, "Ladung Einladung");
            ConsoleHelper.printMenuElement(3, "Wegfliegen");
            int choice1 = ConsoleHelper.inputInt("Wählen sie:", 1, 3);
            int choice2;
            Ladung selectedCargo;
            switch (choice1) {
                case 1 -> {
                    choice2 = ConsoleHelper.printMenu("Gegenstand von Planet", p.getCargoList());
                    selectedCargo = p.getCargoList().get(choice2-1);
                    playerShip.receiveCargo(selectedCargo);
                    p.removeCargo(selectedCargo);
                }
                case 2 -> {
                    choice2 = ConsoleHelper.printMenu("Gegenstand von Planet", playerShip.getCargoList());
                    selectedCargo = playerShip.getCargoList().get(choice2-1);
                    playerShip.deliverCargo(selectedCargo);
                    p.addCargo(selectedCargo);
                }
                case 3 -> {
                    isDone = true;
                    break;
                }
                default -> {
                }
            }
            
        }
    }
}
