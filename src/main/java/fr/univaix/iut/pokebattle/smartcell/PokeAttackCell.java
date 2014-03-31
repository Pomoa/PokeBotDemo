package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokeAttackCell {

	public PokeAttackCell(Object object) {
		// TODO Auto-generated constructor stub
	}

	public String ask(Tweet tweet, PokeBot poke) {
		String result ="@";
		System.out.println(tweet.getHashTag(0));
		if(tweet.getHashTag(0).equals("attack")) {
			for(int i = 1 ; i < tweet.getText().length() ; i++) {
				if(tweet.getText().charAt(i) == '@') {
					for(;Character.isLetterOrDigit(tweet.getText().charAt(++i));) {
						result += tweet.getText().charAt(i);
						if(i+1 == tweet.getText().length()) break;
					}
					result +=" #attack #";
					result +=tweet.getHashTag(1); //ajout de l'attaque
					
					if(i+1 < tweet.getText().length()) {
						if(tweet.getText().charAt(i) == ' ' & tweet.getText().charAt(i+1) == '/') {
							for(;i < tweet.getText().length(); i++) {
								result += tweet.getText().charAt(i);
							}
						}
					} else result += " /cc";
				}
			}
			result += " @";
			result += tweet.getScreenName();
			return result;
		} else return "Ce n'est pas une attaque";
	}	
}
