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


public class CatchPokemonCell implements SmartCell{

	private EntityManager em;
    public CatchPokemonCell(EntityManager em) {
    	this.em = em;
    }

    
    @Override
    public String ask(Tweet question) {
    	
    	DAOPokemonJPA daopok = new DAOPokemonJPA(em);
    	Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
		
		String asking = question.getText().toUpperCase();
		if(asking.contains("POKEBALL"))
		{
			System.out.println("Test2");
	        
	        if (pokemon.getOwner() == null) {
	        	pokemon.setOwner(question.getScreenName());
	        	daopok.update(pokemon);
	        	System.out.println("Test3");
	        	return "@" + question.getScreenName() + " You are now my owner.";
	        	
	        }

			else 
			{
				System.out.println("Test4");
				return "@" + question.getScreenName() + " I already have an owner is name is @" + pokemon.getOwner() + ".";
			}
		}
		System.out.println("Test5");
		return null;
	}
}
