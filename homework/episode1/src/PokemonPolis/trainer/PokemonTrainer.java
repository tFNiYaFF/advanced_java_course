package PokemonPolis.trainer;

import PokemonPolis.abstractPokemons.BasicBattlePokemon;
import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Created by iters on 11/11/16.
 */
public class PokemonTrainer {
    protected BasicBattlePokemon myPok;
    protected BasicBattlePokemon enemyPok;
    protected Scanner scan;
    protected StringBuilder answer;

    public PokemonTrainer(BufferedInputStream stdin,
                          BasicBattlePokemon myPok,
                          BasicBattlePokemon enemyPok) {
        scan = new Scanner(stdin);
        answer = new StringBuilder();
        this.myPok = myPok;
        this.enemyPok = enemyPok;
    }

    public String battleCmd() {
        answer = new StringBuilder();
        int cmd = scan.nextInt();

        switch (cmd) {
            case 1:
                enemyPok.subtractHP(myPok.hitByRightHand());
                answer.append("after attack by right hand, Bot has "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 2:
                enemyPok.subtractHP(myPok.hitByLefttHand());
                answer.append("after attack by left hand Bot, has "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 3:
                enemyPok.subtractHP(myPok.hitByLeftFoot());
                answer.append("after attack by left foot Bot, has "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 4:
                enemyPok.subtractHP(myPok.hitByRightFoot());
                answer.append("after attack by right foot, Bot has "
                        + enemyPok.getCurrentHealth() + " HP");
                break;
            case 5:
                myPok.defend();
                answer.append("I defends!");
                break;
        }
        return answer.toString();
    }
}