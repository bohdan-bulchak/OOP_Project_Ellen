package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;





public class DefectiveLight extends Light implements Repairable {
    private double random;
    private boolean repaired;
   /* private Disposable a;
    private Actor actor;
*/



    public DefectiveLight() {
        super();
        repaired = false;
    }



    public void lightBlink() {

        if (this.isPowered()) {
            if (!repaired) {
                random = (int)(5 * Math.random());
                if (random == 1.0){
                    this.toggle();
                }
                new Invoke<>(this::lightBlink).scheduleFor(this);
            }else {

                //loop().dispose();
                new ActionSequence<>(
                    new Wait<>(10),
                    new Invoke<>(this::polomk),
                    new Invoke<>(this::lightBlink)

                ).scheduleFor(this);
            }
        }
    }


//    public Disposable loop(){
//        Disposable a = new Loop<>(new Invoke<>(this::lightBlink)).scheduleFor(this);
//        return a;
//   }





    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Invoke<>(this::lightBlink).scheduleFor(this);
    }

    private void polomk(){

        repaired = false;
    }

    @Override
    public boolean repair() {
        if(repaired){
            return false;
        }else{
            repaired = true;
            return true;
        }
    }

    public boolean isRepaired(){
        return repaired;
    }
}

