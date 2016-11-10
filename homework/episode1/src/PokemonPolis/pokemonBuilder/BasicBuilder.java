package PokemonPolis.pokemonBuilder;

import PokemonPolis.abstractPokemons.BasicPokemon;

/**
 * Created by iters on 11/11/16.
 */
public interface BasicBuilder {
    BasicBuilder setRightHandHit();

    BasicBuilder setLeftHandHit();

    BasicBuilder setRightFootHit();

    BasicBuilder setLeftFootHit();

    BasicBuilder setName();

    BasicBuilder setMaxHealth();

    BasicPokemon buildPokemon();
}
