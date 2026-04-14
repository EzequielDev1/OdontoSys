package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.Main;
import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Funcionario;

public class ControllerTelaCdastroFuncionario implements Initializable {
      
    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> txtChoiceCargo;

    @FXML
    private ChoiceBox<String> txtChoiceGenero;

    @FXML
    private ChoiceBox<String> txtChoiceNivel; 

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtSenha;


    @FXML
    private TextField txtNomeFuncionario;

    @FXML
    private TextField txtRg;

    @FXML
    private TextField txtTelefone;


    @FXML
    void actionCancelar(ActionEvent event) throws IOException {
        ControllerTelaFuncionario.funcionarioEdi = null;
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
        Main.TelaFuncionario();
    }

    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
        Funcionario funcionario = new Funcionario();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        // Validação de campos obrigatórios
        if (txtNomeFuncionario.getText().isEmpty() || txtSenha.getText().isEmpty() || txtCpf.getText().isEmpty() || txtChoiceCargo.getValue() == null ||
            txtEndereco.getText().isEmpty() || txtRg.getText().isEmpty() || txtTelefone.getText().isEmpty() ||
            txtChoiceGenero.getValue() == null || txtChoiceNivel.getValue() == null ||
            txtEmail.getText().isEmpty() || datePicker.getValue() == null) {

            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro!");
            erro.setContentText("Preencha todos os campos antes de continuar!");
            erro.show();
            return;
        }

        // Validação de CPF
//        if (!validarCPF(txtCpf.getText())) {
//            Alert erro = new Alert(Alert.AlertType.ERROR);
//            erro.setTitle("Erro!");
//            erro.setContentText("CPF inválido! Digite apenas números e verifique o CPF.");
//            erro.show();
//            return;
//        }

        funcionario.setNomeFuncionario(txtNomeFuncionario.getText());
        funcionario.setCpfFuncionario(txtCpf.getText());
        funcionario.setEndereco(txtEndereco.getText());
        funcionario.setRg(txtRg.getText());
        funcionario.setTelefone(txtTelefone.getText());
        funcionario.setEmail(txtEmail.getText());
        funcionario.setGenero(txtChoiceGenero.getValue());
        funcionario.setCargo(txtChoiceCargo.getValue());
        funcionario.setNivel(txtChoiceNivel.getValue());
        funcionario.setDataNasc(datePicker.getValue().toString());
        funcionario.setSenha(txtSenha.getText());

        
        if ( ControllerTelaFuncionario.funcionarioEdi == null) {
            funcionarioDAO.create(funcionario);
            Alert cadastrado = new Alert(Alert.AlertType.INFORMATION);
            cadastrado.setTitle("Cadastro");
            cadastrado.setContentText("Funcionário cadastrado com sucesso!");
            cadastrado.show();
            Main.TelaFuncionario();
        } else {
          
            funcionario.setIdFuncionario(ControllerTelaFuncionario.funcionarioEdi.getIdFuncionario());
            funcionarioDAO.update(funcionario);
            Alert atualizado = new Alert(Alert.AlertType.INFORMATION);
            atualizado.setTitle("Atualização");
            atualizado.setContentText("Funcionário atualizado com sucesso!");
            atualizado.show();         
            ControllerTelaFuncionario.funcionarioEdi = null; 
            Main.TelaFuncionario();
        }

       // Fecha a tela
    }

   
    public static boolean validarCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++)
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            int resto = 11 - (soma % 11);
            int digito1 = (resto >= 10) ? 0 : resto;

            soma = 0;
            for (int i = 0; i < 10; i++)
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            resto = 11 - (soma % 11);
            int digito2 = (resto >= 10) ? 0 : resto;

            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                   digito2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtChoiceGenero.getItems().addAll("Masculino", "Feminino");
        txtChoiceCargo.getItems().addAll("Secretária (O)", "Dentista");

       
        txtChoiceNivel.getItems().addAll("0", "1");

        
     /*   txtCpf.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                txtCpf.setText(newText.replaceAll("[^\\d]", ""));
            }
        });*/

        
        if (ControllerTelaFuncionario.funcionarioEdi != null) {
            Funcionario f = ControllerTelaFuncionario.funcionarioEdi;
            txtNomeFuncionario.setText(f.getNomeFuncionario());
            txtCpf.setText(f.getCpfFuncionario());
            txtSenha.setText(f.getSenha());
            txtEndereco.setText(f.getEndereco());
            txtRg.setText(f.getRg());
            txtTelefone.setText(f.getTelefone());
            txtEmail.setText(f.getEmail());
            txtChoiceGenero.setValue(f.getGenero());
            txtChoiceCargo.setValue(f.getCargo());
            txtChoiceNivel.setValue(String.valueOf(f.getNivel()));
            String dataNasc = ControllerTelaFuncionario.funcionarioEdi.getDataNasc();
			dataNasc = dataNasc.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(dataNasc, format);
			datePicker.setValue(localDate);
            txtCpf.setEditable(false);
            txtRg.setEditable(false);

         
			
		}
    }
}
