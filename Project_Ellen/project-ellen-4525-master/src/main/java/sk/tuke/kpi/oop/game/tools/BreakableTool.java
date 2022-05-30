package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.items.Usable;


public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {

    private int uses;

    public BreakableTool(int uses) {
        this.uses = uses;
      //  setAnimation(hammerAnimation);
    }

    /*public void use(A actor) {
        uses--;
        if (uses == 0) {
            getScene().removeActor(this);
        }
    }*/

    @Override
    public void useWith(A actor) {
        uses--;
        if (uses == 0 && getScene() != null) {
                getScene().removeActor(this);
        }
    }

    public int getRemainingUses() {
        return uses;
    }

    public void setRemainingUses(int numOfUses) {
        uses = numOfUses;
    }
}
