import java.util.Scanner;

public class Game {

    private static Player[] definePlayers(){
        Scanner scanner = new Scanner(System.in);
        Player[] players;
        int numberOfPlayers = 0;

        while (numberOfPlayers == 0) {
            try {
                System.out.print("Podaj liczbę graczy: ");
                numberOfPlayers = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Nieprawidłowa wartość. Spróbuj jeszcze raz! ");
                scanner.next();
            }
        }

        if (numberOfPlayers == 1){
            players = new Player[2];
            players[0] = new Player();
            players[0].setName();
            players[1] = new AutomaticPlayer();
            players[1].setName("Komputer");
        } else {
            players = new Player[numberOfPlayers];
            for (int i = 0; i < numberOfPlayers; i++){
                players[i] = new Player();
                players[i].setName();
            }
        }

        return players;
    }

    public static void main(String[] args){

        Player[] players = definePlayers();
        boolean continueGame = true;
        while (continueGame) {
            continueGame = false;
            for (Player player: players){
                if (player.hasMoves()){
                    System.out.println("\n___________________________________");
                    System.out.print("Kolej gracza: "+ player.getName());
                    System.out.println("\n___________________________________");
                    continueGame = true;
                    player.play();
                }
            }
        }
        System.out.println("\nKOńCOWE WYNIKI:");
        for (Player player: players){
            System.out.println(player.getName()+ ": "+player.getScore());
        }
    }
}
