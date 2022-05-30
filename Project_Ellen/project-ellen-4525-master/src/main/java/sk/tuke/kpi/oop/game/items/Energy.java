package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;


import java.util.Objects;

public class Energy extends AbstractActor implements Usable<Alive> {
    private Animation energyAnimation = new Animation("sprites/energy.png", 16, 16);


    public Energy() {
        setAnimation(energyAnimation);
    }

    @Override
    public void useWith(Alive alive) {
        if (alive != null && alive.getHealth().getValue() != 100) {
            alive.getHealth().restore();
            Objects.requireNonNull(getScene()).removeActor(this);
        }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}


