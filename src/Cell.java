import java.util.Random;

public class Cell {
    private int ownerIdx = -1;
    private String color;
    private String type;
    private long cost = 0;

    private String[] colors = new String[]{"red", "blue", "yellow", "green"};
    public Cell(){
        color = colors[getRandomNumberUsingInts(0, 4)];
        type = "on Sales";
        cost = (long)getRandomNumberUsingInts(50, 101) * 1_000_000;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public int getOwnerIdx() {
        return ownerIdx;
    }

    public void setOwnerIdx(int ownerIdx) {
        this.ownerIdx = ownerIdx;
    }
}
