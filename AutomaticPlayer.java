import java.util.Random;

public class AutomaticPlayer extends Player {
    private Random randomGenerator = new Random();


    @Override
    protected int[] getNumbersOfDieces(){
        int length = randomGenerator.nextInt(7);
        int[] numbers = new int[ length ];
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                numbers[i] = (randomGenerator.nextInt(6)) + 1;
            }
        } else {
            System.out.println("Pomijam rzut.");
        }
        return numbers;
    }

    @Override
    protected void throw3times(Turn turn){

        turn.throwDices(ALL_DIECES);
        turn.printResult();

        for (int i=0; i<2; i++){
            {
                turn.throwDices(this.getNumbersOfDieces());
                turn.printResult();
            }
        }
    }

    @Override
    void play(){
        if (this.hasMoves()){
            Turn turn = new Turn();
            this.throw3times(turn);
            this.printTable();
            System.out.print("Możliwe do wykorzystania linie to: " );
            for (int line: this.availableMoves()){
                System.out.print(line + " ");
            }
            int selection = 0;
            while (selectioNotInAvailableMoves(selection)) {
                selection = randomGenerator.nextInt(6)+1;
            }
            System.out.println("Wybieram " + selection);
            this.storeScores(selection,turn);
            this.printTable();
        } else {
            System.out.println(this.getName() + " wykorzystałeś wszystkie linie tabeli! Twój końcowy wynik to: " + this.getScore());
        }
    }
}
