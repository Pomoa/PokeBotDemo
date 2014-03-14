package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class OwnerCellTest {

    PokeBot pokeBot;

    
    @Test
    public void testAskNoOwner() throws CloneNotSupportedException {
        
        pokeBot = new PokeBot(null);
        assertEquals("I don't have owner.", pokeBot.ask(new Tweet("Do you have an owner ?")));

    }
    
    @Test
    public void testASkOwner() throws CloneNotSupportedException {
        pokeBot = new PokeBot("CaptainObvious");
        assertEquals("My owner is @CaptainObvious.", pokeBot.ask(new Tweet("Do you have an owner ?")));

    }

}
