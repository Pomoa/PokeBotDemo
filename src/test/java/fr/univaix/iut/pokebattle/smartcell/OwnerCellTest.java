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
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class OwnerCellTest {

    private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;

    private DAOPokemon dao = new DAOPokemonJPA(entityManager);

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

    OwnerCell cell = new OwnerCell(entityManager);


    @Test
    public final void testAskNoOwner() throws CloneNotSupportedException {
        assertEquals("@Tristan I don't have owner.",
                cell.ask(new Tweet("Tristan", "Do you have an owner ?")));

    }

    @Test
    public final void testASkOwner() throws CloneNotSupportedException {
        Pokemon abo = new Pokemon();
        abo = dao.getById("AboHotelBis");
        abo.setOwner("CaptainObvious");
        dao.insert(abo);
        assertEquals("@Tristan My owner is @CaptainObvious.",
                cell.ask(new Tweet("Tristan", "Do you have an owner ?")));
    }
}
