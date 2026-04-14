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

public class ControllerTelaLogin {

    @FXML
    private Button btEntrar;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
    static Funcionario funcionario = new Funcionario();
	FuncionarioDAO funcDAO = new FuncionarioDAO();

    @FXML
    void actionEntrar(ActionEvent event) throws IOException {
    	String usuario = txtUsuario.getText();
    	String password = txtSenha.getText();

    	funcionario = funcDAO.autenticarUser(usuario, password);
    	System.out.println(funcionario.getCpfFuncionario());
    	
    	if(funcionario.getCpfFuncionario() != null) {
	    	if(usuario.equals("") || password.equals("")) {
	    		Alert aviso = new Alert(AlertType.ERROR);
	    		aviso.setTitle("Erro ao realizer login!");
	    		aviso.setContentText("Verifique se as informações estão corretas e tente novamente!");
	    		aviso.show();
	    	}else if( funcionario.getCpfFuncionario().equals(usuario) && funcionario.getSenha().equals(password)) {
	    		Alert saudacao = new Alert(AlertType.INFORMATION);
	    		saudacao.setHeaderText("Seja Bem Vindo!");
	    		saudacao.setContentText("Seja bem vindo de volta " + funcionario.getNomeFuncionario() + "!");
	    		saudacao.show();
	        	System.out.println(funcionario.getCpfFuncionario());

	    		Main.TelaAtendimento();
	    	}
    	}else {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro ao realizer login!");
    		aviso.setContentText("Verifique se as informações estão corretas e tente novamente!");
    		aviso.show();
    	}
    }
    }


