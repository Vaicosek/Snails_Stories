package game.players;

import game.itemshandling.ItemBase;

import java.util.List;

public interface PlayerTemplate {
    String getName();

    void setName(String name);

    int getHealth();

    void setHealth(int health);

    int getAttack();

    void setAttack(int attack);


    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

}
