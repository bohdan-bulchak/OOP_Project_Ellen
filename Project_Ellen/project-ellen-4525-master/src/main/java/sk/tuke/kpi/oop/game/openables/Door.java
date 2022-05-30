package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private Animation vDoorAnimation = new Animation("sprites/vdoor.png", 4, 32);
    private Animation hDoorAnimation = new Animation("sprites/hdoor.png", 32, 4);
    private Animation vOpenedDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
    private Animation vClosedDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
    private Animation hOpenedDoorAnimation = new Animation("sprites/hdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
    private Animation hClosedDoorAnimation = new Animation("sprites/hdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
    private Animation openedDoorAnimation;
    private Animation closedDoorAnimation;


    private boolean isOpened;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    private enum  Orientation { VERTICAL, HORIZONTAL }



    public Door() {
        openedDoorAnimation = vOpenedDoorAnimation;
        closedDoorAnimation = vClosedDoorAnimation;
        setAnimation(vDoorAnimation);
        isOpened = false;
    }

    public Door(String name, Orientation orientation) {
        super(name);
        isOpened = false;
        if (orientation == Orientation.VERTICAL) {
            openedDoorAnimation = vOpenedDoorAnimation;
            closedDoorAnimation = vClosedDoorAnimation;
            setAnimation(vDoorAnimation);
        }
        else if (orientation == Orientation.HORIZONTAL) {
            openedDoorAnimation = hOpenedDoorAnimation;
            closedDoorAnimation = hClosedDoorAnimation;
            setAnimation(hDoorAnimation);
        }
    }


    @Override
    public void useWith(Actor actor) {
        if(!isOpened) {
            open();
        } else {
            close();
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        Scene scene = getScene();
        if (scene != null) {
            scene.getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
            setAnimation(openedDoorAnimation);
            getAnimation().play();
            scene.getMessageBus().publish(DOOR_OPENED, this);
            isOpened = true;
        }
    }

    @Override
    public void close() {
        Scene scene = getScene();
        if (scene != null) {
            scene.getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
            setAnimation(closedDoorAnimation);
            getAnimation().play();
            scene.getMessageBus().publish(DOOR_CLOSED, this);
            isOpened = false;
        }

    }

    @Override
    public boolean isOpen() {
        return isOpened;
    }
}
