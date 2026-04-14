package model;

public class Consulta {
    private String idConsulta;
    private String dataConsulta;
    private String horario;
    private String statusConsulta;
    private String precoConsulta;
    private String idPaciente;
    private String idFuncionario;
    private String observacao;

    // Construtor
    public Consulta(String idConsulta, String dataConsulta, String horario, String statusConsulta,
                    String precoConsulta, String idPaciente, String idFuncionario, String observacao) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.horario = horario;
        this.statusConsulta = statusConsulta;
        this.precoConsulta = precoConsulta;
        this.idPaciente = idPaciente;
        this.idFuncionario = idFuncionario;
        this.observacao = observacao;
    }
    
    public Consulta() {
    	super();
    }

    // Getters e Setters
    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(String statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    public String getPrecoConsulta() {
        return precoConsulta;
    }

    public void setPrecoConsulta(String precoConsulta) {
        this.precoConsulta = precoConsulta;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}

