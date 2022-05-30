package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;

import java.util.Objects;

public class MissionImpossible implements SceneListener {
    //private Ripley ripley = new Ripley();
//    private Energy energy = new Energy();
//    private Ammo ammo = new Ammo();

    public static class Factory implements ActorFactory {


        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if (name != null) {
                if (Objects.equals(name, "ellen")) {
                    return new Ripley();
                } else if (Objects.equals(name, "energy")) {
                    return new Energy();
                } else if (Objects.equals(name, "ammo")) {
                    return new Ammo();
                } else if (Objects.equals(name, "alien")) {
                    return new Alien();
                } else if (Objects.equals(name, "alien mother")) {
                    return new AlienMother();
                } else if (Objects.equals(name, "door")){
                    return new Door();
                }
                else {
                    return null;
                }

            } else {
                return null;
            }
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Alien alien  = new Alien();
        scene.addActor(alien, 300, 100);

        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        scene.follow(ellen);


        MovableController movableController = new MovableController(ellen);
        scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ellen);
        scene.getInput().registerListener(keeperController);

        ShooterController shooterController = new ShooterController(ellen);
        scene.getInput().registerListener(shooterController);
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {

    }
//    @Override
//    public void sceneUpdating(@NotNull Scene scene) {
//        int windowHeight = scene.getGame().getWindowSetup().getHeight();
//        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
//        scene.getGame().getOverlay().drawText("Energy: " +ellen.getEnergy(), 120, yTextPos);
//        scene.getGame().getOverlay().drawText("Your Ammo " + ellen.getFirearm().getAmmo(), 280, yTextPos);
//        scene.getGame().getOverlay().drawText("Maximum Ammo " + ellen.getFirearm().getMaxAmmo(), 450, yTextPos);
//
//
//        //scene.getGame().getOverlay().drawText("cau",120,20);
//    }
}
