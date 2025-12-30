public class Seat {

    private int id;

    private int row;

    private int column;

    public Seat(int id, int row, int column)
    {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "---------------------" + id + "\nRow : " + row + "\nColumn : " + column ;
    }
}
