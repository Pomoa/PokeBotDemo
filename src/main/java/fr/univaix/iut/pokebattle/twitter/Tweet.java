package fr.univaix.iut.pokebattle.twitter;

public class Tweet {
    private String text;
    private String screenName;
    private String[] hashTag;

    public Tweet(String text) {
        this.text = text;
    }

    public Tweet(String screenName, String text) {
        this.screenName = screenName;
        this.text = text;
        
        for (int i = 0, p=0 ; i < text.length() -1 ; i++, p++) {// parcours le tweet
        	if (text.charAt(i) == '#') 							// s'arrete si il croise un hashtag
        	{
        		i++;
        		Character car = text.charAt(i);
        		for ( ; Character.isLetterOrDigit(car) ; )		 // vérifie si le caractere met fin au hashtag
        		{
        			hashTag[p] += car; 
        			car = text.charAt(++i);	
        		}  
        	}        	
        	
        }
    }

    public String getScreenName() {
        return screenName;
    }

    public String getText() {
        return text;
    }

	public String getHashTag(int i) {
		return hashTag[i];
	}
}
