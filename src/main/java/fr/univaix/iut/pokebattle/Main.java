package fr.univaix.iut.pokebattle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void createDatabase() {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("pokebattlePU");
		EntityManager em = emf.createEntityManager();
		DAOPokemonJPA daopok = new DAOPokemonJPA(em);
		DAOAttaqueJPA daoattack = new DAOAttaqueJPA(em);
		
		Attaque attack1 = new Attaque();
		attack1.setNomAttaque("Ligotage");
		attack1.setPp(20);
		attack1.setPuissance(15);
		attack1.setPrecision(85);
		daoattack.insert(attack1);
		
		Attaque attack2 = new Attaque();
		attack2.setNomAttaque("Groz'Yeux");
		attack2.setPp(30);
		attack2.setPrecision(100);
		daoattack.insert(attack2);
		
		/**attaques = null;
		attaques.add(attack1);
		attaques.add(attack2);**/
		
		Pokemon pok = new Pokemon();
		pok.setName("AboHotelBis");
		//pok.setAttaques(attaques);
		pok.setType1("Poison");
		pok.setType2(null);
		pok.setCharac(null);
		pok.setColor("Violet");
		pok.setOwner(null);
		pok.setEvolution("Arbok");
		pok.setPrevolution(null);
		pok.setCry("abo abo");
		pok.setNum(023);
		pok.setXP(0);
		pok.setLevel(1);
		pok.setPVMax(30);
		pok.setPVNow(30);
		pok.setHeight((float) 2.0);
		pok.setWeight((float) 6.9);
		daopok.insert(pok);
	
		
	}
}
