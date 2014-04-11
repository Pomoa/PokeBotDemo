package fr.univaix.iut.pokebattle.bot;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.AskSpecificationsCell;
import fr.univaix.iut.pokebattle.smartcell.CatchPokemonCell;
import fr.univaix.iut.pokebattle.smartcell.HelloCell;
import fr.univaix.iut.pokebattle.smartcell.OwnerCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;




public class PokeBot implements Bot {

    private String em;

    private final SmartCell[] smartCells;

    public PokeBot(String enma){
        this.em = enma;
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory(em);
        EntityManager em = emf.createEntityManager();

        smartCells = new SmartCell[] {
                new HelloCell(em),
                new OwnerCell(em),
                new CatchPokemonCell(em),
                new AskSpecificationsCell(em)
        };
    }

    /** final SmartCell[] smartCells = new SmartCell[] {.
    new HelloCell(),
    new OwnerCell(),
    new CatchPokemonCell(),
    new AskSpecificationsCell()
    };**/

    public final String ask(final Tweet question) {
        String answer = "";
            for (SmartCell cell:smartCells) {
                if (cell.ask(question) != null) {
                   answer = answer + cell.ask(question) + " ";
                }

            }

       if ("".equals(answer)) {
           answer = "@" + question.getScreenName()
                        + " I don't understand your question.";
       } else {
           answer = answer.substring(0, answer.length() - 1);
       }

       Date dateTweet = new Date();
       Calendar calendar = GregorianCalendar.getInstance();
       calendar.setTime(dateTweet);

       return answer;
    }
}
