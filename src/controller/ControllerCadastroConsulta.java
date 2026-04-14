package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import application.Main;
import dao.ConsultaDAO;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Consulta;
import model.Paciente;
import javafx.scene.control.Alert.AlertType;

public class ControllerCadastroConsulta implements Initializable {

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField txtCpf;

    @FXML
    private DatePicker txtDataConsulta;
    @FXML
    private TextField txtHorario;

    @FXML
    private TextField txtNomPacient;

    @FXML
    private TextField txtObs;

    @FXML
    private TextField txtPreco;

    @FXML
    private ChoiceBox<String> txtStatus;
    

    @FXML
    void actionSalvar(ActionEvent event) {
//Botão para salvar o cadastro e edição das informações de consulta
  
    	//Botão para salvar o cadastro e edição das informações de consulta
    	  
    	Consulta consulta = new Consulta();
	    ConsultaDAO consultaDAO = new ConsultaDAO();
	    Paciente paciente = new Paciente();
	    PacienteDAO pacienteDAO = new PacienteDAO();
	    
    	
    	// Verificação básica de campos obrigatórios
    	    if (txtCpf.getText().equals("") || txtDataConsulta.getValue().equals("") || 
    	        txtHorario.getText().equals("") || txtStatus.getValue() == null || 
    	        txtPreco.getText().equals("")) {

    	        Alert erro = new Alert(AlertType.ERROR);
    	        erro.setTitle("Erro!");
    	        erro.setContentText("Erro ao cadastrar! Verifique se todos os campos foram preenchidos.");
    	        erro.show();
    	        
    	    }else {
    	    	
    	    	// Conversão para formato que banco de dados aceita
        	    String precoTextoComSimbolos = txtPreco.getText();
            	double precoNumerico;

            	try {
            		// 1. Tenta o parse usando o formato monetário brasileiro (R$ 1.500,00)
            		NumberFormat formatoBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            		Number parsedNumber = formatoBR.parse(precoTextoComSimbolos);
            		precoNumerico = parsedNumber.doubleValue();

            	} catch (java.text.ParseException e) {
            		try {
            			// Limpeza manual: remove R$, espaço, ponto de milhar e troca vírgula decimal por ponto
            			String precoLimpo = precoTextoComSimbolos
            								.replace("R$", "")
            								.replace(" ", "")
            								.replace(".", "") 
            								.replace(",", "."); 
            								
            			precoNumerico = Double.parseDouble(precoLimpo);

            		} catch (NumberFormatException e2) {
            			Alert erro = new Alert(Alert.AlertType.ERROR);
            			erro.setTitle("Erro de valor");
            			erro.setContentText("Valor informado no campo de preço é inválido! Por favor, use o formato '1.500,00'.");
            			erro.show();
            			return;
            		}
            	}
        	    //Esse passo abaixo tem que estar dentro do else para não tentar cadastrar mesmo estando sem informaçõe preenchidas
            	paciente.setCpfPaciente(txtCpf.getText());
        	    paciente = pacienteDAO.search(paciente.getCpfPaciente()).get(0);
        	    
    	    	    consulta.setIdFuncionario(ControllerTelaLogin.funcionario.getIdFuncionario()); 
    	    	    consulta.setIdPaciente(paciente.getIdPaciente()); // chave estrangeira
    	    	    consulta.setDataConsulta(txtDataConsulta.getValue().toString());
    	    	    consulta.setHorario(txtHorario.getText());
    	    	    consulta.setStatusConsulta(txtStatus.getValue());
    	    	    consulta.setObservacao(txtObs.getText());
    	    	    //consulta.setPrecoConsulta(txtPreco.getText());--Original
    	    	    consulta.setPrecoConsulta(String.format(Locale.US, "%.2f", precoNumerico));
    	    	    
    	    	 // Verificar se é cadastro novo ou edição
    	    	    if (ControllerAgendamentoConsulta.consultaEditar == null) {
    	    	        consultaDAO.create(consulta);

    	    	        Alert msg = new Alert(AlertType.INFORMATION);
    	    	        msg.setTitle("Sucesso!");
    	    	        msg.setContentText("Consulta cadastrada com sucesso!");
    	    	        msg.show();
    	    	        
    	    	        Stage stage = (Stage) btSalvar.getScene().getWindow();
    	        	    stage.close();

    	    	    } else {
    	    	        consulta.setIdConsulta(ControllerAgendamentoConsulta.consultaEditar.getIdConsulta());
    	    	        consultaDAO.update(consulta);

    	    	        Alert msg = new Alert(AlertType.INFORMATION);
    	    	        msg.setTitle("Sucesso!");
    	    	        msg.setContentText("Consulta atualizada com sucesso!");
    	    	        msg.show();
    	    	       ControllerAgendamentoConsulta.consultaEditar = null;
    	    	        
    	    	     // Fechar a tela após salvar
    	        	    Stage stage = (Stage) btSalvar.getScene().getWindow();
    	        	    stage.close();  
    	    	        
    	    	    } 
    	    }  

    }

