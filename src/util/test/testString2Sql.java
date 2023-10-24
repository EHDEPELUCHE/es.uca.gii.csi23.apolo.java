package util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import util.Database;

class testString2Sql {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void string2SqlTest() {
		assertEquals("hola", Database.String2Sql("hola", false, false));
		assertEquals("%hola%", Database.String2Sql("hola", false, true));
		assertEquals("'hola'", Database.String2Sql("hola", true, false));
		assertEquals("'%hola%'", Database.String2Sql("hola", true, true));
		assertEquals("O''Connell", Database.String2Sql("O'Connell", false, false));
		assertEquals("'O''Connell'", Database.String2Sql("O'Connell", true, false));
		assertEquals("%''Smith ''%", Database.String2Sql("'Smith '", false, true));
		assertEquals("'''Smith '''", Database.String2Sql("'Smith '", true, false));
		assertEquals("'%''Smith ''%'", Database.String2Sql("'Smith '", true, true));
	}

}
