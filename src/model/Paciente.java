package model;

import java.util.HashMap;
import java.util.Map;

public class Paciente {
    private String idPaciente;
    private String nomePaciente;
    private String numeroProntuario;
    private String cpfPaciente;
    private String dataNasc;
    private String telefone;
    private String email;
    private String endereco;
    private String genero;
    private String estadoCivil;
    private String nacionalidade;
    private String naturalidade;
    private String rg;
    
    
    private Map<String, Map<String, String>> respostasEspecialidades = new HashMap<>();
    
    
    // Construtor
    public Paciente(String idPaciente, String nomePaciente, String numeroProntuario, String cpfPaciente,
                    String dataNasc, String telefone, String email, String endereco, String genero,
                    String estadoCivil, String nacionalidade, String rg, String naturalidade) {
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.numeroProntuario = numeroProntuario;
        this.cpfPaciente = cpfPaciente;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
        this.rg = rg;
    }
    
    public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public Paciente() {
    	super();
    }


    // Getters e Setters
    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNumeroProntuario() {
        return numeroProntuario;
    }

    public void setNumeroProntuario(String numeroProntuario) {
        this.numeroProntuario = numeroProntuario;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void salvarResposta(String especialidade, String pergunta, String resposta) {
        respostasEspecialidades
            .computeIfAbsent(especialidade, k -> new HashMap<>())
            .put(pergunta, resposta);
    }

    public Map<String, String> getRespostas(String especialidade) {
        return respostasEspecialidades.getOrDefault(especialidade, new HashMap<>());
    }

}
