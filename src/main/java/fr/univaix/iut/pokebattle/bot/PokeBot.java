package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.smartcell.AskSpecificationsCell;
import fr.univaix.iut.pokebattle.smartcell.CatchPokemonCell;
import fr.univaix.iut.pokebattle.smartcell.HelloCell;
import fr.univaix.iut.pokebattle.smartcell.OwnerCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokeBot implements Bot {
    private String eleveur;
    private int m_nbPv;
    private int m_level;
    private int m_XP;
  
    public PokeBot (String eleveur){
       this.eleveur = eleveur;
       this.m_nbPv = 100;
       this.m_level = 1;
       this.m_XP = 0;
    }

    public int getNbPv(){
    	return m_nbPv;
    }
    
    public void setNbPv(int nbPv){
    	this.m_nbPv = nbPv;
    }
    
    public int getLevel(){
    	return m_level;
    }
    
    public void setLevel(int level){
    	this.m_level = level;
    }
    
    public int getXP(){
    	return m_XP;
    }
    
    public void setXP(int XP){
    	this.m_XP = XP;
    }
    
    public String getEleveur() {
        return eleveur;
    }

    public void setEleveur(String eleveur) {
        this.eleveur = eleveur;
    }
    
    final SmartCell[] smartCells = new SmartCell[] {
		new HelloCell(),
		new OwnerCell(),
		new CatchPokemonCell(),
		new AskSpecificationsCell()
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
