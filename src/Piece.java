import java.util.HashMap;
import java.util.Map;
public class Piece {
    private String letter;
    private int value;
    private static final Map<String, Integer> letterValues = new HashMap<>();

    static{
        letterValues.put(String.valueOf('A'), 1);
        letterValues.put(String.valueOf('B'), 3);
        letterValues.put(String.valueOf('C'), 3);
        letterValues.put(String.valueOf('D'), 2);
        letterValues.put(String.valueOf('E'), 1);
        letterValues.put(String.valueOf('F'), 4);
        letterValues.put(String.valueOf('G'), 2);
        letterValues.put(String.valueOf('H'), 4);
        letterValues.put(String.valueOf('I'), 1);
        letterValues.put(String.valueOf('J'), 8);
        letterValues.put(String.valueOf('K'), 5);
        letterValues.put(String.valueOf('L'), 1);
        letterValues.put(String.valueOf('M'), 3);
        letterValues.put(String.valueOf('N'), 1);
        letterValues.put(String.valueOf('O'), 1);
        letterValues.put(String.valueOf('P'), 3);
        letterValues.put(String.valueOf('Q'), 10);
        letterValues.put(String.valueOf('R'), 1);
        letterValues.put(String.valueOf('S'), 1);
        letterValues.put(String.valueOf('T'), 1);
        letterValues.put(String.valueOf('U'), 1);
        letterValues.put(String.valueOf('V'), 4);
        letterValues.put(String.valueOf('W'), 4);
        letterValues.put(String.valueOf('X'), 8);
        letterValues.put(String.valueOf('Y'), 4);
        letterValues.put(String.valueOf('Z'), 10);
    }
    public Piece(String letter) {
        this.letter = letter;
        this.value = letterValues.get(letter);
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
