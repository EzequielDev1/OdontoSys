package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import application.Main;
import dao.ConsultaDAO;
import dao.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Consulta;
import model.Paciente;

public class ControllerAgendamentoConsulta implements Initializable{

    @FXML
    private Button btAgendamento;

    @FXML
    private Button btAtendimento;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btEditar;

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
    private TableColumn<Consulta, String> columnDataConsulta;

    @FXML
    private TableColumn<Consulta, String> columnHrs;

    @FXML
    private TableColumn<Consulta, String> columnNome;

    @FXML
    private TableColumn<Consulta, String> columnStatus;
    
    @FXML
    private TableColumn<Consulta, String> columnIdConsulta;//Adicinei IdConsulta

    @FXML
    private TableView<Consulta> tableConsultas;

    @FXML
    private TextField txtPesqPaciente;

    @FXML
    private Text txtUser;

    @FXML
    void actionAgendamento(ActionEvent event) {

    }

    @FXML
    void actionAtendimento(ActionEvent event) throws IOException {
    	Main.TelaAtendimento();
    }

    public static Consulta consultaEditar = new Consulta();
    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
    	consultaEditar = null;
    	Main.TelaCadastroConsulta();
    	carregarTableConsultas();
    }

    //public static Consulta consultaEditar = new Consulta();--modificação teste, para saber se a tela cadastrarConsulta irá aparecer sem informações
    @FXML
    void actionEditar(ActionEvent event) throws IOException {
    int linha = tableConsultas.getSelectionModel().getSelectedIndex();
    if(linha==-1) {
    	Alert aviso = new Alert(AlertType.ERROR);
    	aviso.setTitle("Erro!");
    	aviso.setContentText("Selecione um consulta primeiro para editar!");
    	aviso.show();
    }else {
    	consultaEditar = tableConsultas.getItems().get(linha);
    	Main.TelaCadastroConsulta();
    	carregarTableConsultas();

    }
    //
    }

    @FXML
    void actionFuncionario(ActionEvent event) throws IOException {
    	Main.TelaFuncionario();
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
    	
    	if(txtPesqPaciente.getText().isEmpty()) {
    		carregarTableConsultas();
    	}else {
    		//se tiver preechido
    		pesquisarTableConsultas();
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
    
    ObservableList<Consulta> arrayConsultas;
    public void carregarTableConsultas() {
    	ConsultaDAO consultaDAO = new ConsultaDAO();
    	arrayConsultas = FXCollections.observableArrayList(consultaDAO.readTableConsulta());
    	
    	columnIdConsulta.setCellValueFactory(new PropertyValueFactory<>("idConsulta"));
    	columnNome.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
    	columnDataConsulta.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
    	columnHrs.setCellValueFactory(new PropertyValueFactory<>("horario"));
    	columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusConsulta"));
    	tableConsultas.setItems(arrayConsultas);
    }
    
    public void pesquisarTableConsultas() {
    	ConsultaDAO consultaDAO = new ConsultaDAO();
    	Consulta consulta = new Consulta();
    	consulta.setIdPaciente(txtPesqPaciente.getText());
    	arrayConsultas = FXCollections.observableArrayList(consultaDAO.search(consulta));
    	
    	columnNome.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
    	columnDataConsulta.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
    	columnHrs.setCellValueFactory(new PropertyValueFactory<>("horario"));
    	columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusConsulta"));
    	tableConsultas.setItems(arrayConsultas);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		consultaEditar = null;//para chamar a tela cadastro
		PacienteDAO pacienteDAO = new PacienteDAO();
		String[] listaPacientes = new String[pacienteDAO.read().size()];
		ArrayList<Paciente> arrayPacientes = pacienteDAO.read();
		for(int i =0; i < pacienteDAO.read().size(); i++) {
			Paciente paciente = new Paciente();
			paciente = arrayPacientes.get(i);
			listaPacientes[i] = paciente.getNomePaciente();
		}
		TextFields.bindAutoCompletion(txtPesqPaciente,listaPacientes);
		//Chamar o método para carregar a tabela*
		carregarTableConsultas(); 
		
		txtUser.setText(ControllerTelaLogin.funcionario.getNomeFuncionario());
	}
	
	

}
