public class Player {
    private long money = 2_000_000_000;
    private boolean inGame = true;
    private String name;
    private int pos;
    private int daysLeftPrison = 0;

    public int getDaysLeftPrison() {
        return daysLeftPrison;
    }

    public void setDaysLeftPrison(int daysLeftPrison) {
        this.daysLeftPrison = daysLeftPrison;
    }

    public Player(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void addMoney(int money) {
        this.money+=money;
    }
}
