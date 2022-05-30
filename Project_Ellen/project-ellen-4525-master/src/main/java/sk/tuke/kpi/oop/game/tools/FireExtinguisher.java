package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.items.Collectible;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible {
    private Animation fireExAnimation = new Animation("sprites/extinguisher.png");


    public FireExtinguisher() {
        super(1);
        setAnimation(fireExAnimation);
    }

    @Override
    public void useWith(Reactor reactor) {
        if (this.getRemainingUses() > 0 && reactor != null && reactor.getTemperature() == 6000) {
            reactor.extinguish();
            super.useWith(reactor);
        }

    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return null;
    }

    /*public void use() {
        uses--;
        if (uses == 0) {
            getScene().removeActor(this);
        }
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int numOfUses) {
        uses = numOfUses;

    }*/
}
