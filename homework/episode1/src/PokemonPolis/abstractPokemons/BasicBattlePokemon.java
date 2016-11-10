package PokemonPolis.abstractPokemons;

import PokemonPolis.battlePokTemplate.BattlePokemonTemplate;

/**
 * Created by iters on 11/10/16.
 */
public abstract class BasicBattlePokemon extends BasicPokemon implements BattlePokemonTemplate {
    private float rightHandHit;
    private float leftHandHit;
    private float rightFootHit;
    private float leftFootHit;
    private boolean defending = false;

    @Override
    public void defend() {
        defending = true;
    }

    public void setRightHandHit(float rightHandHit) {
        this.rightHandHit = rightHandHit;
    }

    public void setLeftHandHit(float leftHandHit) {
        this.leftHandHit = leftHandHit;
    }

    public void setRightFootHit(float rightFootHit) {
        this.rightFootHit = rightFootHit;
    }

    public void setLeftFootHit(float leftFootHit) {
        this.leftFootHit = leftFootHit;
    }

    public boolean isDefending() {
        return defending;
    }

    public void toAttackState() {
        defending = false;
    }
}
