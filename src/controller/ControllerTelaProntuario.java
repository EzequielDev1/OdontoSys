package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Funcionario;
import model.Paciente;

public class ControllerTelaProntuario implements Initializable{
	
	public static Paciente pacienteProntuario;
	
	
	public void setPaciente(Paciente pacienteProntuario) {
		this.pacienteProntuario = pacienteProntuario;
	}

	@FXML
	private Button btAba1;
	@FXML
	private Button btAba2;
	@FXML
	private Button btEspecialidades;
	@FXML
	private Button btVoltar;

	@FXML
	private GridPane gridAba1;
	@FXML
	private GridPane gridAba2;
	@FXML
	private GridPane gridEspecialidades;
	@FXML
	private GridPane gridGeral;

	@FXML
	private RowConstraints gridLine0;
	@FXML
	private RowConstraints gridLine1;
	@FXML
	private RowConstraints gridLineEspecialidades;

	@FXML
	private ScrollPane scrollAba1;
	@FXML
	private ScrollPane scrollAba2;
	@FXML
	private ScrollPane scrollEspecialidades;
	@FXML
	private ScrollPane scrollGeral;

	@FXML
	private TextField txtCPF;
	@FXML
	private DatePicker txtDataNascimento;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtEndereco;
	@FXML
	private ChoiceBox<String> txtEstadoCivil;
	@FXML
	private ChoiceBox<String> txtGenero;
	@FXML
	private TextField txtNacionalidade;
	@FXML
	private TextField txtNaturalidade;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtRG;
	@FXML
	private TextField txtTelefone;

	@FXML
	private VBox vbAba1;
	@FXML
	private VBox vbAba2;
	@FXML
	private VBox vbEspecialidades;
	@FXML
	private VBox vbGeral;
	
	private final double ALTURA_EXPANSAO = 1000.0;
	private final double ALTURA_FIXA_SUPERIOR = 450.0;
	private final double ALTURA_COLAPSO_ROW = 87.0;
	private final double ALTURA_PADRAO_VBOX = 100.0;

	private boolean especialidadesCarregadas = false;
	private Paciente paciente;
	private Funcionario funcionario;



