package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionDataBase;
import model.Tratamento;

public class TratamentoDAO {

    public void create(Tratamento tratamento) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Tratamento values(?, ?, ?)");
            stmt.setString(1, tratamento.getNomeTratamento());
            stmt.setString(2, tratamento.getDescricao());
            stmt.setString(3, tratamento.getPrecoTratamento());
            stmt.execute();
            System.out.println("Tratamento cadastrado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar tratamento!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Tratamento> read() {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tratamento> tratamentos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Tratamento");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Tratamento tratamento = new Tratamento();
                tratamento.setIdTratamento(rs.getString("idTratamento"));
                tratamento.setNomeTratamento(rs.getString("nomeTratamento"));
                tratamento.setDescricao(rs.getString("descricao"));
                tratamento.setPrecoTratamento(rs.getString("precoTratamento"));
                tratamentos.add(tratamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler tratamentos!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        return tratamentos;
    }

    public void update(Tratamento tratamento) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Tratamento SET nomeTratamento=?, descricao=?, precoTratamento=? WHERE idTratamento=?");
            stmt.setString(1, tratamento.getNomeTratamento());
            stmt.setString(2, tratamento.getDescricao());
            stmt.setString(3, tratamento.getPrecoTratamento());
            stmt.setString(4, tratamento.getIdTratamento());
            stmt.execute();
            System.out.println("Tratamento atualizado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tratamento!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public void delete(String idTratamento) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Tratamento WHERE idTratamento=?");
            stmt.setString(1, idTratamento);
            stmt.execute();
            System.out.println("Tratamento excluido!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir tratamento!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }
}
