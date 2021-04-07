package snake;

public class Board {


    private final int size = 30;
    private Obj[][] board = new Obj[size][size];

    public Board(Snake s1, Snake s2) {
        for(BodyPart part: s1.getBodyParts()) {
            this.board[part.getX()][part.getY()] = part;
        }
        for(BodyPart part: s2.getBodyParts()) {
            this.board[part.getX()][part.getY()] = part;
        }
    }

    public Obj getSq(int row, int col) {
        return this.board[row][col];
    }

    public void setSq(int row, int col, Obj obj) {
        this.board[row][col] = obj;
    }
}
