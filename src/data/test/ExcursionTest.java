package data.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import data.Excursion;

class ExcursionTest {
	@Test
	void testConstructor() {
		Excursion excursion = new Excursion("Paseo por el mercado");
		assertNull(excursion.getId());
		assertEquals(excursion.getName(), "Paseo por el mercado");
	}
	
	@Test
	void testSets() {
		Excursion excursion = new Excursion("Paseo por el mercado");
		//excursion.setId(3);
		excursion.setName("Paseo por el pueblo");
		//assertEquals(excursion.getId(), 3);
		assertEquals(excursion.getName(), "Paseo por el pueblo");
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		assertEquals(1, Excursion.Get(1).getId());
		assertEquals("Excursión a la Escuela de Esgrima", Excursion.Get(1).getName());
		assertEquals(null, Excursion.Get(4));
	}
}