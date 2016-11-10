package PokemonPolis;

import PokemonPolis.collectionPokemons.Picachu;
import PokemonPolis.pokemonBuilder.PicachuBuilder;

/**
 * Created by iters on 11/11/16.
 */
public class BattleArea {
    public static void main(String[] args) {
        Picachu pok1 = new PicachuBuilder()
                            .setName("Stas")
                            .setLeftFootHit(100f)
                            .setRightFootHit(110f)
                            .setLeftHandHit(50f)
                            .setRightHandHit(70f)
                            .setMaxHealth(1000)
                            .buildPicachu();
    }
}
