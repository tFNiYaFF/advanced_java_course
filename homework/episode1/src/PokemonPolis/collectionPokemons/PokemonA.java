package PokemonPolis.collectionPokemons;

import PokemonPolis.abstractPokemons.BasicBattlePokemon;

/**
 * Created by iters on 11/10/16.
 */
public class PokemonA extends BasicBattlePokemon {
    private boolean isSleep = false;

    @Override
    public float hitByRightHand() {
        return hitByRightHand();
    }

    @Override
    public float hitByLefttHand() {
        return hitByLefttHand();
    }

    @Override
    public float hitByRightFoot() {
        return hitByRightFoot();
    }

    @Override
    public float hitByLeftFoot() {
        return hitByLeftFoot();
    }

    @Override
    public void subtractHP(float dmg) {
        if(isDefending()) {
            setCurrentHealth((float) (getCurrentHealth() - dmg * 0.7));
            toAttackState();
        } else {
            setCurrentHealth(getCurrentHealth() - dmg);
        }
    }

    @Override
    public void sleep() throws InterruptedException {
        isSleep = true;
        while(isSleep && getCurrentHealth() < getMaxHealth()) {
            setCurrentHealth((float) (getCurrentHealth() + 0.1));
            System.out.println("Hrrr - Hrrr");
            Thread.sleep(500);
        }
    }

    @Override
    public void awake() {
        isSleep = false;
    }
}
