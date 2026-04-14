package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;

import dao.FuncionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import model.Funcionario;
import model.Paciente;

public class ControllerTelaFuncionario implements Initializable{

    @FXML
    private Button btAgendamento;

    @FXML
    private Button btAtendimento;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btPacientes;

    @FXML
    private Button btPagamento;

    @FXML
    private Button btPesquisar;

    @FXML
    private Button btSair;

    @FXML
    private Text txtUser;

    @FXML
    private TableColumn<Funcionario, String> columnCargo;

    @FXML
    private TableColumn<Funcionario, String> columnCpf;

    @FXML
    private TableColumn<Funcionario, String> columnID;

    @FXML
    private TableColumn<Funcionario, String> columnNome;

    @FXML
    private TableView<Funcionario> TableFuncionario;

    @FXML
    private TextField txtPesqFuncionario;

    static Funcionario funcionario = new Funcionario();
    
    public static Funcionario funcionarioEditar = new Funcionario();
    @FXML
    void actionAgendamento(ActionEvent event) throws IOException {
    	Main.TelaAgendamento();
    }

    @FXML
    void actionAtendimento(ActionEvent event) throws IOException {
    	Main.TelaAtendimento();
    }

    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
    	funcionarioEdi = null;
    	Main.TelaCadastroFuncionario();
    }
    
    public static Funcionario funcionarioEdi = new Funcionario();
    @FXML
    void actionEditar(ActionEvent event) throws IOException {
    int linha = TableFuncionario.getSelectionModel().getSelectedIndex();
    	
    	if(linha == -1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro ao Editar!");
    		aviso.setContentText("Selecione um funcionario primeiro para editar!");
    		aviso.show();
    		Main.TelaFuncionario();
    	}else {
    		funcionarioEdi = TableFuncionario.getItems().get(linha);
    		Main.TelaEditarFuncionario();
    	}
    	carregarTableFuncionario();
    }

    @FXML
    void actionExcluir(ActionEvent event) {
     int linha = TableFuncionario.getSelectionModel().getSelectedIndex();
    	
    	if(linha == -1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro ao apagar!");
    		aviso.setContentText("Selecione um Funcionário primeiro!");
    		aviso.show();
    	}else{
    		Funcionario funcionario = new Funcionario();
    		FuncionarioDAO funcDao = new FuncionarioDAO();
    		
    		funcionario = TableFuncionario.getItems().get(linha);
    		
    		Alert msg = new Alert(AlertType.CONFIRMATION);
        	msg.setHeaderText("Excluir Funcionário");
        	msg.setContentText("Excluir");
        	msg.setTitle("Deseja realmente excluir o Funcionário "+ funcionario.getNomeFuncionario());
        	
        	Optional<ButtonType> confirmacao = msg.showAndWait();
        	
        	if(confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
        		funcDao.delete(funcionario.getCpfFuncionario());
        		
        		Alert aviso = new Alert(AlertType.INFORMATION);
        		aviso.setTitle("Funcionário apagado!");
        		aviso.setContentText("O Funcionário foi apagado com sucesso!");
        		aviso.show();
        		
        		carregarTableFuncionario();
        	}
    	}
    }

    @FXML
    void actionFuncionario(ActionEvent event) {
    	
    }

    @FXML
    void actionPacientes(ActionEvent event) throws IOException {
    	Main.TelaPaciente();
    }

    @FXML
    void actionPagamento(ActionEvent event) throws IOException {
    	Main.TelaPagamento();
    }

    @FXML
    void actionPesquisar(ActionEvent event) {
    	if(txtPesqFuncionario.getText().isEmpty()) {
    		carregarTableFuncionario();;
    	}else {
    		pesquisarTableCliente();
    	}
    	
    }

    @FXML
    void actionSair(ActionEvent event) throws IOException {
    	Alert msg = new Alert(AlertType.CONFIRMATION);
    	msg.setHeaderText("Sair do Sistema");
    	msg.setContentText("Deseja realmente sair do sistema?");
    	msg.setTitle("Deseja sair do sistema?");
    	
    	Optional<ButtonType> sair = msg.showAndWait();
    	
    	if(sair.isPresent() && sair.get() == ButtonType.OK) {
    		Main.TelaLogin();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtUser.setText(ControllerTelaLogin.funcionario.getNomeFuncionario());
		carregarTableFuncionario();
		funcionarioEditar = null;
		
		
		
		
	}
	
	ObservableList<Funcionario> arrayFuncionario;
	public void carregarTableFuncionario() {
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		arrayFuncionario = FXCollections.observableArrayList(funcDAO.read());
		
		columnID.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfFuncionario"));
		columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		TableFuncionario.setItems(arrayFuncionario);
	}
	
	public void pesquisarTableCliente() {
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		arrayFuncionario = FXCollections.observableArrayList(funcDAO.search(txtPesqFuncionario.getText()));
		
		columnID.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfFuncionario"));
		columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		TableFuncionario.setItems(arrayFuncionario);
	}

}
