 package fr.univaix.iut.pokebattle.smartcell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/* Classe qui permet d'attraper un pokémon si celui-ci n'a pas
 * déja un dresseur. Il renvoie le nom de son éleveur dans tous les cas
 */


public class CatchPokemonCell implements SmartCell {

    private EntityManager em;
    public CatchPokemonCell(final EntityManager em) {
        this.em = em;
    }

    @Override
    public final String ask(final Tweet question) {
        DAOPokemonJPA daopok = new DAOPokemonJPA(em);
        Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
        String asking = question.getText().toUpperCase();
        if (asking.contains("POKEBALL")) {
            if (pokemon.getOwner() == null) {
                pokemon.setOwner(question.getScreenName());
                daopok.update(pokemon);
                return "@" + question.getScreenName()
                           + " You are now my owner.";
            }

            return "@" + question.getScreenName()
                       + " Sorry but my owner is @" + pokemon.getOwner() + ".";
        }
        return null;
    }
}
