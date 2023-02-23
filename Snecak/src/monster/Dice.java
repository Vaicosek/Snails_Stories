package monster;

import java.util.Random;

public class Dice {
    static Random random = new Random();
    int roll = random.nextInt(21 + 1);

    //int modifier = roll * GroupLevel;
    static int getNextRoll() {
        return random.nextInt(20) + 1;
    }

    static int getNextNumber(int min, int max) {
        return (int) Math.round(Math.random() * (max - min + 1) + min);
    }
}
