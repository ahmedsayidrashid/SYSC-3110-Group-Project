import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameView extends JFrame {
    private JLabel playerInfoLabel;
    private JList<Piece> pieceListDisplay;
    protected JButton playButton, removeButton, passButton;
    private JButton[][] boardGrid;
    private JTextField wordPlacementField;
    private GameController controller;
    private final int BOARD_SIZE = 15;
    private List<Point> selectedCells = new ArrayList<>(); // To store selected cells

    public GameView(Player player, pieceList pieces) {
        controller = new GameController(player, pieces, this);

        setTitle("Scrabble Game");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display player info
        playerInfoLabel = new JLabel(player.getName() + " - Score: " + player.getScore());
        add(playerInfoLabel, BorderLayout.NORTH);

        // Display the list of pieces in the player's hand
        pieceListDisplay = new JList<>(pieces);
        pieceListDisplay.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(pieceListDisplay);
        add(scrollPane, BorderLayout.WEST);

        // Add play, remove, and pass buttons, and word placement input
        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play Word");
        removeButton = new JButton("Remove Selected");
        passButton = new JButton("Pass Turn");
        wordPlacementField = new JTextField(20); // Input for placement notation
        buttonPanel.add(new JLabel("Place Word:"));
        buttonPanel.add(wordPlacementField);
        buttonPanel.add(playButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(passButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the board grid
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        boardGrid = new JButton[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boardGrid[row][col] = new JButton("");
                boardGrid[row][col].setFont(new Font("Arial", Font.PLAIN, 18));
                boardPanel.add(boardGrid[row][col]);

                final int r = row;
                final int c = col;
                boardGrid[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Point cell = new Point(r, c);
                        if (selectedCells.contains(cell)) {
                            selectedCells.remove(cell); // Deselect cell if already selected
                            boardGrid[r][c].setBackground(null); // Reset color
                        } else {
                            selectedCells.add(cell); // Select cell
                            boardGrid[r][c].setBackground(Color.LIGHT_GRAY); // Highlight selected cell
                        }
                    }
                });
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Attach action listeners
        playButton.addActionListener(controller);
        removeButton.addActionListener(controller);
        passButton.addActionListener(controller);

        setVisible(true);
    }

    public List<Point> getSelectedCells() {
        return new ArrayList<>(selectedCells); // Return a copy of selected cells
    }

    public void clearSelectedCells() {
        for (Point cell : selectedCells) {
            boardGrid[cell.x][cell.y].setText("");
            boardGrid[cell.x][cell.y].setBackground(null); // Reset color after clearing
        }
        selectedCells.clear();
    }

    public List<Piece> getSelectedPieces() {
        return pieceListDisplay.getSelectedValuesList();
    }

    public String getWordPlacementInput() {
        return wordPlacementField.getText();
    }

    public void updatePlayerInfo(String info) {
        playerInfoLabel.setText(info);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getLetterAtBoard(int row, int col) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            return boardGrid[row][col].getText();
        }
        return null;
    }

    public void placeLetterOnBoard(int row, int col, String letter) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            boardGrid[row][col].setText(letter);
        }
    }
}
