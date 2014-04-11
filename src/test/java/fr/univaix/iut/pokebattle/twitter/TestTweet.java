package fr.univaix.iut.pokebattle.twitter;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

import fr.univaix.iut.pokebattle.Attaque;
import static org.junit.Assert.assertEquals;

public class TestTweet {
    @Test
    public final void testCreateTweet() {
        Tweet tweet = new Tweet("nedseb", "coucou !");
        assertEquals("nedseb", tweet.getScreenName());
        assertEquals("coucou !", tweet.getText());

        Tweet tweet2 = new Tweet("coucou !");
        assertEquals(null, tweet2.getScreenName());
        assertEquals("coucou !", tweet2.getText());

    }

    @Test
    public final void testHashtag() {
        Tweet tweet = new Tweet("nedseb", "Coucou ! #gentil #bonjour");
        assertEquals("nedseb", tweet.getScreenName());
        assertEquals("gentil", tweet.getHashTag(0));
        assertEquals("bonjour", tweet.getHashTag(1));
        Tweet tweet2 = new Tweet("Ca marche #Yosh #Yata");
        assertEquals("Yosh", tweet2.getHashTag(0));
        assertEquals("Yata", tweet2.getHashTag(1));
    }

    @Test
    public void testHashtag2() {
        Tweet tweet = new Tweet("nedseb", "Coucou ! #gentil #bonjour");
        ArrayList<String> list = new ArrayList<String>();
        list.add("gentil");
        list.add("bonjour");
        assertEquals(list, tweet.getHashTagList());
    }
}
