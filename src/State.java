import java.security.cert.TrustAnchor;

public class State {
    private final Board board;
    public boolean isGoal(){
        int[] dimension = board.getDimension();
        int rowCount = dimension[0];
        int colCount = dimension[1];
        int counter = 1;
        for (int row=0; row< rowCount;row++){
            for(int col=0; col<colCount;col++){
                if(board.getTile(row,col).getValue() != counter ) {
                    return false;
                }
                counter++;
            }
        }
        return true;
    }
    public State(Board board){
        this.board = board;

    }

    public Action[] actions(){
        Action action;
        Action[] preActions = new Action[4];
        int[] newLocation;
        int correctCount = 0;
        for(Direction direction: Direction.values()) {
            newLocation = new int[]{board.getEmptyTile()[0],board.getEmptyTile()[1]};
            action = new Action(newLocation,direction,board );
            if(action.checkValid()){
                preActions[correctCount] = action;
                correctCount++;
            }
        }

        if(correctCount==4) {
            return preActions;
        }
        else{
            Action[] actions = new Action[correctCount];
            for(int i = 0 ; i<correctCount ; i++){
                actions[i] = preActions[i];
            }
            return actions;
        }
    }
    public State results(Action action){
        Board newBoard = new Board(board.getAllTiles(),board.getDimension(),action.getMovedTile());
        newBoard.swapTiles(action);
        return new State(newBoard);
    }
    public int setHeuristicValue(){
        int rowSize = board.getDimension()[0];
        int colSize = board.getDimension()[1];
        int hValue = 0;
        int counter = 1;
        int misplacedValue;
        int colPlacement;
        int rowPlacement;
        for (int row = 0; row< rowSize;row++){
            for(int col = 0 ;col < colSize ;col++){
                if(board.getTile(row,col).getValue() != counter){
                    misplacedValue = board.getTile(row,col).getValue() - 1;
                    rowPlacement = misplacedValue / colSize;
                    colPlacement = misplacedValue % colSize;
                    if(rowPlacement > row){
                        hValue += rowPlacement - row;
                    }
                    else{
                        hValue += row - rowPlacement;
                    }
                    if(colPlacement > col){
                        hValue += colPlacement -col;
                    }
                    else {
                        hValue += col - colPlacement;
                    }
                }
                counter++;
            }

        }
        return hValue;
    }



    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
