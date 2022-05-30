package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;


public class Move<A extends Movable> implements Action<A> {
    //private Movable movableActor;
    private A actor;
    private Direction direction;
    private float duration;
    private boolean isDone;
    private int execute_num;

    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
        isDone = false;
        execute_num = 0;
    }

    public Move(Direction direction) {
        this.direction = direction;
        isDone = false;
        execute_num = 0;
    }


    @Override
    public @Nullable A getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable A actor) {
         this.actor = actor;
    }

    @Override
    public void reset() {
        actor.stoppedMoving();
        duration = 0;
        execute_num = 0;
        isDone = false;

    }

    @Override
    public boolean isDone() {
        return isDone;
    }



    @Override
    public void execute(float deltaTime) {
        if (getActor() != null) {
            duration -= deltaTime;

            if (execute_num == 0) {
                actor.startedMoving(direction);
                execute_num ++;
            }

            if (duration > 0){
                int oldPosX = actor.getPosX();
                int oldPosY = actor.getPosY();

                int stepX = direction.getDx() * actor.getSpeed();
                int stepY = direction.getDy() * actor.getSpeed();

                int newPosX = oldPosX + stepX;
                int newPosY = oldPosY + stepY;

                actor.setPosition(newPosX, newPosY);
            } else {
                if (actor != null) {
                    stop();
                }

            }


        }
    }

    public void stop() {
        if (actor != null) {
            actor.stoppedMoving();
            isDone = true;
        }
    }


}
