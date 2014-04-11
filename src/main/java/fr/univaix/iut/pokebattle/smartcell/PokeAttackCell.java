package fr.univaix.iut.pokebattle.smartcell;

import javax.persistence.EntityManager;
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
        
        if(question.getHashTagList().size() < 2) return null;
        if (pokemon.getOwner() != question.getScreenName() && question.getHashTag(0).equals("attack")) {
        	return "Sorry, you're not my owner. My owner is " + pokemon.getOwner();
        } else {
	        String result = "@";
	        if (question.getHashTag(0).equals("attack") && pokemon.isAttackOf(question.getHashTag(1))) {
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
	        } else if (!pokemon.isAttackOf(question.getHashTag(1))){
	        	return "Je ne connais pas cette attaque";
	        }
	        return null;	        
        }
    }
}
