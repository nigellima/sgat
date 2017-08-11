package integra;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe com todas as funções necessárias para o recebimento do Quadro 6 (Indicadores de resultados e avaliação para a Tele-educação).
 * @author Jailton Carlos
 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
 */
@Deprecated
public class QuadroSeis
{
	private enum TipoDeResolucaoDuvida
	{
		Sim,
		Parcialmente,
		Nao,
		NaoSei
	}

	private Map<String, Object> dicionario = new HashMap<String, Object>();

	public QuadroSeis()
	{
	}

	/**
	 * Adiciona o indicador "Até 5 temas com maior participação por mês".
	 * @param codigoDeCSBireme	Código do DeCS (Descritores em Ciências da Saúde) da BIREME.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddTemasFrequentesParticipacao(String codigoDeCSBireme)
	{
		Integra.addDictionary(true,dicionario, "temas_frequentes_participacao", "codigo_decs_bireme", codigoDeCSBireme);
	}

	/**
	 * Adiciona o indicador " Até 5 temas mais acessados, por objetos de aprendizagem".
	 * @param codigoDeCSBireme	Código do DeCS (Descritores em Ciências da Saúde) da BIREME.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddTemasFrequentesObjetoAprendizagem(String codigoDeCSBireme)
	{
		Integra.addDictionary(true,dicionario, "temas_frequntes_objeto_aprendizagem", "codigo_decs_bireme", codigoDeCSBireme);

	}

	/**
	 * Adiciona o indicador "Avaliação global da satisfação dos profissionais participantes do mês".
	 * @param codigoEscalaLikert	Código da Escala Likert. Consulta código no sistema.
	 * @param percentual			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAvaliacaoSatisfacaoParticipantes(String codigoEscalaLikert, double percentual)
	{
		Integra.addDictionary(true,dicionario, "avaliacao_satisfacao_participantes", "codigo_escala_likert", codigoEscalaLikert);
		Integra.addDictionary(dicionario, "avaliacao_satisfacao_participantes", "percentual", percentual);
	}

	/**
	 * Adiciona o indicador " Avaliação global da satisfação profissional com os objetos de aprendizagem por mês".
	 * @param codigoEscalaLikert	Código da Escala Likert. Consultar código no sistema.
	 * @param percentual			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAvaliacaoSatisfacaoObjetoAprendizagem(String codigoEscalaLikert, double percentual)
	{
		Integra.addDictionary(true,dicionario, "avaliacao_satisfacao_objeto_aprendizagem", "codigo_escala_likert", codigoEscalaLikert);
		Integra.addDictionary(dicionario, "avaliacao_satisfacao_objeto_aprendizagem", "percentual", percentual);
	}

	Map<String, Object> ToDictionary()
	{
		return this.dicionario;
	}
}
