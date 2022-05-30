package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<A extends Actor> extends AbstractAction<A> {
    private Usable<A> usableItem;

    public Use(Usable<A> usableItem) {
        this.usableItem = usableItem;
    }


    @Override
    public void execute(float deltaTime) {
        if (!isDone()) {
            setDone(true);
            usableItem.useWith(getActor());
        }
    }

    public Disposable scheduleForIntersectingWith(Actor actor) {
        if (actor.getScene() != null) {
            Class<A> usingActorClass = usableItem.getUsingActorClass();

            for (Actor act : actor.getScene()) {
                if (usingActorClass.isInstance(act) && actor.intersects(act)) {
                    return this.scheduleFor(usingActorClass.cast(actor));
                }

            }
        }
        return null;
    }
}
