package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class AlienMother extends Alien {
    private Animation alienMotherAnimation = new Animation("sprites/mother.png",54, 112, 0.2f);

    public AlienMother() {
        setAnimation(alienMotherAnimation);

    }

}
