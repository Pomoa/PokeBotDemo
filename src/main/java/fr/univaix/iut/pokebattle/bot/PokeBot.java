package fr.univaix.iut.pokebattle.bot;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
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
       this.changeBio();
    }
    
    private void changeBio(){
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true);
    	cb.setOAuthConsumerKey("L3TGAaeq6Ni6Cd0PDD13UA");
    	cb.setOAuthConsumerSecret("MvvYMza3PXqwvgwINyx7fV3tB3m5r0pdpwy3wNdI8");
    	cb.setOAuthAccessToken("2362514731-F4nxaicaGTQkXnIygS1LSH4fERT2KeLkGG8vPkT");
    	cb.setOAuthAccessTokenSecret("pp2KWq1XvNlVIFJ7MEiJpW7Mhk76UXMthvUNIWM6IAaeA");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();
    	try {
			twitter.updateProfile("AboHotel","https://twitter.com/AboHotelBis","Route 32, Johto","#pokebattle - #pokemon - Owner: @" + this.getEleveur());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
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
