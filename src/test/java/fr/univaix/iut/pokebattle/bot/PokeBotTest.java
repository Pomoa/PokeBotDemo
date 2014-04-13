package fr.univaix.iut.pokebattle.bot;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAOPokemon;
import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.CatchPokemonCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Integration tests checking the PokeBot
 * behavior. We just test some cases to make sure that the
 * PokeBot is using smartcell properly.
 */
public class PokeBotTest {


    private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;
    private DAOPokemon dao;

    PokeBot pokeBot;
    @Before
    public final void initTestFixture() throws Exception {
        // Get the entity manager for the tests.
        // Get the entity manager for the tests.
        entityManagerFactory = Persistence.
                createEntityManagerFactory("pokebattlePUTest");
        entityManager = entityManagerFactory.createEntityManager();

        Connection connection = ((EntityManagerImpl)
                (entityManager.getDelegate())).getServerSession()
                .getAccessor().getConnection();
        dbUnitConnection = new DatabaseConnection(connection);
        //Loads the data set from a file
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("pokemonDataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
        dao = new DAOPokemonJPA(entityManager);
        pokeBot = new PokeBot(entityManager);
    }

    @After
    public final void finishTestFixture() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }


    @Test
    public final void testAskHelloFromFollower()
            throws CloneNotSupportedException {
        assertEquals("@Tristan Hi @Tristan, I am AboHotelBis.",
                pokeBot.ask(new Tweet("Tristan", "Hi, how are you ?")));
    }




    @Test
    public final void testAskNoOwner() throws CloneNotSupportedException {
        assertEquals("@Tristan I don't have owner.",
                pokeBot.ask(new Tweet("Tristan", "Do you have an owner ?")));
    }

    @Test
    public final void testASkOwner() throws CloneNotSupportedException {
        Pokemon abo = new Pokemon();
        abo = dao.getById("AboHotelBis");
        abo.setOwner("CaptainObvious");
        dao.insert(abo);
        assertEquals("@Tristan My owner is @CaptainObvious.",
                pokeBot.ask(new Tweet("Tristan", "Do you have an owner ?")));
    }

    @Test
    public final void testNoQuestion() throws CloneNotSupportedException {
        assertEquals("@Tristan I don't understand your question.",
                pokeBot.ask(new Tweet("Tristan", "This is not a question")));

    }
    
    
    @Test
    public final void testAskPp() {
        assertEquals("@CaptainObvious #statAttack #PP de #Ligotage = 20.", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #PP #Ligotage")));
    }
    
    @Test
    public final void testAskPuissance() {
        assertEquals("@CaptainObvious #statAttack #Puissance de #Ligotage = 15.", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Puissance #Ligotage")));
    }
    
    @Test
    public final void testAskPrécision() {
        assertEquals("@CaptainObvious #statAttack #Précision de #Ligotage = 85.", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Précision #Ligotage")));
    }
    
    @Test
    public final void testAskPpGrozYeux() {
        assertEquals("@CaptainObvious #statAttack #PP de #GrozYeux = 30.", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #PP #GrozYeux")));
    }
    
    @Test
    public final void testAskPuissanceGrozYeux() {
        assertEquals("@CaptainObvious #statAttack #Puissance de #GrozYeux = 0.", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Puissance #GrozYeux")));
    }
    
    @Test
    public final void testAskPrécisionGrozYeux() {
        assertEquals("@CaptainObvious #statAttack #Précision de #GrozYeux = 100.", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Précision #GrozYeux")));
    }
    
    @Test
    public final void testAskNoAttaque() {
        assertEquals("@CaptainObvious I don't know this attack.", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Précision #DardVenin")));
    }
    
    @Test
    public final void testCaptureAboHotelBisSuccess()
            throws CloneNotSupportedException {
        assertEquals("@CaptainObvious You are now my owner.",
                pokeBot.ask(new Tweet("CaptainObvious",
                        "@AboHotelBis pokeball !")));
    }

    @Test
    public final void testCaptureAboHotelBisFail()
            throws CloneNotSupportedException {
        Pokemon abo = new Pokemon();
        abo = dao.getById("AboHotelBis");
        abo.setOwner("Tristan");
        dao.insert(abo);
        assertEquals("@CaptainObvious Sorry but my owner is @Tristan.",
                pokeBot.ask(new Tweet("CaptainObvious",
                "@AboHotelBis pokeball !")));
    }
    
    @Test
    public final void testAskLevel() {
        assertEquals("@CaptainObvious #level = 1", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #stat #level")));
    }




    @Test
    public final void testAskPV() {
        assertEquals("@CaptainObvious #PV = 30/30", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #stat #PV")));
    }


    @Test
    public final void testAskXP() {
        assertEquals("@CaptainObvious #XP = 0", pokeBot.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #stat #XP")));
    }
    
    @Test
    public final void testNotOwner() {
        Pokemon poke = new Pokemon();
        Tweet tweet = new Tweet("nedseb",
                "@abohotelbis #attack #ligotage @pika");
        assertEquals("@nedseb Sorry, you're not my owner. My owner is "
                     + poke.getOwner(), pokeBot.ask(tweet));
    }

    @Test
    public final void testNotOwnerNotAnAttack() {
        Tweet tweet = new Tweet("nedseb", "@abohotelbis #coucou");
        assertEquals("@nedseb I don't understand your question.", pokeBot.ask(
                tweet));
    }


    @Test
    public final void testDontKnowAttack() {
        Pokemon poke = new Pokemon();
        Tweet tweet = new Tweet(poke.getOwner(),
                "@abohotelbis #attack #explosion @pika");
        assertEquals("Je ne connais pas cette attaque", pokeBot.ask(tweet));
    }
}
