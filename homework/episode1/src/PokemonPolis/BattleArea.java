package PokemonPolis;

import PokemonPolis.collectionPokemons.PokemonA;
import PokemonPolis.pokemonBuilder.DirectorBuilder;
import PokemonPolis.pokemonBuilder.PokemonBuilderA;
import PokemonPolis.trainer.PokemonAutoTrainer;
import PokemonPolis.trainer.PokemonTrainer;
import java.io.BufferedInputStream;

/**
 * Created by iters on 11/11/16.
 */
public class BattleArea {
    public static void main(String[] args) {
        DirectorBuilder director = new DirectorBuilder();
        director.setBuilder(new PokemonBuilderA());

        PokemonA picachu = (PokemonA) director.buildPokemon();
        PokemonA picachuBot = (PokemonA) director.buildPokemon();

        PokemonTrainer trainerPicachu = new PokemonTrainer((BufferedInputStream) System.in,
                picachu, picachuBot);
        PokemonTrainer trainerPicachuBot = new PokemonAutoTrainer((BufferedInputStream) System.in,
                picachuBot, picachu);

        while (picachu.getCurrentHealth() > 0 && picachuBot.getCurrentHealth() > 0) {
            printCmd();
            System.out.println(trainerPicachu.battleCmd());

            if (picachuBot.getCurrentHealth() < 0) {
                break;
            }
            System.out.println(trainerPicachuBot.battleCmd());
        }

        if (picachu.getCurrentHealth() > picachuBot.getCurrentHealth()) {
            System.out.println("YOU WINS!!!!");
        } else {
            System.out.println("BOT WINS!!!!");
        }
    }

    private static void printCmd() {
        System.out.println("\nEnter a number:");
        System.out.println("1 : hit by right hand");
        System.out.println("2 : hit by left hand");
        System.out.println("3 : hit by left foot");
        System.out.println("4 : hit by right foot");
        System.out.println("5 : defend");
    }
}