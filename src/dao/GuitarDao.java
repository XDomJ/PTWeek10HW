package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Guitar;

public class GuitarDao {
	
	private Connection connection;
	private final String SHOW_GUITAR_QUERY = "SELECT * FROM Guitars";
	private final String GET_INFO_BY_ID_QUERY = "SELECT * FROM Guitars WHERE guitar_id = ?";
	private final String CREATE_GUITAR_QUERY = "INSERT INTO Guitars(Make_Model, Style, Nickname) VALUES (?,?,?)";
	private final String EDIT_MAKE_QUERY = "UPDATE Guitars SET Make_Model = ? WHERE guitar_id = ?";
	private final String EDIT_STYLE_QUERY = "UPDATE Guitars SET style = ? WHERE guitar_id = ?";
	private final String EDIT_NICKNAME_QUERY = "UPDATE Guitars SET Nickname = ? WHERE guitar_id = ?";
	private final String DELETE_GUITAR_QUERY = "DELETE FROM Guitars WHERE guitar_id = ?";
	
	public GuitarDao() {
		connection = DBConn.getConnection();
	}
	
	public List<Guitar> showGuitars() throws SQLException {
		ResultSet rs = connection.prepareStatement(SHOW_GUITAR_QUERY).executeQuery();
		List<Guitar> guitars = new ArrayList<>();
		
		while (rs.next()) {
			guitars.add(new Guitar(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}		
		return guitars;
	}
	
	public Guitar getByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_INFO_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return new Guitar(id, rs.getString(2), rs.getString(3), rs.getString(4));
	}
	
	public void createGuitar(String makeModel, String style, String nickname) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_GUITAR_QUERY);
		ps.setString(1, makeModel);
		ps.setString(2, style);
		ps.setString(3, nickname);
		ps.executeUpdate();
	}
	
	public void editGuitar(int choice, int id, String newInfo) throws SQLException {
		PreparedStatement ps;
		if (choice == 1) {
			ps = connection.prepareStatement(EDIT_MAKE_QUERY);
			ps.setString(1, newInfo);
			ps.setInt(2, id);
			ps.executeUpdate();
		} else if (choice == 2) {
			ps = connection.prepareStatement(EDIT_STYLE_QUERY);
			ps.setString(1, newInfo);
			ps.setInt(2, id);
			ps.executeUpdate();
		} else if (choice == 3) {
			ps = connection.prepareCall(EDIT_NICKNAME_QUERY);
			ps.setString(1, newInfo);
			ps.setInt(2, id);
			ps.executeUpdate();
		} else {
			System.out.println("Invalid option selected.");
			return;
		}		
	}
	
	public void deleteGuitar(int decision) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_GUITAR_QUERY);
		ps.setInt(1, decision);
		ps.executeUpdate();
	}
}
