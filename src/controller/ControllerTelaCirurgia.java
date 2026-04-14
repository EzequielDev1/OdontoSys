package controller;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup; // IMPORT ADICIONADO
import javafx.stage.Stage;
import dao.RespostasEspecialidadesDAO;
import model.Funcionario;
import model.Paciente;
import model.RespostasEspecialidades;

public class ControllerTelaCirurgia {

    @FXML private RadioButton btCirurgiaSim1, btCirurgiaNao1;
    @FXML private RadioButton btCirurgiaSim2, btCirurgiaNao2;
    @FXML private RadioButton btCirurgiaSim3, btCirurgiaNao3;
    @FXML private RadioButton btCirurgiaSim4, btCirurgiaNao4;
    @FXML private RadioButton btCirurgiaSim5, btCirurgiaNao5;
    @FXML private RadioButton btCirurgiaSim6, btCirurgiaNao6;
    @FXML private RadioButton btCirurgiaSim7, btCirurgiaNao7;
    @FXML private RadioButton btCirurgiaSim8, btCirurgiaNao8;
    @FXML private RadioButton btCirurgiaSim9, btCirurgiaNao9;
    @FXML private RadioButton btCirurgiaSim10, btCirurgiaNao10;
    @FXML private Button btSalvar, btCancelar;

    private Paciente paciente;
    private Funcionario funcionario;
    private final RespostasEspecialidadesDAO respostasDAO = new RespostasEspecialidadesDAO(); // DAO INSTANCIADO

    // === Agrupadores para impedir seleção dupla (ADICIONADOS) ===
    private final ToggleGroup grupo1 = new ToggleGroup();
    private final ToggleGroup grupo2 = new ToggleGroup();
    private final ToggleGroup grupo3 = new ToggleGroup();
    private final ToggleGroup grupo4 = new ToggleGroup();
    private final ToggleGroup grupo5 = new ToggleGroup();
    private final ToggleGroup grupo6 = new ToggleGroup();
    private final ToggleGroup grupo7 = new ToggleGroup();
    private final ToggleGroup grupo8 = new ToggleGroup();
    private final ToggleGroup grupo9 = new ToggleGroup();
    private final ToggleGroup grupo10 = new ToggleGroup();

    @FXML
    private void initialize() {
        // associa cada par "Sim/Não" a um grupo
        btCirurgiaSim1.setToggleGroup(grupo1);
        btCirurgiaNao1.setToggleGroup(grupo1);
        btCirurgiaSim2.setToggleGroup(grupo2);
        btCirurgiaNao2.setToggleGroup(grupo2);
        btCirurgiaSim3.setToggleGroup(grupo3);
        btCirurgiaNao3.setToggleGroup(grupo3);
        btCirurgiaSim4.setToggleGroup(grupo4);
        btCirurgiaNao4.setToggleGroup(grupo4);
        btCirurgiaSim5.setToggleGroup(grupo5);
        btCirurgiaNao5.setToggleGroup(grupo5);
        btCirurgiaSim6.setToggleGroup(grupo6);
        btCirurgiaNao6.setToggleGroup(grupo6);
        btCirurgiaSim7.setToggleGroup(grupo7);
        btCirurgiaNao7.setToggleGroup(grupo7);
        btCirurgiaSim8.setToggleGroup(grupo8);
        btCirurgiaNao8.setToggleGroup(grupo8);
        btCirurgiaSim9.setToggleGroup(grupo9);
        btCirurgiaNao9.setToggleGroup(grupo9);
        btCirurgiaSim10.setToggleGroup(grupo10);
        btCirurgiaNao10.setToggleGroup(grupo10);
    }

