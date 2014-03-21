package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/* Classe qui permet d'attraper un pokémon si celui-ci n'a pas
 * déja un dresseur. Il renvoie le nom de son éleveur dans tous les cas 
 */

public class CatchPokemonCell implements SmartCell{
	public CatchPokemonCell(){
		
	}
	public String ask(Tweet question, PokeBot abo){
		String asking = question.getText().toUpperCase();
		if(asking.contains("POKEBALL !"))
		{
			if(abo.getEleveur() == null)
			{
				abo.setEleveur(question.getScreenName());
				return "@" + abo.getEleveur() + " is my owner now";
			}
			else 
			{
				return "I already have an owner is name is @" + abo.getEleveur();
			}
		}
		return null;
	}
	
}
