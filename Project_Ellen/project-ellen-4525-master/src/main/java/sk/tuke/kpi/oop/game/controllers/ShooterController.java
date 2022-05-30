package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

public class ShooterController implements KeyboardListener {
    private Armed armed;

    public ShooterController(Armed armed) {
        this.armed = armed;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (armed.getFirearm() != null && armed != null && (key == Input.Key.SPACE)) {
            Fire<Armed> newFire = new Fire<>();
            newFire.setActor(armed);
            newFire.scheduleFor(armed);
        }
    }
}
