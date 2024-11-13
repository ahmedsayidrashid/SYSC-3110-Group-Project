import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.util.List;

public class GameController implements ActionListener {
    private Player player;
    private pieceList pieces;
    private GameView view;

    public GameController(Player player, pieceList pieces, GameView view) {
        this.player = player;
        this.pieces = pieces;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.playButton) {
            String wordPlacement = view.getWordPlacementInput();
            if (wordPlacement.isEmpty()) {
                view.showMessage("Enter a valid word placement.");
                return;
            }

            // Parse the placement notation (e.g., "H8 horizontal WORD")
            String[] parts = wordPlacement.split(" ");
            if (parts.length != 3) {
                view.showMessage("Invalid notation. Use format: ROW_COL ex: H8, orientation either horizontal or vertical and then your WORD");
                return;
            }

            String position = parts[0];
            String orientation = parts[1];
            String word = parts[2];

            // Convert position (e.g., "H8") to row and column
            int row = position.charAt(0) - 'A';
            int col = Integer.parseInt(position.substring(1)) - 1;

            int wordScore = placeWord(row, col, orientation, word);
            if (wordScore > 0) {
                player.addScore(wordScore);
                view.updatePlayerInfo(player.getName() + " - Score: " + player.getScore());
            } else {
                view.showMessage("Invalid word placement.");
            }

        } else if (e.getSource() == view.removeButton) {
            List<Piece> selectedPieces = view.getSelectedPieces();
            player.removePiece(selectedPieces);

            // Update the view with the new score
            view.updatePlayerInfo(player.getName() + " - Score: " + player.getScore());
            view.clearSelectedCells();
        } else if (e.getSource() == view.passButton) {
            view.showMessage("Turn passed.");
        }

        view.repaint();
    }

    private int placeWord(int initialRow, int initialCol, String orientation, String word) {
        int wordScore = 0;
        int row = initialRow;
        int col = initialCol;

        // Step 1: Check if there’s enough space for the entire word
        for (char letter : word.toCharArray()) {
            if (row < 0 || row >= 15 || col < 0 || col >= 15) return 0;

            String existingLetter = view.getLetterAtBoard(row, col);
            if (existingLetter != null && !existingLetter.isEmpty()) {
                return 0; // Stop and return 0 if any cell is occupied
            }

            if (orientation.equalsIgnoreCase("horizontal")) col++;
            else if (orientation.equalsIgnoreCase("vertical")) row++;
            else return 0;
        }

        row = initialRow;
        col = initialCol;
        for (char letter : word.toCharArray()) {
            Piece piece = new Piece(String.valueOf(letter));
            wordScore += piece.getValue();

            view.placeLetterOnBoard(row, col, piece.getLetter());

            if (orientation.equalsIgnoreCase("horizontal")) col++;
            else if (orientation.equalsIgnoreCase("vertical")) row++;
        }

        return wordScore;
    }
}
