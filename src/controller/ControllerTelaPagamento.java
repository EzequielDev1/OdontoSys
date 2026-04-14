package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.ConsultaDAO;
import dao.ConsultaTratamentoDAO;
import dao.PacienteDAO;
import dao.PagamentoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.ConsultaTratamento;
import model.Pagamento;

public class ControllerTelaPagamento implements Initializable {

	@FXML
	private Button btAgendamento;

	@FXML
	private Button btAtendimento;

	@FXML
	private Button btFuncionario;

	@FXML
	private Button btPagamento;

	@FXML
	private Button btPesquisar;

	@FXML
    private Button btPaciente;
	
	@FXML
	private Button btSair;

	@FXML
	private TableColumn<Pagamento, String> columnPaciente;

	@FXML
	private TableColumn<Pagamento, String> columnProcedimento;

	@FXML
	private TableColumn<Pagamento, String> columnValor;

    @FXML
    private TableColumn<Pagamento, String> columnData;
	
	@FXML
	private TableView<Pagamento> tablePagamentos;

	@FXML
	private DatePicker txtData;

	@FXML
	private TextField txtPaciente;
	
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
	void actionFuncionario(ActionEvent event) throws IOException {
		Main.TelaFuncionario();
	}

	@FXML
	void actionPagamento(ActionEvent event) {

	}

	@FXML
	void actionPesquisar(ActionEvent event) {
			pesquisarTablePagamento(); // faz a pesquisa
	}
	

    @FXML
    void actionPaciente(ActionEvent event) throws IOException {
    	Main.TelaPaciente();
    }

	@FXML
	void actionSair(ActionEvent event) {
		Alert msg = new Alert(AlertType.CONFIRMATION);
		msg.setHeaderText("Sair do Sistema");
		msg.setContentText("Deseja realmente sair do sistema?");
		msg.setTitle("Deseja sair do sistema?");

		Optional<ButtonType> sair = msg.showAndWait();
		if (sair.isPresent() && sair.get() == ButtonType.OK) {
			System.exit(0); // Encerra a aplicação
		}
	}

	private ArrayList<Pagamento> arrayPagamento = new ArrayList<>();
	ObservableList<Pagamento> pagamentos;
	private void carregarTablePagamento() {
		PagamentoDAO pagamentoDAO = new PagamentoDAO();

		pagamentos = FXCollections.observableArrayList(pagamentoDAO.read());
		columnPaciente.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
		columnProcedimento.setCellValueFactory(new PropertyValueFactory<>("nomeTratamento"));
		columnValor.setCellValueFactory(new PropertyValueFactory<>("precoTratamento"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));

		tablePagamentos.setItems(pagamentos);
	}

	public void pesquisarTablePagamento() {
		PagamentoDAO pagamentoDAO = new PagamentoDAO();
		if(!(txtData.getValue()==null)) {
			System.out.println("pesquisa de data");
			pagamentos = FXCollections.observableArrayList(pagamentoDAO.search(txtData.getValue().toString()));
			
			columnPaciente.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
			columnProcedimento.setCellValueFactory(new PropertyValueFactory<>("nomeTratamento"));
			columnValor.setCellValueFactory(new PropertyValueFactory<>("precoTratamento"));
			columnData.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
			
			tablePagamentos.setItems(pagamentos);
		}else if(!txtPaciente.getText().equals("")){
			System.out.println("pesquisa de nome");
			pagamentos = FXCollections.observableArrayList(pagamentoDAO.search(txtPaciente.getText()));
			
			columnPaciente.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
			columnProcedimento.setCellValueFactory(new PropertyValueFactory<>("nomeTratamento"));
			columnValor.setCellValueFactory(new PropertyValueFactory<>("precoTratamento"));
			columnData.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
			
			tablePagamentos.setItems(pagamentos);
		}else{
			System.out.println("pesquisa");
			carregarTablePagamento();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtUser.setText(ControllerTelaLogin.funcionario.getNomeFuncionario());
		// Carrega todos os tratamentos inicialmente
		System.out.println("tela pagamento");
		PagamentoDAO pagamentoDAO = new PagamentoDAO();
		carregarTablePagamento();
		

	}

}
