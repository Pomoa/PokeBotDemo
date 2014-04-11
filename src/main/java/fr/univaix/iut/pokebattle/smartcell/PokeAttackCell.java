package fr.univaix.iut.pokebattle.smartcell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokeAttackCell implements SmartCell {

	private EntityManager em;
    public PokeAttackCell(EntityManager em) {
    	this.em = em;
    }

    
    @Override
    public String ask(Tweet question) {
    	
    	DAOPokemonJPA daopok = new DAOPokemonJPA(em);
    	Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
        
        if (pokemon.getOwner() != question.getScreenName()) {
        	return " Sorry, you're not my owner. My owner is " + pokemon.getOwner();
        } else {
	        String result = "@";
	        if (question.getHashTag(0).equals("attack")) {
	            for (int i = 1; i < question.getText().length(); i++) {
	                if (question.getText().charAt(i) == '@') {
	                    for (; Character.isLetterOrDigit(question.getText().charAt(++i));) {
	                        result += question.getText().charAt(i);
	                        if (i + 1 == question.getText().length()) {
	                            break;
	                        }
	                    }
	                    result += " #attack #";
	                    result += question.getHashTag(1);
	                    //ajout de l'attaque
	                    if (i + 1 < question.getText().length()) {
	                        if (question.getText().charAt(i) == ' ' & question.getText().charAt(i + 1) == '/') {
	                            for (; i < question.getText().length(); i++) {
	                                result += question.getText().charAt(i);
	                            }
	                        }
	                    } else {
	                        result += " /cc";
	                    }
	                }
	            }
	            result += " @";
	            result += question.getScreenName();
	            return result;
	        } else {
	            return "Ce n'est pas une attaque";
	        }
        }
    }
}
