package fr.univaix.iut.pokebattle.smartcell;


import javax.persistence.EntityManager;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;



public class AskAttackSpecificationsCell implements SmartCell {
    private EntityManager em;
    public AskAttackSpecificationsCell(final EntityManager em) {
        this.em = em;
    }

    @Override
    public String ask(Tweet question) {
        DAOPokemonJPA daopok = new DAOPokemonJPA(em);
        Pokemon pokemon = new Pokemon();
        pokemon = daopok.getById("AboHotelBis");
        String asking = question.getText().toUpperCase();
        
        

        
        if (question.getHashTagList().size() != 3){       
            return null;
        }
        
        String attaque = question.getHashTag(2).toUpperCase();
        int i = 0;
        
        
        while (i < pokemon.getAttaques().size()){
            if (attaque.equals(pokemon.getAttaques().get(i).getNomAttaque().toUpperCase())){
                break;
            }
            i++;
        }

        if (i == pokemon.getAttaques().size()){
              return "@" + question.getScreenName()
                        + " I don't know this attack.";
        }
        
        if (containsPP(asking)) {    
        return "@" + question.getScreenName()
                   + " #statAttack #PP de #" + pokemon.getAttaques().get(i).getNomAttaque() + " = " + 
                pokemon.getAttaques().get(i).getPp() + ".";
        }
        
        if (containsPrecision(asking)) {    
            return "@" + question.getScreenName()
                       + " #statAttack #Précision de #" + pokemon.getAttaques().get(i).getNomAttaque() + " = " + 
                    pokemon.getAttaques().get(i).getPrecision() + ".";
            }
        
        if (containsPuissance(asking)) {    
            return "@" + question.getScreenName()
                       + " #statAttack #Puissance de #" + pokemon.getAttaques().get(i).getNomAttaque() + " = " + 
                    pokemon.getAttaques().get(i).getPuissance() + ".";
            }
            
        return null;
     }



    public final boolean containsPP(final String asking) {
        if (asking.contains("#STATATTACK") && asking.contains("#PP")){
            return true;
        }
        return false;
    }
    
    public final boolean containsPrecision(final String asking) {
        if (asking.contains("#STATATTACK") && asking.contains("#PRECISION") 
                || asking.contains("#STATATTACK") && asking.contains("#PRÉCISION")){
            return true;
        }
        return false;
    }
    
    public final boolean containsPuissance(final String asking) {
        if (asking.contains("#STATATTACK") && asking.contains("#PUISSANCE")){

            return true;
        }
        return false;
    }
}
