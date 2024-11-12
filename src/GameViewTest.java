import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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
    // 290, 675 is WordPos text box

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

        // Check if cell list is empty after being clicked a second time
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

    //@org.junit.jupiter.api.Test
    //void getSelectedPieces() {}

    @org.junit.jupiter.api.Test
    void getWordPlacementInput() {
        newGame();
        botClick(290, 675);
        //Type: A1 vertical Hi
        botType("A1 vertical Hello");
        botClick(530, 675);
        try{Thread.sleep(2000);}catch(InterruptedException e){}
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

    private void botType(String string){
        for (char ch: string.toCharArray()) {
            bot.keyPress(getKeyCode(ch));
            try{Thread.sleep(200);}catch(InterruptedException e){}
            bot.keyRelease(getKeyCode(ch));
        }
    }

    //GPT CODE EDIT AS YOU GO
    private int getKeyCode(char ch) {
        // Map individual characters to KeyEvent codes
        switch (ch) {
            case 'A': return KeyEvent.VK_A;
            case '1': return KeyEvent.VK_1;
            case ' ': return KeyEvent.VK_SPACE;
            // Add cases for other characters as needed
            case 'v': return KeyEvent.VK_V;
            case 'e': return KeyEvent.VK_E;
            case 'r': return KeyEvent.VK_R;
            case 't': return KeyEvent.VK_T;
            case 'i': return KeyEvent.VK_I;
            case 'c': return KeyEvent.VK_C;
            case 'a': return KeyEvent.VK_A;
            case 'l': return KeyEvent.VK_L;
            case 'H': return KeyEvent.VK_H;
            case 'o': return KeyEvent.VK_O;

            default: return -1; // Return -1 for unsupported characters
        }
    }
}