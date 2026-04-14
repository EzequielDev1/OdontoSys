package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionDataBase;
import model.ConsultaTratamento;
import model.Paciente;

public class ConsultaTratamentoDAO {

    public void create(ConsultaTratamento ct) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO ConsultaTratamento values(?, ?, ?, ?)");
            stmt.setString(1, ct.getIdConsulta());
            stmt.setString(2, ct.getIdTratamento());
            stmt.setString(3, ct.getPrecoTratamento());
            stmt.setString(4, ct.getObservacao());
            stmt.execute();
            System.out.println("ConsultaTratamento cadastrado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar ConsultaTratamento!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public ArrayList<ConsultaTratamento> read() {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ConsultaTratamento> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM ConsultaTratamento");
            rs = stmt.executeQuery();
            while(rs.next()) {
                ConsultaTratamento ct = new ConsultaTratamento();
                ct.setIdConsultaTratamento(rs.getString("idConsultaTratamento"));
                ct.setIdConsulta(rs.getString("idConsulta"));
                ct.setIdTratamento(rs.getString("idTratamento"));
                ct.setPrecoTratamento(rs.getString("precoTratamento"));
                ct.setObservacao(rs.getString("observacao"));
                lista.add(ct);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler ConsultaTratamento!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    public void update(ConsultaTratamento ct) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE ConsultaTratamento SET idConsulta=?, idTratamento=?, precoTratamento=?, observacao=? WHERE idConsultaTratamento=?");
            stmt.setString(1, ct.getIdConsulta());
            stmt.setString(2, ct.getIdTratamento());
            stmt.setString(3, ct.getPrecoTratamento());
            stmt.setString(4, ct.getObservacao());
            stmt.setString(5, ct.getIdConsultaTratamento());
            stmt.execute();
            System.out.println("ConsultaTratamento atualizado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ConsultaTratamento!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public void delete(String id) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM ConsultaTratamento WHERE idConsultaTratamento=?");
            stmt.setString(1, id);
            stmt.execute();
            System.out.println("ConsultaTratamento excluido!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir ConsultaTratamento!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<ConsultaTratamento> Search(String pesquisa){
    	Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ConsultaTratamento>consultaPaciente = new ArrayList<>();
    	
    	try {
    		stmt = con.prepareStatement("SELECT * FROM ConsultaTratamento where .... like ? or .... like ?");
			stmt.setString(1,"%" + pesquisa + "%");
			stmt.setString(2,"%" + pesquisa + "%");
			rs = stmt.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
		return null;
    	
    }
}
