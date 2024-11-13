import javax.swing.DefaultListModel;
import java.util.List;

public class pieceList extends DefaultListModel<Piece> {
    public pieceList() {
        super();
    }

    public void addPiece(Piece newPiece) {
        addElement(newPiece);
    }

    public boolean removePiece(List<Piece> pieceList) {
        if (pieceList == null || pieceList.isEmpty()) {
            boolean removedAny = false;
            return removedAny;
        }
        boolean removedAny = false;

        for (Piece piece : pieceList) {
            if (contains(piece)) {
                removeElement(piece);
                removedAny = true;
            }
        }

        return removedAny; // Return whether any piece was removed
    }
    }

