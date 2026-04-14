package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionDataBase;
import Connection.ConnectionDataBase;
import model.Funcionario;

public class FuncionarioDAO {

    public void create(Funcionario funcionario) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Funcionario values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, funcionario.getNomeFuncionario());
            stmt.setString(2, funcionario.getNivel());
            stmt.setString(3, funcionario.getCpfFuncionario());
            stmt.setString(4, funcionario.getRg());
            stmt.setString(5, funcionario.getDataNasc());
            stmt.setString(6, funcionario.getTelefone());
            stmt.setString(7, funcionario.getEmail());
            stmt.setString(8, funcionario.getEndereco());
            stmt.setString(9, funcionario.getCargo());
            stmt.setString(10, funcionario.getGenero());
            stmt.setString(11, funcionario.getSenha());

            stmt.execute();
            System.out.println("Funcionario cadastrado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar funcionario!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Funcionario> read() {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Funcionario");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getString("idFuncionario"));
                funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                funcionario.setNivel(rs.getString("nivel"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setDataNasc(rs.getString("dataNasc"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setGenero(rs.getString("genero"));
                funcionario.setSenha(rs.getString("senha"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os dados!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }
    
    
    public ArrayList<Funcionario> search(String pesquisa) {
    	Connection con = ConnectionDataBase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<Funcionario> funcionarios = new ArrayList<>();
    	try {
    		stmt = con.prepareStatement("SELECT * FROM Funcionario WHERE nomeFuncionario LIKE ? OR cpfFuncionario LIKE ?");
    		stmt.setString(1,"%"+ pesquisa + "%");
			stmt.setString(2,"%"+ pesquisa + "%");
    		rs = stmt.executeQuery();
    		while(rs.next()) {
    			Funcionario funcionario = new Funcionario();
    			funcionario.setIdFuncionario(rs.getString("idFuncionario"));
    			funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
    			funcionario.setNivel(rs.getString("nivel"));
    			funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
    			funcionario.setRg(rs.getString("rg"));
    			funcionario.setDataNasc(rs.getString("dataNasc"));
    			funcionario.setTelefone(rs.getString("telefone"));
    			funcionario.setEmail(rs.getString("email"));
    			funcionario.setEndereco(rs.getString("endereco"));
    			funcionario.setCargo(rs.getString("cargo"));
    			funcionario.setGenero(rs.getString("genero"));
    			funcionario.setSenha(rs.getString("senha"));
    			
    			funcionarios.add(funcionario);
    		}
    	} catch (SQLException e) {
    		throw new RuntimeException("Erro ao ler os dados!", e);
    	} finally {
    		ConnectionDataBase.closeConnection(con, stmt, rs);
    	}
    	return funcionarios;
    }
    
    

    public void update(Funcionario funcionario) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Funcionario SET nomeFuncionario=?, nivel=?, cpfFuncionario=?, rg=?, dataNasc=?, telefone=?, email=?, endereco=?, cargo=?, genero=?, senha=? WHERE cpfFuncionario=?");
            stmt.setString(1, funcionario.getNomeFuncionario());
            stmt.setString(2, funcionario.getNivel());
            stmt.setString(3, funcionario.getCpfFuncionario());
            stmt.setString(4, funcionario.getRg());
            stmt.setString(5, funcionario.getDataNasc());
            stmt.setString(6, funcionario.getTelefone());
            stmt.setString(7, funcionario.getEmail());
            stmt.setString(8, funcionario.getEndereco());
            stmt.setString(9, funcionario.getCargo());
            stmt.setString(10, funcionario.getGenero());
            stmt.setString(11, funcionario.getSenha());
            stmt.setString(12, funcionario.getCpfFuncionario());

            stmt.execute();
            System.out.println("Funcionario atualizado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionario!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }

    public void delete(String cpf) {
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Funcionario WHERE cpfFuncionario=?");
            stmt.setString(1, cpf);
            stmt.execute();
            System.out.println("Funcionario excluido!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionario!", e);
        } finally {
        	ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public Funcionario autenticarUser(String user, String password) {
		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Funcionario funcionario = new Funcionario();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Funcionario WHERE cpfFuncionario = ? AND senha = ?");
			stmt.setString(1, user);
			stmt.setString(2, password );
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
                funcionario.setIdFuncionario(rs.getString("idFuncionario"));
                funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                funcionario.setNivel(rs.getString("nivel"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setDataNasc(rs.getString("dataNasc"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setGenero(rs.getString("genero"));
                funcionario.setSenha(rs.getString("senha"));
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler os dados!", e);
		}finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		
		return funcionario;
	}
	
}
