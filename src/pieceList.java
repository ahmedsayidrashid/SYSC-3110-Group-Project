import javax.swing.DefaultListModel;
import java.util.List;

public class pieceList extends DefaultListModel<Piece> {
    public pieceList() {
        super();
    }

    public void addPiece(Piece newPiece) {
        addElement(newPiece);
    }

    public Piece removePiece(List<Piece> pieceList) {
        if (pieceList == null || pieceList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < getSize(); i++) {
            if (contains(pieceList.get(i))) {
                int index = pieceList.indexOf(pieceList.get(i));
                return remove(index);
            }
        }
        return null;
    }
}
