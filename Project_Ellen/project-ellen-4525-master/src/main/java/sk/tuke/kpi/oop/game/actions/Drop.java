package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<K extends Keeper> extends AbstractAction<K> {
    public Drop() {
    }


    @Override
    public void execute(float deltaTime) {
        if (getActor() != null && getActor().getScene() != null && getActor().getBackpack().peek() != null) {
            Scene scene = getActor().getScene();
            if(!isDone()) {
                Backpack backpack = getActor().getBackpack();
                Collectible item = backpack.peek();
                int itemPosX = getActor().getPosX()  + getActor().getWidth() / 2;
                int itemPosY = getActor().getPosY()  + getActor().getHeight() / 2;

                backpack.remove(item);
                scene.addActor(item, itemPosX, itemPosY);
            }
        }
        setDone(true);
    }
}