    @FXML
    void actionCancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) btCancelar.getScene().getWindow();
    	stage.close();

    }
    
    @FXML
    void actionCPFClick(MouseEvent event) {
    	if(txtNomPacient.getText().length()>3) {
    		PacienteDAO pacienteDAO = new PacienteDAO();
    		Paciente paciente = new Paciente();
    		paciente.setNomePaciente(txtNomPacient.getText());
    		ArrayList<Paciente> pacientes = new ArrayList<>();
    		pacientes =pacienteDAO.search(paciente.getNomePaciente());
    		paciente = pacientes.get(0);
    		txtCpf.setText(paciente.getCpfPaciente());
    	}else {
    		txtCpf.setText("");
    	}
    }

    @FXML
    void actionCPFType(KeyEvent event) {
    	if(txtNomPacient.getText().length()>3) {
    		PacienteDAO pacienteDAO = new PacienteDAO();
    		Paciente paciente = new Paciente();
    		paciente.setNomePaciente(txtNomPacient.getText());
    		ArrayList<Paciente> pacientes = new ArrayList<>();
    		pacientes =pacienteDAO.search(paciente.getNomePaciente());
    		paciente = pacientes.get(0);
    		txtCpf.setText(paciente.getCpfPaciente());
    	}else {
    		txtCpf.setText("");
    	}
    }

    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtStatus.getItems().addAll("Agendada","Realizada","Cancelada", "Reagendada", "Concluída");
		
		txtCpf.setEditable(false);
		
		//System.out.println(ControllerAgendamentoConsulta.consultaEditar.getDataConsulta());
		if(ControllerAgendamentoConsulta.consultaEditar != null) {
			Consulta consulta = new Consulta();
			consulta = ControllerAgendamentoConsulta.consultaEditar;
			//String nome = consulta.getIdPaciente();			
			ConsultaDAO consultaDAO = new ConsultaDAO();
			Consulta consultaExtra = consultaDAO.searchInformaConsulta(consulta).get(0);//Esse chama as informações PrecoConsulta e Observações e data
//			consulta = consultaDAO.searchInformaConsulta(consulta).get(0);
//			consulta.setIdPaciente(nome);
			
			//Adicional 
			consulta.setPrecoConsulta(consultaExtra.getPrecoConsulta());
			consulta.setObservacao(consultaExtra.getObservacao());
			
			txtNomPacient.setText(consulta.getIdPaciente());
			txtHorario.setText(consulta.getHorario());
			txtStatus.setValue(consulta.getStatusConsulta());
			txtPreco.setText(consulta.getPrecoConsulta());
//			double preco= Double.parseDouble(consulta.getPrecoConsulta());
//			txtPreco.setText(String.format("%.2f", preco));
			txtObs.setText(consulta.getObservacao());
			
			//Para converter o formato monetário para real
			try {
	            double preco = Double.parseDouble(consulta.getPrecoConsulta());
	            NumberFormat formatoBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	            String precoFormatado = formatoBR.format(preco);
	            txtPreco.setText(precoFormatado); // Exibe como "R$ 1.000,00"
	        } catch (NumberFormatException e) {
	            txtPreco.setText("Valor inválido");
	        }
			//apresentar cpf na tela
			String cpf = consultaDAO.buscarCpfPorConsulta(consulta);
			if (cpf != null) {
			    txtCpf.setText(cpf);
			} else {
			    txtCpf.setText("Paciente não encontrado");
			}

			// Impedir edição visual e lógica no campo CPF
			txtCpf.setEditable(false);
			txtCpf.setStyle("-fx-opacity: 1;");
			//
			String dataConsulta = consulta.getDataConsulta();
			System.out.println("Verificar Data: "+dataConsulta);
			dataConsulta = dataConsulta.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(dataConsulta,format);
			txtDataConsulta.setValue(localDate);
			
		}
		
		PacienteDAO pacienteDAO = new PacienteDAO();
		String[] listaPacientes = new String[pacienteDAO.read().size()];
		ArrayList<Paciente> arrayPacientes = pacienteDAO.read();
		for(int i =0; i < pacienteDAO.read().size(); i++) {
			Paciente paciente = new Paciente();
			paciente = arrayPacientes.get(i);
			listaPacientes[i] = paciente.getNomePaciente();
		}
		TextFields.bindAutoCompletion(txtNomPacient,listaPacientes);
	}

}
