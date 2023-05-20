public class Tile {
    private final int value;

    public Tile(String value){   //constructor
        this.value = Integer.parseInt(value);
    }
    public Tile(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {  //checks if the comparison object is a tile by itself
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}