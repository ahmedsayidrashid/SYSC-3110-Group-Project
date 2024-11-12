import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest {
    private GameView game;
    private Robot bot;

    public GameViewTest() {
        this.game = null;
        try { this.bot = new Robot(); }
        catch (AWTException e){System.out.println(e.getMessage());}
    }

    //buttonWidth = 48px
    //buttonHeight = 40px
    //269,51 is top left of board
    // 620, 675 is Remove Selected button
    // 530, 675 is Play Word Button
    // 750, 675 is Skip Turn Button

    @org.junit.jupiter.api.Test
    void getSelectedCells() {

        //Check if selected cells contains
        newGame();
        botClick(293,71); //Button 0,0
        botClick(341,71); //Button 0,1
        botClick(389,71); //Button 0,2
        botClick(437,71); //Button 0,3
        for(int i=0; i != 3; i++) {
            Point cell = new Point(0, i);
            assertTrue(game.getSelectedCells().contains(cell));
        }
        botClick(293,71);
        botClick(341,71);
        botClick(389,71);
        botClick(437,71);
        botClick(0,0); // reset cursor to finalize click
        assertTrue(game.getSelectedCells().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void clearSelectedCells() {
        newGame();
        botClick(293,71); //Button 0,0
        botClick(341,71); //Button 0,1
        botClick(389,71); //Button 0,2
        botClick(437,71); //Button 0,3
        game.clearSelectedCells();
        assertTrue(game.getSelectedCells().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void getSelectedPieces() {

    }

    @org.junit.jupiter.api.Test
    void getWordPlacementInput() {

    }

    @org.junit.jupiter.api.Test
    void updatePlayerInfo() {
    }

    @org.junit.jupiter.api.Test
    void showMessage() {
    }

    @org.junit.jupiter.api.Test
    void getLetterAtBoard() {
    }

    @org.junit.jupiter.api.Test
    void placeLetterOnBoard() {
    }

    private void newGame(){
        pieceList pieces = new pieceList();
        Player player = new Player(pieces, null, "Player 1");
        this.game = new GameView(player, pieces);
    }

    private void botClick(int x, int y) {
        bot.mouseMove(x,y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        //add time between press and release or the input event system may
        //not think it is a click
        try{Thread.sleep(200);}catch(InterruptedException e){}
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}