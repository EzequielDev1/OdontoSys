package controller;

import java.sql.SQLException;

import dao.AnamneseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Anamnese;

public class ControllerTelaScroll {

    @FXML
    private ToggleGroup GrupoBoca1;

    @FXML
    private ToggleGroup GrupoBoca2;

    @FXML
    private ToggleGroup GrupoOp8;

    @FXML
    private ToggleGroup GrupoOp1;

    @FXML
    private ToggleGroup GrupoOp10;

    @FXML
    private ToggleGroup GrupoOp2;

    @FXML
    private ToggleGroup GrupoOp3;

    @FXML
    private ToggleGroup GrupoOp4;

    @FXML
    private ToggleGroup GrupoOp5;

    @FXML
    private ToggleGroup GrupoOp6;

    @FXML
    private ToggleGroup GrupoOp7;

    @FXML
    private ToggleGroup GrupoOp9;

    @FXML
    private RadioButton btBocaNao11;

    @FXML
    private RadioButton btBocaNao12;

    @FXML
    private RadioButton btBocaSim11;

    @FXML
    private RadioButton btBocaSim12;

    @FXML
    private RadioButton btNao01;

    @FXML
    private RadioButton btNao02;

    @FXML
    private RadioButton btNao03;

    @FXML
    private RadioButton btNao04;

    @FXML
    private RadioButton btNao05;

    @FXML
    private RadioButton btNao06;

    @FXML
    private RadioButton btNao07;

    @FXML
    private RadioButton btNao08;

    @FXML
    private RadioButton btNao09;

    @FXML
    private RadioButton btNao10;

    @FXML
    private Button btSalvar;

    @FXML
    private RadioButton btSim01;

    @FXML
    private RadioButton btSim02;

    @FXML
    private RadioButton btSim03;

    @FXML
    private RadioButton btSim04;

    @FXML
    private RadioButton btSim05;

    @FXML
    private RadioButton btSim06;

    @FXML
    private RadioButton btSim07;

    @FXML
    private RadioButton btSim08;

    @FXML
    private RadioButton btSim09;

    @FXML
    private RadioButton btSim10;

    @FXML
    private RadioButton btpositivo01;

    @FXML
    private RadioButton btpositivo013;

    @FXML
    private RadioButton btpositivo02;

    @FXML
    private RadioButton btpositivo03;

    @FXML
    private RadioButton btpositivo04;

    @FXML
    private RadioButton btpositivo05;

    @FXML
    private RadioButton btpositivo06;

    @FXML
    private RadioButton btpositivo07;

    @FXML
    private RadioButton btpositivo08;

    @FXML
    private RadioButton btpositivo09;

    @FXML
    private RadioButton btpositivo10;

    @FXML
    private RadioButton btpositivo11;

    @FXML
    private RadioButton btpositivo12;

    @FXML
    private TextField txtBoca01;

    @FXML
    private TextField txtBoca02;

    @FXML
    private TextField txtBoca03;

    @FXML
    private TextField txtBoca04;

    @FXML
    private TextField txtBoca05;

    @FXML
    private TextField txtBoca06;

    @FXML
    private TextField txtBoca07;
    
        // NOVO CAMPO: Armazena o ID do paciente (convertido para int)
        private int pacienteIdParaAnamnese; 

        // NOVO MÉTODO: Recebe o ID como String (do Model Paciente) e converte para int
        public void setPacienteId(String idPacienteString) {
            if (idPacienteString != null) {
                try {
                    // Converte a String para int. O trim() ajuda a remover espaços.
                    this.pacienteIdParaAnamnese = Integer.parseInt(idPacienteString.trim());
                } catch (NumberFormatException e) {
                    // Em caso de erro de conversão, defina um valor que indique erro ou lance exceção
                    System.err.println("Erro de conversão: O idPaciente ('" + idPacienteString + "') não é um número válido.");
                    // O valor 0 pode ser inválido, mas o erro de FK ocorrerá se for 0 e não existir, o que é o comportamento esperado.
                    this.pacienteIdParaAnamnese = 0; 
                }
            }
        }


