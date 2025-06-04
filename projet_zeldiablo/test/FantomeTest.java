import gameLaby.LabyJeu;
import gameLaby.casesSpe.CasePiege;
import gameLaby.casesSpe.Interrupteur;
import gameLaby.casesSpe.Porte;
import gameLaby.casesSpe.Sortie;
import gameLaby.entites.Entite;
import gameLaby.entites.Fantome;
import gameLaby.entites.Perso;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class FantomeTest {

	@Test
	public void testInitialisation() {
		Fantome fantome = new Fantome(1, 1);
		assertEquals(1, fantome.getX());
		assertEquals(1, fantome.getY());
		assertEquals(5, fantome.getHP());
		assertEquals(1, fantome.getAtk());
	}

	@Test
	public void testAttaquer() {
		Fantome fantome = new Fantome(1, 1);
		Fantome cible = new Fantome(2, 2) {
			@Override
			public void attaquer(Entite entite) {
				// Ne fait rien pour le test
			}
		};
		cible.setHp(10);
		fantome.attaquer(cible);
		assertEquals(9, cible.getHP());
	}

	@Test
	public void testEstVivant() {
		Fantome fantome = new Fantome(1, 1);
		assertTrue(fantome.estVivant());
		fantome.setHp(-5);
		assertFalse(fantome.estVivant());
	}

	@Test
	public void testPeutAttaquer() {
		Fantome fantome = new Fantome(1, 1);
		Fantome cibleAdjacente = new Fantome(1, 2) {
			@Override
			public void attaquer(Entite entite) {
				// Ne fait rien pour le test
			}
		};
		Fantome cibleNonAdjacente = new Fantome(3, 3) {
			@Override
			public void attaquer(Entite entite) {
				// Ne fait rien pour le test
			}
		};
		assertTrue(fantome.peutAttaquer(cibleAdjacente));
		assertFalse(fantome.peutAttaquer(cibleNonAdjacente));
	}
}
