package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Cooler extends AbstractActor implements Switchable {

    private Animation coolerAnimation = new Animation("sprites/fan.png",32,32,0.2f);
    private boolean on;
    private Reactor reactor;

    public Cooler(Reactor reactor) {
        on = false;
        this.reactor = reactor;

        setAnimation(coolerAnimation);
        getAnimation().stop();
    }

    public void coolReactor() {
        if (on/* && reactor.getTemperature() != 0 && reactor.getDamage() != 100*/) {
            reactor.decreaseTemperature(1);
        }
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }



    @Override
    public void turnOn() {
        if (reactor != null) {
            on = true;
            getAnimation().play();
        }
    }

    @Override
    public void turnOff() {
        if (reactor != null) {
            on = false;
            getAnimation().stop();
        }
    }

    @Override
    public boolean isOn(){
        return on;
    }



}
