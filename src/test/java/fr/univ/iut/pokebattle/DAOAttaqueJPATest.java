package fr.univ.iut.pokebattle;

import fr.univaix.iut.pokebattle.*;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Connection;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class DAOAttaqueJPATest {

	private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;

    private DAOAttaque dao = new DAOAttaqueJPA(entityManager);

    @BeforeClass
    public static void initTestFixture() throws Exception {
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

    @Test
    public void testFindByType() throws Exception {
        List<Attaque> attaques = dao.findByType("POISON");
        assertThat(attaques.get(0).getNomAttaque()).isEqualTo("Dard-Venin");
    }

    @Test
    public void testFindAll() throws Exception {
        List<Attaque> attaques = dao.findAll();
        assertThat(attaques.get(0).getNomAttaque()).isEqualTo("Ligotage");
        assertThat(attaques.get(1).getNomAttaque()).isEqualTo("Groz'Yeux");
        assertThat(attaques.get(2).getNomAttaque()).isEqualTo("Dard-Venin");
    }

    @Test
    public void testGetById() throws Exception {
        assertThat(dao.getById("Groz'Yeux").getNomAttaque()).isEqualTo("Groz'Yeux");
        assertThat(dao.getById("Ligotage").getNomAttaque()).isEqualTo("Ligotage");
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(dao.getById("Ligotage"));
        assertThat(dao.getById("Ligotage")).isNull();
    }

    @Test
    public void testInsert() throws Exception {
        Attaque acide = new Attaque();
        acide.setNomAttaque("Acide");
        acide.setTypeAttaque("POISON");
        dao.insert(acide);
        assertThat(dao.getById("Acide").getNomAttaque()).isEqualTo("Acide");
        assertThat(dao.getById("Acide").getTypeAttaque()).isEqualTo("POISON");
    }

    @Test
    public void testUpdate() throws Exception {
        Attaque ligo = dao.getById("Ligotage");
        assertThat(ligo.getPp()).isGreaterThan(0);
        ligo.setPp(-20);
        dao.update(ligo);
        assertThat(dao.getById("Ligotage").getPp()).isLessThan(0);
    }
}