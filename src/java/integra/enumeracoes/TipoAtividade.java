package integra.enumeracoes;

public enum TipoAtividade { 
	CURSO(1),
	WEBAULAS_PALESTRAS(2),
	WEBSEMINARIOS(3),
	FORUM(4),
	REUNIAO(5);
	
	private int valor; 
	
	TipoAtividade(int valor) { 
		this.valor = valor; 
	} 
    public String getValor() {
        return ""+this.valor;
    }
}