	private void carregarEspecialidadesInternamente() {
	    if (pacienteProntuario == null || funcionario == null) {
	        new Alert(AlertType.ERROR, "Erro: Paciente ou funcionário não definidos.").showAndWait();
	        return;
	    }

	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaEspecialidades.fxml"));
	        AnchorPane pane = loader.load();

	        // Pega o controller da tela de Especialidades
	        ControllerTelaEspecialidades controllerEsp = loader.getController();

	        // 🔹 Passa os dados corretamente
	        controllerEsp.setPaciente(pacienteProntuario);
	        controllerEsp.setFuncionario(funcionario);

	        // 🔹 Adiciona o conteúdo ao grid
	        gridEspecialidades.getChildren().clear();
	        gridEspecialidades.add(pane, 0, 0);
	        GridPane.setMargin(pane, new Insets(0));

	        especialidadesCarregadas = true;

	        System.out.println("Especialidades carregadas para paciente ID: " + pacienteProntuario.getIdPaciente());

	    } catch (IOException e) {
	        e.printStackTrace();
	        new Alert(AlertType.ERROR, "Erro ao carregar a tela de Especialidades: " + e.getMessage()).showAndWait();
	    }
	}


	public void Iniciar() throws IOException {
		int column = 0;
		int row = 0;
		try {
			FXMLLoader fxmlTelaScroll = new FXMLLoader();
			fxmlTelaScroll.setLocation(getClass().getResource("/view/TelaScroll.fxml"));
			AnchorPane pane = fxmlTelaScroll.load();
			ControllerTelaScroll controllerAnamnese = fxmlTelaScroll.getController();
			controllerAnamnese.setPacienteId(paciente.getIdPaciente());
			gridAba1.add(pane, column, row++);
			GridPane.setMargin(pane, new Insets(10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Iniciar2(Paciente paciente) throws IOException {
	    int column = 0;
	    int row = 0;
	    try {
	        FXMLLoader fxmlTelaOdontograma = new FXMLLoader();
	        fxmlTelaOdontograma.setLocation(getClass().getResource("/view/TelaOdontograma.fxml"));
	        AnchorPane pane = fxmlTelaOdontograma.load();

	        // Pega o controller da tela de odontograma
	        ControllerTelaOdontograma controller = fxmlTelaOdontograma.getController();

	        // Passa o paciente para o controller (usa o método certo!)
	        controller.setPaciente(paciente);

	        // Mostra o ID do paciente no console (opcional, só pra confirmar)
	        System.out.println("Paciente enviado para odontograma: " + paciente.getIdPaciente());

	        // Adiciona o odontograma no GridPane
	        gridAba2.add(pane, column, row++);
	        GridPane.setMargin(pane, new Insets(10));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	private void colapsarTodas() {
		scrollAba1.setPrefHeight(0);
		scrollAba1.setVisible(false);
		gridLine0.setPrefHeight(ALTURA_COLAPSO_ROW);
		vbAba1.setPrefHeight(ALTURA_PADRAO_VBOX);

		scrollAba2.setPrefHeight(0);
		scrollAba2.setVisible(false);
		gridLine1.setPrefHeight(ALTURA_COLAPSO_ROW);
		vbAba2.setPrefHeight(ALTURA_PADRAO_VBOX);

		scrollEspecialidades.setPrefHeight(0);
		scrollEspecialidades.setVisible(false);
		gridLineEspecialidades.setPrefHeight(ALTURA_COLAPSO_ROW);
		vbEspecialidades.setPrefHeight(ALTURA_PADRAO_VBOX);

		recalcularAlturaGeral();
	}

	private void recalcularAlturaGeral() {
		double alturaTotal = ALTURA_FIXA_SUPERIOR;
		alturaTotal += vbAba1.getPrefHeight();
		alturaTotal += vbAba2.getPrefHeight();
		alturaTotal += vbEspecialidades.getPrefHeight();
		gridGeral.setPrefHeight(alturaTotal);
	}

	@FXML
	void actionAba1(ActionEvent event) throws IOException {
		if(vbAba1.getPrefHeight() == ALTURA_EXPANSAO) {
			colapsarTodas();
		} else {
			colapsarTodas();

			vbAba1.setPrefHeight(ALTURA_EXPANSAO);
			scrollAba1.setPrefHeight(ALTURA_EXPANSAO);
			scrollAba1.setVisible(true);
			gridLine0.setPrefHeight(ALTURA_EXPANSAO);

			recalcularAlturaGeral();
			Iniciar();
		}
	}

	@FXML
	void actionAba2(ActionEvent event) throws IOException {
		if(vbAba2.getPrefHeight() == ALTURA_EXPANSAO) {
			colapsarTodas();
		} else {
			colapsarTodas();

			vbAba2.setPrefHeight(ALTURA_EXPANSAO);
			scrollAba2.setPrefHeight(ALTURA_EXPANSAO);
			scrollAba2.setVisible(true);
			gridLine1.setPrefHeight(ALTURA_EXPANSAO);

			recalcularAlturaGeral();
			Iniciar2(pacienteProntuario);
		}
	}


	@FXML
	void actionEspecialidades(ActionEvent event) throws IOException {
		if(vbEspecialidades.getPrefHeight() == ALTURA_EXPANSAO) {
			colapsarTodas();
		} else {
			colapsarTodas();

			vbEspecialidades.setPrefHeight(ALTURA_EXPANSAO);
			scrollEspecialidades.setPrefHeight(ALTURA_EXPANSAO);
			scrollEspecialidades.setVisible(true);
			gridLineEspecialidades.setPrefHeight(ALTURA_EXPANSAO);

			recalcularAlturaGeral();

			if (!especialidadesCarregadas) {
				carregarEspecialidadesInternamente();
			}
		}
	}


	@FXML
	void actionVoltar(ActionEvent event) throws IOException {
		Alert msg = new Alert(AlertType.CONFIRMATION);
		msg.setHeaderText("Voltar a Tela Anterior");
		msg.setContentText("Deseja realmente voltar a tela anterior?");
		msg.setTitle("Deseja Voltar a Tela de Atendimento?");

		Optional<ButtonType> sair = msg.showAndWait();

		if(sair.isPresent() && sair.get() == ButtonType.OK) {
			pacienteProntuario = null;
			Main.TelaAtendimento();
		}
	}


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colapsarTodas();
		scrollAba1.setFitToWidth(true);
		scrollAba1.setFitToHeight(true);
		scrollAba2.setFitToWidth(true);
		scrollAba2.setFitToHeight(true);
		scrollEspecialidades.setFitToWidth(true);
		scrollEspecialidades.setFitToHeight(true);

		// Permite que o grid se expanda totalmente conforme o conteúdo
		gridAba1.setPrefHeight(Region.USE_COMPUTED_SIZE);
		gridAba2.setPrefHeight(Region.USE_COMPUTED_SIZE);
		gridEspecialidades.setPrefHeight(Region.USE_COMPUTED_SIZE);
		
        this.paciente = ControllerTelaAtendimento.pacienteAtendimento;

        this.funcionario = ControllerTelaLogin.funcionario;
		carregarDadosProntuario();
		
		
	}


	public void carregarDadosProntuario() {
		if(ControllerTelaAtendimento.pacienteAtendimento != null) {
			pacienteProntuario = ControllerTelaAtendimento.pacienteAtendimento;
			txtNome.setText(pacienteProntuario.getNomePaciente());
			txtCPF.setText(pacienteProntuario.getCpfPaciente());
			txtEndereco.setText(pacienteProntuario.getEndereco());
			txtEstadoCivil.setValue(pacienteProntuario.getEstadoCivil());
			txtEmail.setText(pacienteProntuario.getEmail());
			txtGenero.setValue(pacienteProntuario.getGenero());
			txtNacionalidade.setText(pacienteProntuario.getNacionalidade());
			txtRG.setText(pacienteProntuario.getRg());
			txtTelefone.setText(pacienteProntuario.getTelefone());

			String datanasc = pacienteProntuario.getDataNasc();
			datanasc = datanasc.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(datanasc, format);
			txtDataNascimento.setValue(localDate);
		}
	}


}