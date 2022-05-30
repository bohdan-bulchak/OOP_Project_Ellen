package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;

public class Take<K extends Keeper> extends AbstractAction<K> {
    public Take(){
    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null || getActor().getScene() == null) {
            setDone(true);
            return;
        }

        Scene scene = getActor().getScene();
        if (!isDone()) {
            List<Actor> items = getActor().getScene().getActors();

            for(Actor item : items) {
                if(item.intersects(getActor()) && item instanceof Collectible) {
                    try {
                        Backpack backpack = getActor().getBackpack();
                        backpack.add((Collectible)item);
                        scene.removeActor(item);
                        break;
                    } catch (IllegalStateException ex) {
                        scene.getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
                    }
                }
            }
        }
        setDone(true);

    }
}
