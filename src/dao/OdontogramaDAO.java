package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Connection.ConnectionDataBase;
import model.Odontograma;

public class OdontogramaDAO {

    // ======================================================
    // MÉTODO PARA SALVAR OS DENTES MARCADOS
    // ======================================================
    public boolean salvar(String idPaciente, String idFuncionario, Map<Integer, Map<String, String>> dentesMarcados) {
        String sql = """
            INSERT INTO Odontograma (idPaciente, idFuncionario, numeroDente, faceDente, condicaoDente, dataRegistro, idTratamento)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConnectionDataBase.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            for (Map.Entry<Integer, Map<String, String>> entry : dentesMarcados.entrySet()) {
                int numero = entry.getKey();
                String face = entry.getValue().get("face");
                String condicao = entry.getValue().get("condicao");
                String idTratamento = entry.getValue().get("idTratamento");

                stmt.setString(1, idPaciente);
                stmt.setString(2, idFuncionario);
                stmt.setInt(3, numero);
                stmt.setString(4, face);
                stmt.setString(5, condicao);
                stmt.setDate(6, Date.valueOf(LocalDate.now()));
                stmt.setString(7, idTratamento);

                stmt.addBatch();
            }

            int[] resultados = stmt.executeBatch();
            for (int i = 0; i < resultados.length; i++) {
                if (resultados[i] == PreparedStatement.EXECUTE_FAILED) {
                    System.err.println("❌ Falha ao inserir dente " + i);
                } else {
                    System.out.println("✅ Dente " + i + " inserido com sucesso!");
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar odontograma: " + e.getMessage());
            return false;
        }
    }

    // ======================================================
    // MÉTODO PARA BUSCAR OS DADOS DO ODONTOGRAMA DO PACIENTE
    // ======================================================
    public List<Odontograma> buscarOdontogramaPorPaciente(String idPaciente) {
        List<Odontograma> lista = new ArrayList<>();

        String sql = """
            SELECT o.idOdontograma, o.idPaciente, o.idFuncionario,
                   o.numeroDente, o.faceDente, o.condicaoDente,
                   o.observacao, o.dataRegistro,
                   o.idTratamento, t.nomeTratamento
            FROM Odontograma o
            LEFT JOIN Tratamento t ON o.idTratamento = t.idTratamento
            WHERE o.idPaciente = ?
        """;

        try (Connection con = ConnectionDataBase.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Odontograma o = new Odontograma();
                o.setIdOdontograma(rs.getString("idOdontograma"));
                o.setIdPaciente(rs.getString("idPaciente"));
                o.setIdFuncionario(rs.getString("idFuncionario"));
                o.setNumeroDente(rs.getString("numeroDente"));
                o.setFaceDente(rs.getString("faceDente"));
                o.setCondicaoDente(rs.getString("condicaoDente"));
                o.setObservacao(rs.getString("observacao"));
                o.setDataRegistro(rs.getString("dataRegistro"));
                o.setIdTratamento(rs.getString("idTratamento"));
                o.setNomeTratamento(rs.getString("nomeTratamento"));
                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao buscar odontograma: " + e.getMessage());
        }

        return lista;
    }

    // ======================================================
    // MÉTODO PARA LISTAR OS TRATAMENTOS COM NOME E ID
    // ======================================================
    public Map<String, String> listarTratamentos() {
        Map<String, String> tratamentos = new LinkedHashMap<>();
        String sql = "SELECT idTratamento, nomeTratamento FROM Tratamento ORDER BY nomeTratamento";

        try (Connection con = ConnectionDataBase.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tratamentos.put(rs.getString("idTratamento"), rs.getString("nomeTratamento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao listar tratamentos: " + e.getMessage());
        }

        return tratamentos;
    }

    // ======================================================
    // DELETAR ODONTOGRAMA DO PACIENTE
    // ======================================================
    public void deletarOdontogramaPorPaciente(String idPaciente) {
        String sql = "DELETE FROM Odontograma WHERE idPaciente = ?";

        try (Connection con = ConnectionDataBase.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idPaciente);
            int linhas = stmt.executeUpdate();
            System.out.println("🧾 Odontograma do paciente " + idPaciente + " apagado. Linhas removidas: " + linhas);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao excluir odontograma: " + e.getMessage());
        }
    }
}
