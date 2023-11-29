package data.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

import data.Lugar;

class LugarTest {
	@Test
	void testConstructor() {
		Lugar lugar = new Lugar("Oeste");
		assertNull(lugar.getId());
		assertEquals("Oeste", lugar.getName());
		assertEquals(null, lugar.getDeletedAt());
	}
	
	@Test
	void testSets() {
		Lugar lugar = new Lugar("Oeste");
		lugar.setName("Este");
		assertEquals("Este", lugar.getName());
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		Lugar lugar = Lugar.Get(1);
		assertEquals(1, lugar.getId());
		assertEquals("Norte", lugar.getName());
		
		assertEquals(null, Lugar.Get(4));
	}
	
	@Test
	void testSaveDelete() throws IOException, SQLException {
		Lugar lugar = new Lugar("O'Connell");
		lugar.Save();
		int iId = lugar.getId();
		assertEquals(iId, Lugar.Get(iId).getId());
		assertEquals("O'Connell", Lugar.Get(iId).getName());
		lugar.setName("Eugenio");
		lugar.Save();
		assertEquals("Eugenio", lugar.getName());
		
		lugar.Delete();
		assertNotNull(lugar.getDeletedAt());
		assertNull(Lugar.Get(iId));
	}
	
	@Test
	void testSearch() throws IOException, SQLException {
		List<Lugar> aLugar = Lugar.Search(null);
		assertEquals(3, aLugar.size());
		assertEquals("Lavadero", aLugar.get(0).getName());
		assertEquals("Norte", aLugar.get(1).getName());
		assertEquals("Sur", aLugar.get(2).getName());
		
		aLugar = Lugar.Search("Norte");
		assertEquals(1, aLugar.size());
		assertEquals("Norte", aLugar.get(0).getName());
		
		aLugar = Lugar.Search("e");
		assertEquals(2, aLugar.size());
		assertEquals("Lavadero", aLugar.get(0).getName());
		assertEquals("Norte", aLugar.get(1).getName());
		
		aLugar = Lugar.Search("ATRUSCUS");
		assertEquals(0, aLugar.size());
	}
}