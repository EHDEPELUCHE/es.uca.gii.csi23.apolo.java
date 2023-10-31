package data;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Database;
public class Excursion {
	private Integer _iId = null;
	private void setId(Integer iId) { _iId = iId; }
	public Integer getId() { return _iId; }
	
	private String _sName = null;
	public void setName(String sName) { 
		if (sName == null || sName == "") 
			throw new IllegalArgumentException ("El nombre no puede ser vacío o null");
		else
			_sName = sName; 
	}
	public String getName() { return _sName; }
	
	public Excursion(String sName) { this(null, sName);}
	
	/**
	 * @brief Constructor privado de la clase
	 * @param iId Es el id de la excursion
	 * @param sName Es el nombre de la excursion
	 */
	private Excursion(Integer iId, String sName) { setName(sName); _iId = iId; }
	
	public String toString() { return (super.toString()+ ":" + this.getId() + ":" + this.getName());	}
	
	/**
	 * @param iPrimaryKey Es la clave primaria Integer de una instancia de la clase excursion
	 * @return la instancia excursion de la clase
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Excursion Get(Integer iId) throws IOException, SQLException {
		Excursion excursion;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT nombre FROM Excursion WHERE id = " + iId);
			if(rs.next())excursion = new Excursion(iId, rs.getString("nombre"));
			else excursion = null;
			 	
		}
		catch (SQLException e) { throw e; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
		return excursion;
	}
	
}