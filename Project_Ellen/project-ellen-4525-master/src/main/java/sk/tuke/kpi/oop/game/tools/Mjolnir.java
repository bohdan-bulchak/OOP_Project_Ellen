package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Mjolnir extends Hammer {

    private Animation mjolnirAnimation = new Animation("sprites/hammer.png");

    public Mjolnir() {
        super();
        setRemainingUses(4);
        setAnimation(mjolnirAnimation);
    }

    @Override
    public void useWith(Reactor reactor) {
        if (this.getRemainingUses() > 0 && reactor != null) {
            reactor.repair();
            super.useWith(reactor);
        }
    }
}
