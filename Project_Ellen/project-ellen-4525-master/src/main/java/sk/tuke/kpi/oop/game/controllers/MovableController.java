package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Set<Input.Key> keys;
    private Movable actor;
    private Move<Movable> move = null;
    private Disposable disposable;
    private Input.Key key_1 = null;
    private Input.Key key_2 = null;


    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.LEFT, Direction.WEST)

        );

    public MovableController(Movable actor) {
        this.actor = actor;
        keys = new HashSet<>();
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            keys.add(key);

            if (key_1 == null) {
                key_1 = key;
            }
            else if (key_2 == null) {
                key_2 = key;
            }
            Direction actual_direction = null;
            int count = 0;

            for (Input.Key act_key : keys) {
                if (count == 0) {
                    actual_direction = keyDirectionMap.get(act_key);
                }
                if (count == 1) {
                    actual_direction = actual_direction.combine(keyDirectionMap.get(act_key));
                }
                count++;
            }
            if (move != null) {
                move.stop();
                disposable.dispose();
                move = null;
            }
            if (actual_direction != null)  {
                move = new Move<>(actual_direction,Float.MAX_VALUE);
                disposable = move.scheduleFor(actor);
            }
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            keys.remove(key);

            if (key == key_1) {
                key_1 = null;
            }
            else if (key == key_2) {
                key_2 = null;
            }
            Direction actual_direction = null;
            int count = 0;

            for (Input.Key act_key : keys) {
                if (count == 0) {
                    actual_direction = keyDirectionMap.get(act_key);
                }
                if (count == 1) {
                    actual_direction = actual_direction.combine(keyDirectionMap.get(act_key));
                }
                count++;
            }
            if (move != null) {
                move.stop();
                disposable.dispose();
                move = null;
            }
            if (actual_direction != null) {
                move = new Move<>(actual_direction,Float.MAX_VALUE);
                disposable = move.scheduleFor(actor);
            }
        }
    }


}
