package engine.windows.common;

public class Position {
    public float x;
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distantTo(Position p) {
        return (float) Math.sqrt(Math.pow((this.x-p.x), 2) + Math.pow((this.y-p.y), 2));
    }
}
