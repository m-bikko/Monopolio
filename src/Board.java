public class Board {
    private Cell[] cells = new Cell[40];

    public Board(){
        for (int i = 0; i < 40; i++) {
            cells[i] = new Cell();
        }
        cells[20].setType("Prison");
        cells[10].setType("Free parking");
        cells[30].setType("Go to prison");
        cells[5].setType("Train");
        cells[15].setType("Train");
        cells[25].setType("Train");
        cells[35].setType("Train");
        cells[7].setType("Chance");
        cells[22].setType("Chance");
        cells[36].setType("Chance");
        cells[2].setType("Chest");
        cells[17].setType("Chest");
        cells[33].setType("Chest");
    }

    public Cell[] getCells() {
        return cells;
    }
}
