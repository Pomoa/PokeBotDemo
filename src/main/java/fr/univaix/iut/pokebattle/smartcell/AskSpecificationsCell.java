package fr.univaix.iut.pokebattle.smartcell;


import javax.persistence.EntityManager;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;



public class AskSpecificationsCell implements SmartCell {
    private EntityManager em;
    public AskSpecificationsCell(final EntityManager em) {
        this.em = em;
    }

    @Override
    public final String ask(final Tweet question) {
        DAOPokemonJPA daopok = new DAOPokemonJPA(em);
        Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
        String asking = question.getText().toUpperCase();

        if (containsLevel(asking)) {
            return "@" + question.getScreenName()
                       + " #level = " + pokemon.getLevel();
         } else if (containsPV(asking)) {
            return "@" + question.getScreenName()
                   + " #PV = " + pokemon.getPVNow() + "/" + pokemon.getPVMax();
         } else if (containsXP(asking)) {
            return "@" + question.getScreenName() + " #XP = " + pokemon.getXP();
         } else {
            return null;
         }
     }



    public final boolean containsLevel(final String asking) {
        if (asking.contains("#STAT") && asking.contains("#LEVEL")) {
            return true;
        }
        return false;
    }

    public final boolean containsPV(final String asking) {
        if (asking.contains("#STAT") && asking.contains("#PV")) {
            return true;
        }
        return false;
    }

    public final boolean containsXP(final String asking) {
        if (asking.contains("#STAT") && asking.contains("#XP")) {
            return true;
        }
        return false;
    }
}
