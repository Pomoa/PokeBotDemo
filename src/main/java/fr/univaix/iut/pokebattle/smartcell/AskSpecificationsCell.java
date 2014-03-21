package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class AskSpecificationsCell implements SmartCell{
	public String ask(Tweet question, PokeBot abo){
		String asking = question.getText();
		if(asking.contains("@AboHotel #stat #level")){
			return "#level = " + abo.getLevel();
		}
		else if(asking.contains("@AboHotel #stat #PV")){
			return "#PV = " + abo.getNbPv();
		}
		else if(asking.contains("@AboHotel #stat #XP")){
			return "#XP = " + abo.getXP();
		}
		else{
			System.out.println(abo.getLevel());
			return null;
		}
	}
}
