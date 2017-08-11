package integra.enumeracoes;


public enum TipoTeleconsultoria {
	ASSINCRONA("A"),
	SINCRONA("S")
    ;

    private final String valor;
    private TipoTeleconsultoria(final String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return ""+this.valor;
    }
}