//package sk.tuke.kpi.oop.game;
//
//import org.jetbrains.annotations.NotNull;
//import sk.tuke.kpi.gamelib.Scene;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
//import sk.tuke.kpi.gamelib.actions.When;
//import sk.tuke.kpi.gamelib.framework.Scenario;
//import sk.tuke.kpi.oop.game.tools.Hammer;
//import sk.tuke.kpi.oop.game.tools.Mjolnir;
//import sk.tuke.kpi.oop.game.tools.Wrench;
//
//import java.util.function.Supplier;
//
//public class TrainingGameplay extends Scenario {
//
//    @Override
//    public void setupPlay(@NotNull Scene scene) {
//   /*     Reactor reactor = new Reactor();
//        scene.addActor(reactor,145,95);
//        reactor.turnOn();
//       // reactor.increaseTemperature(5000);
//       // reactor.turnOff();
//
//        Mjolnir mjolnir = new Mjolnir();
//        scene.addActor(mjolnir,120,95);
//
//        Wrench wrench = new Wrench();
//        scene.addActor(wrench,110,95);
//
//        DefectiveLight defectiveLight = new DefectiveLight();
//        scene.addActor(defectiveLight,100,95);
//        //defectiveLight.switchTo();
//        reactor.addDevice(defectiveLight);
//
//        Cooler cooler = new Cooler(reactor);
//        scene.addActor(cooler,241,95);
//        new ActionSequence<>(
//
//            new Wait<>(5),
//            new Invoke<>(cooler::turnOn)
//            ).scheduleFor(cooler);
//
//        /*Hammer hammer = new Hammer();
//        scene.addActor(hammer,145,63);
//        new When<>(
//            () -> reactor.getTemperature() >= 3000,
//            new Invoke<>(() -> reactor.repairWith(hammer))
//        ).scheduleFor(reactor);
//*/
//
//    }
//}
//
