import java.util.ArrayList;
import java.util.Scanner;

public class Sonnensystem {
    static ArrayList<Raumschiff> ships;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Captains
        Kapitaen alexiaNova = new Kapitaen("Alexia Nova", 7, 7);
        Kapitaen admiralZenithNightfall = new Kapitaen("Admiral Zenith Nightfall", 5, 10);

        //Ships
        ships = new ArrayList<>();
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
                    printShipMenu(playerShip, r);
                    break;
                }
            }
            for (Planet p : planets) {
                if (playerShip.validatePosition(p.getPosX(), p.getPosY())) {
                    printPlanetMenu(playerShip, p);
                    break;
                }
            }
            if (playerShip.getHealthPoints() <= 0) {
                gameOver = true;
            }

        }
        System.out.println("Das Spiel ist beendet, du bist gestorben");
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
    public static void printShipMenu(Raumschiff playerShip, Raumschiff enemyShip){
        boolean isDone = false;
        while (!isDone) { 
            ConsoleHelper.header("Hier ist das Raumschiff " + enemyShip.getName());
            ConsoleHelper.printMenuElement(1, "Angreifen");
            ConsoleHelper.printMenuElement(2, "Wegfliegen");
            int choice1 = ConsoleHelper.inputInt("Wählen sie:", 1, 2);
            boolean isAttackDone = false;
            while(!isAttackDone){
                switch (choice1) {
                    case 1 -> {
                        ConsoleHelper.printMenuElement(1, "Frontalangriff");
                        ConsoleHelper.printMenuElement(2, "Schilde aufladen");
                        ConsoleHelper.printMenuElement(3, "Fliehen");
                        int choice2 = ConsoleHelper.inputInt("Wählen sie:", 1, 3);
                        switch (choice2) {
                            case 1-> {
                                playerShip.attack(enemyShip);
                                if (enemyShip.getHealthPoints() <= 0) {
                                    System.out.println("Die " + enemyShip.getName() + " ist Zerstört");
                                    ships.remove(enemyShip);
                                    isAttackDone = true;
                                    isDone = true;
                                }
                        }
                            case 2-> {
                                playerShip.rechargeShield();
                        }
                            case 3-> {
                                if (playerShip.tryFleeFromEnemy(enemyShip)){
                                    System.out.println("Fliehen erfolgreich");
                                    isAttackDone = true;
                                } else {
                                    System.out.println("Fliehen fehlgeschlagen");
                                }
                        }
                            default -> throw new AssertionError();
                        }
                    }
                    case 2 -> {
                        isDone = true;
                        isAttackDone = true;
                    }
                    default -> throw new AssertionError();
                }
                enemyShip.attack(playerShip);
                System.out.println("Die " + enemyShip.getName() + " hat dich getroffen");
                System.out.println("Deine verbleibenden HP: " + playerShip.getHealthPoints());
                System.out.println("Verbleibende HP der " + enemyShip.getName() + ": " + enemyShip.getHealthPoints());
                if(playerShip.getHealthPoints() <= 0) {
                    isAttackDone = true;
                    isDone = true;
                }
            }
        }
    }
}

