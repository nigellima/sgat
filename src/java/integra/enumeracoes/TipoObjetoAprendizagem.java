package integra.enumeracoes;

public enum TipoObjetoAprendizagem {
	TEXTO(1),
	MULTIMIDIA(2),
	IMAGENS(3),
	APLICATIVOS(4),
	JOGOS_EDUCACIONAIS(5),
	OUTROS(6);

	private int valor;

	TipoObjetoAprendizagem(int valor) {
		this.valor = valor;
	}
    public String getValor() {
        return ""+this.valor;
    }
}
