import java.util.Scanner;

public class Sonnensystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Kapitaen alexiaNova = new Kapitaen("Alexia Nova", 7, 7);

        Kapitaen admiralZenithNightfall = new Kapitaen("Admiral Zenith Nightfall", 5, 10);

        Raumschiff eosNova = new Raumschiff("Eos Nova", 0, 0, alexiaNova);
        Raumschiff auroraQuest = new Raumschiff("Aurora Quest", 0, 2, admiralZenithNightfall);

        alexiaNova.setName("Alexia Starlight Nova");
        System.out.println("Sie fliegen das Raumschiff " + eosNova.getName() + ".");
        System.out.println("Gesteuert von " + eosNova.getCaptain().getName() + ".");


        boolean gameOver = false;

        while (!gameOver) {
            eosNova.fly(scan.next().charAt(0));
            if (eosNova.validatePosition(auroraQuest)) {
                System.out.println("Hier ist das Raumschiff " + auroraQuest.getName());
                alexiaNova.diplomaticNegotiationsWith(admiralZenithNightfall);
                gameOver = true;
            }
        }
    }
}
