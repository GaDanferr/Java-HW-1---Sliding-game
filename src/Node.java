import javax.swing.*;

public class Node {
    Node pNode;
    Action action;
    State state;
    int heuristicValue;

    public Node (Node pNode , Action action, State state){
        this.pNode = pNode;
        this.action = action;
        this.state = state;
        this.heuristicValue = this.state.setHeuristicValue();
    }
    public Node  (String boardString){
        Board board = new Board(boardString);
        this.pNode = null;
        this.action = null;
        this.state = new State(board);
        this.heuristicValue = this.state.setHeuristicValue();
    }

    public State getState(){
        return this.state;
    }
    public Node[] expand(){
        Action[] newActions = state.actions();
        int childCount = newActions.length;
        int counter = 0;
        Node[] children = new Node[childCount];
        for(Action newAction: newActions){
            Node newChild = new Node(this,newAction,state.results(newAction));
            children[counter] = newChild;
            counter++;

        }
        return children;
    }
    public int heuristicValue(){
        return heuristicValue;
    }

    public Action getAction(){
        return action;
    }
    public Node getParent(){
        return pNode;
    }

}
