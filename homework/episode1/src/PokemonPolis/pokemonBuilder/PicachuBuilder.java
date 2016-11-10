package PokemonPolis.pokemonBuilder;

import PokemonPolis.collectionPokemons.Picachu;

/**
 * Created by iters on 11/10/16.
 */
public class PicachuBuilder {
    private Picachu pokemon;

    public PicachuBuilder() {
        pokemon = new Picachu();
    }

    public PicachuBuilder setRightHandHit(float force) {
        pokemon.setRightHandHit(force);
        return this;
    }

    public PicachuBuilder setLeftHandHit(float force) {
        pokemon.setLeftHandHit(force);
        return this;
    }

    public PicachuBuilder setRightFootHit(float force) {
        pokemon.setRightFootHit(force);
        return this;
    }

    public PicachuBuilder setLeftFootHit(float force) {
        pokemon.setLeftFootHit(force);
        return this;
    }

    public PicachuBuilder setName(String name) {
        pokemon.setName(name);
        return this;
    }

    public PicachuBuilder setMaxHealth(float hp) {
        pokemon.setMaxHealth(hp);
        return this;
    }

    public Picachu buildPicachu() {
        return pokemon;
    }
}
