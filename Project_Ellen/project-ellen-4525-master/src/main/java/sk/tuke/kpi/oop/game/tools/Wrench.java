package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Wrench extends BreakableTool<DefectiveLight> implements Collectible {
    private Animation wrenchAnimation = new Animation("sprites/wrench.png ",16,16);

    public Wrench() {
        super(2);
        setAnimation(wrenchAnimation);
    }

    @Override
    public void useWith(DefectiveLight defectiveLight) {
        if (this.getRemainingUses() > 0 && defectiveLight != null) {
            if (defectiveLight.repair()) {
                defectiveLight.repair();
                super.useWith(defectiveLight);
            }else {
                setRemainingUses(2);
            }
        }
    }

    @Override
    public Class<DefectiveLight> getUsingActorClass() {
        return null;
    }
}
