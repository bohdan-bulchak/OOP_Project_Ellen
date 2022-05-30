package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private Animation controllerAnimation = new Animation("sprites/switch.png",16,16);
    private Switchable device;


    public PowerSwitch(Switchable device){
        this.device = device;
        setAnimation(controllerAnimation);
    }

    public void toggle(){
        if (device.isOn()) {
            device.turnOff();
        }else {
            device.turnOn();
        }
        updateAnimation();
    }

    private void updateAnimation(){
        if (device.isOn()) {
            getAnimation().setTint(Color.WHITE);
        }else {
            getAnimation().setTint(Color.GRAY);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new Loop<>(new Invoke<>(this::updateAnimation)).scheduleFor(this);

    }

    public void switchOn() {
        if(device != null) {
            device.turnOn();
            updateAnimation();
        }
    }

    public void switchOff() {
        if (device != null) {
            device.turnOff();
            updateAnimation();
        }
    }

    public Switchable getDevice() {
        return device;
    }



}
