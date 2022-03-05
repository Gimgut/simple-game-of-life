public class Point {

    private int widthPos;
    private int heightPos;

    public Point() {
    }

    public Point(int heightPos, int widthPos) {
        this.widthPos = widthPos;
        this.heightPos = heightPos;
    }

    public int getWidthPos() {
        return widthPos;
    }

    public void setWidthPos(int widthPos) {
        this.widthPos = widthPos;
    }

    public int getHeightPos() {
        return heightPos;
    }

    public void setHeightPos(int heightPos) {
        this.heightPos = heightPos;
    }
}
