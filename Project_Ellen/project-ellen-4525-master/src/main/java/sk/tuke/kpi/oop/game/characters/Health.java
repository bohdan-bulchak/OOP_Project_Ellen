package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private List <ExhaustionEffect> effects;
    private int maxHealth;
    private int curHealth;

    public Health(int actHealth, int maxHealth) {
        this.maxHealth = maxHealth;
        this.curHealth = actHealth;
        effects = new ArrayList<>();
    }

    public Health(int actHealth) {
        this.curHealth = actHealth;
        //maxHealth = this.curHealth;
        this.maxHealth = actHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurHealth(int curHealth) {
        this.curHealth = curHealth;
    }

    public int getValue() {
        return curHealth;
    }

    public void refill(int amount) {
        if (maxHealth > amount + getValue())
            curHealth += amount;
        else
            restore();
    }


    public void drain (int amount) {
        if (curHealth != 0) {
            if (curHealth > amount) {
                curHealth -= amount;
            }
            else{
                exhaust();
            }
        }
    }

    public void restore() {
        curHealth = maxHealth;
    }

    public void exhaust() {
        if (curHealth != 0) {
            curHealth = 0;
            if (effects != null) {
                effects.forEach(ExhaustionEffect::apply);
            }
        }
    }


    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }


    public void onExhaustion(ExhaustionEffect effect) {
        if (effects != null) {
            effects.add(effect);
        }
    }




}
