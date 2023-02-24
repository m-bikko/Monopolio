import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        int playersNum = sc.nextInt();
        for (int i = 0; i < playersNum; i++) {
            System.out.println("Enter name of " + (i + 1) + "\'th player");
            players.add(new Player(sc.next()));
        }
        Game game = new Game(players);
        game.start();
        System.out.println("Congrats "+game.getWinner());
        System.out.println("Thanks for the game!!!");
    }
}
