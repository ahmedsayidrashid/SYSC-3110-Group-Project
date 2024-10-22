import java.util.List;

public class Player {
    private pieceList pieces;
    private Board view;
    private String name;
    private int score;

    public Player(pieceList pieces, Board view, String name) {
        this.pieces = pieces;
        this.view = view;
        this.name = name;
        this.score = 0;
    }

    // Method to add a piece to players hand
    public void addPiece(String letter) {
        if (letter != null) {
            letter.toUpperCase();
            Piece newPiece = new Piece(letter);
            pieces.addPiece(newPiece);
        }
    }

    //Method to remove a piece from a players hand
    public void removePiece(List<Piece> pieceList) {
        if (pieceList.size() < 8) {
            pieces.removePiece(pieceList);
        } else {
            view.showMessage("Too many pieces selected to remove");
        }
    }

    // Method to play then remove a piece from players hand
    public void playPieces(List<Piece> pieceList) {
        for (Piece piece : pieceList) {
            this.score += piece.getValue();
        }
        if (pieceList.size() < 8) {
            pieces.removePiece(pieceList);
        } else {
            view.showMessage("Too many pieces selected to remove");
        }
    }

    // Method to clear the players hand
    public void clearPieceList() {
        pieces.clear();
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
}
