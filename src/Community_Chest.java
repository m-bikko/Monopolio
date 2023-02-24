import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Community_Chest {


    static class Pair{
        String type;
        int money;
        public Pair(String type, int money){
            this.money = money;
            this.type = type;
        }
    }

    private Queue<Pair> chestList;
    String[] arr = new String[]{"Get Money", "Reduce Money"};
    private Random rnd = new Random();
    public Community_Chest(){
        chestList = new LinkedList<>();
        //Number of Chest
        for (int i = 0; i < 16; i++) {
            chestList.add(new Pair(arr[getRandomNumberUsingInts(0, 2)], getRandomNumberUsingInts(10, 101) * 1_000_000));
        }
    }

    public int getCard(){
        chestList.add(chestList.peek());
        if(!chestList.isEmpty())
        return (chestList.peek().type.equals("Get Money")) ? chestList.poll().money : (-1 * chestList.poll().money);
        else return 0;
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
