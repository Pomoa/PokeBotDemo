package fr.univaix.iut.pokebattle.bot;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAOPokemon;
import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
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
    private DAOPokemon dao = new DAOPokemonJPA(entityManager);

    @BeforeClass
    public static void initTestFixture() throws Exception {
        // Get the entity manager for the tests.
        // Get the entity manager for the tests.
        entityManagerFactory = Persistence.
                createEntityManagerFactory("pokebattlePUTest");
        entityManager = entityManagerFactory.createEntityManager();

        Connection connection = ((EntityManagerImpl)
                (entityManager.getDelegate())).getServerSession().
                getAccessor().getConnection();

        dbUnitConnection = new DatabaseConnection(connection);
        //Loads the data set from a file
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("pokemonDataset.xml"));
    }

    @AfterClass
    public static void finishTestFixture() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Before
    public void setUp() throws Exception {
        //Clean the data from previous test and insert new data test.
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
    }

    PokeBot pokeBot = new PokeBot(entityManager);


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
}
