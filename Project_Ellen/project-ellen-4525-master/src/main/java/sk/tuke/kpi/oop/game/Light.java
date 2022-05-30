package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {
    private Animation lightOnAnimation = new Animation("sprites/light_on.png");
    private Animation lightOffAnimation = new Animation("sprites/light_off.png");
    private boolean on;
    private boolean powered;

    public Light() {
        on = false;
        powered = false;
        setAnimation(lightOffAnimation);
    }

    public void toggle() {
        on = !on;
        updateAnimation();

    }

    public void setElectricityFlow(boolean electricityFlow) {
       powered = electricityFlow;
       updateAnimation();

    }

    public boolean isPowered(){
        return powered;
    }

    @Override
    public void turnOn() {
        on = true;
        updateAnimation();
    }

    @Override
    public void turnOff() {
        on = false;
        updateAnimation();
    }

    public boolean isOn(){
        return on;
    }

    private void updateAnimation() {
        if(on && powered) {
            setAnimation(lightOnAnimation);
        }else {
            setAnimation(lightOffAnimation);
        }
    }


    @Override
    public void setPowered(boolean elecFlow) {
        powered = elecFlow;
        updateAnimation();
    }
}
