package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;

import java.util.List;
import java.util.Objects;

public class Bullet extends AbstractActor implements Fireable {
    private Animation bulletAnimation = new Animation("sprites/bullet.png", 16, 16);
    private int damage = 20;
    private int speed;

    public Bullet() {
        setAnimation(bulletAnimation);
        speed = 4;

    }

    @Override
    public void startedMoving(Direction direction) {
        this.getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public void stoppedMoving() {

    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void collidedWithWall() {
        if (getScene() != null) {
            getScene().removeActor(this);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(this::fire)
        ).scheduleFor(this);
    }

    public void fire() {
        List<Actor> actorList;
        actorList = Objects.requireNonNull(getScene()).getActors();
        for(Actor actor : actorList) {
            if ((actor instanceof Alive) && this.intersects(actor)) {
                ((Alive) actor).getHealth().drain(damage);
                collidedWithWall();
            }
        }
    }
}
