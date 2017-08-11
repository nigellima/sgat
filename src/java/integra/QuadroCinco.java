package integra;

import java.util.HashMap;
import java.util.Map;

enum TipoDeResolucaoDuvida
{
	SIM,
	PARCIALMENTE,
	NAO,
	NAOSEI 
}
/**
 * Classe com todas as funções necessárias para o recebimento do Quadro 5 (Indicadores mínimos de resultados e avaliação para monitoramento de Teleconsultoria).
 * @author Jailton Carlos
 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
 */
@Deprecated
public class QuadroCinco
{
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Inicializa uma nova instância da classe com os indicadores passados nos parâmetros.
	 * @param NumSOFAprovadaBireme							Nº de SOF produzidas, enviadas e aprovadas pela BIREME.
	 * @param tempoMedioSincronas							Tempo médio de respostas de solicitações síncronas.
	 * @param tempoMedioAssincronas							Tempo médio de respostas de solicitações assíncronas.
	 * @param percentualSolicitacoesAssincronasAntesPrazo	% de solictações assíncronas respondidas em menos de 72h.
	 */
	public QuadroCinco(int NumSOFAprovadaBireme, int tempoMedioSincronas, int tempoMedioAssincronas, double percentualSolicitacoesAssincronasAntesPrazo)
	{
		dicionario.put("num_sof_enviada_bireme", NumSOFAprovadaBireme);
		dicionario.put("tempo_medio_sincronas", tempoMedioSincronas);
		dicionario.put("tempo_medio_assincronas", tempoMedioAssincronas);
		dicionario.put("percentual_assinc_resp_emmenos72", percentualSolicitacoesAssincronasAntesPrazo);
	}
	
	/**
	 * Adiciona o indicador "Lista dos 10 temas mais frequentes das solicitações de teleconsultorias respondidas".
	 * <p/>
	 * Observações: ao menos codigo_cid ou codigo_ciap deve ser informado.
	 * 
	 * @param codigoCID		Código CID (Classificação Internacional de Doenças).
	 * @param codigoCIAP	Código CIAP (Classificação Internacional de Assistência Primária).
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddTemasFrequentes(String codigoCID, String codigoCIAP)
	{
		Integra.addDictionary(true,dicionario, "temas_frequentes", "codigo_cid", codigoCID);
		Integra.addDictionary(dicionario, "temas_frequentes", "codigo_ciap", codigoCIAP);
	}

	/**
	 * Adiciona o indicador " Especialidades dos teleconsultores mais frequentes entre as solicitações de telconsultorias respondidas".
	 * @param codigoCBO	Código de 6 digítos referente à ocupação na CBO2002.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddEspecialidadesFrequentes(String codigoCBO)
	{
		Integra.addDictionary(true,dicionario, "especialidades_frequentes", "codigo_cbo", codigoCBO);
	}
	
	/**
	 * Adiciona o indicador " Categoria profissional dos teleconsultores mais frequentes entre as solicitações de telconsultorias respondidas".
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddCatProfissionaisFrequentes(String codigoFamiliaCBO)
	{
		Integra.addDictionary(true,dicionario, "cat_profissionais_frequentes", "codigo_familia_cbo", codigoFamiliaCBO);;
	}
	
	/**
	 * Adiciona o indicador "Percentual de teleconsultorias respondidas em que houve satisfação do solicitante".
	 * @param codigoEscalaLikert	Código na Escala Likert.Consulta código no sistema.
	 * @param percentual			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSatisfacaoSolicitante(String codigoEscalaLikert, double percentual)
	{
		Integra.addDictionary(true,dicionario, "satisfacao_solicitante", "codigo_escala_likert", codigoEscalaLikert);
		Integra.addDictionary(dicionario, "satisfacao_solicitante", "percentual", percentual);
	}

	/**
	 * Adiciona o indicador "Percentual de teleconsultorias respondidas em que houve resolução da dúvida (sim, parcialmente, não, não sei)".
	 * @param percentual_sim		Valor do percentual para "Sim".
	 * @param percentual_parcial	Valor do percentual para "Parcialmente".
	 * @param percentual_nao		Valor do percentual para "Não".
	 * @param percentual_nao_sei	Valor do percentual para "Não Sei/Não avaliado".
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddResolucaoDuvida(double percentual_sim, double percentual_parcial, double percentual_nao, double percentual_nao_sei)
	{
		Integra.addDictionary(dicionario, "resolucao_duvida", "percentual_sim", percentual_sim);
		Integra.addDictionary(dicionario, "resolucao_duvida", "percentual_parcial", percentual_parcial);
		Integra.addDictionary(dicionario, "resolucao_duvida", "percentual_nao", percentual_nao);
		Integra.addDictionary(dicionario, "resolucao_duvida", "percentual_nao_sei", percentual_nao_sei);

	}
	
	/**
	 * % teleconsultorias respondidas que havia intenção de encaminhar paciente em que houve evitação de encaminhamentos".
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @param percentual_sim		Valor do percentual para "Sim".
	 * @param percentual_nao		Valor do percentual para "Não".
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddEvitacaoEncaminhamentoCatProfissional(String codigoFamiliaCBO, double percentual_sim, double percentual_nao)
	{
		Integra.addDictionary(true,dicionario, "evitacao_encaminhamentos", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "evitacao_encaminhamentos", "percentual_sim", percentual_sim);
		Integra.addDictionary(dicionario, "evitacao_encaminhamentos", "percentual_nao", percentual_nao);
	}

	Map<String, Object> ToDictionary()
	{
		return this.dicionario;
	}
}
