package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.smartcell.HelloCell;
import fr.univaix.iut.pokebattle.smartcell.OwnerCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokeBot implements Bot {
    private String eleveur;
    

    
    
    public PokeBot (String eleveur){
       this.eleveur = eleveur;
    }

    public String getEleveur() {
        return eleveur;
    }

    public void setEleveur(String eleveur) {
        this.eleveur = eleveur;
    }
    
    final SmartCell[] smartCells = new SmartCell[] {
		new HelloCell(),
		new OwnerCell()
	};
    
    public String ask(Tweet question) {
        
        String answer = "";
        PokeBot abo = new PokeBot(this.eleveur); 
            for(SmartCell cell:smartCells) {
                
                if (cell.ask(question, abo) != null)
                    answer = answer + cell.ask(question, abo) + " ";
              
            }
       if ("".equals(answer)){
           answer = "@" + question.getScreenName() + " I don't understand your question.";
       }
       else{
           answer = answer.substring(0, answer.length()-1);
       }
       
       return answer;
    }

}
