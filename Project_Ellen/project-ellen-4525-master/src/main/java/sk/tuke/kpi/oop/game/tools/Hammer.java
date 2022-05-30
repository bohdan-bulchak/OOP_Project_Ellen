package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Hammer extends BreakableTool<Reactor> implements Collectible {
    private Animation hammerAnimation = new Animation("sprites/hammer.png");


    public Hammer() {
        super(1);
        setAnimation(hammerAnimation);
    }


    @Override
    public void useWith(Reactor reactor) {
        if (this.getRemainingUses() > 0 && reactor != null && reactor.getDamage() != 0 ) {
            reactor.repair();
            super.useWith(reactor);
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return null;
    }
}
