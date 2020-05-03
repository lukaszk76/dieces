import java.util.Random;

class Dice {
    private int result = 0;

    int getResult(){
        if (this.result == 0){
            System.out.println("Jeszcze nie rzucono kostką!");
        }
        return this.result;
    }

    void throwDice(){
        Random r = new Random();
        this.result = r.nextInt(6) + 1;
    }
}
