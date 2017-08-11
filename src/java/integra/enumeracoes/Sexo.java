package integra.enumeracoes;

public enum Sexo {
	FEMININO("F"),
	MASCULINO("M")
    ;

    private final String valor;
    private Sexo(final String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return ""+this.valor;
    }
}
