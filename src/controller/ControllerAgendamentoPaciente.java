package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.PacienteDAO;
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
import model.Paciente;

public class ControllerAgendamentoPaciente implements Initializable{

	@FXML
	private Button btAgendamento;

	@FXML
	private Button btAtendimento;

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btEditar;

	@FXML
	private Button btFuncionario;

	@FXML
	private Button btPaciente;

	@FXML
	private Button btPagamento;

	@FXML
	private Button btPesquisar;

	@FXML
	private Button btSair;

	@FXML
	private TableColumn<Paciente, String> columnId;

	@FXML
	private TableColumn<Paciente, String> columnCpf;

	@FXML
	private TableColumn<Paciente, String> columnNome;

	@FXML
	private TableColumn<Paciente, String> columnTelefone;

	@FXML
	private TableColumn<Paciente, String> columnEmail;

	@FXML
	private TableView<Paciente> tablePaciente;

	@FXML
	private TextField txtPesqPaciente;
	
	@FXML
    private Text txtUser;

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
		Main.TelaCadastro();
		carregarTablePaciente();
	}
	public static Paciente pacienteEditar = new Paciente(); 

	@FXML
	void actionEditar(ActionEvent event)throws IOException {
		int linha = tablePaciente.getSelectionModel().getSelectedIndex();

		if(linha == -1) {
			Alert aviso = new Alert(AlertType.ERROR);
			aviso.setTitle("Erro!");
			aviso.setContentText("Selecione um paciente primeiro para editar!");
			aviso.show();
		}else {
			pacienteEditar = tablePaciente.getItems().get(linha);
			Main.TelaCadastro();
		}
		carregarTablePaciente();

	}

	@FXML
	void actionExcluir(ActionEvent event) {
		int linha = tablePaciente.getSelectionModel().getSelectedIndex();

		if(linha == -1) {
			Alert aviso = new Alert(AlertType.ERROR);
			aviso.setTitle("Erro ao apagar!");
			aviso.setContentText("Selecione um pacienteprimeiro!");
			aviso.show();
		}else {
			Paciente paciente = new Paciente();
			PacienteDAO pacienteDAO =  new PacienteDAO();
			paciente = tablePaciente.getItems().get(linha);

			Alert msg = new Alert(AlertType.CONFIRMATION);
			msg.setTitle("Excluir Paciente");
			msg.setHeaderText("Excluir");
			msg.setContentText("Deseja realmente excluir o paciente "+paciente.getNomePaciente()+"?");

			Optional<ButtonType> confirmacao = msg.showAndWait();
			if(confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
				pacienteDAO.delete(paciente.getCpfPaciente());
				Alert aviso = new Alert(AlertType.INFORMATION);
				aviso.setTitle("Paciete apagado!");
				aviso.setContentText("O paciente foi apagado com sucesso");
				aviso.show();
				carregarTablePaciente();

			}
		}

	}
	
	@FXML
    void actionPaciente(ActionEvent event) {

    }

	@FXML
	void actionFuncionario(ActionEvent event) throws IOException {
		Main.TelaFuncionario();
	}

	@FXML
	void actionPagamento(ActionEvent event) throws IOException {
		Main.TelaPagamento();
	}

	@FXML
	void actionPesquisar(ActionEvent event) {
		if (btPesquisar.getText().isEmpty()) {
			carregarTablePaciente();
		} else {
			carregarTablePesquisar();
			// pesquisarTablePaciente();
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

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtUser.setText(ControllerTelaLogin.funcionario.getNomeFuncionario());//AparecerFuncionario
		
		carregarTablePaciente();
		pacienteEditar = null;

	}

	ObservableList<Paciente> arrayPacientes;
	public void carregarTablePaciente() {
		PacienteDAO pacienteDAO = new PacienteDAO();
		arrayPacientes = FXCollections.observableArrayList(pacienteDAO.read());
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfPaciente"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		tablePaciente.setItems(arrayPacientes);
	}

	public void carregarTablePesquisar() {
		PacienteDAO pacienteDAO = new PacienteDAO();
		arrayPacientes 	= FXCollections.observableArrayList(pacienteDAO.search(txtPesqPaciente.getText()));

		columnId.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfPaciente"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		tablePaciente.setItems(arrayPacientes);
	}

}
