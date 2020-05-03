class Turn {
    private Dice[] dices = new Dice[6];

    Turn(){
        for (int i=1; i<7; i++){
            this.dices[i-1] = new Dice();
        }
    }

    int getResult(int i){
        int result = 0;
        for (Dice dice: this.dices){
            if (dice.getResult() == i){
                result += i;
            }
        }
        return result;
    }


    void throwDices( int[] diceNumbers ){
        System.out.print("Rzucam kostkami: ");
        for (int diceNumber: diceNumbers){
            System.out.print(diceNumber + " ");
            this.dices[diceNumber-1].throwDice();
        }
    }

    void printResult(){
        String[] lines = new String[3];
        lines[0] = "|";
        lines[1] = "|";
        lines[2] = "|";
        for (Dice dice : this.dices) {
            if (dice.getResult() == 1) {
                lines[0] = lines[0] + "     |";
                lines[1] = lines[1] + "  o  |";
                lines[2] = lines[2] + "     |";
            } else if (dice.getResult() == 2) {
                lines[0] = lines[0] + " o   |";
                lines[1] = lines[1] + "     |";
                lines[2] = lines[2] + "   o |";
            } else if (dice.getResult() == 3) {
                lines[0] = lines[0] + " o   |";
                lines[1] = lines[1] + "  o  |";
                lines[2] = lines[2] + "   o |";
            } else if (dice.getResult() == 4) {
                lines[0] = lines[0] + " o o |";
                lines[1] = lines[1] + "     |";
                lines[2] = lines[2] + " o o |";
            } else if (dice.getResult() == 5) {
                lines[0] = lines[0] + " o o |";
                lines[1] = lines[1] + "  o  |";
                lines[2] = lines[2] + " o o |";
            } else if (dice.getResult() == 6) {
                lines[0] = lines[0] + " o o |";
                lines[1] = lines[1] + " o o |";
                lines[2] = lines[2] + " o o |";
            }
        }
        System.out.println("\n   1     2     3     4     5     6");
        System.out.println(lines[0]);
        System.out.println(lines[1]);
        System.out.println(lines[2]);
    }
}

