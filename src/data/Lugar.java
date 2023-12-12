package data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lugar {
	private Integer _iId = null;
	public Integer getId() { return _iId; }
	
	private String _sName = null;
	public String getName() { return _sName; }
	public void setName(String sName) { 
		if (sName == null || sName == "") 
			throw new IllegalArgumentException ("El nombre no puede ser vacío o null.");
		_sName = sName; 
	}
	
	private Date _dtDeletedAt = null;
	public Date getDeletedAt() { return _dtDeletedAt; }

	public Lugar(String sName) { this(null, sName); }
	
	private Lugar(Integer iId, String sName) { setName(sName); _iId = iId; }
	
	public String toString() { return this.getName(); }
	
	/**
	 * @param	iId Es la clave primaria tipo int de una instancia de la clase Lugar
	 * 			Es de tipo int y no integer para no permitir valores nulos.
	 * @return	Una instancia lugar de la clase Lugar
	 * @throws	IOException
	 * @throws	SQLException
	 */
	public static Lugar Get(int iId) throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT nombre FROM lugar WHERE id = " + iId);
			if(rs.next()) return new Lugar(iId, rs.getString("nombre"));
			return null;
		}
		catch (SQLException e) { throw e; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	public void Save() throws IOException, SQLException {
		Connection con = null;
		try {
			con = Database.Connection();
			if (_iId == null) {
				con.createStatement().executeUpdate("INSERT INTO lugar (nombre) VALUES ("
						+ Database.String2Sql(_sName, true, false) + ")");
				_iId = Database.LastId(con);
			} else
				con.createStatement().executeUpdate("UPDATE lugar SET nombre=" 
						+ Database.String2Sql(_sName, true, false) + " WHERE id = " + _iId);
		}
		catch (SQLException e) { throw e; }
		finally { if (con != null) con.close(); }	
	}

	public void Delete() throws IOException, SQLException {
		if (_iId == null || _dtDeletedAt != null)
			throw new IllegalStateException("El lugar que intenta borrar no existe.");
		
		Connection con = null;
		try {
			con = Database.Connection();
			con.createStatement().executeUpdate("DELETE FROM lugar WHERE id = " + _iId);
			_dtDeletedAt = new Date();
		}
		catch (SQLException e) { throw e; }
		finally { if (con != null) con.close(); }	
	}
	
	public static List<Lugar> Search(String sName) throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre FROM lugar " + Where(sName) 
					+ " ORDER BY nombre ASC"); 
			
			List<Lugar> aLugar = new ArrayList<Lugar>();
			while (rs.next()) aLugar.add(new Lugar(rs.getInt("id"), rs.getString("nombre")));
			return aLugar;
		}
		catch (SQLException e) { throw e; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	private static String Where(String sName) {
		if (sName != null)
			return "WHERE nombre LIKE " + Database.String2Sql(sName, true, true);
		return "";
	}
}