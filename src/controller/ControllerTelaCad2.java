package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Funcionario;

public class ControllerTelaCad2 implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btSalvar;

    @FXML
    private TextField txtCargo;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtRg;

    @FXML
    private TextField txtTelefone;

    @FXML
    private DatePicker txtDataNasc;

    public static Funcionario funcionarioEditar = null;

    @FXML
    void actionCancelar(ActionEvent event) {
        funcionarioEditar = null;
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actionSalvar(ActionEvent event) {
        Funcionario funcionario = new Funcionario();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        if (txtCargo.getText().isEmpty() || txtCpf.getText().isEmpty() || txtDataNasc.getValue() == null ||
            txtEndereco.getText().isEmpty() || txtRg.getText().isEmpty() || txtTelefone.getText().isEmpty()) {

            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro!");
            erro.setContentText("Preencha todos os campos antes de continuar!");
            erro.show();
            return;
        }

        if (!validarCPF(txtCpf.getText())) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro!");
            erro.setContentText("CPF inválido! Digite apenas números e verifique o CPF.");
            erro.show();
            return;
        }

        funcionario.setCargo(txtCargo.getText());
        funcionario.setCpfFuncionario(txtCpf.getText());
        funcionario.setDataNasc(txtDataNasc.getValue().toString());
        funcionario.setEndereco(txtEndereco.getText());
        funcionario.setRg(txtRg.getText());
        funcionario.setTelefone(txtTelefone.getText());

       
        if (funcionarioEditar == null) {
            funcionarioDAO.create(funcionario);
            Alert cadastrado = new Alert(Alert.AlertType.INFORMATION);
            cadastrado.setTitle("Cadastro");
            cadastrado.setContentText("Funcionário cadastrado com sucesso!");
            cadastrado.show();
        } else {
            funcionarioDAO.update(funcionario);
            Alert cadastrado = new Alert(Alert.AlertType.INFORMATION);
            cadastrado.setTitle("Atualização");
            cadastrado.setContentText("Funcionário atualizado com sucesso!");
            cadastrado.show();
            funcionarioEditar = null;
        }

        Stage stage = (Stage) btSalvar.getScene().getWindow();
        stage.close();
    }

   
    public static boolean validarCPF(String cpf) {
        
        cpf = cpf.replaceAll("\\D", "");

        
        if (cpf.length() != 11) return false;

        
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            int resto = 11 - (soma % 11);
            int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

            soma = 0;
            for (int i = 0; i < 10; i++) soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            resto = 11 - (soma % 11);
            int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                   digito2 == Character.getNumericValue(cpf.charAt(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        txtCpf.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                txtCpf.setText(newText.replaceAll("[^\\d]", ""));
            }
        });

        
        if (funcionarioEditar != null) {
            txtCargo.setText(funcionarioEditar.getCargo());
            txtCpf.setText(funcionarioEditar.getCpfFuncionario());
            txtEndereco.setText(funcionarioEditar.getEndereco());
            txtRg.setText(funcionarioEditar.getRg());
            txtTelefone.setText(funcionarioEditar.getTelefone());

            String dataNasc = funcionarioEditar.getDataNasc().replace("-", "/");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            txtDataNasc.setValue(LocalDate.parse(dataNasc, format));
        }
    }
}
