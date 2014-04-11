package fr.univaix.iut.pokebattle.smartcell;


import javax.persistence.EntityManager;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;



public class AskSpecificationsCell implements SmartCell{
	
	private EntityManager em;
    public AskSpecificationsCell(EntityManager em) {
    	this.em = em;
    }

    
    @Override
    public String ask(Tweet question) {
    	
    	DAOPokemonJPA daopok = new DAOPokemonJPA(em);
    	Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
        
		String asking = question.getText().toUpperCase();

        if (containsLevel(asking)) {
        	System.out.println(pokemon.getLevel());
            return "@" + question.getScreenName() + " #level = " + pokemon.getLevel();           
         } else if (containsPV(asking)) {
            return "@" + question.getScreenName() + " #PV = " + pokemon.getPVNow() + "/" + pokemon.getPVMax();
         } else if (containsXP(asking)) {
            return "@" + question.getScreenName() + " #XP = " + pokemon.getXP();
         } else {
            return null;
         }
     }



    public final boolean containsLevel(final String asking) {
        if (asking.contains("#STAT #LEVEL")
            || asking.contains("#STAT#LEVEL")
            || asking.contains(" #STAT#LEVEL")
            || asking.contains(" #STAT #LEVEL")) {
            return true;
        }
        return false;
    }

    public final boolean containsPV(final String asking) {
        if (asking.contains("#STAT #PV")
            || asking.contains("#STAT#PV")
            || asking.contains(" #STAT#PV")
            || asking.contains(" #STAT #PV")) {
            return true;
        }
        return false;
    }

    public boolean containsXP(final String asking) {
        if (asking.contains("#STAT #XP")
            || asking.contains("#STAT#XP")
            || asking.contains(" #STAT#XP")
            || asking.contains(" #STAT #XP")) {
            return true;
        }
        return false;
    }
}
