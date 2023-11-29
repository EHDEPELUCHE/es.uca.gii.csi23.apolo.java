package data.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

import data.Excursion;
import data.Lugar;

class ExcursionTest {
	@Test
	void testConstructor() throws IOException, SQLException {
		Excursion excursion = new Excursion("Paseo por el mercado", Lugar.Get(1));
		assertNull(excursion.getId());
		assertEquals("Paseo por el mercado", excursion.getName());
		assertEquals("Norte", excursion.getLugar().getName());
		assertEquals(1, excursion.getLugar().getId());
		assertEquals(null, excursion.getDeletedAt());
	}
	
	@Test
	void testSets() throws IOException, SQLException {
		Excursion excursion = new Excursion("Paseo por el mercado", Lugar.Get(1));
		excursion.setName("Paseo por el pueblo");
		excursion.setLugar(Lugar.Get(2));
		assertEquals("Paseo por el pueblo", excursion.getName());
		assertEquals("Sur", excursion.getLugar().getName());
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		Excursion excursion = Excursion.Get(1);
		assertEquals(1, excursion.getId());
		assertEquals("Excursión a la Escuela de Esgrima", excursion.getName());
		assertEquals("Oeste", excursion.getLugar().getName());
		
		assertEquals(null, Excursion.Get(4));
	}
	
	@Test
	void testSaveDelete() throws IOException, SQLException {
		Excursion excursion = new Excursion("O'Connell", Lugar.Get(1));
		excursion.Save();
		int iId = excursion.getId();
		assertEquals(iId, Excursion.Get(iId).getId());
		assertEquals("O'Connell", Excursion.Get(iId).getName());
		assertEquals("Norte", Excursion.Get(iId).getLugar().getName());
		excursion.setName("Eugenio");
		excursion.setLugar(Lugar.Get(2));
		excursion.Save();
		assertEquals("Eugenio", excursion.getName());
		assertEquals("Sur", Excursion.Get(iId).getLugar().getName());
		
		excursion.Delete();
		assertNotNull(excursion.getDeletedAt());
		assertNull(Excursion.Get(iId));
	}
	
	@Test
	void testSearch() throws IOException, SQLException {
		List<Excursion> aExcursion = Excursion.Search(null, null);
		assertEquals(4, aExcursion.size());
		assertEquals("Excursión a la Escuela de Esgrima", aExcursion.get(0).getName());
		assertEquals("Oeste", aExcursion.get(0).getLugar().getName());
		assertEquals("Excursión a la Fuente de la Gran Hada", aExcursion.get(1).getName());
		assertEquals("Norte", aExcursion.get(1).getLugar().getName());
		assertEquals("Excursión a la Tienda de Bombas", aExcursion.get(2).getName());
		assertEquals("Oeste", aExcursion.get(2).getLugar().getName());
		assertEquals("Excursión al Bar Lácteo", aExcursion.get(3).getName());
		assertEquals("Este", aExcursion.get(3).getLugar().getName());
		
		aExcursion = Excursion.Search("Excursión a la Escuela de Esgrima", null);
		assertEquals(1, aExcursion.size());
		assertEquals("Excursión a la Escuela de Esgrima", aExcursion.get(0).getName());
		assertEquals("Oeste", aExcursion.get(0).getLugar().getName());
		
		aExcursion = Excursion.Search("gr", null);
		assertEquals(2, aExcursion.size());
		assertEquals("Excursión a la Escuela de Esgrima", aExcursion.get(0).getName());
		assertEquals("Oeste", aExcursion.get(0).getLugar().getName());
		assertEquals("Excursión a la Fuente de la Gran Hada", aExcursion.get(1).getName());
		assertEquals("Norte", aExcursion.get(1).getLugar().getName());
		
		aExcursion = Excursion.Search("ATRUSCUS", null);
		assertEquals(0, aExcursion.size());
		
		aExcursion = Excursion.Search(null, "Norte");
		assertEquals(1, aExcursion.size());
		assertEquals("Excursión a la Fuente de la Gran Hada", aExcursion.get(0).getName());
		assertEquals("Norte", aExcursion.get(0).getLugar().getName());
		
		aExcursion = Excursion.Search(null, "Oeste");
		assertEquals(2, aExcursion.size());
		assertEquals("Excursión a la Escuela de Esgrima", aExcursion.get(0).getName());
		assertEquals("Oeste", aExcursion.get(0).getLugar().getName());
		assertEquals("Excursión a la Tienda de Bombas", aExcursion.get(1).getName());
		assertEquals("Oeste", aExcursion.get(1).getLugar().getName());
		
		aExcursion = Excursion.Search(null, "ATRUSCUS");
		assertEquals(0, aExcursion.size());
	}
}