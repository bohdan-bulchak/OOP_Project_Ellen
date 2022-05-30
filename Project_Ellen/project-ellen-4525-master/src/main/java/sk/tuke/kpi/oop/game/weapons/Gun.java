package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{

    public Gun(int actAmmo, int maxAmmo) {
        super(actAmmo, maxAmmo);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
