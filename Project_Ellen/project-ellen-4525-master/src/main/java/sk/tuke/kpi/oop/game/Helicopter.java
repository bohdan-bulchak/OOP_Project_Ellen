package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;

import java.util.Objects;


public class Helicopter extends AbstractActor {
    private Animation heliAnimation = new Animation("sprites/heli.png",64,64,0.1f, Animation.PlayMode.LOOP_PINGPONG);
   // private double time = System.currentTimeMillis();


    public Helicopter() {
        setAnimation(heliAnimation);
    }


    private void searchAndDestroyProcessing(){

        Actor p = Objects.requireNonNull(getScene()).getFirstActorByName("Player");
        Player player = (Player) p;

        Point pointHeli = new Point(getPosX(),getPosY());
        Point pointPlayer = new Point(player.getPosX(),player.getPosY());
        Point pointMeet = new Point((int)(pointHeli.getX() * 0.97) + (int)(pointPlayer.getX() * 0.03),
                                   (int)(pointHeli.getY() * 0.97) + (int)(pointPlayer.getY() * 0.03));
        setPosition(pointMeet.getX(),pointMeet.getY());

       // double deltaTime = System.currentTimeMillis() - time;

        //if(deltaTime > 1000){                       //helicopter would bea ble to damage only once in a second
         //   time = System.currentTimeMillis();       // if to compare currentTime and time when this "if" was realised
                                                    // ( such a delay in a second)
            if(this.intersects(player)) {

                int energy = player.getEnergy();
                energy--;
                player.setEnergy(energy);
            }
        //}


    }

    public void searchAndDestroy() {
        new Loop<>(new Invoke<>(this::searchAndDestroyProcessing)).scheduleFor(this);
    }

}
