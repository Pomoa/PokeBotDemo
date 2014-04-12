package fr.univaix.iut.pokebattle.run;

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

    public static void main(String[] args) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.
                    createEntityManagerFactory("pokebattlePUTest");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            BotRunner.runBot(new PokeBot(entityManager), "twitter4j.properties");
            Main.createDatabase();
        } catch (TUSEException e) {
            LOGGER.error("Erreur sérieuse dans le BotRunner", e);
        }
    }
}
