package PokemonPolis.battlePokTemplate;

/**
 * Created by iters on 11/10/16.
 */
public interface BattlePokemon {
    float hitByRightHand();

    float hitByLefttHand();

    float hitByRightFoot();

    float hitByLeftFoot();

    void subtractHP(float dmg);

    void defend();
}
