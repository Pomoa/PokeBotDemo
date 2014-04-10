package fr.univaix.iut.pokebattle.smartcell;
import static org.junit.Assert.*;

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

import fr.univaix.iut.pokebattle.DAOPokemon;
import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class AskSpecificationsTest {
	
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
    
    AskSpecificationsCell cell = new AskSpecificationsCell(entityManager);
    
    @Test
    public final void testAskLevel() {
       
        assertEquals("@CaptainObvious #level = 1", cell.ask(new Tweet(
                "CaptainObvious", "#STAT #LEVEL")));
    }

    @Test
    public final void testAskLevelColle() {
       

        assertEquals("@CaptainObvious #level = 1", cell.ask(new Tweet(
                "CaptainObvious", "#STAT#LEVEL")));
    }

    @Test
    public final void testAskLevelEspace() {
       

        assertEquals("@CaptainObvious #level = 1", cell.ask(new Tweet(
                "CaptainObvious", " #STAT #LEVEL")));
    }

    @Test
    public final void testAskLevelEspaceColle() {  

        assertEquals("@CaptainObvious #level = 1", cell.ask(new Tweet(
                "CaptainObvious", " #Stat#LEVEL")));
    }

    @Test
    public final void testAskPV() {
       

        assertEquals("@CaptainObvious #PV = 100", cell.ask(new Tweet(
                "CaptainObvious", "#STAT #PV")));
    }

    @Test
    public final void testAskPVColle() {
       

        assertEquals("@CaptainObvious #PV = 100", cell.ask(new Tweet(
                "CaptainObvious", "#STAT#PV")));
    }

    @Test
    public final void testAskPVEspace() {
       

        assertEquals("@CaptainObvious #PV = 100", cell.ask(new Tweet(
                "CaptainObvious", " #STAT #PV")));
    }

    @Test
    public final void testAskPVEspaceColle() {
       

        assertEquals("@CaptainObvious #PV = 100", cell.ask(new Tweet(
                "CaptainObvious", " #STAT#PV")));
    }

    @Test
    public final void testAskXP() {
       

        assertEquals("@CaptainObvious #XP = 0", cell.ask(new Tweet(
                "CaptainObvious", "#STAT #XP")));
    }

    @Test
    public final void testAskXPColle() {
       

        assertEquals("@CaptainObvious #XP = 0", cell.ask(new Tweet(
                "CaptainObvious", "#STAT#XP")));
    }

    @Test
    public final void testAskXPEspace() {
       

        assertEquals("@CaptainObvious #XP = 0", cell.ask(new Tweet(
                "CaptainObvious", " #STAT #XP")));
    }

    @Test
    public final void testAskXPEspaceColle() {
       

        assertEquals("@CaptainObvious #XP = 0", cell.ask(new Tweet(
                "CaptainObvious", " #STAT#XP")));
    }
}
