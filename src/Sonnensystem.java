import java.util.ArrayList;
import java.util.Scanner;

//A4.5 done
public class Sonnensystem {
    static ArrayList<Raumschiff> ships;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Captains
        Kapitaen alexiaNova = new Kapitaen("Alexia Nova", 7, 7, 1000);
        Kapitaen admiralZenithNightfall = new Kapitaen("Admiral Zenith Nightfall", 5, 10, 100);

        //Ships
        ships = new ArrayList<>();
        ships.add(new Raumschiff("Eos Nova", 0, 0, alexiaNova, 30));
        ships.add(new Raumschiff("Aurora Quest", 0, 2, admiralZenithNightfall, 10));

        //Planets
        ArrayList<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Auroria", true, 6, 1));
        planets.add(new Planet("Solara", false, 1, 0));
        planets.add(new Planet("Ktaris", true, 2, 0));
        planets.add(new Planet("Müllhaufen", true, 5, 1));
        planets.add(new Planet("Berlin", false, 5, 0));
        planets.add(new Planet("Halle", true, 5, 4));

        // Astroidfields
        ArrayList<Asteroidenfeld> astroidFields = new ArrayList<>();
        astroidFields.add(new Asteroidenfeld(0, 4, 20));
        astroidFields.add(new Asteroidenfeld(0, -4, 20));
        astroidFields.add(new Asteroidenfeld(1, 4, 5));


        //Cargo
        Ladung stones = new Ladung("Steine", 3, 2, 3.0);
        Ladung iron = new Ladung("Eisen", 1, 2, 5.0);
        Ladung gold = new Ladung("Gold", 2, 2, 4.0);
        Ladung aluminium = new Ladung("Aluminium", 1, 2, 6.0);

        //Add Cargo to planets
        for (Planet p : planets) {
            p.getHandelsstation().addCargo(stones);
            p.getHandelsstation().addCargo(iron);
            p.getHandelsstation().addCargo(gold);
            p.getHandelsstation().addCargo(aluminium);
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
            for(Asteroidenfeld af : astroidFields) {
                if (playerShip.validatePosition(af.getPosX(), af.getPosY())) {
                    System.out.println("Dein Schiff ist in ein Asteroidenfeld geraten.");
                    af.crossField(playerShip);
                    System.out.println("Verbleibende HP: " + playerShip.getHealthPoints());
                }
            }
            if (playerShip.getHealthPoints() <= 0) {
                gameOver = true;
            }

        }
        System.out.println("Das Spiel ist beendet, du bist gestorben");
    }

    private static void printTradingStationMenu(Planet p, Raumschiff playerShip){
        boolean isDone = false;
        while (!isDone){
            ConsoleHelper.header("Hier ist die Handelsstation " + p.getHandelsstation().getName());
            ConsoleHelper.printMenuElement(1, "Ladung Kaufen");
            ConsoleHelper.printMenuElement(2, "Ladung Verkaufen");
            ConsoleHelper.printMenuElement(3, "Ladung Anzeigen");
            ConsoleHelper.printMenuElement(4, "Wegfliegen");
            int choice1 = ConsoleHelper.inputInt("Wählen sie:", 1, 4);
            switch (choice1) {
                case 1 -> {
                    if (p.getHandelsstation().getCargoList().isEmpty()) {
                        System.out.println("Die Handelsstation bietet keine Waren zum kauf an.");
                    } else {
                        buyCargo(playerShip, p);
                    }
                }
                case 2 -> {
                    if (playerShip.getCargoList().isEmpty()) {
                        System.out.println("Du hast keine Waren zum Verkaufen.");
                    } else {
                        sellCargo(playerShip, p);
                    }
                }
                case 3 -> {
                    ConsoleHelper.printMenuItems("Gegenstände in der Handelsstation", p.getHandelsstation().getCargoList().toArray());
                }
                case 4 -> {
                    isDone = true;
                    break;
                }
                default -> {
                }
            }
        }
    }

    private static void printPlanetMenu(Raumschiff playerShip, Planet p){
        boolean isDone = false;
        while (!isDone) { 
            ConsoleHelper.header("Hier ist der Planet " + p.getName() + ", Atmosphäre: " + p.getAtmosphere());
            ConsoleHelper.printMenuElement(1, "Handelsstation anfliegen");
            ConsoleHelper.printMenuElement(2, "Wegfliegen");
            int choice1 = ConsoleHelper.inputInt("Wählen sie:", 1, 2);
            switch (choice1) {
                case 1 -> {
                    printTradingStationMenu(p, playerShip);
                }
                case 2 -> {
                    isDone = true;
                }
                default -> {
                }
            }
            
        }
    }

    private static void sellCargo(Raumschiff playerShip, Planet p) {
        int choice2;
        Ladung selectedCargo;
        
        choice2 = ConsoleHelper.printMenu("Ladung -> Handelsstation", playerShip.getCargoList());
        selectedCargo = playerShip.getCargoList().get(choice2-1);
        
        playerShip.getCaptain().sellCargo(selectedCargo);
        playerShip.deliverCargo(selectedCargo);
        p.getHandelsstation().addCargo(selectedCargo);
        System.out.println("Ware verkauft, verbeleibendes Geld: " + playerShip.getCaptain().getMoney());
    }
    private static void buyCargo(Raumschiff playerShip, Planet p) {
        int choice2;
        Ladung selectedCargo;
        choice2 = ConsoleHelper.printMenu("Ladung -> Raumschiff", p.getHandelsstation().getCargoList());
        selectedCargo = p.getHandelsstation().getCargoList().get(choice2-1);
        if (playerShip.getCaptain().getMoney() < selectedCargo.getPrice()) {
            System.out.println("Du hast zu wenig Geld:" + playerShip.getCaptain().getMoney());
        } else if (!playerShip.receiveCargo(selectedCargo)) {
            System.out.println("Du hast nicht genügend Platz, verbleibende Kapazität: " + (playerShip.getCapacity() - playerShip.calculateCargoWeight()));
        } else{
            playerShip.getCaptain().buyCargo(selectedCargo);
            playerShip.receiveCargo(selectedCargo);
            p.getHandelsstation().removeCargo(selectedCargo);
            System.out.println("Ware gekauft, verbeleibendes Geld: " + playerShip.getCaptain().getMoney());
            System.out.println("Verbleibende Kapazität: " + playerShip.calculateCargoWeight());
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

