package controller;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Consulta;
import model.Paciente;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Comparator; // Novo: Para a ordenação
import java.util.ArrayList;


public class ControllerTelaAtendimento implements Initializable{



	@FXML
	private Button btAgendamento;



	@FXML
	private Button btAtendimento;



	@FXML
	private Button btCancelar;



	@FXML
	private Button btFuncionario;



	@FXML
	private Button btPaciente;



	@FXML
	private Button btPagamento;



	@FXML
	private Button btPesquisar;



	@FXML
	private Button btProntuario;



	@FXML
	private Button btRenovarDados;



	@FXML
	private Button btSair;



	@FXML
	private TableColumn<Consulta, String> columnPaciente;



	@FXML
	private TableColumn<Consulta, String> columnData;



	@FXML
	private TableColumn<Consulta, String> columnHorario;



	@FXML
	private TableColumn<Consulta, String> columnStatus;



	@FXML
	private TableView<Consulta> tableAtendimento;



	@FXML
	private TextField txtNome;



	@FXML
	private Text txtUser;
	
	@FXML
    private DatePicker txtDataAtend;



	public static Paciente pacienteInfo = null;



	@FXML
	void actionAgendamento(ActionEvent event) throws IOException {
		Main.TelaAgendamento();
	}



	@FXML
	void actionAtendimento(ActionEvent event) {


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
	void actionPaciente(ActionEvent event) throws IOException {
		Main.TelaPaciente();
	}


	@FXML
	void actionPesquisar(ActionEvent event) {
//		if(txtNome.getText().equals("")) {
//			carregarTableAtendimentoDoDia();
//		}else {
//			pesquisarTableAtendimento();
//		}
//	}
		if(!txtNome.getText().trim().isEmpty()) {
	        pesquisarTableAtendimento();
//			carregarTableAtendimentoDoDia();
	    } else {
	        // Lógica de pesquisa por DATA, usando a data do DatePicker
	        carregarTableAtendimentoDoDia();
	    } 
	}

	@FXML
	void actionMenuClick(MouseEvent event) {
		if(txtNome.getText().length() > 3) {
			PacienteDAO pacienteDAO = new PacienteDAO();
			Paciente paciente = new Paciente();
			paciente.setNomePaciente(txtNome.getText());
			ArrayList<Paciente> pacientes = new ArrayList<>(); 
			pacientes = pacienteDAO.search(paciente.getNomePaciente());
			paciente = pacientes.get(0);
			// CORREÇÃO: VERIFICAR SE A LISTA NÃO ESTÁ VAZIA
//	        if (pacientes != null && !pacientes.isEmpty()) { 
//	            paciente = pacientes.get(0);
//	        } else {
//	            // Se o paciente não for encontrado, apenas ignora ou lida com a situação (não lança erro)
//	            System.out.println("Paciente não encontrado para autocomplete.");
//	        }
		}
	}



	@FXML
	void actionMenuType(KeyEvent event) {
		if(txtNome.getText().length() > 3) {
			PacienteDAO pacienteDAO = new PacienteDAO();
			Paciente paciente = new Paciente();
			paciente.setNomePaciente(txtNome.getText());
			ArrayList<Paciente> pacientes = new ArrayList<>(); 
			pacientes = pacienteDAO.search(paciente.getNomePaciente());
			//paciente = pacientes.get(0);
			
			// CORREÇÃO: VERIFICAR SE A LISTA NÃO ESTÁ VAZIA
	        if (pacientes != null && !pacientes.isEmpty()) { 
	            paciente = pacientes.get(0); 
	        } else {
	            // Se o paciente não for encontrado, apenas ignora ou lida com a situação (não lança erro)
	            System.out.println("Paciente não encontrado durante a digitação.");
	        }
		}
	}



	@FXML
	void actionCancelar(ActionEvent event) {
		int linha = tableAtendimento.getSelectionModel().getSelectedIndex();


		if(linha == -1) {
			Alert aviso = new Alert(AlertType.ERROR);
			aviso.setTitle("Erro ao cancelar!");
			aviso.setContentText("Selecione uma consulta primeiro!");
			aviso.show();
		}else{
			Consulta consulta = new Consulta();
			ConsultaDAO consultaDAO = new ConsultaDAO();

			consulta = tableAtendimento.getItems().get(linha);

			Alert msg = new Alert(AlertType.CONFIRMATION);
			msg.setHeaderText("Cancelar Consulta");
			msg.setTitle("Cancelar");
			msg.setContentText("Deseja realmente cancelar a consulta do paciente "+ consulta.getIdPaciente());
			
			Optional<ButtonType> confirmacao = msg.showAndWait();

			//pegando as informações que estão na TABELA
			Consulta consultaCancelar = new Consulta();
			consultaCancelar = tableAtendimento.getItems().get(linha);
			consultaCancelar = consultaDAO.searchInformaConsulta(consultaCancelar).get(0);
			
			if(confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
				consultaDAO.delete(consultaCancelar.getIdConsulta());

				Alert aviso = new Alert(AlertType.INFORMATION);
				aviso.setTitle("Consulta Cancelada!");
				aviso.setContentText("A consulta foi cancelada com sucesso!");
				aviso.show();

				carregarTableAtendimentoDoDia();
			}
		}
	}

	
	public static Paciente pacienteAtendimento = new Paciente();
	public static int idPacienteSelecionado;

	public static int getIdPacienteSelecionado() {
	    return idPacienteSelecionado;
	}

	public static void setIdPacienteSelecionado(int idPacienteSelecionado) {
	    ControllerTelaAtendimento.idPacienteSelecionado = idPacienteSelecionado;
	}
	
	
	@FXML
	void actionProntuario(ActionEvent event) throws IOException {
	    int linha = tableAtendimento.getSelectionModel().getSelectedIndex();

	    if(linha == -1) {
	        Alert aviso = new Alert(AlertType.ERROR);
	        aviso.setTitle("Erro ao Pegar o Prontuário!");
	        aviso.setContentText("Selecione um paciente primeiro!");
	        aviso.show();
	    } else {
	        Consulta consulta = tableAtendimento.getItems().get(linha);

	        PacienteDAO pacienteDAO = new PacienteDAO();
	        Paciente paciente = pacienteDAO.search(consulta.getIdPaciente()).get(0);
	        System.out.println("id do paciente: "+ paciente.getNomePaciente());
	        pacienteAtendimento = paciente;
//	        ControllerTelaAtendimento.setIdPacienteSelecionado(Integer.parseInt(paciente.getIdPaciente()));
	        Main.TelaProntuario();
	    }
	}

//	@FXML
//	void actionProntuario(ActionEvent event) throws IOException {
//		int linha = tableAtendimento.getSelectionModel().getSelectedIndex();
//
//		if(linha == -1) {
//			Alert aviso = new Alert(AlertType.ERROR);
//			aviso.setTitle("Erro ao Pegar o Prontuário!");
//			aviso.setContentText("Selecione um paciente primeiro!");
//			aviso.show();
//		}else{
//			Consulta consulta = new Consulta();
//
//			consulta = tableAtendimento.getItems().get(linha);
//			PacienteDAO pacienteDAO = new PacienteDAO();
//			Paciente paciente = new Paciente();
//			paciente = pacienteDAO.search(consulta.getIdPaciente()).get(0);
//
//			pacienteInfo = paciente;
//
//			Main.TelaProntuario();
//		}
//	}

	@FXML
	void actionRenovarDados(ActionEvent event) {
		Alert msg = new Alert(AlertType.CONFIRMATION);
		msg.setHeaderText("Renovar a Tabela");
		msg.setContentText("Deseja obter apenas as cosultas do dia?");
		msg.setTitle("Deseja renovar a tabela?");

		Optional<ButtonType> sair = msg.showAndWait();

		if(sair.isPresent() && sair.get() == ButtonType.OK) {
			carregarTableAtendimentoDoDia();
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
		carregarTableAtendimentoDoDia();
		//carregarTableAtendimentoTotal();
		txtUser.setText(ControllerTelaLogin.funcionario.getNomeFuncionario());
		
		// -> ESTE BLOCO ESTÁ CAUSANDO O ERRO DE CONEXÃO E IMPEDINDO A TELA DE CARREGAR!
				try {
					// faz o menu suspenso de pesquisar funcionar
					PacienteDAO pacienteDAO = new PacienteDAO();
					ArrayList<Paciente> arrayPacientes = pacienteDAO.read(); // <-- AQUI O ERRO OCORRE
					
					String[] listaPacientes = new String[arrayPacientes.size()];

					for(int i=0; i < arrayPacientes.size(); i++) {
						Paciente paciente = new Paciente();
						paciente = arrayPacientes.get(i);
						listaPacientes[i] = paciente.getNomePaciente();	
					}
			
					TextFields.bindAutoCompletion(txtNome, listaPacientes).setOnAutoCompleted(event -> actionMenuClick(null));
				} catch (RuntimeException e) {
					// Apenas ignora ou loga o erro para permitir que o restante da tela carregue.
					// É crucial que a tela não trave por causa de um recurso secundário.
					System.err.println("Atenção: Falha ao carregar a lista de pacientes para o AutoComplete: " + e.getMessage());
				}

		//fazendo o menu suspenso de pesquisar funcionar
//		PacienteDAO pacienteDAO = new PacienteDAO();
//		String[] listaPacientes = new String[pacienteDAO.read().size()];
//		ArrayList<Paciente> arrayPacientes = pacienteDAO.read();
//		for(int i=0; i < pacienteDAO.read().size(); i++) {
//			Paciente paciente = new Paciente();
//			paciente = arrayPacientes.get(i);
//			listaPacientes[i] = paciente.getNomePaciente();	
//		}
//
//		TextFields.bindAutoCompletion(txtNome, listaPacientes).setOnAutoCompleted(event -> actionMenuClick(null));

	}

	ObservableList<Consulta> arrayConsultas;
	public void carregarTableAtendimentoDoDia() {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		Consulta consulta = new Consulta();
		consulta.setDataConsulta(getDataSelecionadaFormatada());
		arrayConsultas = FXCollections.observableArrayList(consultaDAO.search(consulta));
		
		// 1. CHAVE: Buscar TODOS os agendamentos (assumindo que consultaDAO.read() faz isso)
//				ArrayList<Consulta> consultas = consultaDAO.readTableConsulta(); 

			
				// 3. Converte para ObservableList
//				arrayConsultas = FXCollections.observableArrayList(consultas);

		columnData.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		columnPaciente.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusConsulta"));
		
//	

		tableAtendimento.setItems(arrayConsultas);

	}

	
	public void carregarTableAtendimentoTotal() {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		Consulta consulta = new Consulta();
//		consulta.setDataConsulta(getDataAtualFormatada());
//		arrayConsultas = FXCollections.observableArrayList(consultaDAO.search(consulta));
		
		// 1. CHAVE: Buscar TODOS os agendamentos (assumindo que consultaDAO.read() faz isso)
				ArrayList<Consulta> consultas = consultaDAO.readTableConsulta(); 

				// 2. Ordena a lista: primeiro pela data (orderby dia), depois pelo horário.
		        // Se 'dataConsulta' e 'horario' são Strings no formato correto (yyyy-MM-dd e HH:mm), isso funciona.
//				consultas.sort(Comparator
//						.comparing(Consulta::getDataConsulta)
//						.thenComparing(Consulta::getHorario));
//				
				// 3. Converte para ObservableList
				arrayConsultas = FXCollections.observableArrayList(consultas);

		columnData.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		columnPaciente.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusConsulta"));
		
//		columnPaciente.setCellFactory(column -> {
//		    return new javafx.scene.control.TableCell<Consulta, String>() {
//		        @Override
//		        protected void updateItem(String idPaciente, boolean empty) { // O item aqui é o valor de idPaciente
//		            super.updateItem(idPaciente, empty);
//		            if (empty || idPaciente == null) {
//		                setText(null);
//		            } else {
//		                // Busca o nome do paciente a partir do ID
//		                // Atenção: Esta linha pressupõe que `idPaciente` realmente contém o ID aqui.
//		                PacienteDAO pacienteDAO = new PacienteDAO();
//		                try {
//		                    // Assumindo que search(String id) retorna uma lista de Paciente
//		                    // e você pega o primeiro elemento.
//                            // OBS: A classe PacienteDAO.search(String id) deve ser capaz de buscar por ID.
//		                    Paciente paciente = pacienteDAO.search(idPaciente).get(0); 
//		                    setText(paciente.getNomePaciente());
//		                } catch (Exception e) {
//		                    // Se falhar, exibe o ID
//		                    setText(idPaciente); 
//		                }
//		            }
//		        }
//		    };
//		});
//
//		columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusConsulta"));

		tableAtendimento.setItems(arrayConsultas);

	}
	
	public void pesquisarTableAtendimento() {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		
		Consulta consulta = new Consulta();
		consulta.setIdPaciente(txtNome.getText());
		arrayConsultas = FXCollections.observableArrayList(consultaDAO.search(consulta));

		columnData.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		columnPaciente.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusConsulta"));
		
//		columnPaciente.setCellFactory(column -> {
//		    return new javafx.scene.control.TableCell<Consulta, String>() {
//		        @Override
//		        protected void updateItem(String idPaciente, boolean empty) {
//		            super.updateItem(idPaciente, empty);
//		            
//		            if (empty || idPaciente == null || idPaciente.trim().isEmpty()) {
//		                setText(null);
//		            } else {
//		                PacienteDAO pacienteDAO = new PacienteDAO();
//		                try {
//		                    // 1. CHAVE: Buscar o paciente
//		                    // Assume que `idPaciente` é o ID numérico/texto que identifica o paciente.
//		                    ArrayList<Paciente> pacientes = pacienteDAO.search(idPaciente); 
//		                    
//		                    // 2. CHAVE: Verificar se a lista não está vazia ANTES de tentar acessar o índice 0
//		                    if (pacientes != null && !pacientes.isEmpty()) {
//		                        Paciente paciente = pacientes.get(0); 
//		                        setText(paciente.getNomePaciente());
//		                    } else {
//		                        // Se a busca retornar vazia (paciente não existe), exibe a informação do ID.
//		                        setText("[ID Inválido: " + idPaciente + "]"); 
//		                    }
//		                } catch (Exception e) {
//		                    // CATCH: Para qualquer outro erro (conexão, etc.)
//		                    System.err.println("Erro ao buscar nome do Paciente ID " + idPaciente + ": " + e.getMessage());
//		                    setText("[Erro na Busca]"); 
//		                }
//		            }
//		        }
//		    };
//		});
//
//		columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusConsulta"));

		tableAtendimento.setItems(arrayConsultas);

	}

	public static String getDataAtualFormatada() {
		LocalDate dataAtual = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return dataAtual.format(formatter);
	}
	///Para datePicker
	public String getDataSelecionadaFormatada() {
	    LocalDate dataSelecionada = txtDataAtend.getValue();

	    if (dataSelecionada == null) {
	        // Se nenhuma data foi selecionada no DatePicker, usa a data atual
	        return getDataAtualFormatada(); 
	    }

	    // Formata a data selecionada para o padrão do SQL (yyyy-MM-dd)
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return dataSelecionada.format(formatter);
	}

	

}

