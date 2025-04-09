import java.util.Scanner;

public class Sonnensystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Raumschiff eosNova = new Raumschiff("Eos Nova", 0, 0);
        Raumschiff auroraQuest = new Raumschiff("Aurora Quest", 0, 2);

        System.out.println("Sie fliegen das Raumschiff " + eosNova.getName());

        boolean gameOver = false;

        while (!gameOver) {
            eosNova.fly(scan.next().charAt(0));
            if (eosNova.validatePosition(auroraQuest)) {
                System.out.println("Hier ist das Raumschiff " + auroraQuest.getName());
                gameOver = true;
            }
        }
    }
}
