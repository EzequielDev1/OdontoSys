package controller;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import dao.RespostasEspecialidadesDAO;
import model.Funcionario;
import model.Paciente;
import model.RespostasEspecialidades;

public class ControllerTelaEndodontia {

    @FXML private RadioButton btEndoSim1, btEndoNao1;
    @FXML private RadioButton btEndoSim2, btEndoNao2;
    @FXML private RadioButton btEndoSim3, btEndoNao3;
    @FXML private RadioButton btEndoSim4, btEndoNao4;
    @FXML private RadioButton btEndoSim5, btEndoNao5;
    @FXML private RadioButton btEndoSim6, btEndoNao6;
    @FXML private RadioButton btEndoSim7, btEndoNao7;
    @FXML private RadioButton btEndoSim8, btEndoNao8;
    @FXML private RadioButton btEndoSim9, btEndoNao9;
    @FXML private RadioButton btEndoSim10, btEndoNao10;
    @FXML private Button btSalvar, btCancelar;

    private Paciente paciente;
    private Funcionario funcionario;
    private final RespostasEspecialidadesDAO respostasDAO = new RespostasEspecialidadesDAO();

    // === Agrupadores para impedir seleção dupla ===
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
        btEndoSim1.setToggleGroup(grupo1);
        btEndoNao1.setToggleGroup(grupo1);

        btEndoSim2.setToggleGroup(grupo2);
        btEndoNao2.setToggleGroup(grupo2);

        btEndoSim3.setToggleGroup(grupo3);
        btEndoNao3.setToggleGroup(grupo3);

        btEndoSim4.setToggleGroup(grupo4);
        btEndoNao4.setToggleGroup(grupo4);

        btEndoSim5.setToggleGroup(grupo5);
        btEndoNao5.setToggleGroup(grupo5);

        btEndoSim6.setToggleGroup(grupo6);
        btEndoNao6.setToggleGroup(grupo6);

        btEndoSim7.setToggleGroup(grupo7);
        btEndoNao7.setToggleGroup(grupo7);

        btEndoSim8.setToggleGroup(grupo8);
        btEndoNao8.setToggleGroup(grupo8);

        btEndoSim9.setToggleGroup(grupo9);
        btEndoNao9.setToggleGroup(grupo9);

        btEndoSim10.setToggleGroup(grupo10);
        btEndoNao10.setToggleGroup(grupo10);
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
     * Tenta carregar as respostas de Endodontia salvas para o paciente atual
     * e aplica o estado de marcado aos RadioButtons.
     */
    private void carregarRespostasAnteriores() {
        if (this.paciente == null) {
            return; // Não tem paciente para buscar
        }

        // Pega o ID como String
        String idPacienteStr = this.paciente.getIdPaciente(); 
        int idPacienteInt = 0;

        try {
            // Tenta converter para int para checagem de ID = 0
            idPacienteInt = Integer.parseInt(idPacienteStr);
        } catch (NumberFormatException e) {
            System.err.println("ID do paciente inválido para carregamento: " + idPacienteStr);
            return;
        }

        // Checa se o ID é válido (diferente de 0)
        if (idPacienteInt == 0) {
            return; 
        }

        try {
            // Chama a função DAO que espera a String
            RespostasEspecialidades resposta = respostasDAO.buscarPorPacienteEspecialidade(
                idPacienteStr, 
                "Endodontia"
            );

            if (resposta != null) {
                // *** ALTERAÇÃO AQUI: USANDO isBtXX ***
                setRadioButtonState(btEndoSim1, btEndoNao1, resposta.isBt01());
                setRadioButtonState(btEndoSim2, btEndoNao2, resposta.isBt02());
                setRadioButtonState(btEndoSim3, btEndoNao3, resposta.isBt03());
                setRadioButtonState(btEndoSim4, btEndoNao4, resposta.isBt04());
                setRadioButtonState(btEndoSim5, btEndoNao5, resposta.isBt05());
                setRadioButtonState(btEndoSim6, btEndoNao6, resposta.isBt06());
                setRadioButtonState(btEndoSim7, btEndoNao7, resposta.isBt07());
                setRadioButtonState(btEndoSim8, btEndoNao8, resposta.isBt08());
                setRadioButtonState(btEndoSim9, btEndoNao9, resposta.isBt09());
                setRadioButtonState(btEndoSim10, btEndoNao10, resposta.isBt10());
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
            new Alert(Alert.AlertType.WARNING, "Responda todas as perguntas antes de salvar.").showAndWait();
            return;
        }

        try {
            RespostasEspecialidades resposta = new RespostasEspecialidades();
            
            // Pega os IDs como String (retorno de Paciente/Funcionario) e converte para int
            int idPaciente = Integer.parseInt(paciente.getIdPaciente());
            int idFuncionario = Integer.parseInt(funcionario.getIdFuncionario());

            // Chama o setter da RespostasEspecialidades que espera INT
            resposta.setIdPaciente(idPaciente);
            resposta.setIdFuncionario(idFuncionario);
            
            resposta.setEspecialidade("Endodontia");
            resposta.setDataRegistro(LocalDateTime.now());

            // Atribuição de Boolean para os botões (correto)
            resposta.setBt01(btEndoSim1.isSelected());
            resposta.setBt02(btEndoSim2.isSelected());
            resposta.setBt03(btEndoSim3.isSelected());
            resposta.setBt04(btEndoSim4.isSelected());
            resposta.setBt05(btEndoSim5.isSelected());
            resposta.setBt06(btEndoSim6.isSelected());
            resposta.setBt07(btEndoSim7.isSelected());
            resposta.setBt08(btEndoSim8.isSelected());
            resposta.setBt09(btEndoSim9.isSelected());
            resposta.setBt10(btEndoSim10.isSelected());

            respostasDAO.salvarOuAtualizar(resposta);

            new Alert(Alert.AlertType.INFORMATION, "Respostas de Endodontia salvas com sucesso!").showAndWait();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Erro de tipo: ID do Paciente/Funcionário inválido (Não é um número).").showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar: " + e.getMessage()).showAndWait();
        }
    }

    private boolean validarRespostas() {
        return (btEndoSim1.isSelected() || btEndoNao1.isSelected()) &&
               (btEndoSim2.isSelected() || btEndoNao2.isSelected()) &&
               (btEndoSim3.isSelected() || btEndoNao3.isSelected()) &&
               (btEndoSim4.isSelected() || btEndoNao4.isSelected()) &&
               (btEndoSim5.isSelected() || btEndoNao5.isSelected()) &&
               (btEndoSim6.isSelected() || btEndoNao6.isSelected()) &&
               (btEndoSim7.isSelected() || btEndoNao7.isSelected()) &&
               (btEndoSim8.isSelected() || btEndoNao8.isSelected()) &&
               (btEndoSim9.isSelected() || btEndoNao9.isSelected()) &&
               (btEndoSim10.isSelected() || btEndoNao10.isSelected());
    }

    @FXML
    void actionCancelar(ActionEvent event) {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}