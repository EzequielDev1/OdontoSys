package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; // Adicionado para lidar com a data do Java

import Connection.ConnectionDataBase;
import model.RespostasEspecialidades;

public class RespostasEspecialidadesDAO {

    public void salvarOuAtualizar(RespostasEspecialidades resposta) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDataBase.getConnection();

            // 1. Verifica se já existe registro para o mesmo paciente e especialidade
            String sqlVerifica = "SELECT idResposta FROM RespostasEspecialidades WHERE idPaciente = ? AND especialidade = ?";
            
            // Usamos um bloco try-with-resources se possível, mas mantendo seu padrão:
            PreparedStatement stmtVerifica = null;
            
            try {
                stmtVerifica = conn.prepareStatement(sqlVerifica);
                stmtVerifica.setInt(1, resposta.getIdPaciente());
                stmtVerifica.setString(2, resposta.getEspecialidade());
                rs = stmtVerifica.executeQuery();

                if (rs.next()) {
                    // Já existe → faz UPDATE
                    int idResposta = rs.getInt("idResposta");

                    // É CRUCIAL fechar os recursos da busca ANTES de preparar uma nova query
                    ConnectionDataBase.closeConnection(null, stmtVerifica, rs);
                    stmtVerifica = null; // Zera a referência para ser segura
                    rs = null;

                    String sqlUpdate = """
                        UPDATE RespostasEspecialidades SET 
                            idFuncionario = ?, 
                            dataRegistro = ?, -- Usaremos o Timestamp do objeto resposta, não GETDATE()
                            bt01 = ?, bt02 = ?, bt03 = ?, bt04 = ?, bt05 = ?, 
                            bt06 = ?, bt07 = ?, bt08 = ?, bt09 = ?, bt10 = ?
                        WHERE idResposta = ?
                    """;

                    stmt = conn.prepareStatement(sqlUpdate);
                    
                    // PARÂMETROS PARA UPDATE
                    stmt.setInt(1, resposta.getIdFuncionario());
                    // Conversão de LocalDateTime (do Model) para Timestamp (para o BD)
                    stmt.setTimestamp(2, Timestamp.valueOf(resposta.getDataRegistro())); 
                    
                    stmt.setBoolean(3, resposta.isBt01());
                    stmt.setBoolean(4, resposta.isBt02());
                    stmt.setBoolean(5, resposta.isBt03());
                    stmt.setBoolean(6, resposta.isBt04());
                    stmt.setBoolean(7, resposta.isBt05());
                    stmt.setBoolean(8, resposta.isBt06());
                    stmt.setBoolean(9, resposta.isBt07());
                    stmt.setBoolean(10, resposta.isBt08());
                    stmt.setBoolean(11, resposta.isBt09());
                    stmt.setBoolean(12, resposta.isBt10());
                    
                    // Condição WHERE
                    stmt.setInt(13, idResposta); 

                    stmt.executeUpdate();

                } else {
                    // Se não encontrou, fecha os recursos e faz INSERT
                    ConnectionDataBase.closeConnection(null, stmtVerifica, rs);
                    stmtVerifica = null;
                    rs = null;

                    String sqlInsert = """
                        INSERT INTO RespostasEspecialidades (
                            idPaciente, idFuncionario, especialidade, dataRegistro,
                            bt01, bt02, bt03, bt04, bt05, bt06, bt07, bt08, bt09, bt10
                        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

                    stmt = conn.prepareStatement(sqlInsert);
                    
                    // PARÂMETROS PARA INSERT
                    stmt.setInt(1, resposta.getIdPaciente());
                    stmt.setInt(2, resposta.getIdFuncionario());
                    stmt.setString(3, resposta.getEspecialidade());
                    // Conversão de LocalDateTime (do Model) para Timestamp (para o BD)
                    stmt.setTimestamp(4, Timestamp.valueOf(resposta.getDataRegistro()));
                    
                    stmt.setBoolean(5, resposta.isBt01());
                    stmt.setBoolean(6, resposta.isBt02());
                    stmt.setBoolean(7, resposta.isBt03());
                    stmt.setBoolean(8, resposta.isBt04());
                    stmt.setBoolean(9, resposta.isBt05());
                    stmt.setBoolean(10, resposta.isBt06());
                    stmt.setBoolean(11, resposta.isBt07());
                    stmt.setBoolean(12, resposta.isBt08());
                    stmt.setBoolean(13, resposta.isBt09());
                    stmt.setBoolean(14, resposta.isBt10());

                    stmt.executeUpdate();
                }
            } finally {
                // Fechamento da verificação caso o fluxo não entre no if/else
                if (stmtVerifica != null) {
                    ConnectionDataBase.closeConnection(null, stmtVerifica, rs);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao salvar ou atualizar RespostasEspecialidades: " + e.getMessage());
        } finally {
            // Fecha a conexão e o PreparedStatement final (do UPDATE ou INSERT)
            ConnectionDataBase.closeConnection(conn, stmt, null); 
        }
    }

    // O método buscarPorPacienteEspecialidade (mantido sem alterações)
    public RespostasEspecialidades buscarPorPacienteEspecialidade(String idPacienteStr, String especialidade) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RespostasEspecialidades resposta = null;

        try {
            conn = ConnectionDataBase.getConnection();

            String sql = """
                SELECT * FROM RespostasEspecialidades
                WHERE idPaciente = ? AND especialidade = ?
            """;

            stmt = conn.prepareStatement(sql);

            // converte String para int
            int idPaciente = Integer.parseInt(idPacienteStr);
            stmt.setInt(1, idPaciente);
            stmt.setString(2, especialidade);

            rs = stmt.executeQuery();

            if (rs.next()) {
                resposta = new RespostasEspecialidades();
                resposta.setIdResposta(rs.getInt("idResposta"));
                resposta.setIdPaciente(rs.getInt("idPaciente"));

                // converte idFuncionario se ele vier como String da tabela/entrada
             
                int idFuncionario = rs.getInt("idFuncionario");
                resposta.setIdFuncionario(idFuncionario);

                resposta.setEspecialidade(rs.getString("especialidade"));
                resposta.setDataRegistro(rs.getTimestamp("dataRegistro").toLocalDateTime());

                // valores booleanos
                resposta.setBt01((Boolean) rs.getObject("bt01"));
                resposta.setBt02((Boolean) rs.getObject("bt02"));
                resposta.setBt03((Boolean) rs.getObject("bt03"));
                resposta.setBt04((Boolean) rs.getObject("bt04"));
                resposta.setBt05((Boolean) rs.getObject("bt05"));
                resposta.setBt06((Boolean) rs.getObject("bt06"));
                resposta.setBt07((Boolean) rs.getObject("bt07"));
                resposta.setBt08((Boolean) rs.getObject("bt08"));
                resposta.setBt09((Boolean) rs.getObject("bt09"));
                resposta.setBt10((Boolean) rs.getObject("bt10"));
            }

        } catch (NumberFormatException e) {
            System.out.println("ID do paciente inválido: " + idPacienteStr);
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDataBase.closeConnection(conn, stmt, rs);
        }

        return resposta;
    }

}