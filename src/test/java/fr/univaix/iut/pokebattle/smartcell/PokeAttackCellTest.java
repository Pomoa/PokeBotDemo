package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokeAttackCellTest {
	PokeAttackCell cell = new PokeAttackCell(null);
	
	@Test
	public void test () {
		Tweet tweet = new Tweet("nedseb","@abohotelbis #attack #ligotage @pika /cc @thepomoaa");
		assertEquals("@pika #attack #ligotage /cc @thepomoaa @nedseb", cell.ask(tweet, new PokeBot(null)));
	}
	
	@Test
	public void test2 () {
		Tweet tweet = new Tweet("nedseb","@abohotelbis #attack #ligotage @pika");
		assertEquals("@pika #attack #ligotage /cc @nedseb", cell.ask(tweet, new PokeBot(null)));
	}
	
	@Test
	public void test3 () {
		Tweet tweet = new Tweet("nedseb","@abohotelbis #coucou");
		assertEquals("Ce n'est pas une attaque", cell.ask(tweet, new PokeBot(null)));
	}
}