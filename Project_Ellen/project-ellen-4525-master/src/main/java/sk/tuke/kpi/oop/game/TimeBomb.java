package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class TimeBomb extends AbstractActor {
    private Animation bomb = new Animation("sprites/bomb.png",16,16);
    private Animation bomb_activated = new Animation("sprites/bomb_activated.png",16,16,0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation small_explosion = new Animation("sprites/small_explosion.png",16,16,0.1f, Animation.PlayMode.ONCE);
private boolean activated;
    private float seconds;

    public TimeBomb(float seconds) {
        setAnimation(bomb);
        activated = false;
        this.seconds = seconds;
    }

    public void activate(){
        setAnimation(bomb_activated);
        activated = true;
        float a = small_explosion.getFrameCount()*small_explosion.getFrameDuration();
        new ActionSequence<>(
            new Wait<>(seconds),
            new Invoke<>(this::setSmallExplosion),
            new Wait<>(a),
            new Invoke<>(this::remove)
        ).scheduleFor(this);

//        setAnimation(bomb_activated);
//        new Wait<>(time);
//        setAnimation(small_explosion);
    }


    public void remove(){
        Objects.requireNonNull(getScene()).removeActor(this);
    }


    public void setBombActivated(){
        setAnimation(bomb_activated);
    }

    public void setSmallExplosion(){
        setAnimation(small_explosion);
    }

    boolean isActivated(){
        return activated;
    }
}
