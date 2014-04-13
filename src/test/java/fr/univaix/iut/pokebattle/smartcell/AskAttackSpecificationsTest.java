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

public class AskAttackSpecificationsTest {

    private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;

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

    AskAttackSpecificationsCell cell = new AskAttackSpecificationsCell(entityManager);

    @Test
    public final void testAskPp() {
        assertEquals("@CaptainObvious #statAttack #PP de #Ligotage = 20.", cell.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #PP #Ligotage")));
    }
    
    @Test
    public final void testAskPuissance() {
        assertEquals("@CaptainObvious #statAttack #Puissance de #Ligotage = 15.", cell.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Puissance #Ligotage")));
    }
    
    @Test
    public final void testAskPrécision() {
        assertEquals("@CaptainObvious #statAttack #Précision de #Ligotage = 85.", cell.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Précision #Ligotage")));
    }
    
    @Test
    public final void testAskPpGrozYeux() {
        assertEquals("@CaptainObvious #statAttack #PP de #GrozYeux = 30.", cell.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #PP #GrozYeux")));
    }
    
    @Test
    public final void testAskPuissanceGrozYeux() {
        assertEquals("@CaptainObvious #statAttack #Puissance de #GrozYeux = 0.", cell.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Puissance #GrozYeux")));
    }
    
    @Test
    public final void testAskPrécisionGrozYeux() {
        assertEquals("@CaptainObvious #statAttack #Précision de #GrozYeux = 100.", cell.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Précision #GrozYeux")));
    }
    
    @Test
    public final void testAskNoAttaque() {
        assertEquals("@CaptainObvious I don't know this attack.", cell.ask(new Tweet(
                "CaptainObvious", "@AboHotelBis #StatAttack #Précision #DardVenin")));
    }


}
