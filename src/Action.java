public class Action {
    private int[] movedTile;
    private final Direction direction;
    private final Board board;

    public Action(int[] emptyTile, Direction direction, Board board){
        int[] tmp = new  int[]{emptyTile[0],emptyTile[1]};
        switch(direction){ //prob a better way to do this since operating on the empty as well its a bit confusing doing
            case UP:        // it this way
                ++tmp[0];
                break;
            case DOWN:
                --tmp[0];
                break;
            case LEFT:
                ++tmp[1];
                break;
            case RIGHT:
                --tmp[1];
                break;
        }
        this.movedTile = tmp;
        this.direction = direction;
        this.board = board;
    }

    public String toString(){
        if(this.checkValid()){
            return "Move" +" "+ board.getTile(movedTile[0],movedTile[1]).getValue()+" " + direction.name().toLowerCase();

        }
        return "Invalid Action";
    }
    public boolean checkValid(){
        int[] dimension = this.board.getDimension();
        switch(this.direction) {
            case UP:
            case DOWN:
                if (( this.movedTile[0] >= 0)&&(this.movedTile[0] < dimension[0])) {
                    return true;

                }
                break;
            case LEFT:
            case RIGHT:
                if((this.movedTile[1] >= 0)&&(this.movedTile[1] < dimension[1])){
                    return true;
                }
                break;

        }
        return false;
    }
    public Direction getDirection(){
        return this.direction;
    }
    public int[] getMovedTile(){
        return movedTile;
    }
}
