import java.util.HashMap;
import java.util.Map;

public class Piece {
    private String letter;
    private int value;
    private static final Map<String, Integer> letterValues = new HashMap<>();

    static {
        letterValues.put("A", 1);
        letterValues.put("B", 3);
        letterValues.put("C", 3);
        letterValues.put("D", 2);
        letterValues.put("E", 1);
        letterValues.put("F", 4);
        letterValues.put("G", 2);
        letterValues.put("H", 4);
        letterValues.put("I", 1);
        letterValues.put("J", 8);
        letterValues.put("K", 5);
        letterValues.put("L", 1);
        letterValues.put("M", 3);
        letterValues.put("N", 1);
        letterValues.put("O", 1);
        letterValues.put("P", 3);
        letterValues.put("Q", 10);
        letterValues.put("R", 1);
        letterValues.put("S", 1);
        letterValues.put("T", 1);
        letterValues.put("U", 1);
        letterValues.put("V", 4);
        letterValues.put("W", 4);
        letterValues.put("X", 8);
        letterValues.put("Y", 4);
        letterValues.put("Z", 10);
    }

    public Piece(String letter) {
        this.letter = letter.toUpperCase();

        // Check if the letter exists in the map, and handle cases where it does not
        Integer letterValue = letterValues.get(this.letter);
        if (letterValue == null) {
            throw new IllegalArgumentException("Invalid letter: " + letter);
        }
        this.value = letterValue;
    }

    public String getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Letter: " + letter + ", Value: " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece piece = (Piece) obj;
        return letter.equals(piece.letter);
    }
}
