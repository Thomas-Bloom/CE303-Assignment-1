package Utilities;

public class Coordinate {
    private int xPos;
    private int yPos;

    public Coordinate(int x, int y){
        xPos = x;
        yPos = y;
    }

    public int getxPos(){
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int x) {
        this.xPos = x;
    }

    public void setyPos(int y) {
        this.yPos = y;
    }
}
