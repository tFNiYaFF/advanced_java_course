package PokemonPolis.trainer;

import PokemonPolis.abstractPokemons.BasicBattlePokemon;
import java.io.BufferedInputStream;
import java.util.Random;

/**
 * Created by iters on 11/11/16.
 */
public class PokemonAutoTrainer extends PokemonTrainer {
    public PokemonAutoTrainer(BufferedInputStream stdin, BasicBattlePokemon myPok, BasicBattlePokemon enemyPok) {
        super(stdin, myPok, enemyPok);
    }

    @Override
    public String battleCmd() {
        answer = new StringBuilder();
        Random rnd = new Random();
        int cmd = rnd.nextInt(5) + 1;

        switch (cmd) {
            case 1:
                enemyPok.subtractHP(myPok.hitByRightHand());
                answer.append("You was attacked by bot by right hand. You have  "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 2:
                enemyPok.subtractHP(myPok.hitByLefttHand());
                answer.append("You was attacked by bot by left hand. You have  "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 3:
                enemyPok.subtractHP(myPok.hitByLeftFoot());
                answer.append("You was attacked by bot by left foot. You have  "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 4:
                enemyPok.subtractHP(myPok.hitByRightFoot());
                answer.append("You was attacked by bot by right foot. You have  "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 5:
                myPok.defend();
                answer.append("Bot defends!");
                break;
        }
        return answer.toString();
    }
}