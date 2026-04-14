package model;

public class ConsultaTratamento {
    private String idConsultaTratamento;
    private String idConsulta;
    private String idTratamento;
    private String precoTratamento;
    private String observacao;

    // Construtor
    public ConsultaTratamento(String idConsultaTratamento, String idConsulta, String idTratamento,
                              String precoTratamento, String observacao) {
        this.idConsultaTratamento = idConsultaTratamento;
        this.idConsulta = idConsulta;
        this.idTratamento = idTratamento;
        this.precoTratamento = precoTratamento;
        this.observacao = observacao;
    }
    
    public ConsultaTratamento() {
    	super();
    }

    // Getters e Setters
    public String getIdConsultaTratamento() {
        return idConsultaTratamento;
    }

    public void setIdConsultaTratamento(String idConsultaTratamento) {
        this.idConsultaTratamento = idConsultaTratamento;
    }

    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(String idTratamento) {
        this.idTratamento = idTratamento;
    }

    public String getPrecoTratamento() {
        return precoTratamento;
    }

    public void setPrecoTratamento(String precoTratamento) {
        this.precoTratamento = precoTratamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}

