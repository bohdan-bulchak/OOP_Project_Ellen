package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int maxAmmo;
    private int curAmmo;

    public Firearm(int actAmmo, int maxAmmo) {
        this.maxAmmo = maxAmmo;
        this.curAmmo = actAmmo;
    }

    public Firearm(int actAmmo) {
        this.curAmmo = actAmmo;
        this.maxAmmo = actAmmo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getAmmo() {
        return curAmmo;
    }

    public void reload(int newAmmo) {
        if (maxAmmo > newAmmo + getAmmo()) {
            curAmmo += newAmmo;
        } else {
            curAmmo = maxAmmo;
        }
    }

    protected abstract Fireable createBullet();

    public Fireable fire() {
        if (curAmmo == 0) {
            return null;
        }else {
            curAmmo--;
            return createBullet();
        }
    }







}
