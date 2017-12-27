package com.ahid.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cccc on 12/27/2017.
 */
public class GameObject {

    public boolean isGameEnd() {
        boolean isGameEnd = false;
        isGameEnd = isGameWinnerByTrue();
        if (isGameEnd)
            return isGameEnd;
        isGameEnd = isGameWinnerByFalse();
        return isGameEnd;
    }

    public boolean isGameWinnerByTrue() {
        return isGameWinAndEnd(Boolean.TRUE);
    }

    public boolean isGameWinnerByFalse() {
        return isGameWinAndEnd(Boolean.FALSE);
    }

    private boolean isGameWinAndEnd(Boolean b) {

        // vertical
        for (Boolean[] rows : this.getContent()) {
            int cnt = 0;
            for (Boolean value : rows) {
                if (b.equals(value)) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                if (cnt == this.sizeForFinish)
                    return true;
            }
        }

        // horizontal
        Boolean[][] reversed = GameObject.reverse(this.getContent());
        for (Boolean[] rows : reversed) {
            int cnt = 0;
            for (Boolean val : rows) {
                if (b.equals(val)) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                if (cnt == this.sizeForFinish)
                    return true;
            }
        }

        // left top to right bottom diagonal
        for(int i = 0; i < this.getContent().length; i++) {
            for(int j = 0; j < this.getContent()[i].length; j++) {
                int cnt = 0;
                int ii = i;
                int jj = j;
                while (ii < this.size && jj < this.size) {
                    if (b.equals(this.getContent()[ii][jj])) {
                        cnt++;
                    } else {
                        cnt = 0;
                    }
                    if (cnt == this.sizeForFinish)
                        return true;
                    ii++;
                    jj++;
                }
            }
        }

        // right top to left bottom diagonal
        for(int i = 0; i < this.getContent().length; i++) {
            for(int j = this.getContent().length - 1; j >=0 ; j--) {
                int cnt = 0;
                int ii = i;
                int jj = j;
                while (ii < this.size && jj >= 0) {
                    if (b.equals(this.getContent()[ii][jj])) {
                        cnt++;
                    } else {
                        cnt = 0;
                    }
                    if (cnt == this.sizeForFinish)
                        return true;
                    ii++;
                    jj--;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Boolean[] rows : this.getContent()) {
            for (Boolean value : rows) {
                sb.append(Boolean.TRUE.equals(value) ? "X " : (Boolean.FALSE.equals(value) ? "O " : "  "));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static GameObject randomGameObject(int size) throws InvalidPointException {
        GameObject go = new GameObject(size);
        Random random = new Random();
        for (int i = 0; i < size*size; i++) {
            int xRandom = random.nextInt(size);
            int yRandom = random.nextInt(size);
            boolean valueRandom = random.nextBoolean();
            go = go.markAndGet(new Point(xRandom, yRandom), valueRandom);
        }
        return go;
    }

    public List<Point> getNotMarkedPoints() {
        List<Point> notMarked = new ArrayList<Point>();
        for (int i = 0; i < this.getContent().length; i++) {
            for (int j = 0; j < this.getContent()[i].length; j++) {
                if (this.getContent()[i][j] == null) {
                    notMarked.add(new Point(i, j));
                }
            }
        }
        return notMarked;
    }

    public GameObject markAndGet(Point point, Boolean value) throws InvalidPointException {
        this.checkBounds(point);
        this.getContent()[point.getX()][point.getY()] = value;
        return this;
    }


    public GameObject markAndGet(List<Point> points, Boolean value) {
        points.forEach(point -> {
            try {
                this.checkBounds(point);
                this.getContent()[point.getX()][point.getY()] = value;
            } catch (InvalidPointException ipe) {
            }
        });
        return this;
    }

    public Boolean getValue(Point point) throws InvalidPointException {
        this.checkBounds(point);
        return this.getContent()[point.getX()][point.getY()];
    }

    private void checkBounds(Point point) throws InvalidPointException {
        if (point.getX() >= this.size || point.getY() >= this.size)
            throw new InvalidPointException();

    }

    public GameObject(int size) {
        this.size = size;
        this.sizeForFinish = size / 2;
        this.content = new Boolean[size][size];
    }

    public GameObject(int size, int sizeForFinish) {
        this.size = size;
        this.sizeForFinish = sizeForFinish;
        this.content = new Boolean[size][size];
    }

    private int size;
    private  int sizeForFinish;
    private Boolean[][] content;

    private Boolean[][] getContent() {
        return content;
    }

    private void setContent(Boolean[][] content) {
        this.content = content;
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static Boolean[][] reverse(Boolean[][] arr){

        int rows = arr.length;
        int cols = arr[0].length;
        Boolean array[][]=new Boolean[rows][cols];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                array[j][i] = arr[i][j];
            }
        }

        return array;
    }
}
