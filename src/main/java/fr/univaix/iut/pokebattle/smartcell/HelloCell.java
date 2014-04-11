package fr.univaix.iut.pokebattle.smartcell;

import javax.persistence.EntityManager;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class HelloCell implements SmartCell {

    private EntityManager em;
    public HelloCell(EntityManager em) {
        this.em = em;
    }

    @Override
    public final String ask(final Tweet question) {
        DAOPokemonJPA daopok = new DAOPokemonJPA(em);
        Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
        String asking = question.getText().toUpperCase();
        if (containsHello(asking)) {
                return "@" + question.getScreenName()
                       + " Hi @" + question.getScreenName()
                       + ", I am " + pokemon.getName() + ".";
        }
        return null;

    }

    private boolean containsHello(final String asking) {
        if (asking.contains("HELLO") || asking.contains("HI ")
            || asking.contains("HI,") || asking.contains("HI.")
            || asking.contains("HI!") || asking.contains("HI !")
            || asking.equals("HI")) {
            return true;
        }
        return false;
    }
}