package integra.enumeracoes;

public enum ResolucaoDuvidaTeleconsultoria { 
	NAO_INFORMADO(9), 
	ATENDEU_TOTALMENTE(1),
	ATENDEU_PARCIALMENTE(2),
	NAO_ATENDEU(3);
	
	private int valor; 
	
	ResolucaoDuvidaTeleconsultoria(int valor) { 
		this.valor = valor; 
	} 
    public String getValor() {
        return ""+this.valor;
    }
}
