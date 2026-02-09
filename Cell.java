package classique;

public class Cell {

    private int x;
    private int y;
    private Item item;
    private Player occupiedBy;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Player getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Player occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public boolean hasItem() {
        return item != null;
    }

    public void removeItem() {
        item = null;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAdjacentTo(Cell other) {
        if (other == null) return false;
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return (dx + dy) == 1;
    }
}