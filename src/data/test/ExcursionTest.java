package data.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

import data.Excursion;

class ExcursionTest {
	@Test
	void testConstructor() {
		Excursion excursion = new Excursion("Paseo por el mercado");
		assertNull(excursion.getId());
		assertEquals("Paseo por el mercado", excursion.getName());
		assertEquals(null, excursion.getDeletedAt());
	}
	
	@Test
	void testSets() {
		Excursion excursion = new Excursion("Paseo por el mercado");
		excursion.setName("Paseo por el pueblo");
		assertEquals("Paseo por el pueblo", excursion.getName());
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		Excursion excursion = Excursion.Get(1);
		assertEquals(1, excursion.getId());
		assertEquals("Excursión a la Escuela de Esgrima", excursion.getName());
		
		assertEquals(null, Excursion.Get(4));
	}
	
	@Test
	void testSaveDelete() throws IOException, SQLException {
		Excursion excursion = new Excursion("O'Connell");
		excursion.Save();
		int iId = excursion.getId();
		assertEquals(iId, Excursion.Get(iId).getId());
		assertEquals("O'Connell", Excursion.Get(iId).getName());
		excursion.setName("Eugenio");
		excursion.Save();
		assertEquals("Eugenio", excursion.getName());
		excursion.Delete();
		assertNotNull(excursion.getDeletedAt());
		assertNull(Excursion.Get(iId));
	}
	
	@Test
	void testSearch() throws IOException, SQLException {
		List<Excursion> aExcursion = Excursion.Search(null);
		assertEquals(3, aExcursion.size());
		assertEquals("Excursión a la Escuela de Esgrima", aExcursion.get(0).getName());
		assertEquals("Excursión a la Fuente de la Gran Hada", aExcursion.get(1).getName());
		assertEquals("Excursión a la Tienda de Bombas", aExcursion.get(2).getName());
		aExcursion = Excursion.Search("Excursión a la Escuela de Esgrima");
		assertEquals(1, aExcursion.size());
		assertEquals("Excursión a la Escuela de Esgrima", aExcursion.get(0).getName());
		aExcursion = Excursion.Search("gr");
		assertEquals(2, aExcursion.size());
		assertEquals("Excursión a la Escuela de Esgrima", aExcursion.get(0).getName());
		assertEquals("Excursión a la Fuente de la Gran Hada", aExcursion.get(1).getName());
		aExcursion = Excursion.Search("ATRUSCUS");
		assertEquals(0, aExcursion.size());
	}
}