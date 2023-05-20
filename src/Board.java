import java.util.Arrays;

public class Board {
    private Tile[][] tiles;
    private final int []dimension;
    private int[] emptyTile;

    public Board(String boardString){
        String []temp = boardString.split("\\|");// _ does this even work
        String[] testing_size = temp[0].split(" ");
        String[] loop_string;
        this.dimension = new int[2];
        this.emptyTile = new int[2];
        this.dimension[0] = temp.length;
        this.dimension[1]= testing_size.length;
        int size = dimension[0]*dimension[1];
        this.tiles = new Tile[dimension[0]][dimension[1]];
        Tile tile;

        for(int i=0 ; i < dimension[0];i++){
            loop_string = temp[i].split(" ");
            for(int k=0; k<dimension[1];k++){
                if (loop_string[k].equals("_")){
                    this.emptyTile[0]=i;
                    this.emptyTile[1]=k;
                    loop_string[k] = (String.valueOf(size));
                }
                tile = new Tile(loop_string[k]); // need to add a filter for "_"
                this.tiles[i][k] = tile;
            }
        }
    }
    public Board(Tile[][] tiles, int[] dimension, int[] emptyTile){
        int[] newEmptyTile = new int[2];
        newEmptyTile[0] = emptyTile[0];
        newEmptyTile[1] = emptyTile[1];
        int rows = dimension[0];
        int cols = dimension[1];
        Tile[][] newTiles = new Tile[rows][cols];
        for(int row =0; row < rows ; row++){
            for(int col = 0; col < cols; col++){
                Tile tmpTile =  new Tile(tiles[row][col].getValue());
                newTiles[row][col] = tmpTile;

            }
        }
        this.tiles=newTiles;
        this.dimension=dimension;
        this.emptyTile =newEmptyTile;

    }

    public int[] getDimension(){
        return dimension;
    }
    public Tile[][] getAllTiles(){
        return this.tiles;
    }
    public Tile getTile(int row , int col){
        return tiles[row][col];

    }
    public int[] getEmptyTile(){
        return emptyTile;
    }
    public void swapTiles(Action action){
        Tile tmp;
        int[] tmpArray = new int[2];
        switch(action.getDirection()){ //prob a better way to do this since operating on the empty as well its a bit confusing doing
            case UP:
                tmpArray[0]--;
                break;
            case DOWN:
                tmpArray[0]++;
                break;
            case RIGHT:
                tmpArray[1]--;
                break;
            case LEFT:
                tmpArray[1]++;
                break;
        }
        tmp = this.tiles[this.emptyTile[0]][this.emptyTile[1]];
        tiles[this.emptyTile[0]][this.emptyTile[1]] = tiles[this.emptyTile[0]-tmpArray[0]][this.emptyTile[1]-tmpArray[1]];
        tiles[this.emptyTile[0]-tmpArray[0]][this.emptyTile[1]-tmpArray[1]] = tmp;
        //emptyTile[0] -= tmpArray[0];
        //emptyTile[1] -= tmpArray[1];

    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
