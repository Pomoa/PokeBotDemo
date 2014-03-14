package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class PokemonCriesCell implements SmartCell {

    public String ask(Tweet question, PokeBot abo) {
        if (question.getScreenName() != null) {
            return "@" + question.getScreenName() + " Pika pika";
        }
        return "Pika pika";
    }

}
