package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionDataBase;
import model.Paciente;


public class PacienteDAO {

	public void create(Paciente paciente) {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO Paciente (nomePaciente, cpfPaciente, dataNasc, telefone, email, endereco, genero, estadoCivil, nacionalidade, rg) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, paciente.getNomePaciente());
			stmt.setString(2, paciente.getCpfPaciente());
			stmt.setString(3, paciente.getDataNasc());
			stmt.setString(4, paciente.getTelefone());
			stmt.setString(5, paciente.getEmail());
			stmt.setString(6, paciente.getEndereco());
			stmt.setString(7, paciente.getGenero());
			stmt.setString(8, paciente.getEstadoCivil());
			stmt.setString(9, paciente.getNacionalidade());
			stmt.setString(10, paciente.getRg());

			stmt.execute();
			System.out.println("Paciente cadastrado!");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar paciente!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}
	}

	public ArrayList<Paciente> read() {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Paciente> pacientes = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM Paciente");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getString("idPaciente"));
				paciente.setNomePaciente(rs.getString("nomePaciente"));
				paciente.setCpfPaciente(rs.getString("cpfPaciente"));
				paciente.setDataNasc(rs.getString("dataNasc"));
				paciente.setTelefone(rs.getString("telefone"));
				paciente.setEmail(rs.getString("email"));
				paciente.setEndereco(rs.getString("endereco"));
				paciente.setGenero(rs.getString("genero"));
				paciente.setEstadoCivil(rs.getString("estadoCivil"));
				paciente.setNacionalidade(rs.getString("nacionalidade"));
				paciente.setRg(rs.getString("rg"));

				pacientes.add(paciente);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler os dados!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return pacientes;
	}

	public void update(Paciente paciente) {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("UPDATE Paciente SET nomePaciente=?,dataNasc=?, telefone=?, email=?, endereco=?, genero=?, estadoCivil=?, nacionalidade=?, rg=?WHERE cpfPaciente=?");
			stmt.setString(1, paciente.getNomePaciente());
			stmt.setString(2, paciente.getDataNasc());
			stmt.setString(3, paciente.getTelefone());
			stmt.setString(4, paciente.getEmail());
			stmt.setString(5, paciente.getEndereco());
			stmt.setString(6, paciente.getGenero());
			stmt.setString(7, paciente.getEstadoCivil());
			stmt.setString(8, paciente.getNacionalidade());
			stmt.setString(9, paciente.getRg());
			stmt.setString(10, paciente.getCpfPaciente());
			

			stmt.execute();
			System.out.println("Paciente atualizado!");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar paciente!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}
	}

	public void delete(String cpfPaciente) {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("DELETE FROM Paciente WHERE cpfPaciente = ?");
			stmt.setString(1, cpfPaciente);
			stmt.execute();
			System.out.println("Paciente excluido!");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir paciente!", e);
		} finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}
	}

	public ArrayList<Paciente> search(String pesquisa) {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Paciente>pacientes = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * FROM Paciente where nomePaciente like ? or cpfPaciente like ?");
			stmt.setString(1,"%" + pesquisa + "%");
			stmt.setString(2,"%" + pesquisa + "%");
			rs = stmt.executeQuery();

			while(rs.next()) {

				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getString("idPaciente"));
				paciente.setNomePaciente(rs.getString("nomePaciente"));
				paciente.setCpfPaciente(rs.getString("cpfPaciente"));
				paciente.setDataNasc(rs.getString("dataNasc"));
				paciente.setTelefone(rs.getString("telefone"));
				paciente.setEmail(rs.getString("email"));
				paciente.setEndereco(rs.getString("endereco"));
				paciente.setGenero(rs.getString("genero"));
				paciente.setEstadoCivil(rs.getString("estadoCivil"));
				paciente.setNacionalidade(rs.getString("nacionalidade"));
				paciente.setRg(rs.getString("rg"));

				pacientes.add(paciente);			
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}






		return pacientes;

	}

}
