package PokemonPolis.pokemonBuilder;

import PokemonPolis.abstractPokemons.BasicPokemon;

/**
 * Created by iters on 11/11/16.
 */
public class DirectorBuilder {
    BasicBuilder builder;

    public void setBuilder(BasicBuilder builder) {
        this.builder = builder;
    }

    public BasicPokemon buildPokemon() {
        return builder.setLeftFootHit()
                      .setRightFootHit()
                      .setLeftHandHit()
                      .setRightHandHit()
                      .setName()
                      .setMaxHealth()
                      .buildPokemon();
    }
}
