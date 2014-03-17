package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class OwnerCell implements SmartCell {

    public OwnerCell() {
    }

    /**
     *
     * @param question
     * @param abo
     * @return
     */
    

    @Override
    public String ask(Tweet question, PokeBot abo) {
        
        String asking = question.getText().toUpperCase();
        if(asking.contains("OWNER")){
            
            if( abo.getEleveur() != null)
            {
               String answer = "@" + question.getScreenName() + " My owner is @" + abo.getEleveur() + ".";
               return answer;
            }
            else {
                return "@" + question.getScreenName() + " I don't have owner.";
            }
        }
        return null;
    }

    
}