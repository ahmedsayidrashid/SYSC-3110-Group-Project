public class ScrabbleGame {
    public static void main(String[] args) {
        pieceList pieces = new pieceList();
        Player player = new Player(pieces, null, "Player 1");
        GameView view = new GameView(player, pieces);
    }
}
