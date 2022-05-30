package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;


public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    private Animation playerAnimation = new Animation("sprites/player.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation deadPlayerAnimation = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
    public static final Topic<Ripley> RIPLEY_DIED  = Topic.create("Ripley is dead", Ripley.class);
    private int energy;
    private int ammo;
    private Backpack backpack;
    private Health health;
    private Firearm gun;

    public Ripley() {
        setAnimation(playerAnimation);
        setEnergy(50);
        setAmmo(400);
        health = new Health(100, 100);
        backpack = new Backpack("Ripley's backpack",10);

        health.onExhaustion( () -> {
                setAnimation(deadPlayerAnimation);
                Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
            }
        );
        gun = new Gun(100,200);
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction) {

        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();

    }

    @Override
    public void stoppedMoving() {
        getAnimation().stop();

    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
    this.energy = energy;
    }

    public int getAmmo() { return ammo; }

    public void setAmmo(int ammo) { this.ammo = ammo; }


    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }


    @Override
    public Firearm getFirearm() {
        return gun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        gun = weapon;
    }
}
