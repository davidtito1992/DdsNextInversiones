package DB;

import java.sql.Connection;
import java.util.ArrayList;

import dataManagment.dataLoader.DataLoader;

import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

public class DBLoader implements DataLoader {

	private static Connection conn = null;

	public static void conectar() {
		try {
			if (conn == null) {
				// Parametros
				String ddbbDriver = "org.hsqldb.jdbcDriver"; // CAMBIAR 
				String connectioString = "jdbc:hsqldb:hsql://localhost/xdb"; // CAMBIAR 
				String user = "sa"; // CAMBIAR 
				String password = ""; // CAMBIAR 

				// Import del driver en vivo
				Class.forName(ddbbDriver);

				// Establezco la conexion
				conn = DriverManager.getConnection(connectioString, user, password);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void desconectar() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	public static ResultSet consulta(String sql) {
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			pstm.close();
			return rs;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static ResultSet consulta(String sql, Parametro[] parametros) {
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			for (int i = 0; i < parametros.length; i++) {
				switch (parametros[i].tipo.getValor()) {
				case 1:
					pstm.setInt(i + 1, (int) parametros[i].valor);
					break;
				case 2:
					pstm.setString(i + 1, (String) parametros[i].valor);
					break;
				case 3:
					pstm.setTimestamp(i + 1, (Timestamp) parametros[i].valor);
					break;
				}
			}

			ResultSet rs = pstm.executeQuery();
			pstm.close();
			return rs;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static int update(String sql, Parametro[] parametros) {
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			for (int i = 0; i < parametros.length; i++) {
				switch (parametros[i].tipo.getValor()) {
				case 1:
					pstm.setInt(i + 1, (int) parametros[i].valor);
					break;
				case 2:
					pstm.setString(i + 1, (String) parametros[i].valor);
					break;
				case 3:
					pstm.setTimestamp(i + 1, (Timestamp) parametros[i].valor);
					break;
				}
			}
			int result = pstm.executeUpdate();
			pstm.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int update(String sql) {
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			int result = pstm.executeUpdate();
			pstm.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int insertar(String sql, Parametro[] parametros, String table) {
		try {
			if (update(sql, parametros) == 1) {
				ResultSet rs = consulta("SELECT TOP 1 ID FROM " + table + " ORDER BY ID DESC");
				if (rs.next()) {
					return rs.getInt("ID");
				} else {
					return -1;
				}

			} else {
				return -1;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int borrar(String sql, Parametro[] parametros) {
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			for (int i = 0; i < parametros.length; i++) {
				switch (parametros[i].tipo.getValor()) {
				case 1:
					pstm.setInt(i + 1, (int) parametros[i].valor);
					break;
				case 2:
					pstm.setString(i + 1, (String) parametros[i].valor);
					break;
				case 3:
					pstm.setTimestamp(i + 1, (Timestamp) parametros[i].valor);
					break;
				}
			}
			int result = pstm.executeUpdate();
			pstm.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int borrar(String sql) {
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			int result = pstm.executeUpdate();
			pstm.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Empresa> getDataEmpresas() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RegistroIndicador> getDataIndicadores() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Metodologia> getDataMetodologias() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
