import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Chance {
     class Pair{
        String type;
        int step;
        public Pair(String type, int step){
            this.step = step;
            this.type = type;
        }
    }

    private Queue<Pair> chanceList;
    private Random rnd = new Random();
    private String[] arr = new String[]{"Step Back", "Step Forward", "Leave Prison"};

    public Chance(){
        chanceList = new LinkedList<>();
        for (int i = 0; i < 16; i++) {
            String type = arr[getRandomNumberUsingInts(0, 3)];
            if(type.equals("Step Back")) chanceList.add(new Pair(type, getRandomNumberUsingInts(1, 7)));
            else if(type.equals("Step Forward")) chanceList.add(new Pair(type, -getRandomNumberUsingInts(1, 7)));
            else chanceList.add(new Pair(type, 0));
        }
    }

    public int getCard(){
        chanceList.add(chanceList.peek());
        if(!chanceList.isEmpty())
            return chanceList.poll().step;
        else return 0;
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
