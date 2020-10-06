import org.junit.jupiter.api.Test;

public class TestWizard(){

    @Test
    public void testGetColor(){
        ChessBoard board = new ChessBoard();
        Wizard wiz = new Wizard(board, ChessPiece.Color.WHITE);
        assert wiz.getColor() == ChessPiece.Color.WHITE: "Expected color to be white";
    }

    @Test
    public void testGetPosition(){
        ChessBoard board = new ChessBoard();
        Wizard wiz = new Wizard(board, ChessPiece.Color.WHITE);
        board.placePiece(wiz, "a4");
        assert wiz.getPosition == "a4": "Piece expected to return \"a4\"";
    }

    @Test
    public void testLegalMoves(){
        ChessBoard board = new ChessBoard();
        Wizard wiz = new Wizard(board, ChessPiece.Color.BLACK);
        board.placePiece(wiz, "g5");
        ArrayList<String> moves = new ArrayList<>();
        moves.add("f6"); moves.add("f8"); moves.add("h6"); moves.add("j6");
        moves.add("h4"); moves.add("h2"); moves.add("f4"); moves.add("d4");
        moves.add("h8"); moves.add("j4"); moves.add("f2"); moves.add("d6");
        assert moves.equals(wiz.legalMoves()): "Expected moves not provided";
    }
}