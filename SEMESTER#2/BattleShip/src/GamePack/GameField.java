package GamePack;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameField extends Canvas {
    public static final int SIZE = 10;
    public static final int PLAYER = 3;
    public static final int ENEMY = 4;
    private Cell cells[][];


    public GameField(int mode) {
        switch (mode) {
            case PLAYER: {
                setLayoutX(95);
                setLayoutY(126);
                setWidth(240);
                setHeight(240);
                break;
            }
            case ENEMY: {
                setLayoutX(367);
                setLayoutY(126);
                setWidth(240);
                setHeight(240);
                break;
            }
        }
        initAndDraw();
    }

    public GameField () {}

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public void initAndDraw() {
        cells = new Cell[SIZE][SIZE];
        draw();
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE ; j++) {
                cells[i][j] = new Cell(j * Cell.SIZE, i * Cell.SIZE);
                cells[i][j].setCellColor(Color.WHITE);
                cells[i][j].draw(gc, false);
            }
        }
    }

    public void update() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0,0, getWidth(), getHeight());
        draw();
    }

    public void setBusyAroundCell(int i, int j, int mode) {
        for (int w = -1; w <= 1; w++) {
            for (int v = -1; v <= 1; v++) {
                if (inRange(i + w, j + v)) {
                    cells[i + w][j + v].changeBusyCount(mode);
                }
            }
        }
    }

    public void setBusyAroundShip(Ship ship, int mode) {
        for(Cell deck : ship.getDecks()) {
            setBusyAroundCell(deck.getY() / Cell.SIZE, deck.getX() / Cell.SIZE, mode);
        }
    }

    public void drawShips(ArrayList<Ship> ships) {
        for(Ship ship : ships) {
            for (Cell deck : ship.getDecks()) {
                deck.setCellColor(Color.RED);
                deck.drawShipDeck(getGraphicsContext2D(), false);
            }
        }
    }





    public boolean inRange(int i, int j) {
        return i >= 0 && i < SIZE && j >= 0 && j < SIZE;
    }
}
