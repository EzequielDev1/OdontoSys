package application;
	
import java.io.IOException;

import controller.ControllerTelaEspecialidades;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Paciente;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private static Stage stage;
	private static Scene main;

	public void start(Stage primaryStage) {

		try {
			stage = primaryStage;
			Parent fxmlTelaPrincipal;
			fxmlTelaPrincipal = FXMLLoader.load(getClass().getResource("/view/TelaLogin.fxml"));
			//fxmlTelaPrincipal = FXMLLoader.load(getClass().getResource("/view/TelaAgendamentoConsulta.fxml"));
			main = new Scene(fxmlTelaPrincipal);
			stage.setTitle("Tela Principal");
			primaryStage.setScene(main);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void TelaLogin() throws IOException {
		FXMLLoader fxmlLogin = new FXMLLoader();
		fxmlLogin.setLocation(Main.class.getResource("/view/TelaLogin.fxml"));
		Parent TelaLogin = fxmlLogin.load();
		main = new Scene(TelaLogin);
		stage.setScene(main);
		stage.setTitle("Tela de Login");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public static void TelaFuncionario() throws IOException {
		FXMLLoader fxmlFuncionario = new FXMLLoader();
		fxmlFuncionario.setLocation(Main.class.getResource("/view/TelaFuncionarios.fxml"));
		Parent TelaFuncionario = fxmlFuncionario.load();
		main = new Scene(TelaFuncionario);
		stage.setScene(main);
		stage.setTitle("Tela De Funcionario");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void TelaCadastroFuncionario() throws IOException {
		FXMLLoader fxmlCaFuncionario = new FXMLLoader();
		fxmlCaFuncionario.setLocation(Main.class.getResource("/view/viewTelaCadastroFuncionario.fxml"));
		Parent TelaCaFuncionario = fxmlCaFuncionario.load();
		main = new Scene(TelaCaFuncionario);
		stage.setScene(main);
		stage.setTitle("Tela De Funcionario");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void TelaEditarFuncionario() throws IOException {
	    	FXMLLoader fxmlEdFuncionario = new FXMLLoader();
	    	fxmlEdFuncionario.setLocation(Main.class.getResource("/view/TelaEditarFuncionario.fxml"));
			Parent TelaEdFuncionario = fxmlEdFuncionario.load();
			main = new Scene(TelaEdFuncionario);
			stage.setScene(main);
			stage.setTitle("Tela De editar Funcionario");
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		}
	  
	    

	
	public static void TelaPagamento() throws IOException {
		FXMLLoader fxmlPagamento = new FXMLLoader();
		fxmlPagamento.setLocation(Main.class.getResource("/view/TelaPagamento.fxml"));
		Parent TelaPagamento = fxmlPagamento.load();
		main = new Scene(TelaPagamento);
		stage.setScene(main);
		stage.setTitle("Tela De Pagamento");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaProntuario() throws IOException {
		FXMLLoader fxmlProntuario = new FXMLLoader();
		fxmlProntuario.setLocation(Main.class.getResource("/view/TelaProntuario.fxml"));
		Parent TelaProntuario = fxmlProntuario.load();
		main = new Scene(TelaProntuario);
		stage.setScene(main);
		stage.setTitle("Tela De Prontuario");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaAtendimento() throws IOException {
		FXMLLoader fxmlAtendimento = new FXMLLoader();
		fxmlAtendimento.setLocation(Main.class.getResource("/view/telaAtendimento.fxml"));
		Parent TelaAtendimento = fxmlAtendimento.load();
		main = new Scene(TelaAtendimento);
		stage.setScene(main);
		stage.setTitle("Tela De Atendimento");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaAgendamento() throws IOException {
		FXMLLoader fxmlAgendamento = new FXMLLoader();
		fxmlAgendamento.setLocation(Main.class.getResource("/view/telaAgendamentoConsulta.fxml"));
		Parent TelaAgendamento = fxmlAgendamento.load();
		main = new Scene(TelaAgendamento);
		stage.setScene(main);
		stage.setTitle("Tela De Atendimento");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaPaciente() throws IOException {
		FXMLLoader fxmlPaciente = new FXMLLoader();
		fxmlPaciente.setLocation(Main.class.getResource("/view/telaAgendamentoPaciente.fxml"));
		Parent TelaPaciente = fxmlPaciente.load();
		main = new Scene(TelaPaciente);
		stage.setScene(main);
		stage.setTitle("Tela De Pacientes");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaCadastro1() throws IOException{
		FXMLLoader fxmlCadastro1 = new FXMLLoader();
		fxmlCadastro1.setLocation(Main.class.getResource("/view/TelaCadastro1.fxml"));
		Parent TelaCadastro1 = fxmlCadastro1.load();
		main = new Scene(TelaCadastro1);
		stage.setTitle("Cadastro de Funcionário - Parte 1");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void TelaCadastro2() throws IOException{
		FXMLLoader fxmlCadastro2 = new FXMLLoader();
		fxmlCadastro2.setLocation(Main.class.getResource("/view/TelaCadastro2.fxml"));
		Parent TelaCadastro2 = fxmlCadastro2.load();
		main = new Scene(TelaCadastro2);
		stage.setTitle("Cadastro de Funcionário - Parte 2");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	
	}

	private static Stage cadPaciente;
	public static void TelaCadastro() throws IOException{
		FXMLLoader PacienteCadastro = new FXMLLoader();
		PacienteCadastro .setLocation(Main.class.getResource("/view/telaCadastroPaciente.fxml"));
		Parent pacienteCadastro =PacienteCadastro .load();
		Scene sene2 = new Scene(pacienteCadastro );

		cadPaciente =  new Stage();
		cadPaciente.setTitle("Cadastro/Edição de Paciente");
		cadPaciente.initModality(Modality.WINDOW_MODAL);
		cadPaciente.setScene(sene2);
		cadPaciente.centerOnScreen();
		cadPaciente.showAndWait();
	}
	
	public static Stage cadConsulta;
	public static void TelaCadastroConsulta() throws IOException{
		FXMLLoader CadastrarConsulta = new FXMLLoader();
		CadastrarConsulta.setLocation(Main.class.getResource("/view/viewCadastroConsulta.fxml"));
		Parent consultaCadastro = CadastrarConsulta.load();
		Scene scene2 = new Scene(consultaCadastro);
		
		cadConsulta = new Stage();
		cadConsulta.setTitle(" Cadastro/Edição de Consulta");
		cadConsulta.initModality(Modality.WINDOW_MODAL);
		cadConsulta.setScene(scene2);
		cadConsulta.centerOnScreen();
		cadConsulta.showAndWait();		
	}
	

	public static Stage telaEspecialidades; 

	public static void TelaEspecialidades(Paciente paciente) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(Main.class.getResource("/view/TelaEspecialidades.fxml"));
	    Parent especialidadesView = loader.load();
	    Scene scene = new Scene(especialidadesView);
	    ControllerTelaEspecialidades controller = loader.getController();
	    if (controller != null) {
	        controller.setPaciente(paciente);
	    }
	    telaEspecialidades = new Stage();
	    telaEspecialidades.setTitle("Avaliação por Especialidades");
	    telaEspecialidades.initModality(Modality.APPLICATION_MODAL); 
	    telaEspecialidades.setScene(scene);
	    telaEspecialidades.centerOnScreen();
	    telaEspecialidades.showAndWait();
	}
	
	public static Stage telaProntuario; 

	public static void TelaProntuario(model.Paciente paciente) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(Main.class.getResource("/view/TelaProntuario.fxml"));
	    Parent prontuarioView = loader.load();
	    Scene scene = new Scene(prontuarioView);
	    telaProntuario = new Stage();
	    telaProntuario.setTitle("Prontuário do Paciente");
	    telaProntuario.setScene(scene);
	    telaProntuario.centerOnScreen();
	    telaProntuario.show(); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
