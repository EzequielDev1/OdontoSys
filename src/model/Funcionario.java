package model;

public class Funcionario {
    private String idFuncionario;
    private String nomeFuncionario;
    private String nivel;
    private String cpfFuncionario;
    private String rg;
    private String dataNasc;
    private String telefone;
    private String email;
    private String endereco;
    private String cargo;
    private String genero;
    private String senha;
 
    // Construtor
    public Funcionario(String idFuncionario, String nomeFuncionario, String nivel, String cpfFuncionario,
                       String rg, String dataNasc, String telefone, String email, String endereco,
                       String cargo, String genero, String senha) {
    	
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.nivel = nivel;
        this.cpfFuncionario = cpfFuncionario;
        this.rg = rg;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cargo = cargo;
        this.genero = genero;
        this.senha = senha;
    }
    
    public Funcionario() {
    	super();
    }

    // Getters e Setters
    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}

