package br.com.api.domain.enums;

public enum TipoConta {

	COMPRA_LIVRE(1,"Compra livre"),
	CONTA_COMANDA(2,"Conta comanda"),
	CONTA_MESA(3,"Conta mesa");
	
	private int cod;
	private String descricao;
	
	private TipoConta(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoConta toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoConta x : TipoConta.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Invalido:"+cod);
	}
}
