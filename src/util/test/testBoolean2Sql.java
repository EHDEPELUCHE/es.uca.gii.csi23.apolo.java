package util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import util.Database;

class testBoolean2Sql {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void boolean2SqlTest() {
		assertEquals(1, Database.Boolean2Sql(true));
		assertEquals(0, Database.Boolean2Sql(false));
	}
}
