package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokeAttackCellTest {
	
	private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;
	
	@BeforeClass
	public static void initTestFixture() throws Exception {
	    // Get the entity manager for the tests.
        // Get the entity manager for the tests.
        entityManagerFactory = Persistence.createEntityManagerFactory("pokebattlePUTest");
        entityManager = entityManagerFactory.createEntityManager();

        Connection connection = ((EntityManagerImpl) (entityManager.getDelegate())).getServerSession().getAccessor().getConnection();

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

	PokeAttackCell cell = new PokeAttackCell(entityManager);
    Pokemon poke = new Pokemon();
    
    @Test
    public final void testNorm() {
        Tweet tweet = new Tweet(poke.getOwner(),
        "@abohotelbis #attack #ligotage @pika /cc @thepomoaa");
        assertEquals("@pika #attack #ligotage /cc @thepomoaa @" + poke.getOwner(),
                cell.ask(tweet));
    }
    
    @Test
    public final void testNormbis() {
        Tweet tweet = new Tweet(poke.getOwner(),
        "@abohotelbis #attack #grozyeux @pika /cc @thepomoaa");
        assertEquals("@pika #attack #grozyeux /cc @thepomoaa @" + poke.getOwner(),
                cell.ask(tweet));
    }

    @Test
    public final void testNotOwner() {
        Tweet tweet = new Tweet("nedseb",
                "@abohotelbis #attack #ligotage @pika");
        assertEquals("Sorry, you're not my owner. My owner is " + poke.getOwner(),
                cell.ask(tweet));
    }

    @Test
    public final void testNotOwnerNotAnAttack() {
        Tweet tweet = new Tweet("nedseb", "@abohotelbis #coucou");
        assertEquals(null, cell.ask(
                tweet));
    }
    
    @Test
    public final void testNotAnAttack() {
        Tweet tweet = new Tweet(poke.getOwner(), "@abohotelbis #coucou");
        assertEquals(null, cell.ask(
                tweet));
    }
    
    @Test
    public final void testDontKnowAttack() {
    	Tweet tweet = new Tweet(poke.getOwner(), "@abohotelbis #attack #explosion @pika");
    	assertEquals("Je ne connais pas cette attaque", cell.ask(tweet));
    }
}