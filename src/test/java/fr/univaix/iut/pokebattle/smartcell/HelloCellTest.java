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

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class HelloCellTest {

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
                (entityManager.getDelegate())).getServerSession()
                .getAccessor().getConnection();

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
    public final void setUp() throws Exception {
        //Clean the data from previous test and insert new data test.
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
    }

    HelloCell cell = new HelloCell(entityManager);


    @Test
    public final void testAskHelloFromFollower()
                throws CloneNotSupportedException {
        assertEquals("@Tristan Hi @Tristan, I am AboHotelBis.",
                cell.ask(new Tweet("Tristan", "Hi, how are you ?")));

    }

    @Test
    public final void testAskJustHelloFromFollower()
            throws CloneNotSupportedException {
        assertEquals("@Tristan Hi @Tristan, I am AboHotelBis.",
                cell.ask(new Tweet("Tristan", "Hello")));

    }

    @Test
    public final void testAskJustHiFromFollower()
            throws CloneNotSupportedException {
        assertEquals("@Tristan Hi @Tristan, I am AboHotelBis.",
                cell.ask(new Tweet("Tristan", "Hi")));

    }
    
    @Test
    public final void testNoQuestion() throws CloneNotSupportedException {
        assertEquals("@Tristan I don't understand your question.",
                cell.ask(new Tweet("Tristan", "This is not a question")));

    }


}
