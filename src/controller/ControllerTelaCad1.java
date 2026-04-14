package controller;

import java.io.IOException;

import application.Main;
import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Funcionario;

public class ControllerTelaCad1 {

    @FXML
    private Button btProximo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;
    
    public Funcionario funcionario = new Funcionario();
    FuncionarioDAO funcDAO;

    @FXML
    void actionProximo(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String nome = txtNome.getText();
        String senha = txtSenha.getText();

       
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Alert erro = new Alert(AlertType.ERROR);
            erro.setTitle("Erro no Cadastro");
            erro.setHeaderText("Campos obrigatórios");
            erro.setContentText("Preencha todos os campos antes de continuar.");
            erro.show();
        } else {
            
            funcionario.setEmail(email);
            funcionario.setNomeFuncionario(nome);
            funcionario.setSenha(senha);  
            funcDAO.create(funcionario);

            
            Main.TelaCadastro2();
            limparCampos();
        }
    }

   
    private void limparCampos() {
        txtNome.clear();
        txtEmail.clear();
        txtSenha.clear();
    }
}
