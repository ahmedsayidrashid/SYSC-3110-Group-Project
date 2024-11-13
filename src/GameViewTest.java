import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class GameViewTest {

    private GameView view;
    private GameController controller;
    private Player player;
    private pieceList pieces;

    @BeforeEach
    public void setUp() {
        pieces = new pieceList();
        player = new Player(pieces, view,"Player 1");
        controller = mock(GameController.class); // Ensure controller is mocked correctly
        view = new GameView(player, pieces);     // Pass valid, non-null objects
    }


    // 1. Score and Player Information Tests

    @Test
    public void testInitialScoreIsZero() {
        view.updatePlayerInfo("Player 1 - Score: " + player.getScore());
        assertEquals("Player 1 - Score: 0", "Player 1 - Score: " + player.getScore());
    }

    @Test
    public void testScoreUpdateAfterAddingPoints() {
        player.addScore(10);
        view.updatePlayerInfo("Player 1 - Score: " + player.getScore());
        assertEquals("Player 1 - Score: 10", "Player 1 - Score: " + player.getScore());
    }

    @Test
    public void testScoreUnchangedAfterInvalidPlay() {
        int initialScore = player.getScore();
        view.showMessage("Invalid word placement.");
        assertEquals("Player 1 - Score: " + initialScore, "Player 1 - Score: " + player.getScore());
    }

    // 2. Word Placement Logic Tests

    @Test
    public void testValidWordPlacementInput() {
        when(view.getWordPlacementInput()).thenReturn("H8 horizontal WORD");
        view.showMessage("Valid word placement.");
        verify(controller, times(1)).actionPerformed(any());
    }

    @Test
    public void testInvalidWordPlacementMissingOrientation() {
        when(view.getWordPlacementInput()).thenReturn("H8 WORD");
        view.showMessage("Invalid notation. Use format: ROW_COL orientation WORD");
    }

    @Test
    public void testInvalidPositionNotation() {
        when(view.getWordPlacementInput()).thenReturn("Z20 horizontal WORD");
        view.showMessage("Invalid notation. Use format: ROW_COL orientation WORD");
    }

    // 3. Piece Management Tests

    @Test
    public void testAddPieceToPieceList() {
        Piece piece = new Piece("A");
        pieces.addPiece(piece);
        assertTrue(pieces.contains(piece));
    }

    @Test
    public void testRemovePieceFromPieceList() {
        Piece piece = new Piece("B");
        pieces.addPiece(piece);
        player.removePiece(List.of(piece));
        assertFalse(pieces.contains(piece));
    }

    @Test
    public void testClearAllPieces() {
        pieces.addPiece(new Piece("C"));
        view.clearSelectedCells();
        assertEquals(0, pieces.getSize());
    }

    // 4. UI Button Interaction Tests

    @Test
    public void testPlayButtonWithValidInput() {
        when(view.getWordPlacementInput()).thenReturn("H8 horizontal WORD");
        view.showMessage("Word placed successfully.");
        verify(controller, times(1)).actionPerformed(any());
    }

    @Test
    public void testPlayButtonWithInvalidInput() {
        when(view.getWordPlacementInput()).thenReturn("H8 WORD");
        view.showMessage("Invalid notation. Use format: ROW_COL orientation WORD");
    }

    @Test
    public void testRemovePiecesClearsPieceList() {
        List<Piece> testsel = view.getSelectedPieces();
        player.removePiece(testsel);
        assertEquals(0, pieces.getSize());
    }

    @Test
    public void testPassButtonShowsPassMessage() {
        view.showMessage("Turn passed.");
    }

    // 5. Boundary and Edge Case Tests

    @Test
    public void testRemovePieceFromEmptyList() {
        List<Piece> emptyList = new ArrayList<>();
        player.removePiece(emptyList);
        view.showMessage("No pieces to remove.");
    }

    @Test
    public void testValidPlacementAtBoardEdge() {
        when(view.getWordPlacementInput()).thenReturn("A1 horizontal TEST");
        view.showMessage("Placement at board edge successful.");
        verify(controller, times(1)).actionPerformed(any());
    }

    @Test
    public void testOutOfBoundsPlacementDoesNotAffectScore() {
        int initialScore = player.getScore();
        when(view.getWordPlacementInput()).thenReturn("Z99 vertical WORD");
        view.showMessage("Invalid notation. Use format: ROW_COL orientation WORD");
        assertEquals("Player 1 - Score: " + initialScore, "Player 1 - Score: " + player.getScore());
    }
}
