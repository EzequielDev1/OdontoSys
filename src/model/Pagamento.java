package model;

public class Pagamento {
private String nomePaciente;
private String nomeTratamento;
private String precoTratamento;
private String dataConsulta;

public Pagamento(String nomePaciente, String nomeTratamento, String precoTratamento, String dataConsulta) {
	super();
	this.nomePaciente = nomePaciente;
	this.nomeTratamento = nomeTratamento;
	this.precoTratamento = precoTratamento;
	this.dataConsulta = dataConsulta;
}
public Pagamento() {
	super();
}
public String getNomePaciente() {
	return nomePaciente;
}
public void setNomePaciente(String nomePaciente) {
	this.nomePaciente = nomePaciente;
}
public String getNomeTratamento() {
	return nomeTratamento;
}
public void setNomeTratamento(String nomeTratamento) {
	this.nomeTratamento = nomeTratamento;
}
public String getPrecoTratamento() {
	return precoTratamento;
}
public void setPrecoTratamento(String precoTratamento) {
	this.precoTratamento = precoTratamento;
}
public String getDataConsulta() {
	return dataConsulta;
}
public void setDataConsulta(String dataConsulta) {
	this.dataConsulta = dataConsulta;
}

}
