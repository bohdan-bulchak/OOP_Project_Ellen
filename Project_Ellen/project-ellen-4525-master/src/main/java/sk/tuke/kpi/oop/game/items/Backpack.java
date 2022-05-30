package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.*;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private List<Collectible> backpacked = new ArrayList<>();

    public Backpack(String name, int capacity){
        this.capacity = capacity;
        this.name = name;
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(backpacked);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return backpacked.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (backpacked.size() < getCapacity()) {
            backpacked.add(actor);
        }
        else {
            throw new IllegalStateException(getName()+ " is full");
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if (backpacked != null) {
            backpacked.remove(actor);
        }
    }

    @Override
    public @Nullable Collectible peek() {
        if (getSize() > 0) {
            return backpacked.get(getSize() - 1);
        }
        else {
            return null;
        }
    }

    @Override
    public void shift() {
        Collections.rotate(backpacked, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return backpacked.iterator();
    }
}
