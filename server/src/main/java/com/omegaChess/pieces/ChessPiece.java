import java.util.ArrayList;
public abstract class ChessPiece {
    public enum Color {WHITE, BLACK}

    // The board the piece belongs to.
    protected ChessBoard board;

    // Horizontal position 0-7
    protected int row;

    // Vertical position 0-7
    protected int column;

    // Color of piece
    protected Color color;

    // Sets board and color attributes
    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
    }

    // Returns the color of the current piece
    public Color getColor(){
        return this.color;
    }

    // Returns the position of the piece in a two-character string. (1st char: a-h, 2nd char: 1-8)
    public String getPosition(){

        String pos = new String();

        pos = reverseParse(row, column);

        return pos;
    }

    // Method to convert string position into two integer coordinates
    protected int[] parsePosition(String position) throws IllegalPositionException{
        int pos[] = new int[2];
        switch (position.charAt(0)){
            case 'a':
                pos[1] = 0;
                break;
            case 'b':
                pos[1] = 1;
                break;
            case 'c':
                pos[1] = 2;
                break;
            case 'd':
                pos[1] = 3;
                break;
            case 'e':
                pos[1] = 4;
                break;
            case 'f':
                pos[1] = 5;
                break;
            case 'g':
                pos[1] = 6;
                break;
            case 'h':
                pos[1] = 7;
                break;
            case 'i':
                pos[1] = 8;
                break;
            case 'j':
                pos[1] = 9;
                break;
            default:
                throw new IllegalPositionException();
        }
        switch (position.charAt(1)){
            case '0':
                pos[0] = 0;
                break;
            case '1':
                pos[0] = 1;
                break;
            case '2':
                pos[0] = 2;
                break;
            case '3':
                pos[0] = 3;
                break;
            case '4':
                pos[0] = 4;
                break;
            case '5':
                pos[0] = 5;
                break;
            case '6':
                pos[0] = 6;
                break;
            case '7':
                pos[0] = 7;
                break;
            case '8':
                pos[0] = 8;
                break;
            case '9':
                pos[0] = 9;
                break;
            default:
                throw new IllegalPositionException();
        }
        return pos;
    }

    // A method that converts the integer coordinates of a piece to a String
    protected String reverseParse(int r, int c){
        String pos = new String();
        char colRow[] = new char[2];

        colRow[1] = (char)(r+'0');
        switch (c){
            case 0:
                colRow[0] = 'a';
                break;
            case 1:
                colRow[0] = 'b';
                break;
            case 2:
                colRow[0] = 'c';
                break;
            case 3:
                colRow[0] = 'd';
                break;
            case 4:
                colRow[0] = 'e';
                break;
            case 5:
                colRow[0] = 'f';
                break;
            case 6:
                colRow[0] = 'g';
                break;
            case 7:
                colRow[0] = 'h';
                break;
            case 8:
                colRow[0] = 'i';
                break;
            case 9:
                colRow[0] = 'j';
                break;
        }
        pos = new String(colRow);

        return pos;
    }

    /* Sets the position of the piece based on the argument. Throws exception if the position
    * contains illegal characters or represents a position outside the board. */
    public void setPosition(String position) throws IllegalPositionException{
        int pos[] = parsePosition(position);
        column = pos[1];
        row = pos[0];
    }

    /* To be implemented in the concrete subclasses. Returns a one-character piece corresponding
    * to the type of the piece. */
    abstract public String toString();

    /* To be implemented in the concrete subclasses. Returns a list of all legal moves that
    * piece can make. Each string in the arraylist should be the position of a possible destination
    * for the piece. Order of legal moves is irrelevant. Return an empty list if no moves are
    * available. Queen and Knight should return empty lists.*/
    abstract public ArrayList<String> legalMoves();
}
