package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Backpack;

public class Shift<K extends Keeper> extends AbstractAction<K> {

    public Shift() {
    }

    @Override
    public void execute(float deltaTime) {
        if (!isDone() && getActor() != null && getActor().getBackpack().peek() != null) {
            Backpack backpack = getActor().getBackpack();
            backpack.shift();
        }
        setDone(true);
    }
}
