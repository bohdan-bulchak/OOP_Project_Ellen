package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;
import sk.tuke.kpi.oop.game.tools.Wrench;

public class FirstSteps extends Scenario {
    private Ripley ripley = new Ripley();
    private Energy energy = new Energy();
    private Ammo ammo = new Ammo();

    private Hammer hammer = new Hammer();
    private Hammer hammer1 = new Hammer();
    private Hammer hammer2= new Hammer();
    private Hammer hammer3= new Hammer();
    private Hammer hammer4= new Hammer();
    private Hammer hammer5 = new Hammer();
    private FireExtinguisher fireExtinguisher = new FireExtinguisher();
    private FireExtinguisher fireExtinguisher1 = new FireExtinguisher();
    private FireExtinguisher fireExtinguisher2 = new FireExtinguisher();
    private FireExtinguisher fireExtinguisher3 = new FireExtinguisher();
    private FireExtinguisher fireExtinguisher4 = new FireExtinguisher();
    private  Wrench wrench = new Wrench();


    @Override
    public void setupPlay(@NotNull Scene scene) {
       // Ripley ripley = new Ripley("Ellen");
        //Ripley ripley2 = new Ripley("Ellen");

        scene.addActor(ripley, 0, 0);
        scene.addActor(energy, -50, -50);
        scene.addActor(ammo, -100, -100);

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        new When<>(
            () -> ripley.intersects(energy),
            new Invoke<>(() -> energy.useWith(ripley))
        ).scheduleFor(ripley);


        new When<>(
            () -> ripley.intersects(ammo),
            new Invoke<>(() -> ammo.useWith(ripley))
        ).scheduleFor(ripley);

        scene.addActor(hammer, 100, -50);
        scene.addActor(hammer1, 200, -50);
        scene.addActor(hammer2, 250, -50);
        scene.addActor(hammer3, 150, -50);
        scene.addActor(hammer4, 50, -50);
        scene.addActor(hammer5, 50, 50);
        scene.addActor(fireExtinguisher, 120, 40);
        scene.addActor(fireExtinguisher1, 130, 40);
        scene.addActor(fireExtinguisher2, 140, 40);
        scene.addActor(fireExtinguisher3, 160, 40);
        scene.addActor(fireExtinguisher4, 100, 40);
        scene.addActor(wrench, -150, 200);

        ripley.getBackpack().add(hammer);
        ripley.getBackpack().add(fireExtinguisher);
        ripley.getBackpack().add(wrench);

        scene.getGame().pushActorContainer(ripley.getBackpack());
        ripley.getBackpack().shift();


        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);

        ShooterController shooterController = new ShooterController(ripley);
        scene.getInput().registerListener(shooterController);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("Energy: " +ripley.getEnergy(), 120, yTextPos);
        scene.getGame().getOverlay().drawText("Your Ammo " + ripley.getFirearm().getAmmo(), 280, yTextPos);
        scene.getGame().getOverlay().drawText("Maximum Ammo " + ripley.getFirearm().getMaxAmmo(), 450, yTextPos);


        //scene.getGame().getOverlay().drawText("cau",120,20);
    }



}
