import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board extends JFrame{
    private pieceList pieces;
    private JList<Piece> piecesList;
    private Player player;

    public Board() {
        pieces = new pieceList();
        piecesList = new JList<>(pieces);

        // Initialize the controller, aka the player
        player = new Player(pieces, this, "Player 1");
        newPlayer(player);

        // Set up JFrame
        setTitle("Scrabble");
        setSize(1600, 930);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // Add the JList and JLabel to the frame
        JLabel name = new JLabel(player.getName() + "    Score: " + player.getScore());
        name.setBounds(10, -35, 150, 100); // Set position and size
        add(name);
        // Difference of -70 y to align the name label and the scrollpane
        JScrollPane List = new JScrollPane(piecesList);
        List.setBounds(10, 30, 130, 130); // Set position and size
        add(List);
        // Button to confirm played pieces (Remove after board is made)
        JButton play = new JButton("Play Selected");
        play.setBounds(10, 160, 130, 20);
        add(play);

        // Allow for multiple letters to be selected from the players hand
        piecesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Set up menu bar
        JMenuBar menuBar = new JMenuBar();

        // Player menu
        JMenu playerMenu = new JMenu("Player Menu");
        JMenuItem newPlayerItem = new JMenuItem("Add player"); //to be implemented
        JMenuItem removePlayerItem = new JMenuItem("Remove player"); //to be implemented
        playerMenu.add(newPlayerItem);
        playerMenu.add(removePlayerItem);

        // Piece menu DEBUG ONLY, REMOVE WHEN FINISHED
        JMenu pieceMenu = new JMenu("Piece");
        JMenuItem addPieceItem = new JMenuItem("Add piece");
        JMenuItem removePieceItem = new JMenuItem("Remove piece");
        pieceMenu.add(addPieceItem);
        pieceMenu.add(removePieceItem);

        // Add menus to the menu bar
        menuBar.add(playerMenu);
        menuBar.add(pieceMenu);
        setJMenuBar(menuBar);

        // Menu item action listeners using the controller
        newPlayerItem.addActionListener(e -> player.clearPieceList());
        addPieceItem.addActionListener(e -> addPiece());
        removePieceItem.addActionListener(e -> removePiece());
        play.addActionListener(e -> playPiece());

        setVisible(true);
    }

    private void addPiece() {
        //Random letter between ACSII 65 (A) and 90 (Z)
        int randomLetter = ThreadLocalRandom.current().nextInt(65, 90 + 1);
        String letter = Character.toString((char)randomLetter);
        player.addPiece(letter);
    }

    private void removePiece() {
        List<Piece> selectedItems = piecesList.getSelectedValuesList();
        player.removePiece(selectedItems);
    }

    private void playPiece() {
        List<Piece> selectedItems = piecesList.getSelectedValuesList();
        player.removePiece(selectedItems);
    }

    private void newPlayer(Player player){
        for(int i = 0; i < 7; i++ ){
            int randomLetter = ThreadLocalRandom.current().nextInt(65, 90 + 1);
            String letter = Character.toString((char)randomLetter);
            player.addPiece(letter);
        }

    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        new Board();
    }
}
