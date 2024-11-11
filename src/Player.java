import java.util.List;

public class Player {
    private pieceList pieces;
    private GameView view;
    private String name;
    private int score;

    public Player(pieceList pieces, GameView view, String name) {
        this.pieces = pieces;
        this.view = view;
        this.name = name;
        this.score = 0;
    }

    public void addPiece(String letter) {
        if (letter != null) {
            letter = letter.toUpperCase();
            Piece newPiece = new Piece(letter);
            pieces.addPiece(newPiece);
        }
    }

    public void removePiece(List<Piece> pieceList) {
        if (pieceList.size() < 8) {
            pieces.removePiece(pieceList);
        } else {
            view.showMessage("Too many pieces selected to remove");
        }
    }

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

    public void clearPieceList() {
        pieces.clear();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // New method to add points to the player's score
    public void addScore(int points) {
        this.score += points;
    }
}
