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

public class ControllerTelaProtese {

    @FXML private RadioButton btProteseSim1, btProteseNao1;
    @FXML private RadioButton btProteseSim2, btProteseNao2;
    @FXML private RadioButton btProteseSim3, btProteseNao3;
    @FXML private RadioButton btProteseSim4, btProteseNao4;
    @FXML private RadioButton btProteseSim5, btProteseNao5;
    @FXML private RadioButton btProteseSim6, btProteseNao6;
    @FXML private RadioButton btProteseSim7, btProteseNao7;
    @FXML private RadioButton btProteseSim8, btProteseNao8;
    @FXML private RadioButton btProteseSim9, btProteseNao9;
    @FXML private RadioButton btProteseSim10, btProteseNao10;
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
        btProteseSim1.setToggleGroup(grupo1);
        btProteseNao1.setToggleGroup(grupo1);
        btProteseSim2.setToggleGroup(grupo2);
        btProteseNao2.setToggleGroup(grupo2);
        btProteseSim3.setToggleGroup(grupo3);
        btProteseNao3.setToggleGroup(grupo3);
        btProteseSim4.setToggleGroup(grupo4);
        btProteseNao4.setToggleGroup(grupo4);
        btProteseSim5.setToggleGroup(grupo5);
        btProteseNao5.setToggleGroup(grupo5);
        btProteseSim6.setToggleGroup(grupo6);
        btProteseNao6.setToggleGroup(grupo6);
        btProteseSim7.setToggleGroup(grupo7);
        btProteseNao7.setToggleGroup(grupo7);
        btProteseSim8.setToggleGroup(grupo8);
        btProteseNao8.setToggleGroup(grupo8);
        btProteseSim9.setToggleGroup(grupo9);
        btProteseNao9.setToggleGroup(grupo9);
        btProteseSim10.setToggleGroup(grupo10);
        btProteseNao10.setToggleGroup(grupo10);
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        // CHAMADA PARA CARREGAR APÓS DEFINIR O PACIENTE
        if (this.paciente != null) {
            carregarRespostasAnteriores();
        }
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Tenta carregar as respostas de Prótese salvas para o paciente atual
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
                "Prótese" // Nome da especialidade
            );

            if (resposta != null) {
                // Aplica os valores booleanos aos botões (usando isBtXX())
                setRadioButtonState(btProteseSim1, btProteseNao1, resposta.isBt01());
                setRadioButtonState(btProteseSim2, btProteseNao2, resposta.isBt02());
                setRadioButtonState(btProteseSim3, btProteseNao3, resposta.isBt03());
                setRadioButtonState(btProteseSim4, btProteseNao4, resposta.isBt04());
                setRadioButtonState(btProteseSim5, btProteseNao5, resposta.isBt05());
                setRadioButtonState(btProteseSim6, btProteseNao6, resposta.isBt06());
                setRadioButtonState(btProteseSim7, btProteseNao7, resposta.isBt07());
                setRadioButtonState(btProteseSim8, btProteseNao8, resposta.isBt08());
                setRadioButtonState(btProteseSim9, btProteseNao9, resposta.isBt09());
                setRadioButtonState(btProteseSim10, btProteseNao10, resposta.isBt10());
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
        if (!validarRespostas()) {
            new Alert(Alert.AlertType.WARNING, "Por favor, responda todas as perguntas.").showAndWait();
            return;
        }

        try {
            RespostasEspecialidades resposta = new RespostasEspecialidades();
            
            // CONVERSÃO DE STRING PARA INT E SET DA RESPOSTA
            int idPaciente = Integer.parseInt(paciente.getIdPaciente());
            int idFuncionario = Integer.parseInt(funcionario.getIdFuncionario());

            resposta.setIdPaciente(idPaciente);
            resposta.setIdFuncionario(idFuncionario);
            
            resposta.setEspecialidade("Prótese");
            resposta.setDataRegistro(LocalDateTime.now());

            resposta.setBt01(btProteseSim1.isSelected());
            resposta.setBt02(btProteseSim2.isSelected());
            resposta.setBt03(btProteseSim3.isSelected());
            resposta.setBt04(btProteseSim4.isSelected());
            resposta.setBt05(btProteseSim5.isSelected());
            resposta.setBt06(btProteseSim6.isSelected());
            resposta.setBt07(btProteseSim7.isSelected());
            resposta.setBt08(btProteseSim8.isSelected());
            resposta.setBt09(btProteseSim9.isSelected());
            resposta.setBt10(btProteseSim10.isSelected());

            respostasDAO.salvarOuAtualizar(resposta); // USANDO A INSTÂNCIA DO DAO
            new Alert(Alert.AlertType.INFORMATION, "Respostas de Prótese salvas com sucesso!").showAndWait();

        } catch (NumberFormatException e) {
             new Alert(Alert.AlertType.ERROR, "Erro de tipo: ID do Paciente/Funcionário inválido (Não é um número).").showAndWait();
             e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar: " + e.getMessage()).showAndWait();
        }
    }

    private boolean validarRespostas() {
        return (btProteseSim1.isSelected() || btProteseNao1.isSelected()) &&
               (btProteseSim2.isSelected() || btProteseNao2.isSelected()) &&
               (btProteseSim3.isSelected() || btProteseNao3.isSelected()) &&
               (btProteseSim4.isSelected() || btProteseNao4.isSelected()) &&
               (btProteseSim5.isSelected() || btProteseNao5.isSelected()) &&
               (btProteseSim6.isSelected() || btProteseNao6.isSelected()) &&
               (btProteseSim7.isSelected() || btProteseNao7.isSelected()) &&
               (btProteseSim8.isSelected() || btProteseNao8.isSelected()) &&
               (btProteseSim9.isSelected() || btProteseNao9.isSelected()) &&
               (btProteseSim10.isSelected() || btProteseNao10.isSelected());
    }

    @FXML
    void actionCancelar(ActionEvent event) {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}