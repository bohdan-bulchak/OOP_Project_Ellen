package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.List;

public class Alien extends AbstractActor implements  Alive, Enemy{
    private Animation alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.f, Animation.PlayMode.LOOP_PINGPONG);
    private Health health;
    private Disposable damaging = null;

    public Alien() {
        setAnimation(alienAnimation);
        health = new Health(50);
    }

    @Override
    public Health getHealth() {
        return health;
    }

    public void damage(){
        if (getScene() != null) {
            List<Actor> actorList;
            actorList = getScene().getActors();
            for(Actor actor : actorList) {
                if (!(actor instanceof Enemy) && actor instanceof Alive && this.intersects(actor)) {
                    ((Alive) actor).getHealth().drain(1);
                    new ActionSequence<>(
                        new Invoke<>(this::stopDamage),
                        new Wait<>(1),
                        new Invoke<>(this::damage)
                    ).scheduleFor(this);
                }
            }

        }
    }

    public void stopDamage() {
        if (damaging != null) {
            damaging = null;
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        damaging = new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::damage),
                new Wait<>(0.5f)
            )
        ).scheduleFor(this);
    }
}
