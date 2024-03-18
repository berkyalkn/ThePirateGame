package dev.lpa;

import dev.lpa.game.GameConsole;
import dev.lpa.game.Player;
import dev.lpa.pirate.Island;
import dev.lpa.pirate.Pirate;
import dev.lpa.pirate.PirateGame;
import dev.lpa.pirate.Weapon;

public class Main {
    public static void main(String[] args) {

        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);



    }
}
