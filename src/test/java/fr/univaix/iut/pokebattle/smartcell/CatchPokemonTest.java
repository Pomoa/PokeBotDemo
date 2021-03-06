package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
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

import fr.univaix.iut.pokebattle.DAOPokemon;
import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class CatchPokemonTest {

        private static EntityManager entityManager;
        private static FlatXmlDataSet dataset;
        private static DatabaseConnection dbUnitConnection;
        private static EntityManagerFactory entityManagerFactory;
        private DAOPokemon dao;

        CatchPokemonCell cell;
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
            cell = new CatchPokemonCell(entityManager);
        }

        @After
        public final void finishTestFixture() throws Exception {
            entityManager.close();
            entityManagerFactory.close();
        }

            @Test
            public final void testCaptureAboHotelBisSuccess()
                    throws CloneNotSupportedException {
                assertEquals("@CaptainObvious You are now my owner.",
                        cell.ask(new Tweet("CaptainObvious",
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
                cell.ask(new Tweet("CaptainObvious",
                        "@AboHotelBis pokeball !")));
            }
}