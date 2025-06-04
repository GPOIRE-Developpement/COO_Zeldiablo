import gameLaby.entites.Entite;
import gameLaby.entites.Monstre;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MonstreTest {

	@Test
	void testInitialisation() {
		Monstre monstre = new Monstre(1, 1);
		assertEquals(1, monstre.getX());
		assertEquals(1, monstre.getY());
		assertEquals(5, monstre.getHP());
		assertEquals(1, monstre.getAtk());
	}

	@Test
	void testAttaquer() {
		Monstre monstre = new Monstre(1, 1);
		Monstre cible = new Monstre(2, 2) {
			@Override
			public void attaquer(Entite entite) {
				// Ne fait rien pour le test
			}
		};
		cible.setHp(10);
		monstre.attaquer(cible);
		assertEquals(9, cible.getHP());
	}

	@Test
	void testEstVivant() {
		Monstre monstre = new Monstre(1, 1);
		assertTrue(monstre.estVivant());
		monstre.setHp(-5);
		assertFalse(monstre.estVivant());
	}

	@Test
	void testPeutAttaquer() {
		Monstre monstre = new Monstre(1, 1);
		Monstre cibleAdjacente = new Monstre(1, 2) {
			@Override
			public void attaquer(Entite entite) {
				// Ne fait rien pour le test
			}
		};
		Monstre cibleNonAdjacente = new Monstre(3, 3) {
			@Override
			public void attaquer(Entite entite) {
				// Ne fait rien pour le test
			}
		};
		assertTrue(monstre.peutAttaquer(cibleAdjacente));
		assertFalse(monstre.peutAttaquer(cibleNonAdjacente));
	}
}

