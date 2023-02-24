import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final List<Player> players;
    private int activePlayers;
    private Board board;
    private String winner = "Game didn't end!";
    private Chance chance = new Chance();
    private Community_Chest chest = new Community_Chest();

    private Dice dice1;
    private Dice dice2;

    public String getWinner() {
        return winner;
    }

    public Game(List<Player> players) {
        this.players = players;
    }

    public void start() {
        board = new Board();
        dice1 = new Dice();
        dice2 = new Dice();
        activePlayers = players.size();
        int queue = 0;
        while (activePlayers != 1){
            queue%=players.size();
            if(players.get(queue).isInGame()){
                System.out.println("Now is " + (queue + 1) + "\'th player's turn");
                getStatusOfPlayer(queue);
                turn(queue, 1);
            }


            if(players.get(queue).isInGame() && players.get(queue).getMoney()<0){
                players.get(queue).setInGame(false);
                activePlayers--;
            }
            if(activePlayers == 1) {
                winner = players.get(queue).getName();
            }
            queue++;
        }
    }

    private void getStatusOfPlayer(int queue) {
        System.out.println("Your current position is: " + queue);
        System.out.println("Your total capital is: " + players.get(queue).getMoney() + "KZT");
        System.out.println("Days left in prison: " + players.get(queue).getDaysLeftPrison() + "\n");
    }

    private void turn(int queue, int time) {
        if(time == 3) goPrison(queue, 3);
        int cube1 = dice1.roll();
        int cube2 = dice2.roll();

        if(players.get(queue).getDaysLeftPrison() == 0){
            System.out.println("Dice is taken...");
            int sum = cube1 + cube2;
            System.out.println("Dice's values are " + cube1 + " & " + cube2);
            makeStep(queue, sum);
            System.out.println("You came to: " + board.getCells()[players.get(queue).getPos()].getType() + "\n");
            if(board.getCells()[players.get(queue).getPos()].getType().equals("Chance"))
                takeChance(queue);
            if(board.getCells()[players.get(queue).getPos()].getType().equals("Chest"))
                takeChest(queue);
            if(board.getCells()[players.get(queue).getPos()].getType().equals("on Sales"))
                buyOrNo(queue);
            if(board.getCells()[players.get(queue).getPos()].getType().equals("Prison") || board.getCells()[players.get(queue).getPos()].getType().equals("Go to prison"))
                goPrison(queue, 3);
            if(board.getCells()[players.get(queue).getPos()].getType().equals("Train"))
                travelByTrain(queue);
        }
        else System.out.println("You are in prison");
        if(players.get(queue).getDaysLeftPrison() > 0){
            players.get(queue).setDaysLeftPrison(players.get(queue).getDaysLeftPrison()-1);
        }
        if(cube1 == cube2){
            System.out.println("Since your 1'st and 2'nd dices have same value you are immediately get out of the prison!!!");
            players.get(queue).setDaysLeftPrison(0);
            turn(queue, time++);
        }
    }

    private void travelByTrain(int queue) {
        System.out.println("Train reduced " + queue + "\'th players money by 10_000_000 and randomly went to another station");
        players.get(queue).setMoney(players.get(queue).getMoney() - 10_000_000);
        players.get(queue).setPos(getRandomNumberUsingInts(0, 4) * 10 + 5);
    }

    private void takeChest(int queue) {
        System.out.println("You took chest card");
        int mny = chest.getCard();
        System.out.println("You get " + mny + "KZT for this choise");
        players.get(queue).setMoney(players.get(queue).getMoney() + mny);
    }

    private void takeChance(int queue) {
        System.out.println("You took chance card");
        int step = chance.getCard();

        if(step == 0) goPrison(queue, 0);
        else makeStep(queue, step);
    }

    private void makeStep(int queue, int step) {
        System.out.println("You took chance card & stepped " + step + " steps\n");
        if(players.get(queue).getPos()+step>39) players.get(queue).addMoney(500_000_000);
        players.get(queue).setPos((players.get(queue).getPos()+step)%40);
    }

    private void buyOrNo(int queue) {
        Scanner sc = new Scanner(System.in);
        if(board.getCells()[players.get(queue).getPos()].getOwnerIdx() == -1){
            System.out.println("Would you buy it? (Type YES or NO) (It costs" + board.getCells()[players.get(queue).getPos()].getCost() + "KZT!)") ;
            String ans = sc.next();
            if(ans.equals("YES")){
                board.getCells()[players.get(queue).getPos()].setOwnerIdx(queue);
                players.get(queue).setMoney(players.get(queue).getMoney()-board.getCells()[players.get(queue).getPos()].getCost());
            }
        } else{
            System.out.println("Ypur money reduced by " + board.getCells()[players.get(queue).getPos()].getCost()+"KZT, because someone else already owns it!(" + board.getCells()[players.get(queue).getPos()].getOwnerIdx() + ")");
            players.get(queue).setMoney(players.get(queue).getMoney()-board.getCells()[players.get(queue).getPos()].getCost());
            players.get(board.getCells()[players.get(queue).getPos()].getOwnerIdx()).setMoney(players.get(queue).getMoney()+board.getCells()[players.get(queue).getPos()].getCost());
        }
    }

    private void goPrison(int queue , int days) {
        System.out.println("You are in prison :( , for three days(loops)");
        players.get(queue).setPos(20);
        players.get(queue).setDaysLeftPrison(days);
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
