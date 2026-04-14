package model;

import java.time.LocalDateTime;

public class RespostasEspecialidades {



	public RespostasEspecialidades() {
		super();
	}

	public RespostasEspecialidades(int idResposta, int idPaciente, int idFuncionario, String especialidade,
			LocalDateTime dataRegistro, boolean bt01, boolean bt02, boolean bt03, boolean bt04, boolean bt05,
			boolean bt06, boolean bt07, boolean bt08, boolean bt09, boolean bt10) {
		super();
		this.idResposta = idResposta;
		this.idPaciente = idPaciente;
		this.idFuncionario = idFuncionario;
		this.especialidade = especialidade;
		this.dataRegistro = dataRegistro;
		this.bt01 = bt01;
		this.bt02 = bt02;
		this.bt03 = bt03;
		this.bt04 = bt04;
		this.bt05 = bt05;
		this.bt06 = bt06;
		this.bt07 = bt07;
		this.bt08 = bt08;
		this.bt09 = bt09;
		this.bt10 = bt10;
	}

	public int getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public boolean isBt01() {
		return bt01;
	}

	public void setBt01(boolean bt01) {
		this.bt01 = bt01;
	}

	public boolean isBt02() {
		return bt02;
	}

	public void setBt02(boolean bt02) {
		this.bt02 = bt02;
	}

	public boolean isBt03() {
		return bt03;
	}

	public void setBt03(boolean bt03) {
		this.bt03 = bt03;
	}

	public boolean isBt04() {
		return bt04;
	}

	public void setBt04(boolean bt04) {
		this.bt04 = bt04;
	}

	public boolean isBt05() {
		return bt05;
	}

	public void setBt05(boolean bt05) {
		this.bt05 = bt05;
	}

	public boolean isBt06() {
		return bt06;
	}

	public void setBt06(boolean bt06) {
		this.bt06 = bt06;
	}

	public boolean isBt07() {
		return bt07;
	}

	public void setBt07(boolean bt07) {
		this.bt07 = bt07;
	}

	public boolean isBt08() {
		return bt08;
	}

	public void setBt08(boolean bt08) {
		this.bt08 = bt08;
	}

	public boolean isBt09() {
		return bt09;
	}

	public void setBt09(boolean bt09) {
		this.bt09 = bt09;
	}

	public boolean isBt10() {
		return bt10;
	}

	public void setBt10(boolean bt10) {
		this.bt10 = bt10;
	}

	private int idResposta;
    private int idPaciente;
    private int idFuncionario;
    private String especialidade;
    private LocalDateTime dataRegistro;

    // 10 botões genéricos (você pode usar menos em cada tela)
    private boolean bt01, bt02, bt03, bt04, bt05, bt06, bt07, bt08, bt09, bt10;

}