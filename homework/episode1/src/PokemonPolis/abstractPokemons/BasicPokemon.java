package PokemonPolis.abstractPokemons;

/**
 * Created by iters on 11/10/16.
 */
public abstract class BasicPokemon {
    private String name;
    private float maxHealth;
    private float currentHealth;

    // to restore health
    public abstract void sleep() throws InterruptedException;

    // awake to work hard
    public abstract void awake();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(float currentHealth) {
        this.currentHealth = currentHealth;
    }
}