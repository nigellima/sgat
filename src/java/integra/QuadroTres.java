package integra;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe com todas as funções necessárias para o recebimento do Quadro 3 (Indicadores mínimos de processo para monitoramento e avaliação de Telediagnóstico).
 * @author Jailton Carlos
 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
 */
@Deprecated
public class QuadroTres
{
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Inicializa uma nova instância da classe com os indicadores passados nos parâmetros.
	 * @param numeroPontosAtivos					Nº de pontos ativos (pelo menos uma utilização de serviço mensal) em telediagóstico.
	 * @param percentualSolicitacoesAprovadasCIB	% de solicitações de telediagnósticos baseados em protocolos de regulação aprovados na CIB.
	 */
	public QuadroTres(int numeroPontosAtivos, double percentualSolicitacoesAprovadasCIB)
	{
		dicionario.put("num_pontos_ativos", numeroPontosAtivos);
		dicionario.put("porcentual_aprovado_cib", percentualSolicitacoesAprovadasCIB);
	}

	/**
	 * Adiciona o indicador "Número de solicitações com exame realizado e laudo enviado ao solicitado por estado (codigoUF)".
	 * @param codigoUF	Código IBGE do estado.
	 * @param numero	valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesTelediagnosticoUF(String codigoUF, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_telediagnostico_uf", "codigo_uf", codigoUF);
		Integra.addDictionary(dicionario, "solicitacoes_telediagnostico_uf", "numero", numero);
		
	}

	/**
	 * Adiciona o indicador "Número de solicitações com exame realizado e laudo enviado ao solicitado por município (codigoMunicipio)".
	 * @param codigoMunicipio	Código IBGE do município.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesTelediagnosticoMunicipio(String codigoMunicipio, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_telediagnostico_municipio", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "solicitacoes_telediagnostico_municipio", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número de solicitações com exame realizado e laudo enviado ao solicitante por equipe".
	 * Observação: Pelo menos um dos dois (codigoEquipe ou codigoEquipeIne) deve ser informado.
	 * @param codigo_equipe			Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria.
	 * @param codigo_equipe_ine		Código Identificador Nacional de Equipe (INE).
	 * @param numero				Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesTelediagnosticoEquipe(String codigo_equipe, String codigo_equipe_ine, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_telediagnostico_equipe", "numero", numero);
		if (!Utils.isBlankOrNull(codigo_equipe))
			Integra.addDictionary(dicionario, "solicitacoes_telediagnostico_equipe", "codigo_equipe", codigo_equipe);
		if (!Utils.isBlankOrNull(codigo_equipe_ine))
			Integra.addDictionary(dicionario, "solicitacoes_telediagnostico_equipe", "codigo_equipe_ine", codigo_equipe_ine);
		
	}

	/**
	 * Adiciona o indicador "Número de solicitações com exame realizado e laudo enviado ao solicitante por ponto de telessaúde."
	 * @param codigoPonto	Código Cadastro Nacional de Estabelecimento de Saúde (CNES)
	 * @param numero		Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesTelediagnosticoPontoTelessaude(String codigoPonto, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_telediagnostico_ponto", "codigo_ponto", codigoPonto);
		Integra.addDictionary(dicionario, "solicitacoes_telediagnostico_ponto", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número de solicitações com exame realizado e laudo enviado ao solicitante por tipo (codigoSIA)."
	 * @param codigoSIA	Código SIA
	 * @param numero	Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesTelediagnosticoTipo(String codigoSIA, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_telediagnostico_tipo", "codigo_sia", codigoSIA);
		Integra.addDictionary(dicionario, "solicitacoes_telediagnostico_tipo", "numero", numero);
	}

	Map<String, Object> ToDictionary()
	{
		return this.dicionario;
	}
}
