package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class HelloCell implements SmartCell {

    
    public HelloCell() {
    }

    @Override
    public String ask(Tweet question, PokeBot abo) {
        String asking = question.getText().toUpperCase();
        if(containsHello(asking)){
            if (question.getScreenName() != null) {
                    return "@" + question.getScreenName() + " Hi @" + question.getScreenName() + ", I am Abo.";  
            }
            else{
                return "Hi, I am Abo.";
            }
        }
        return null;
    }

	private boolean containsHello(String asking) {
		if (asking.contains("HELLO") || asking.contains("HI ") || asking.contains("HI,") || asking.contains("HI.") || asking.contains("HI!") || asking.contains("HI !") || asking.equals("HI")){
			return true;
		}
		return false;
	}
    

   

}