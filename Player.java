import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private int[] resultTable = {0,0,0,0,0,0};
    int[] ALL_DIECES = {1,2,3,4,5,6};
    private String name;
    private Scanner scanner = new Scanner(System.in);

    String getName() {
        return this.name;
    }

    void setName() {
        System.out.print("Podaj imię gracza: ");
        this.setName( scanner.next() );
    }

    void setName(String name) {
        this.name = name ;
    }

    boolean hasMoves(){
        boolean hasMoves = false;
        for (int result: resultTable){
            if (result == 0) {
                hasMoves = true;
                break;
            }
        }
        return hasMoves;
    }

    protected void storeScores(int selection, Turn turn){
        this.resultTable[selection-1] = turn.getResult(selection);
    }

    private int[] appendTo(int[] array, int element) {
        int[] result = Arrays.copyOf(array, array.length + 1);
        result[result.length - 1] = element;
        return result;
    }

    protected int[] availableMoves(){
        int[] availableMoves = new int[0];
        for (int position = 0; position<6; position++){
            if (this.resultTable[position] == 0){
                availableMoves = this.appendTo(availableMoves,position+1);
            }
        }
        return availableMoves;
    }

    int getScore(){
        int score = 0;
        for (int result: this.resultTable){
            score += result;
        }
        return score;
    }

    protected void printTable(){
        System.out.println("\nTABELA WYNIKOW DLA: "+ this.getName());
        System.out.print("jedynki: ");
        if (this.resultTable[0] == 0){
            System.out.println("-");
        } else {
            System.out.println(this.resultTable[0]);
        }

        System.out.print("dwojki : ");
        if (this.resultTable[1] == 0){
            System.out.println("-");
        } else {
            System.out.println(this.resultTable[1]);
        }

        System.out.print("trojki : ");
        if (this.resultTable[2] == 0){
            System.out.println("-");
        } else {
            System.out.println(this.resultTable[2]);
        }

        System.out.print("czworki: ");
        if (this.resultTable[3] == 0){
            System.out.println("-");
        } else {
            System.out.println(this.resultTable[3]);
        }

        System.out.print("piatki : ");
        if (this.resultTable[4] == 0){
            System.out.println("-");
        } else {
            System.out.println(this.resultTable[4]);
        }

        System.out.print("szostki: ");
        if (this.resultTable[5] == 0){
            System.out.println("-");
        } else {
            System.out.println(this.resultTable[5]);
        }

        System.out.println("RAZEM: " + this.getScore() + "\n");
    }

    protected boolean selectioNotInAvailableMoves(int selection){
        int[] availableMoves = this.availableMoves();
        boolean selectionInAvailableMoves = false;
        for (int availableMove : availableMoves) {
            if (availableMove == selection) {
                selectionInAvailableMoves = true;
                break;
            }
        }
        return !selectionInAvailableMoves;
    }


    protected int[] getNumbersOfDieces(){
        System.out.print("\nPodaj oddzielone przecinkami numery kości, którymi chcesz rzucić ponownie (0 aby pominąć)? ");
        String input = scanner.next();
        String[] numbersAsString = input.split(",");
        int length = numbersAsString.length;
        int[] numbers = new int[ length ];
        for (int i = 0; i<length; i++) {
            try {
                numbers[i] = Integer.parseInt(numbersAsString[i]);
            }
            catch (NumberFormatException e) {
                System.out.print("Nieprawidłowy numer kości: " + numbersAsString[i]);
            }
        }
        return numbers;
    }
    protected void throw3times(Turn turn){
        turn.throwDices(ALL_DIECES);
        turn.printResult();

        for (int i=0; i<2; i++){
            {
                int[] numbersOfDieces = this.getNumbersOfDieces();
                if ( numbersOfDieces[0] != 0) {
                    turn.throwDices(numbersOfDieces);
                    turn.printResult();
                }
            }
        }
    }
    void play(){
        if (this.hasMoves()){
            Turn turn = new Turn();
            this.printTable();
            this.throw3times(turn);
            System.out.print("Możliwe do wykorzystania linie to: " );
            for (int line: this.availableMoves()){
                System.out.print(line + " ");
            }
            int selection = 0;
            while (selectioNotInAvailableMoves(selection)) {
                System.out.print("\nTwój wybór? ");
                try {
                    selection = scanner.nextInt();
                }
                catch (Exception e){
                    System.out.print("Nieprawidłowa wartość. Spróbuj ponownie. ");
                    selection = 0;
                }
            }
            this.storeScores(selection,turn);
            this.printTable();
        } else {
            System.out.println(this.getName() + " wykorzystałeś wszystkie linie tabeli! Twój końcowy wynik to: " + this.getScore());
        }
    }
}
