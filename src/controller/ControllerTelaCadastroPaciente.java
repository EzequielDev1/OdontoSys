package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.PacienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Paciente;



public class ControllerTelaCadastroPaciente implements Initializable{
	
	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCancelar;
    
	@FXML
	private TextField txtCPF;

	@FXML
	private DatePicker txtDataNasc;

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
	void actionCadastrar(ActionEvent event) {
		Paciente paciente = new Paciente();
		PacienteDAO pacienteDAO = new PacienteDAO();

		paciente.setNomePaciente(txtNome.getText());
		paciente.setCpfPaciente(txtCPF.getText());
		paciente.setTelefone(txtTelefone.getText());
		paciente.setEmail(txtEmail.getText());
		paciente.setDataNasc(txtDataNasc.getValue().toString());
		paciente.setNacionalidade(txtNacionalidade.getText());
		paciente.setEndereco(txtEndereco.getText());
		paciente.setGenero(txtGenero.getValue());
		paciente.setEstadoCivil(txtEstadoCivil.getValue());
		paciente.setRg(txtRG.getText());
	
		if(txtNome.getText().isEmpty() || txtDataNasc.getValue() == null || txtEndereco.getText().isEmpty()) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro!");
			erro.setContentText("Erro ao agenda! Verifique se os campos foram Preenchidos corretamente e tente novamente. ");
			erro.show();
		}else if(!validarCPF(txtCPF.getText())) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro!");
			erro.setContentText("Erro ao agendar! Verifique se o CPF digitado está correto!");
			erro.show();
		}else if(ControllerAgendamentoPaciente.pacienteEditar == null){
				pacienteDAO.create(paciente);
				
				Alert mgs = new Alert(AlertType.INFORMATION);
				mgs.setTitle("Sucesso!!");
				mgs.setContentText("Paciente cadastrado com sucesso!!");
				mgs.show();
				Stage stage = (Stage) btCadastrar.getScene().getWindow();
				stage.close();
			
		}else {
			pacienteDAO.update(paciente);
    		Alert mgs = new Alert(AlertType.INFORMATION);
			mgs.setTitle("Sucesso!!");
			mgs.setContentText("Paciente atualizado com sucesso!!");
			mgs.show();
			ControllerAgendamentoPaciente.pacienteEditar = null;
			Stage stage = (Stage) btCadastrar.getScene().getWindow();
			stage.close();

		}

	}

	@FXML
	void actionCancelar(ActionEvent event) {
		ControllerAgendamentoPaciente.pacienteEditar = null;
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtGenero.getItems().add("F");
		txtGenero.getItems().add("M");

		txtEstadoCivil.getItems().add("Solteiro(a)");
		txtEstadoCivil.getItems().add("Casado(a)");
		txtEstadoCivil.getItems().add("Divorciado(a)");
		

		if(ControllerAgendamentoPaciente.pacienteEditar != null) {
			Paciente paciente = new Paciente();
			paciente = ControllerAgendamentoPaciente.pacienteEditar;
			txtNome.setText(paciente.getNomePaciente());
			txtCPF.setText(paciente.getCpfPaciente());
			txtTelefone.setText(paciente.getTelefone());
			txtEndereco.setText(paciente.getEndereco());
			txtEmail.setText(paciente.getEmail());
			txtGenero.setValue(paciente.getGenero());
			txtNacionalidade.setText(paciente.getNacionalidade());
			txtEstadoCivil.setValue(paciente.getEstadoCivil());
			txtRG.setText(paciente.getRg());
			String dataNasc = paciente.getDataNasc();
			dataNasc = dataNasc.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(dataNasc, format);
			txtDataNasc.setValue(localDate);
			txtCPF.setEditable(false);
			txtRG.setEditable(false);
			
			
		}
		
	}


	//Método para verificar cpf
	public static boolean validarCPF(String cpf) {
		// Verifica se o CPF tem 11 dígitos
		if (cpf == null || !cpf.matches("\\d{11}")) {
			return false;
		}

		// Verifica se todos os dígitos são iguais (caso inválido)
		if (cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		try {
			// Calcula o primeiro dígito verificador
			int soma = 0;
			for (int i = 0; i < 9; i++) {
				soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
			}
			int resto = 11 - (soma % 11);
			int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

			// Calcula o segundo dígito verificador
			soma = 0;
			for (int i = 0; i < 10; i++) {
				soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
			}
			resto = 11 - (soma % 11);
			int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

			// Compara com os dígitos informados
			return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
					digito2 == Character.getNumericValue(cpf.charAt(10));

		} catch (NumberFormatException e) {
			return false;
		}
	}

}
