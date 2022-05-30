package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;

public class Main {
    public static void main(String[] args) {

        // nastavenie okna hry: nazov okna a jeho rozmery
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        // vytvorenie instancie hernej aplikacie
        // pouzijeme implementaciu rozhrania `Game` triedou `GameApplication`
        Game game = new GameApplication(windowSetup, new LwjglBackend());  // v pripade Mac OS bude druhy parameter "new Lwjgl2Backend()"

        // vytvorenie sceny pre hru
        // pouzijeme implementaciu rozhrania `Scene` triedou `World`
        //Scene scene = new World("world","maps/mission-impossible.tmx");
        Scene scene = new World("world","maps/mission-impossible.tmx",new MissionImpossible.Factory());
//        FirstSteps firstSteps = new FirstSteps();
//        scene.addListener(firstSteps);

        MissionImpossible escapeRoom = new MissionImpossible();
        scene.addListener(escapeRoom);

        // pridanie sceny do hry
        game.addScene(scene);

        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);

        // spustenie hry
        game.start();



    }
}