    @FXML
    void actionSalvar(ActionEvent event) {

    	// 1. Coleta e mapeamento dos dados da tela para o objeto Model
        Anamnese anamnese = new Anamnese();

        // ** DEFINIR ID DO PACIENTE (Necessário para a FOREIGN KEY) **
        // Este ID deve ser obtido de algum lugar, como a tela de pesquisa de paciente 
        // ou da sessão atual. Exemplo:
        anamnese.setIdPaciente(this.pacienteIdParaAnamnese);
        
        try {
            // 2. Coleta das 12 perguntas Sim/Não
            
            // Lógica: se o RadioButton 'Sim' estiver selecionado, é true, senão (se 'Não' estiver selecionado), é false.
            // Para ser robusto, é ideal garantir que o ToggleGroup não está nulo.
            
            // Pergunta 1
            if (GrupoOp1.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 1 (Sim/Não)."); }
            anamnese.setBt01SimNao(btSim01.isSelected()); 

            // Pergunta 2
            if (GrupoOp2.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 2 (Sim/Não)."); }
            anamnese.setBt02SimNao(btSim02.isSelected()); 
            
            // ** CONTINUE AQUI PARA O RESTANTE DOS CAMPOS SIM/NÃO (ATÉ O BT10) **
            if(GrupoOp3.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 3 (Sim/Não)."); }
            anamnese.setBt03SimNao(btSim03.isSelected());
            
            if(GrupoOp4.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 4 (Sim/Não)."); }
            anamnese.setBt04SimNao(btSim04.isSelected());
            
            if (GrupoOp5.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 5 (Sim/Não)."); }
            anamnese.setBt05SimNao(btSim05.isSelected());
            
            if (GrupoOp6.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 6 (Sim/Não)."); }
            anamnese.setBt06SimNao(btSim06.isSelected());
            
            if (GrupoOp7.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 7 (Sim/Não)."); }
            anamnese.setBt07SimNao(btSim07.isSelected());
            
            if (GrupoOp8.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 8 (Sim/Não)."); }
            anamnese.setBt08SimNao(btSim08.isSelected());
            
            if (GrupoOp9.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 9 (Sim/Não)."); }
            anamnese.setBt09SimNao(btSim09.isSelected());
            
            if (GrupoOp10.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta 10 (Sim/Não)."); }
            anamnese.setBt10SimNao(btSim10.isSelected());
            
            // Pergunta 11 (Boca 1)
            if (GrupoBoca1.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta Boca 1 (Sim/Não)."); }
            anamnese.setBt11Boca(btBocaSim11.isSelected()); 

            // Pergunta 12 (Boca 2)
            if (GrupoBoca2.getSelectedToggle() == null) { throw new Exception("Responda a Pergunta Boca 2 (Sim/Não)."); }
            anamnese.setBt12Boca(btBocaSim12.isSelected()); 

            // 3. Coleta dos 13 Problemas Positivos (BIT NULL)
            // Lógica: se o RadioButton estiver selecionado, envia TRUE (1), senão, envia NULL.
            
            anamnese.setBt01Positivo(btpositivo01.isSelected() ? true : null);
            anamnese.setBt02Positivo(btpositivo02.isSelected() ? true : null);
            anamnese.setBt03Positivo(btpositivo03.isSelected() ? true : null);
            // O nome do campo no banco é bt4Positivo, mas o botão é btpositivo04, vamos seguir a numeração do banco:
            anamnese.setBt04Positivo(btpositivo04.isSelected() ? true : null); 
            anamnese.setBt05Positivo(btpositivo05.isSelected() ? true : null);
            anamnese.setBt06Positivo(btpositivo06.isSelected() ? true : null);
            anamnese.setBt07Positivo(btpositivo07.isSelected() ? true : null);
            anamnese.setBt08Positivo(btpositivo08.isSelected() ? true : null);
            anamnese.setBt09Positivo(btpositivo09.isSelected() ? true : null);
            anamnese.setBt10Positivo(btpositivo10.isSelected() ? true : null);
            anamnese.setBt11Positivo(btpositivo11.isSelected() ? true : null);
            anamnese.setBt12Positivo(btpositivo12.isSelected() ? true : null);
            anamnese.setBt13Positivo(btpositivo013.isSelected() ? true : null);
            
            // 4. Coleta dos 7 Campos de Texto
            anamnese.setTxtBoca01(txtBoca01.getText().trim());
            anamnese.setTxtBoca02(txtBoca02.getText().trim());
            anamnese.setTxtBoca03(txtBoca03.getText().trim());
            anamnese.setTxtBoca04(txtBoca04.getText().trim());
            anamnese.setTxtBoca05(txtBoca05.getText().trim());
            anamnese.setTxtBoca06(txtBoca06.getText().trim());
            anamnese.setTxtBoca07(txtBoca07.getText().trim());

            // 5. Chama o DAO para salvar no banco
            AnamneseDAO anamneseDAO = new AnamneseDAO();
            anamneseDAO.salvarAnamnese(anamnese);

            // 6. Feedback de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Anamnese salva com sucesso!");
            alert.showAndWait();
            
            // Opcional: Limpar a tela ou fechar a janela
            // limparCampos(); 

        } catch (SQLException e) {
            // Erro de banco de dados
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar no banco de dados: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            // Erro de validação/campos obrigatórios
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }

    	
    }


