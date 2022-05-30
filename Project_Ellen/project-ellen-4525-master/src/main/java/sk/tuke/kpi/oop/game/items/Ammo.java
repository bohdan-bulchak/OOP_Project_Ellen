package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.Objects;

public class Ammo extends AbstractActor implements Usable<Armed> {
    private Animation ammoAnimation = new Animation("sprites/ammo.png", 16, 16);

    public Ammo() {
        setAnimation(ammoAnimation);
    }

    @Override
    public void useWith(Armed armed) {
        if (armed != null && armed.getFirearm().getAmmo() < armed.getFirearm().getMaxAmmo()) {
            armed.getFirearm().reload(50);
            Objects.requireNonNull(getScene()).removeActor(this);
        }
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }


}

