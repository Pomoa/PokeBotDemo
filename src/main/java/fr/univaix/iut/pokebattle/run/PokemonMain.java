package fr.univaix.iut.pokebattle.run;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.Main;
import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.tuse.TUSEException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PokemonMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonMain.class);

    private PokemonMain() { }

    public static void main(String[] args) throws URISyntaxException {
        try {
            String databaseUrl = System.getenv("DATABASE_URL");
            URI dbUri = new URI(databaseUrl);
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
            
            Map<String, String> props = new HashMap<String, String>();
            props.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            props.put("eclipselink.target-database", "PostgreSQL");
            props.put("javax.persistence.jdbc.url", dbUrl);
            props.put("javax.persistence.jdbc.user", username);
            props.put("javax.persistence.jdbc.password", password);
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokebattlePU", props);
            EntityManager em = emf.createEntityManager();
            
            /**EntityManagerFactory entityManagerFactory = Persistence.
                    createEntityManagerFactory("pokebattlePU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();**/
            
            BotRunner.runBot(new PokeBot(em), "twitter4j.properties");
            Main.createDatabase();
        } catch (TUSEException e) {
            LOGGER.error("Erreur sérieuse dans le BotRunner", e);
        }
    }
}
