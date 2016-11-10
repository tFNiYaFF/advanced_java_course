package PokemonPolis;

import PokemonPolis.collectionPokemons.PokemonA;
import PokemonPolis.pokemonBuilder.DirectorBuilder;
import PokemonPolis.pokemonBuilder.PokemonBuilderA;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by iters on 11/11/16.
 */
public class BattleArea {
    public static void main(String[] args) {
        DirectorBuilder director = new DirectorBuilder();
        director.setBuilder(new PokemonBuilderA());

        PokemonA picachu = (PokemonA) director.buildPokemon();
        PokemonA picachuBot = (PokemonA) director.buildPokemon();

        Scanner scan = new Scanner(System.in);
        while (picachu.getCurrentHealth() != 0 && picachuBot.getCurrentHealth() != 0) {
            printCmd();
            int cmd = scan.nextInt();
            switch (cmd) {
                case 1:
                    picachuBot.subtractHP(picachu.hitByRightHand());
                    System.out.println("after attack by right hand Bot has "
                            + picachuBot.getCurrentHealth());
                    break;
                case 2:
                    picachuBot.subtractHP(picachu.hitByLefttHand());
                    System.out.println("after attack by left hand Bot has "
                            + picachuBot.getCurrentHealth());
                    break;
                case 3:
                    picachuBot.subtractHP(picachu.hitByLeftFoot());
                    System.out.println("after attack by left foot Bot has "
                            + picachuBot.getCurrentHealth());
                    break;
                case 4:
                    picachuBot.subtractHP(picachu.hitByRightFoot());
                    System.out.println("after attack by right foot Bot has "
                            + picachuBot.getCurrentHealth());
                    break;
                case 5:
                    picachu.defend();
                    System.out.println("I defends!");
                    break;
                default:
                    continue;
            }

            Random rnd = new Random();
            cmd = rnd.nextInt(5) + 1;

            switch (cmd) {
                case 1:
                    picachu.subtractHP(picachuBot.hitByRightHand());
                    System.out.println("You was attacked by bot by right hand. You have  "
                            + picachu.getCurrentHealth() + "HP");
                    break;
                case 2:
                    picachu.subtractHP(picachuBot.hitByLefttHand());
                    System.out.println("You was attacked by bot by left hand. You have  "
                            + picachu.getCurrentHealth() + "HP");
                    break;
                case 3:
                    picachu.subtractHP(picachuBot.hitByLeftFoot());
                    System.out.println("You was attacked by bot by left foot. You have  "
                            + picachu.getCurrentHealth() + "HP");
                    break;
                case 4:
                    picachu.subtractHP(picachuBot.hitByRightFoot());
                    System.out.println("You was attacked by bot by right foot. You have  "
                            + picachu.getCurrentHealth() + "HP");
                    break;
                case 5:
                    picachuBot.defend();
                    System.out.println("Bot defends!");
                    break;
            }
        }
    }

    public static void printCmd() {
        System.out.println("Enter a number: ");
        System.out.println("1 : hit by right hand");
        System.out.println("2 : hit by left hand");
        System.out.println("3 : hit by left foot");
        System.out.println("4 : hit by right foot");
        System.out.println("5 : defend");
    }
}