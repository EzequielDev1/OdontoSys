package dao;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionDataBase;
import model.Anamnese;


public class AnamneseDAO {

	// 2. Método para Salvar a Anamnese (INSERT)
    public void salvarAnamnese(Anamnese anamnese) throws SQLException {
        
        // Comando INSERT INTO (ID Anamnese é Identity, não precisa ser incluído)
        String sql = "INSERT INTO anamnese ("
                + "idPaciente, bt01SimNao, bt02SimNao, bt03SimNao, bt04SimNao, bt05SimNao, "
                + "bt06SimNao, bt07SimNao, bt08SimNao, bt09SimNao, bt10SimNao, "
                + "bt11Boca, bt12Boca, "
                + "bt01Positivo, bt02Positivo, bt03Positivo, bt4Positivo, bt05Positivo, "
                + "bt06Positivo, bt07Positivo, bt08Positivo, bt09Positivo, bt10Positivo, "
                + "bt11Positivo, bt12Positivo, bt13Positivo, "
                + "txtBoca01, txtBoca02, txtBoca03, txtBoca04, txtBoca05, txtBoca06, txtBoca07"
                // 33 VALUES (32 '?' para campos + 1 '?' para idPaciente)
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int i = 1; // Contador para os parâmetros (i inicia em 1)

            // 1. idPaciente (Deve ser obtido da tela ou da sessão)
            stmt.setInt(i++, anamnese.getIdPaciente()); 

            // 2. 12 Perguntas Sim/Não (BIT NOT NULL -> setBoolean)
            stmt.setBoolean(i++, anamnese.isBt01SimNao());
            stmt.setBoolean(i++, anamnese.isBt02SimNao());
            stmt.setBoolean(i++, anamnese.isBt03SimNao());
            stmt.setBoolean(i++, anamnese.isBt04SimNao());
            stmt.setBoolean(i++, anamnese.isBt05SimNao());
            stmt.setBoolean(i++, anamnese.isBt06SimNao());
            stmt.setBoolean(i++, anamnese.isBt07SimNao());
            stmt.setBoolean(i++, anamnese.isBt08SimNao());
            stmt.setBoolean(i++, anamnese.isBt09SimNao());
            stmt.setBoolean(i++, anamnese.isBt10SimNao());
            stmt.setBoolean(i++, anamnese.isBt11Boca());
            stmt.setBoolean(i++, anamnese.isBt12Boca());

            // 3. 13 Problemas Positivos (BIT NULL -> Lógica setBoolean ou setNull)
            // Lógica auxiliar para setar Boolean (true/false) ou Null
            setBooleanOrNull(stmt, i++, anamnese.getBt01Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt02Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt03Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt04Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt05Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt06Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt07Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt08Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt09Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt10Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt11Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt12Positivo());
            setBooleanOrNull(stmt, i++, anamnese.getBt13Positivo());
            
            // 4. 7 Campos de Texto (VARCHAR)
            stmt.setString(i++, anamnese.getTxtBoca01());
            stmt.setString(i++, anamnese.getTxtBoca02());
            stmt.setString(i++, anamnese.getTxtBoca03());
            stmt.setString(i++, anamnese.getTxtBoca04());
            stmt.setString(i++, anamnese.getTxtBoca05());
            stmt.setString(i++, anamnese.getTxtBoca06());
            stmt.setString(i++, anamnese.getTxtBoca07());

            // Executa o comando SQL
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar anamnese: " + e.getMessage());
            throw e; // Lança a exceção para que o Controller possa tratar
        }
    }
    
    // Método auxiliar para setar Boolean ou Null no PreparedStatement
    private void setBooleanOrNull(PreparedStatement stmt, int index, Boolean value) throws SQLException {
        if (value == null) {
            stmt.setNull(index, java.sql.Types.BIT);
        } else {
            // Se for false, ele será convertido para 0 no banco (BIT)
            stmt.setBoolean(index, value); 
        }
    }

	
}
