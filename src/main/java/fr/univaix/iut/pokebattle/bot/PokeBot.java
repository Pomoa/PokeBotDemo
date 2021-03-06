package fr.univaix.iut.pokebattle.bot;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.DAOPokemonJPA;
import fr.univaix.iut.pokebattle.Pokemon;
import fr.univaix.iut.pokebattle.smartcell.AskAttackSpecificationsCell;
import fr.univaix.iut.pokebattle.smartcell.AskSpecificationsCell;
import fr.univaix.iut.pokebattle.smartcell.CatchPokemonCell;
import fr.univaix.iut.pokebattle.smartcell.HelloCell;
import fr.univaix.iut.pokebattle.smartcell.OwnerCell;
import fr.univaix.iut.pokebattle.smartcell.PokeAttackCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;




public class PokeBot implements Bot {

    private EntityManager em;

    private final SmartCell[] smartCells;

    public PokeBot(EntityManager enma){
        this.em = enma;

        smartCells = new SmartCell[] {
                new PokeAttackCell(em),
        		new CatchPokemonCell(em),
        		new AskSpecificationsCell(em),
        		new AskAttackSpecificationsCell(em),
                new OwnerCell(em),
                new HelloCell(em)
        };
    }

    /** final SmartCell[] smartCells = new SmartCell[] {.
    new HelloCell(),
    new OwnerCell(),
    new CatchPokemonCell(),
    new AskSpecificationsCell()
    };**/
    
    @Override
    public String ask(Tweet question) {
        for (SmartCell cell : smartCells) {
            String answer = cell.ask(question);
            if (answer != null) {
                return answer;
            }
        }
        return null;
    }
}
