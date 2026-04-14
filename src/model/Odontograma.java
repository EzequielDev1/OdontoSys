package model;

public class Odontograma {
    private String idOdontograma;
    private String idPaciente;
    private String idFuncionario;
    private String numeroDente;
    private String faceDente;
    private String condicaoDente;
    private String observacao;
    private String dataRegistro;
    private String idTratamento;
    private String nomeTratamento;

    public String getNomeTratamento() {
        return nomeTratamento;
    }

    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }

    // Construtor
    public Odontograma(String idOdontograma, String idPaciente, String idFuncionario,
                       String numeroDente, String faceDente, String condicaoDente,
                       String observacao, String dataRegistro, String idTratamento) {
        this.idOdontograma = idOdontograma;
        this.idPaciente = idPaciente;
        this.idFuncionario = idFuncionario;
        this.numeroDente = numeroDente;
        this.faceDente = faceDente;
        this.condicaoDente = condicaoDente;
        this.observacao = observacao;
        this.dataRegistro = dataRegistro;
        this.idTratamento = idTratamento;
    }
    
    public Odontograma() {
    	super();
    }


    // Getters e Setters
    public String getIdOdontograma() {
        return idOdontograma;
    }

    public void setIdOdontograma(String idOdontograma) {
        this.idOdontograma = idOdontograma;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNumeroDente() {
        return numeroDente;
    }

    public void setNumeroDente(String numeroDente) {
        this.numeroDente = numeroDente;
    }

    public String getFaceDente() {
        return faceDente;
    }

    public void setFaceDente(String faceDente) {
        this.faceDente = faceDente;
    }

    public String getCondicaoDente() {
        return condicaoDente;
    }

    public void setCondicaoDente(String condicaoDente) {
        this.condicaoDente = condicaoDente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

	public String getIdTratamento() {
		return idTratamento;
	}

	public void setIdTratamento(String idTratamento) {
		this.idTratamento = idTratamento;
	}
    
    
}

