package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;
import sk.tuke.kpi.oop.game.weapons.Firearm;

import java.util.Objects;

public class Fire<A extends Armed> extends AbstractAction<A> {

    public Fire(){
    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() != null && !isDone()) {
            Firearm gun = getActor().getFirearm();
            Fireable fireable = gun.fire();

            float actRotation = getActor().getAnimation().getRotation();
            Direction actDirection = Direction.fromAngle(actRotation);

//            int firePosX = actDirection.getDx();
//            int firePosY = actDirection.getDy();

//            int actPosX = getActor().getPosX();
//            int actPosY = getActor().getPosY();

//            int posX = firePosX + actPosX;
//            int posY = firePosY + actPosY;

            int actPosX = getActor().getPosX();
            int actPosY = getActor().getPosY();

            int firePosX = actDirection.getDx();
            int firePosY = actDirection.getDy();

            int posX = actPosX + firePosX * 24;
            int posY = actPosY + firePosY * 24;

            if (fireable != null) {
                Objects.requireNonNull(getActor().getScene()).addActor(fireable, posX + 8, posY + 8);
                fireable.startedMoving(actDirection);

                new Move<Fireable>(actDirection, Float.MAX_VALUE).scheduleFor(fireable);
            }

        }
        setDone(true);
    }
}
