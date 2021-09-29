public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    public Frog() { position = 5; }
    // прыжок лягушки
    public boolean jump(int steps) {
        int newPosition = position + steps;
        if (newPosition > MAX_POSITION || newPosition < MIN_POSITION) return false;
        position = newPosition;
        return true;
    }



}