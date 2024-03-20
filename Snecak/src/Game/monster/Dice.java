package Game.monster;

public class Dice {
    public static int getNextNumber(int min, int max) {
        return (int) Math.round(Math.random() * (max - min + 1) + min);
    }
}
