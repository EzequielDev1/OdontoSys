package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Connection.ConnectionDataBase;
import model.Consulta;
import model.Paciente;

public class ConsultaDAO {

    public void create(Consulta consulta) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Consulta values(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, consulta.getDataConsulta());
            stmt.setString(2, consulta.getHorario());
            stmt.setString(3, consulta.getStatusConsulta());
            stmt.setString(4, consulta.getPrecoConsulta());
            stmt.setString(5, consulta.getIdPaciente());
            stmt.setString(6, consulta.getIdFuncionario());
            stmt.setString(7, consulta.getObservacao());
            stmt.execute();
            System.out.println("Consulta cadastrada!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar consulta!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Consulta> read() {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Consulta> consultas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Consulta");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setIdConsulta(rs.getString("idConsulta"));
                consulta.setDataConsulta(rs.getString("dataConsulta"));
                consulta.setHorario(rs.getString("horario"));
                consulta.setStatusConsulta(rs.getString("statusConsulta"));
                consulta.setPrecoConsulta(rs.getString("precoConsulta"));
                consulta.setIdPaciente(rs.getString("idPaciente"));
                consulta.setIdFuncionario(rs.getString("idFuncionario"));
                consulta.setObservacao(rs.getString("observacao"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler consultas!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        return consultas;
    }

    //Aparecer na tabela no AgendamentoConsulta
    public ArrayList<Consulta> readTableConsulta(){
    	Connection con =ConnectionDataBase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs=null;
    	ArrayList<Consulta> consultas = new ArrayList<>();
    	
    	try {
            stmt = con.prepareStatement("SELECT * FROM VW_AgendamentoConsulta");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setIdConsulta(rs.getString("idConsulta"));
                consulta.setIdPaciente(rs.getString("nomePaciente"));
                consulta.setDataConsulta(rs.getString("dataConsulta"));
                consulta.setHorario(rs.getString("horario").replace(":00.0000000", ""));//O replace foi para formatar o horario
                consulta.setStatusConsulta(rs.getString("statusConsulta"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler consultas!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt, rs);
        }
		return consultas;
    }
    
    public void update(Consulta consulta) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Consulta SET dataConsulta=?, horario=?, statusConsulta=?, precoConsulta=?, idPaciente=?, idFuncionario=?, observacao=? WHERE idConsulta=?");
            stmt.setString(1, consulta.getDataConsulta());
            stmt.setString(2, consulta.getHorario());
            stmt.setString(3, consulta.getStatusConsulta());
            stmt.setString(4, consulta.getPrecoConsulta());
            stmt.setString(5, consulta.getIdPaciente());
            stmt.setString(6, consulta.getIdFuncionario());
            stmt.setString(7, consulta.getObservacao());
            stmt.setString(8, consulta.getIdConsulta());
            stmt.execute();
            System.out.println("Consulta atualizada!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public void delete(String idConsulta) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE * FROM Consulta WHERE idConsulta=?");
            stmt.setString(1, idConsulta);
            stmt.execute();
            System.out.println("Consulta excluida!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir consulta!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    

    //Para pesquisar na tabela ControllerAgendamentoConsulta
    public ArrayList<Consulta> search(Consulta consulta1){
    	Connection con = ConnectionDataBase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<Consulta> consultas = new ArrayList<>();
    	
    	try {
    		stmt = con.prepareStatement("SELECT * FROM VW_AgendamentoConsulta where nomePaciente like ? or dataConsulta = ? or idConsulta = ?");
    		stmt.setString(1, "%"+consulta1.getIdPaciente()+"%");
    		stmt.setString(2, consulta1.getDataConsulta());
    		stmt.setString(3, consulta1.getIdConsulta());
    		
    		rs = stmt.executeQuery();
    		while(rs.next()) {
    			Consulta consulta = new Consulta();
    			consulta.setIdPaciente(rs.getString("nomePaciente"));
    			consulta.setDataConsulta(rs.getString("dataConsulta"));
    			consulta.setHorario(rs.getString("horario"));
    			consulta.setStatusConsulta(rs.getString("statusConsulta"));
    			
    			consultas.add(consulta);
    		}
    		
    	}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return consultas;
    	
    }
    
    //Para aparecer o restantes das informações na tela de edição, pois elas não estão da tabela da AgendamentoConsulta
    public ArrayList<Consulta> searchInformaConsulta(Consulta consulta){
    	Connection con = ConnectionDataBase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<Consulta> consultas = new ArrayList<>();
    	
    	try {
    		stmt = con.prepareStatement("SELECT * FROM Consulta where dataConsulta = ? and horario = ? and statusConsulta = ?");
    		stmt.setString(1, consulta.getDataConsulta());
    		stmt.setString(2,consulta.getHorario());
    		stmt.setString(3,consulta.getStatusConsulta());
    		
    		rs = stmt.executeQuery();
    		while(rs.next()) {
    			Consulta consulta2 = new Consulta();
    			consulta2.setPrecoConsulta(rs.getString("precoConsulta"));
    			consulta2.setObservacao(rs.getString("observacao"));
    			
    			consultas.add(consulta2);
    		}
    		
    	}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
    	
		return consultas;
    	
    }
    
    //Método para aparecer o CpfPaciente
    public String buscarCpfPorConsulta(Consulta consulta) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String cpf = null;

        try {
            stmt = con.prepareStatement(
                "SELECT p.cpfPaciente FROM Consulta c JOIN Paciente p ON c.idPaciente = p.idPaciente WHERE c.dataConsulta = ? AND c.horario = ? AND c.statusConsulta = ?"
            );
            stmt.setString(1, consulta.getDataConsulta());
            stmt.setString(2, consulta.getHorario());
            stmt.setString(3, consulta.getStatusConsulta());
            rs = stmt.executeQuery();

            if (rs.next()) {
                cpf = rs.getString("cpfPaciente");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar CPF do paciente!", e);
        } finally {
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }

        return cpf;
    }


    
    
}
