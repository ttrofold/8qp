package connector;

import tabula.ChessBoardContext;
import tabula.board.Coordinate;
import tabula.board.MoveListener;
import tabula.pieces.Chessman;
import tabula.pieces.PieceSets;

import javax.swing.*;
import java.awt.*;

public class Connector {

        private static ConnectMethod connectMethod = new ConnectMethod();

        public static JComponent connect(String s) {
        final ChessBoardContext context = new ChessBoardContext();
        context.setMoveListener(new MoveListener() {
            @Override
            public boolean acceptMove(Chessman piece, Coordinate source, Coordinate destination) {

                return true;
            }
        })
                .setPositionFEN(s)
                .setColors(Color.BLUE, Color.WHITE)
                .setPieceImageSet(PieceSets.ARIAL);
        return context.getBoardView();
    }
}