    // 🔹 Recebe o paciente enviado pelo ControllerTelaEspecialidades
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        // CHAMADA PARA CARREGAR APÓS DEFINIR O PACIENTE
        if (this.paciente != null) {
            carregarRespostasAnteriores();
        }
    }

    // 🔹 Recebe o funcionário logado
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Tenta carregar as respostas de Cirurgia salvas para o paciente atual
     * e aplica o estado de marcado aos RadioButtons.
     */
    private void carregarRespostasAnteriores() {
        if (this.paciente == null) {
            return;
        }

        String idPacienteStr = this.paciente.getIdPaciente();
        int idPacienteInt = 0;

        try {
            idPacienteInt = Integer.parseInt(idPacienteStr);
        } catch (NumberFormatException e) {
            System.err.println("ID do paciente inválido para carregamento: " + idPacienteStr);
            return;
        }

        if (idPacienteInt == 0) {
            return;
        }

        try {
            RespostasEspecialidades resposta = respostasDAO.buscarPorPacienteEspecialidade(
                idPacienteStr, 
                "Cirurgia" // Nome da especialidade
            );

            if (resposta != null) {
                // Aplica os valores booleanos aos botões (usando isBtXX())
                setRadioButtonState(btCirurgiaSim1, btCirurgiaNao1, resposta.isBt01());
                setRadioButtonState(btCirurgiaSim2, btCirurgiaNao2, resposta.isBt02());
                setRadioButtonState(btCirurgiaSim3, btCirurgiaNao3, resposta.isBt03());
                setRadioButtonState(btCirurgiaSim4, btCirurgiaNao4, resposta.isBt04());
                setRadioButtonState(btCirurgiaSim5, btCirurgiaNao5, resposta.isBt05());
                setRadioButtonState(btCirurgiaSim6, btCirurgiaNao6, resposta.isBt06());
                setRadioButtonState(btCirurgiaSim7, btCirurgiaNao7, resposta.isBt07());
                setRadioButtonState(btCirurgiaSim8, btCirurgiaNao8, resposta.isBt08());
                setRadioButtonState(btCirurgiaSim9, btCirurgiaNao9, resposta.isBt09());
                setRadioButtonState(btCirurgiaSim10, btCirurgiaNao10, resposta.isBt10());
            }

        } catch (Exception e) {
            System.err.println("Erro ao carregar respostas anteriores: " + e.getMessage());
        }
    }
    
    /**
     * Função auxiliar para marcar o RadioButton correto
     */
    private void setRadioButtonState(RadioButton simButton, RadioButton naoButton, Boolean valorSalvo) {
        if (valorSalvo != null) {
            if (valorSalvo) {
                simButton.setSelected(true);
            } else {
                naoButton.setSelected(true);
            }
        }
    }

    @FXML
    void actionSalvar(ActionEvent event) {
        if (paciente == null || funcionario == null) {
            new Alert(Alert.AlertType.ERROR, 
                "Erro: Paciente ou funcionário não definidos.").showAndWait();
            return;
        }

        if (!validarRespostas()) {
            new Alert(Alert.AlertType.WARNING, 
                "Responda todas as perguntas antes de salvar.").showAndWait();
            return;
        }

        try {
            RespostasEspecialidades resposta = new RespostasEspecialidades();
            
            // CONVERSÃO DE STRING PARA INT E SET DA RESPOSTA
            int idPaciente = Integer.parseInt(paciente.getIdPaciente());
            int idFuncionario = Integer.parseInt(funcionario.getIdFuncionario());

            resposta.setIdPaciente(idPaciente);
            resposta.setIdFuncionario(idFuncionario);
            
            resposta.setEspecialidade("Cirurgia");
            resposta.setDataRegistro(LocalDateTime.now());

            // ✅ Armazena as respostas
            resposta.setBt01(btCirurgiaSim1.isSelected());
            resposta.setBt02(btCirurgiaSim2.isSelected());
            resposta.setBt03(btCirurgiaSim3.isSelected());
            resposta.setBt04(btCirurgiaSim4.isSelected());
            resposta.setBt05(btCirurgiaSim5.isSelected());
            resposta.setBt06(btCirurgiaSim6.isSelected());
            resposta.setBt07(btCirurgiaSim7.isSelected());
            resposta.setBt08(btCirurgiaSim8.isSelected());
            resposta.setBt09(btCirurgiaSim9.isSelected());
            resposta.setBt10(btCirurgiaSim10.isSelected());

            // ✅ Salva no banco
            respostasDAO.salvarOuAtualizar(resposta); // USANDO A INSTÂNCIA DO DAO

            new Alert(Alert.AlertType.INFORMATION, 
                "Respostas de Cirurgia salvas com sucesso!").showAndWait();

        } catch (NumberFormatException e) {
             new Alert(Alert.AlertType.ERROR, "Erro de tipo: ID do Paciente/Funcionário inválido (Não é um número).").showAndWait();
             e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, 
                "Erro ao salvar: " + e.getMessage()).showAndWait();
        }
    }

    private boolean validarRespostas() {
        return (btCirurgiaSim1.isSelected() || btCirurgiaNao1.isSelected()) &&
               (btCirurgiaSim2.isSelected() || btCirurgiaNao2.isSelected()) &&
               (btCirurgiaSim3.isSelected() || btCirurgiaNao3.isSelected()) &&
               (btCirurgiaSim4.isSelected() || btCirurgiaNao4.isSelected()) &&
               (btCirurgiaSim5.isSelected() || btCirurgiaNao5.isSelected()) &&
               (btCirurgiaSim6.isSelected() || btCirurgiaNao6.isSelected()) &&
               (btCirurgiaSim7.isSelected() || btCirurgiaNao7.isSelected()) &&
               (btCirurgiaSim8.isSelected() || btCirurgiaNao8.isSelected()) &&
               (btCirurgiaSim9.isSelected() || btCirurgiaNao9.isSelected()) &&
               (btCirurgiaSim10.isSelected() || btCirurgiaNao10.isSelected());
    }

    @FXML
    void actionCancelar(ActionEvent event) {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}