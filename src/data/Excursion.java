package data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Database;

public class Excursion {
	private Integer _iId = null;
	
	public Integer getId() { return _iId; }
	
	private String _sName = null;
	
	public void setName(String sName) { 
		if (sName == null || sName == "") 
			throw new IllegalArgumentException ("El nombre no puede ser vacío o null");
		else _sName = sName; 
	}
	
	public String getName() {return _sName;}
	
	public Excursion(String sName) {this(null, sName);}
	
	private Excursion(Integer iId, String sName) {setName(sName); _iId = iId;}
	
	public String toString() {return (super.toString() + ":" + this.getId() + ":" + this.getName());}
	
	/**
	 * @param	iId Es la clave primaria tipo int de una instancia de la clase excursion
	 * 			Es de tipo int y no integer para no permitir valores nulos.
	 * @return	Una instancia excursion de la clase Excursion
	 * @throws	IOException
	 * @throws	SQLException
	 */
	public static Excursion Get(int iId) throws IOException, SQLException {
		Excursion excursion = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT nombre FROM Excursion WHERE id = " + iId);
			if(rs.next()) excursion = new Excursion(iId, rs.getString("nombre"));
		}
		catch (SQLException e) {throw e;}
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
		return excursion;
	}
	
}