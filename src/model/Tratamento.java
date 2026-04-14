package model;

public class Tratamento {
    private String idTratamento;
    private String nomeTratamento;
    private String descricao;
    private String precoTratamento;

    // Construtor
    public Tratamento(String idTratamento, String nomeTratamento, String descricao, String precoTratamento) {
        this.idTratamento = idTratamento;
        this.nomeTratamento = nomeTratamento;
        this.descricao = descricao;
        this.precoTratamento = precoTratamento;
    }
    
    public Tratamento() {
    	super();
    }


    // Getters e Setters
    public String getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(String idTratamento) {
        this.idTratamento = idTratamento;
    }

    public String getNomeTratamento() {
        return nomeTratamento;
    }

    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrecoTratamento() {
        return precoTratamento;
    }

    public void setPrecoTratamento(String precoTratamento) {
        this.precoTratamento = precoTratamento;
    }
}
