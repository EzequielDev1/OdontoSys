package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Connection.ConnectionDataBase;
import model.Pagamento;

public class PagamentoDAO {
	public ArrayList<Pagamento> read() {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pagamento> pagamentos = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM VW_Pagamento");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pagamento pagamento = new Pagamento();
				pagamento.setNomePaciente(rs.getString("Paciente"));
				pagamento.setNomeTratamento(rs.getString("procedimento"));
				pagamento.setDataConsulta(rs.getString("dataConsulta"));
				pagamento.setPrecoTratamento(rs.getString("valor"));
				
				pagamentos.add(pagamento);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler os dados!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return pagamentos;
	}
	public ArrayList<Pagamento> search(String pesquisa) {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pagamento> pagamentos = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM VW_Pagamento where Paciente like ? or dataConsulta like ?");
			stmt.setString(1,"%" + pesquisa + "%");
			stmt.setString(2, pesquisa );
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pagamento pagamento = new Pagamento();
				pagamento.setNomePaciente(rs.getString("Paciente"));
				pagamento.setNomeTratamento(rs.getString("procedimento"));
				pagamento.setDataConsulta(rs.getString("dataConsulta"));
				pagamento.setPrecoTratamento(rs.getString("valor"));
				
				pagamentos.add(pagamento);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler os dados!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return pagamentos;
	}
}
