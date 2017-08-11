package integra.enumeracoes;

public enum GrauSatisfacao { 
	NAO_INFORMADO(9), 
	MUITO_INSATISFEITO(1),
	INSATISFEITO(2),
	INDIFERENTE(3),
	SATISFEITO(4),
	MUITO_SATISFEITO(5);
	
	private int valor; 
	
	GrauSatisfacao(int valor) { 
		this.valor = valor; 
	} 
    public String getValor() {
        return ""+this.valor;
    }
}
