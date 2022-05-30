package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature;

    private int damage;
    private boolean on = false;
    private Animation offAnimation = new Animation("sprites/reactor.png",80,80);
    private Animation normalAnimation = new Animation("sprites/reactor_on.png",80,80,0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation overheatedAnimation = new Animation("sprites/reactor_hot.png",80,80,0.1f - (float)damage/(float)1000, Animation.PlayMode.LOOP);
    private Animation brokenAnimation = new Animation("sprites/reactor_broken.png",80,80,0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation extinguishedAnimation = new Animation("sprites/reactor_extinguished.png");


    private Set<EnergyConsumer> devices = new HashSet<EnergyConsumer>();

    //alt + insert
    //ctr + Q   ctrl + space

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        PerpetualReactorHeating perpetualReactorHeating = new PerpetualReactorHeating(1);
        // 1. way:
        perpetualReactorHeating.scheduleFor(this);
        // 2.way:
        //getScene().scheduleAction(perpetualReactorHeating, this);

    }

    public Reactor() {
        temperature = 0;
        damage = 0;
        setAnimation(offAnimation);



    }

    public int getDamage() {
        return damage;
    }

    public int getTemperature() {
        return temperature;
    }

    public void increaseTemperature(int increment) {
        if (increment > 0 && on) {
            if (damage < 33) {
//                if (increment < (6000 - temperature)) {
                temperature += increment;
//                }else {
//                    temperature += (6000 - temperature);
//                }
            } else if (damage < 66) {
//                if (increment * 1.5 < (6000 - temperature)) {
                temperature += (int) (increment * 1.5);
//                }else {
//                    temperature += (6000 - temperature);
//                }

            } else if (damage < 100) {
//                if ((increment * 2) < (6000 - temperature)) {
                temperature += increment * 2;
//                } else {
//                    temperature += (6000 - temperature);
//                }
            }
        }
        damageLevel();

        updateAnimation();
    }

    public void decreaseTemperature(int decrement) {
        if (decrement > 0 && on) {
            if (damage >= 50 && damage < 100) {
                temperature -= (int) (decrement * 0.5);

            }else if (damage < 50){
                temperature -= decrement;
            }
        }
        // damageLevel();
        updateAnimation();
    }

    public  void damageLevel() {
        if (temperature <= 2000) {
            damage = 0;
        }else if (temperature < 6000) {
            damage = (int)((temperature - 2000) * 0.025);
        }else {
            damage = 100;
            if (devices != null) {
                for (EnergyConsumer device : devices) {
                    device.setPowered(false);
                }
            }
        }
    }

    private void updateAnimation() {
        if (on) {

            if (temperature >= 6000) {
                setAnimation(brokenAnimation);
                on = false;
            }else if (temperature > 4000) {
                setAnimation(overheatedAnimation);
            }else {
                setAnimation(normalAnimation);
            }
        }else if (damage != 100) {
            setAnimation(offAnimation);
        }
    }


    @Override
    public boolean repair() {

        if (damage != 100 && damage > 0) {
            if (damage <= 50){
                temperature = 2000 + ((damage - 50) * 40);
                damage = 0;
            }else {
                damage -= 50;
                temperature = ((damage * 4000) / 100) + 2000;
            }

            //breakableTool.useWith(this);
            updateAnimation();
            return true;
        }
        return false;

    }

    @Override
    public void turnOn() {
        if (damage != 100) {
            on = true;
            if (devices != null) {
                for (EnergyConsumer device : devices) {
                    device.setPowered(true);
                }
            }
            updateAnimation();

        }
    }

    @Override
    public void turnOff() {
        on = false;

        if (devices != null) {
            for (EnergyConsumer device : devices) {
                device.setPowered(false);
            }
        }
        updateAnimation();
    }



    @Override
    public boolean isOn() {
        return on;
    }

    public void addDevice(EnergyConsumer device) {
        devices.add(device);
        device.setPowered(isOn());
    }

    public void removeDevice(EnergyConsumer device) {
        devices.remove(device);
        device.setPowered(false);
    }


    public Set<EnergyConsumer> getDevices() {
        return devices;
    }

    public boolean extinguish() {
        if (getAnimation() == brokenAnimation/* && fireExtinguisher.getRemainingUses() > 0*/) {
            temperature = 4000;
            setAnimation(extinguishedAnimation);
            return true;
        }
        return false;
    }

}

