package model;

public class Anamnese {
	
   private int idAnamnese;
   private int idPaciente;
   
   private boolean bt01SimNao;
   private boolean bt02SimNao;
   private boolean bt03SimNao;
   private boolean bt04SimNao;
   private boolean bt05SimNao;
   private boolean bt06SimNao;
   private boolean bt07SimNao;
   private boolean bt08SimNao;
   private boolean bt09SimNao;
   private boolean bt10SimNao;
   private boolean bt11Boca;
   private boolean bt12Boca;
   
   private Boolean  bt01Positivo;
   private Boolean  bt02Positivo;
   private Boolean  bt03Positivo;
   private Boolean  bt04Positivo;
   private Boolean  bt05Positivo;
   private Boolean  bt06Positivo;
   private Boolean  bt07Positivo;
   private Boolean  bt08Positivo;
   private Boolean  bt09Positivo;
   private Boolean  bt10Positivo;
   private Boolean  bt11Positivo;
   private Boolean  bt12Positivo;
   private Boolean  bt13Positivo;
   
   private String  txtBoca01;
   private String  txtBoca02;
   private String  txtBoca03;
   private String  txtBoca04;
   private String  txtBoca05;
   private String  txtBoca06;
   private String  txtBoca07;
   
public Anamnese(int idAnamnese, int idPaciente, boolean bt01SimNao, boolean bt02SimNao, boolean bt03SimNao,
		boolean bt04SimNao, boolean bt05SimNao, boolean bt06SimNao, boolean bt07SimNao, boolean bt08SimNao,
		boolean bt09SimNao, boolean bt10SimNao, boolean bt11Boca, boolean bt12Boca, Boolean bt01Positivo,
		Boolean bt02Positivo, Boolean bt03Positivo, Boolean bt04Positivo, Boolean bt05Positivo, Boolean bt06Positivo,
		Boolean bt07Positivo, Boolean bt08Positivo, Boolean bt09Positivo, Boolean bt10Positivo, Boolean bt11Positivo,
		Boolean bt12Positivo, Boolean bt13Positivo, String txtBoca01, String txtBoca02, String txtBoca03,
		String txtBoca04, String txtBoca05, String txtBoca06, String txtBoca07) {
	super();
	this.idAnamnese = idAnamnese;
	this.idPaciente = idPaciente;
	this.bt01SimNao = bt01SimNao;
	this.bt02SimNao = bt02SimNao;
	this.bt03SimNao = bt03SimNao;
	this.bt04SimNao = bt04SimNao;
	this.bt05SimNao = bt05SimNao;
	this.bt06SimNao = bt06SimNao;
	this.bt07SimNao = bt07SimNao;
	this.bt08SimNao = bt08SimNao;
	this.bt09SimNao = bt09SimNao;
	this.bt10SimNao = bt10SimNao;
	this.bt11Boca = bt11Boca;
	this.bt12Boca = bt12Boca;
	this.bt01Positivo = bt01Positivo;
	this.bt02Positivo = bt02Positivo;
	this.bt03Positivo = bt03Positivo;
	this.bt04Positivo = bt04Positivo;
	this.bt05Positivo = bt05Positivo;
	this.bt06Positivo = bt06Positivo;
	this.bt07Positivo = bt07Positivo;
	this.bt08Positivo = bt08Positivo;
	this.bt09Positivo = bt09Positivo;
	this.bt10Positivo = bt10Positivo;
	this.bt11Positivo = bt11Positivo;
	this.bt12Positivo = bt12Positivo;
	this.bt13Positivo = bt13Positivo;
	this.txtBoca01 = txtBoca01;
	this.txtBoca02 = txtBoca02;
	this.txtBoca03 = txtBoca03;
	this.txtBoca04 = txtBoca04;
	this.txtBoca05 = txtBoca05;
	this.txtBoca06 = txtBoca06;
	this.txtBoca07 = txtBoca07;
	
}

public Anamnese() {
	super();
}

public int getIdAnamnese() {
	return idAnamnese;
}

public void setIdAnamnese(int idAnamnese) {
	this.idAnamnese = idAnamnese;
}

public int getIdPaciente() {
	return idPaciente;
}

public void setIdPaciente(int idPaciente) {
	this.idPaciente = idPaciente;
}

public boolean isBt01SimNao() {
	return bt01SimNao;
}

public void setBt01SimNao(boolean bt01SimNao) {
	this.bt01SimNao = bt01SimNao;
}

public boolean isBt02SimNao() {
	return bt02SimNao;
}

public void setBt02SimNao(boolean bt02SimNao) {
	this.bt02SimNao = bt02SimNao;
}

public boolean isBt03SimNao() {
	return bt03SimNao;
}

public void setBt03SimNao(boolean bt03SimNao) {
	this.bt03SimNao = bt03SimNao;
}

public boolean isBt04SimNao() {
	return bt04SimNao;
}

public void setBt04SimNao(boolean bt04SimNao) {
	this.bt04SimNao = bt04SimNao;
}

public boolean isBt05SimNao() {
	return bt05SimNao;
}

public void setBt05SimNao(boolean bt05SimNao) {
	this.bt05SimNao = bt05SimNao;
}

public boolean isBt06SimNao() {
	return bt06SimNao;
}

public void setBt06SimNao(boolean bt06SimNao) {
	this.bt06SimNao = bt06SimNao;
}

public boolean isBt07SimNao() {
	return bt07SimNao;
}

public void setBt07SimNao(boolean bt07SimNao) {
	this.bt07SimNao = bt07SimNao;
}

public boolean isBt08SimNao() {
	return bt08SimNao;
}

public void setBt08SimNao(boolean bt08SimNao) {
	this.bt08SimNao = bt08SimNao;
}

public boolean isBt09SimNao() {
	return bt09SimNao;
}

public void setBt09SimNao(boolean bt09SimNao) {
	this.bt09SimNao = bt09SimNao;
}

public boolean isBt10SimNao() {
	return bt10SimNao;
}

public void setBt10SimNao(boolean bt10SimNao) {
	this.bt10SimNao = bt10SimNao;
}

public boolean isBt11Boca() {
	return bt11Boca;
}

public void setBt11Boca(boolean bt11Boca) {
	this.bt11Boca = bt11Boca;
}

public boolean isBt12Boca() {
	return bt12Boca;
}

public void setBt12Boca(boolean bt12Boca) {
	this.bt12Boca = bt12Boca;
}

public Boolean getBt01Positivo() {
	return bt01Positivo;
}

public void setBt01Positivo(Boolean bt01Positivo) {
	this.bt01Positivo = bt01Positivo;
}

public Boolean getBt02Positivo() {
	return bt02Positivo;
}

public void setBt02Positivo(Boolean bt02Positivo) {
	this.bt02Positivo = bt02Positivo;
}

public Boolean getBt03Positivo() {
	return bt03Positivo;
}

public void setBt03Positivo(Boolean bt03Positivo) {
	this.bt03Positivo = bt03Positivo;
}

public Boolean getBt04Positivo() {
	return bt04Positivo;
}

public void setBt04Positivo(Boolean bt04Positivo) {
	this.bt04Positivo = bt04Positivo;
}

public Boolean getBt05Positivo() {
	return bt05Positivo;
}

public void setBt05Positivo(Boolean bt05Positivo) {
	this.bt05Positivo = bt05Positivo;
}

public Boolean getBt06Positivo() {
	return bt06Positivo;
}

public void setBt06Positivo(Boolean bt06Positivo) {
	this.bt06Positivo = bt06Positivo;
}

public Boolean getBt07Positivo() {
	return bt07Positivo;
}

public void setBt07Positivo(Boolean bt07Positivo) {
	this.bt07Positivo = bt07Positivo;
}

public Boolean getBt08Positivo() {
	return bt08Positivo;
}

public void setBt08Positivo(Boolean bt08Positivo) {
	this.bt08Positivo = bt08Positivo;
}

public Boolean getBt09Positivo() {
	return bt09Positivo;
}

public void setBt09Positivo(Boolean bt09Positivo) {
	this.bt09Positivo = bt09Positivo;
}

public Boolean getBt10Positivo() {
	return bt10Positivo;
}

public void setBt10Positivo(Boolean bt10Positivo) {
	this.bt10Positivo = bt10Positivo;
}

public Boolean getBt11Positivo() {
	return bt11Positivo;
}

public void setBt11Positivo(Boolean bt11Positivo) {
	this.bt11Positivo = bt11Positivo;
}

public Boolean getBt12Positivo() {
	return bt12Positivo;
}

public void setBt12Positivo(Boolean bt12Positivo) {
	this.bt12Positivo = bt12Positivo;
}

public Boolean getBt13Positivo() {
	return bt13Positivo;
}

public void setBt13Positivo(Boolean bt13Positivo) {
	this.bt13Positivo = bt13Positivo;
}

public String getTxtBoca01() {
	return txtBoca01;
}

public void setTxtBoca01(String txtBoca01) {
	this.txtBoca01 = txtBoca01;
}

public String getTxtBoca02() {
	return txtBoca02;
}

public void setTxtBoca02(String txtBoca02) {
	this.txtBoca02 = txtBoca02;
}

public String getTxtBoca03() {
	return txtBoca03;
}

public void setTxtBoca03(String txtBoca03) {
	this.txtBoca03 = txtBoca03;
}

public String getTxtBoca04() {
	return txtBoca04;
}

public void setTxtBoca04(String txtBoca04) {
	this.txtBoca04 = txtBoca04;
}

public String getTxtBoca05() {
	return txtBoca05;
}

public void setTxtBoca05(String txtBoca05) {
	this.txtBoca05 = txtBoca05;
}

public String getTxtBoca06() {
	return txtBoca06;
}

public void setTxtBoca06(String txtBoca06) {
	this.txtBoca06 = txtBoca06;
}

public String getTxtBoca07() {
	return txtBoca07;
}

public void setTxtBoca07(String txtBoca07) {
	this.txtBoca07 = txtBoca07;
}



}
   

