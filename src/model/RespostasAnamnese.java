package model;

public class RespostasAnamnese {
    private String idRespostasAnamnese;
    private String idPaciente;
    private String idFuncionario;
    private String idPerguntasAnamnese;
    private String resposta;
    private String dataAnamnese;

    // Construtor
    public RespostasAnamnese(String idRespostasAnamnese, String idPaciente, String idFuncionario,
                             String idPerguntasAnamnese, String resposta, String dataAnamnese) {
        this.idRespostasAnamnese = idRespostasAnamnese;
        this.idPaciente = idPaciente;
        this.idFuncionario = idFuncionario;
        this.idPerguntasAnamnese = idPerguntasAnamnese;
        this.resposta = resposta;
        this.dataAnamnese = dataAnamnese;
    }
    
    public RespostasAnamnese() {
    	super();
    }

    // Getters e Setters
    public String getIdRespostasAnamnese() {
        return idRespostasAnamnese;
    }

    public void setIdRespostasAnamnese(String idRespostasAnamnese) {
        this.idRespostasAnamnese = idRespostasAnamnese;
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

    public String getIdPerguntasAnamnese() {
        return idPerguntasAnamnese;
    }

    public void setIdPerguntasAnamnese(String idPerguntasAnamnese) {
        this.idPerguntasAnamnese = idPerguntasAnamnese;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getDataAnamnese() {
        return dataAnamnese;
    }

    public void setDataAnamnese(String dataAnamnese) {
        this.dataAnamnese = dataAnamnese;
    }
}

