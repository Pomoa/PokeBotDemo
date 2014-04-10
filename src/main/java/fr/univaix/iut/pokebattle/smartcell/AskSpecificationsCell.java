package fr.univaix.iut.pokebattle.smartcell;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;



public class AskSpecificationsCell implements SmartCell{
	public String ask(Tweet question){
		
    	EntityManagerFactory emf = Persistence
    			.createEntityManagerFactory("pokebattlePU");
    	EntityManager em = emf.createEntityManager();
    	DAOPokemonJPA daopok = new DAOPokemonJPA(em);
    	Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
        
		String asking = question.getText();

        if (containsLevel(asking)) {
        	System.out.println(pokemon.getLevel());
            return "@" + question.getScreenName() + " #level = " + pokemon.getLevel();           
         } else if (containsPV(asking)) {
            return "@" + question.getScreenName() + " #PV = " + pokemon.getPVNow() + "/" + pokemon.getPVMax();
         } else if (containsXP(asking)) {
            return "@" + question.getScreenName() + " #XP = " + pokemon.getXP();
         } else {
            System.out.println(pokemon.getLevel());
            return null;
         }
     }



    public final boolean containsLevel(final String asking) {
    	asking.toUpperCase();
        if (asking.contains("#STAT #LEVEL")
            || asking.contains("#STAT#LEVEL")
            || asking.contains(" #STAT#LEVEL")
            || asking.contains(" #STAT #LEVEL")) {
            return true;
        }
        return false;
    }

    public final boolean containsPV(final String asking) {
    	asking.toUpperCase();
        if (asking.contains("#STAT #PV")
            || asking.contains("#STAT#PV")
            || asking.contains(" #STAT#PV")
            || asking.contains(" #STAT #PV")) {
            return true;
        }
        return false;
    }

    public boolean containsXP(final String asking) {
    	asking.toUpperCase();
        if (asking.contains("#STAT #XP")
            || asking.contains("#STAT#XP")
            || asking.contains(" #STAT#XP")
            || asking.contains(" #STAT #XP")) {
            return true;
        }
        return false;
    }
}
