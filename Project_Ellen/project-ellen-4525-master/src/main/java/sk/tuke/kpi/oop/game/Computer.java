package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private boolean powered;
    private Animation offComputer = new Animation("sprites/computer.png",80,48,0, Animation.PlayMode.ONCE);
    private Animation onComputer = new Animation("sprites/computer.png",80,48,0.7f, Animation.PlayMode.LOOP);

    public Computer(){
        powered = false;
        setAnimation(offComputer);
    }

    private void updateAnimation(){
        if (powered) {
            setAnimation(onComputer);
        }else {
            setAnimation(offComputer);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new Loop<>(new Invoke<>(this::updateAnimation)).scheduleFor(this);

    }

    public int add(int x, int y){
        if (powered) {
            return x + y;
        }else {
            return 0;
        }

    }

    public int sub(int x, int y){
        if (powered) {
            return x - y;
        }else {
            return 0;
        }

    }

    public float sub(float x, float y){
        if (powered) {
            return x - y;
        }else {
            return 0;
        }

    }



    public float add(float x, float y){
        if (powered) {
            return x + y;
        }else {
            return 0;
        }
    }

    public float sub(int x, float y){
        if (powered) {
            return x - y;
        }else {
            return 0;
        }
    }

    public float sub(float x, int y){
        if (powered) {
            return x - y;
        }else {
            return 0;
        }
    }


    @Override
    public void setPowered(boolean elecFlow) {
        powered = elecFlow;
    }
}

