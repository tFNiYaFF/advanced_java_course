package PokemonPolis.pokemonBuilder;

import PokemonPolis.collectionPokemons.PokemonA;

/**
 * Created by iters on 11/10/16.
 */
public class PokemonBuilderA implements BasicBuilder {
    private PokemonA pokemonA;

    public PokemonBuilderA() {
        pokemonA = new PokemonA();
    }

    @Override
    public PokemonBuilderA setRightHandHit() {
        pokemonA.setRightHandHit(50f);
        return this;
    }

    @Override
    public PokemonBuilderA setLeftHandHit() {
        pokemonA.setLeftHandHit(55f);
        return this;
    }

    @Override
    public PokemonBuilderA setRightFootHit() {
        pokemonA.setRightFootHit(100f);
        return this;
    }

    @Override
    public PokemonBuilderA setLeftFootHit() {
        pokemonA.setLeftFootHit(95f);
        return this;
    }

    @Override
    public PokemonBuilderA setName() {
        pokemonA.setName("Picachu");
        return this;
    }

    @Override
    public PokemonBuilderA setMaxHealth() {
        pokemonA.setMaxHealth(1000f);
        return this;
    }

    @Override
    public PokemonA buildPokemon() {
        return pokemonA;
    }
}
