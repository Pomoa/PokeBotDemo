package fr.univaix.iut.pokebattle.smartcell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class HelloCell implements SmartCell {

    
    public HelloCell() {

    }
    
    @Override
    public String ask(Tweet question) {
    	
    	EntityManagerFactory emf = Persistence
    			.createEntityManagerFactory("pokebattlePU");
    	EntityManager em = emf.createEntityManager();
    	DAOPokemonJPA daopok = new DAOPokemonJPA(em);
    	Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
    	
        
        String asking = question.getText().toUpperCase();
        if (containsHello(asking)) {
                return "@" + question.getScreenName() + " Hi @" + question.getScreenName()
                       + ", I am " + pokemon.getName();
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