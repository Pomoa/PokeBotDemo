package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class HelloCellTest {
	
    PokeBot pokeBot;
    
    
    @Test
    public void testAskHelloFromFollower() throws CloneNotSupportedException {
        
        pokeBot = new PokeBot(null);
        assertEquals("@Tristan Hi @Tristan, I am Abo.", pokeBot.ask(new Tweet("Tristan", "Hi, how are you ?")));

    }
    
    @Test
    public void testAskHelloFromNoOne() throws CloneNotSupportedException {
        
        pokeBot = new PokeBot(null);
        assertEquals("Hi, I am Abo.", pokeBot.ask(new Tweet("Hi, how are you ?")));

    }

}
