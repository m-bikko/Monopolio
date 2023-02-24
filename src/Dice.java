import java.util.Random;

public class Dice {
    private int Val = 0;

    public Dice() {
    }

    public int roll(){
        return getRandomNumberUsingInts(1, 7);
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
