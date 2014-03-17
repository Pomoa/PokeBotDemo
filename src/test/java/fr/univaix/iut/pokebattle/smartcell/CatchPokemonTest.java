package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CatchPokemonTest {
	CatchPokemonCell cell = new CatchPokemonCell();
	
	@Test
	public void TestCatchWithoutEleveur(){
		PokeBot pokeBot = new PokeBot(null);
		
		assertEquals("@AboHotel @CaptainObvious is my owner now", cell.ask(new Tweet ("CaptainObvious","POKEBALL!"), pokeBot));
	}
	
	@Test
	public void TestCatchWithEleveur(){
		PokeBot pokeBot = new PokeBot("@CaptainObvious");
		
		assertEquals("@AboHotel My owner is @" + pokeBot.getEleveur(), cell.ask(new Tweet("Jacky", "POKEBALL!"), pokeBot));
	}
}