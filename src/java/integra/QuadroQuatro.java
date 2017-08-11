
package integra;

import java.util.HashMap;
import java.util.Map;

import javax.rmi.CORBA.Util;

/**
 * Classe com todas as funções necessárias para o recebimento do Quadro 4 (Indicadores mínimos de processo para monitoramento e avaliação de Tele-educação).
 * @author Jailton Carlos
 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
 */
@Deprecated
public class QuadroQuatro
{
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Inicializa uma nova instância da classe com os indicadores passados nos parâmetros.
	 * @param numObjetosDisponibilizadosARES	Número de objetos de aprendizagem disponibilizados no ARES por mês.
	 * @param numeroPontosAtivos				Número de pontos ativos (pelo menos uma utilização de serviços mensal) em tele-educação.
	 */
	public QuadroQuatro(int numObjetosDisponibilizadosARES, int numeroPontosAtivos)
	{
		dicionario.put("quantidade_disponibilizada_ares", numObjetosDisponibilizadosARES);
		dicionario.put("num_pontos_ativos", numeroPontosAtivos);
	}

	/**
	 * Adiciona o indicador "Número de atividades realizadas por estado (codigoUF)".
	 * @param codigoUF	Código IBGE do estado.
	 * @param numero	Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAtividadesRealizadasUF(String codigoUF, int numero)
	{
		Integra.addDictionary(true,dicionario, "atividades_realizadas_uf", "codigo_uf", codigoUF);
		Integra.addDictionary(dicionario, "atividades_realizadas_uf", "numero", numero);;
	}

	/**
	 * Adiciona o indicador "Número de atividades realizadas por município (codigoMunicipio)".
	 * @param codigoMunicipio	Código IBGE do município.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAtividadesRealizadasMunicipio(String codigoMunicipio, int numero)
	{
		Integra.addDictionary(true,dicionario, "atividades_realizadas_municipio", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "atividades_realizadas_municipio", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número de atividades realizadas por ponto de telessaúde (codigoPontoTelessaude)."
	 * @param codigoPonto	Código Cadastro Nacional de Estabelecimento de Saúde (CNES).
	 * @param numero		Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAtividadesRealizadasPontoTelessaude(String codigoPonto, int numero)
	{
		Integra.addDictionary(true,dicionario, "atividades_realizadas_ponto", "codigo_ponto", codigoPonto);
		Integra.addDictionary(dicionario, "atividades_realizadas_ponto", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número de atividades realizdas por equipe".
	 * Observação: Pelo menos um dos dois (codigoEquipe ou codigoEquipeIne) deve ser informado.
	 * @param codigo_equipe			Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria.
	 * @param codigo_equipe_ine		Código Identificador Nacional de Equipe (INE).
	 * @param numero				Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAtividadesRealizadasEquipe(String codigo_equipe, String codigo_equipe_ine, int numero)
	{
		Integra.addDictionary(true, dicionario, "atividades_realizadas_equipe", "numero", numero);
		if (!Utils.isBlankOrNull(codigo_equipe))
			Integra.addDictionary(dicionario, "atividades_realizadas_equipe", "codigo_equipe", codigo_equipe);
		if (!Utils.isBlankOrNull(codigo_equipe_ine))
			Integra.addDictionary(dicionario, "atividades_realizadas_equipe", "codigo_equipe_ine", codigo_equipe_ine);
		
	}

	/**
	 * Adiciona o indicador "Número de particpantes por categoriga profissional (codigoFamiliaCBO) por estado (codigoUF)".
	 * @param codigoUF			Código IBGE do estado.
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddParticipantesCatProfissionalUF(String codigoUF, String codigoFamiliaCBO, int numero)
	{
		Integra.addDictionary(true,dicionario, "participantes_cat_profissional_uf", "codigo_uf", codigoUF);
		Integra.addDictionary(dicionario, "participantes_cat_profissional_uf", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "participantes_cat_profissional_uf", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número de particpantes por categoriga profissional (codigoFamiliaCBO) por município (codigoMunicipio)".
	 * @param codigoMunicipio	Código IBGE do município.
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddParticipantesCatProfissionalMunicipio(String codigoMunicipio, String codigoFamiliaCBO, int numero)
	{
		Integra.addDictionary(true,dicionario, "participantes_cat_profissional_municipio", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "participantes_cat_profissional_municipio", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "participantes_cat_profissional_municipio", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número de particpantes por categoriga profissional (codigoFamiliaCBO) por equipe".
	 * Observação: Pelo menos um dos dois (codigoEquipe ou codigoEquipeIne) deve ser informado.
	 * @param codigo_equipe			Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria.
	 * @param codigo_equipe_ine		Código Identificador Nacional de Equipe (INE).
	 * @param codigoFamiliaCBO		Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @param numero				Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddParticipantesCatProfissionalEquipe(String codigo_equipe, String codigo_equipe_ine, String codigoFamiliaCBO, int numero)
	{
		Integra.addDictionary(true,dicionario, "participantes_cat_profissional_equipe", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "participantes_cat_profissional_equipe", "numero", numero);
		if(!Utils.isBlankOrNull(codigo_equipe))
			Integra.addDictionary(dicionario, "participantes_cat_profissional_equipe", "codigo_equipe", codigo_equipe);
		if(!Utils.isBlankOrNull(codigo_equipe_ine))
			Integra.addDictionary(dicionario, "participantes_cat_profissional_equipe", "codigo_equipe_ine", codigo_equipe_ine);
	}

	/**
	 * Adiciona o indicador "Número de particpantes por categoriga profissional (codigoFamiliaCBO) por ponto/mês (codigoPonto)".
	 * @param codigoPonto		Código Cadastro Nacional de Estabelecimento de Saúde (CNES)
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.	
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddParticipantesCatProfissionalPontoTelessaude(String codigoPonto, String codigoFamiliaCBO, int numero)
	{
		Integra.addDictionary(true,dicionario, "participantes_cat_profissional_ponto", "codigo_ponto", codigoPonto);
		Integra.addDictionary(dicionario, "participantes_cat_profissional_ponto", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "participantes_cat_profissional_ponto", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número global de acessos aos objetos de aprendizagem por estado, município, equipe e ponto/mês".
	 * Observação: Pelo menos um dos dois (codigoEquipe ou codigoEquipeIne) deve ser informado.
	 * @param codigoUF					Código IBGE do estado.
	 * @param codigoMunicipio			Código IBGE do município.
	 * @param codigo_equipe				Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria.
	 * @param codigo_equipe_ine			Código Identificador Nacional de Equipe (INE). 
	 * @param codigoPontoTelessaude		Código Cadastro Nacional de Estabelecimento de Saúde (CNES).
	 * @param numero					Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAcessosObjetosAprendizagem(String codigoUF, String codigoMunicipio, String codigo_equipe, String codigo_equipe_ine, String codigoPontoTelessaude, int numero)
	{
		Integra.addDictionary(true,dicionario, "acessos_objetos_aprendizagem", "codigo_uf", codigoUF);
		Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem", "codigo_ponto", codigoPontoTelessaude);
		Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem", "numero", numero);		
		if (!Utils.isBlankOrNull(codigo_equipe))
			Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem", "codigo_equipe", codigo_equipe);
		if (!Utils.isBlankOrNull(codigo_equipe_ine))
			Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem", "codigo_equipe_ine", codigo_equipe_ine);
	}

	/**
	 * Adiciona o indicador "Número global de acessos aos objetos de aprendizagem por município (codigoMunicipio)".
	 * @param codigoMunicipio	Código IBGE do município.
	 * @param numero			Valor do indicador
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAcessosObjetosAprendizagemMunicipio(String codigoMunicipio, int numero)
	{
		Integra.addDictionary(true,dicionario, "acessos_objetos_aprendizagem_municipio", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem_municipio", "numero", numero);
	}

	/**
	 * ona o indicador "Número global de acessos aos objetos de aprendizagem por ponto de telessaúde."
	 * @param codigoPonto	Código Cadastro Nacional de Estabelecimento de Saúde (CNES).
	 * @param numero		Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAcessosObjetosAprendizagemPontoTelessaude(String codigoPonto, int numero)
	{
		Integra.addDictionary(true,dicionario, "acessos_objetos_aprendizagem_ponto", "codigo_ponto", codigoPonto);
		Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem_ponto", "numero", numero);
	}
	
	/**
	 * Adiciona o indicador "Número global de acessos aos objetos de aprendizagem por equipe".
	 * Observação: Pelo menos um dos dois (codigoEquipe ou codigoEquipeIne) deve ser informado.
	 * @param codigo_equipe			Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria.
	 * @param codigo_equipe_ine		Código Identificador Nacional de Equipe (INE).
	 * @param numero				Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAcessosObjetosAprendizagemEquipe(String codigo_equipe, String codigo_equipe_ine, int numero)
	{
		Integra.addDictionary(true,dicionario, "acessos_objetos_aprendizagem_equipe", "numero", numero);
		if (!Utils.isBlankOrNull(codigo_equipe))
			Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem_equipe", "codigo_equipe", codigo_equipe);
		if (!Utils.isBlankOrNull(codigo_equipe_ine))
			Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem_equipe", "codigo_equipe_ine", codigo_equipe_ine);
		
	}

	/**
	 * Adiciona o indicador "Número global de acessos aos objetos de aprendizagem por categoria profissional (codigoFamiliaCBO)".
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddAcessosObjetosAprendizagemCatProfissional(String codigoFamiliaCBO, int numero)
	{
		Integra.addDictionary(true,dicionario, "acessos_objetos_aprendizagem_cat_profissional", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "acessos_objetos_aprendizagem_cat_profissional", "numero", numero);
	}

	Map<String, Object> ToDictionary()
	{
		return this.dicionario;
	}
}
