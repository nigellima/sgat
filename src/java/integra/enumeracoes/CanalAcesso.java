package integra.enumeracoes;

public enum CanalAcesso { 

	INTERNET(1),
	TELEFONE(2);
	
	private int valor; 
	
	CanalAcesso(int valor) { 
		this.valor = valor; 
	} 	
    public String getValor() {
        return ""+this.valor;
    }
	
}
