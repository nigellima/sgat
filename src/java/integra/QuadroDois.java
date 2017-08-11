package integra;


import java.util.HashMap;
import java.util.Map;

/**
 * Classe com todas as funções necessárias para o recebimento do Quadro 2 (Indicadores mínimos de processo
 * para monitoramento e avaliação de Teleconsultoria).
 * @author Jailton Carlos
 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
 */
@Deprecated
public class QuadroDois
{
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Inicializa uma nova instância da classe com os indicadores passados nos parâmetros.
	 * @param numeroSolicitacoesSincronas			Número de solicitações de teleconsultorias síncronas respondidas.
	 * @param numeroSolicitacoesAssincronas			Número de solicitações de teleconsultorias assíncronas respondidas.
	 * @param numeroPontosAtivos					Número de pontos ativos (pelo menos uma utilização de serviços mensal) em teleconsultorias.
	 * @param percentualSolicitacoesAprovadasCIB	% de solicitações de teleconsultorias baseadas em protocolos de regulação aprovadas na CIB.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public QuadroDois(int numeroSolicitacoesSincronas, int numeroSolicitacoesAssincronas, int numeroPontosAtivos, double percentualSolicitacoesAprovadasCIB)
	{
		dicionario.put("num_sincronas", numeroSolicitacoesSincronas);
		dicionario.put("num_assincronas", numeroSolicitacoesAssincronas);
		dicionario.put("num_pontos_ativos", numeroPontosAtivos);
		dicionario.put("percentual_aprovado_cib", percentualSolicitacoesAprovadasCIB);
	}

	/**
	 * Adiciona o indicador "Número de solicitações de teleconsultorias no estado (codigoUF) respondidas".
	 * @param codigoUF	Código IBGE do estado.
	 * @param numero	valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesUF(String codigoUF, int numero)
	{	
		Integra.addDictionary(true, dicionario, "solicitacoes_uf", "codigo_uf", codigoUF);
		Integra.addDictionary(dicionario, "solicitacoes_uf", "numero", numero);	
	}

	/**
	 * Adiciona o indicador "Número de solicitações de teleconsultorias por município (codigoMunicipio) respondidas".
	 * @param codigoMunicipio	Código IBGE do município.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesMunicipio(String codigoMunicipio, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_municipio", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "solicitacoes_municipio", "numero", numero);	
	}

	/**
	 * Adiciona o indicador "Número de solicitações de teleconsultorias por equipe de saúde respondidas".
	 * Observação: Pelo menos um dos dois (codigoEquipe ou codigoEquipeIne) deve ser informado.
	 * @param codigo_equipe			Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria.
	 * @param codigo_equipe_ine		Código Identificador Nacional de Equipe (INE).	
	 * @param numero				Valor do indicador.	
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesEquipe(String codigo_equipe, String codigo_equipe_ine, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_equipe", "numero", numero);
		if (!Utils.isBlankOrNull(codigo_equipe))
			Integra.addDictionary(dicionario, "solicitacoes_equipe", "codigo_equipe", codigo_equipe);
		if (!Utils.isBlankOrNull(codigo_equipe_ine))
			Integra.addDictionary(dicionario, "solicitacoes_equipe", "codigo_equipe_ine", codigo_equipe_ine);
		
	}

	/**
	 * Adiciona o indicador "Número de solicitações de teleconsultorias por membro de gestão respondidas".
	 * Observação: A indentificação do membro de gestão será inicialmente pelo CPF (codigoCPF), caso não exista usar o identificador Cartão Nacional de Saúde (CNS) e como última opção o email.
	 * @param codigoCPF		CPF  do membro de gestão.		
	 * @param codigo_cns	Identificador CNS do membro de gestão.
	 * @param nome			Nome do membro de gestão.
	 * @param email			Email do membro de gestão.
	 * @param numero		Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesMembroGestao(String codigoCPF, String codigo_cns, String nome, String email, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_membro", "email", email);
		Integra.addDictionary(dicionario, "solicitacoes_membro", "membro_nome", nome);
		Integra.addDictionary(dicionario, "solicitacoes_membro", "numero", numero);
		if (!Utils.isBlankOrNull(codigoCPF))
			Integra.addDictionary(dicionario, "solicitacoes_membro", "codigo_cpf", codigoCPF);
		if (!Utils.isBlankOrNull(codigo_cns))	
			Integra.addDictionary(dicionario, "solicitacoes_membro", "codigo_cns", codigo_cns);
	}
	
	/**
	 * Adiciona o indicador "Número de solicitações de teleconsultorias por ponto (codigoPontoTelessaude) respondidas."
	 * @param codigoPontoTelessaude	Código Cadastro Nacional de Estabelecimento de Saúde (CNES)
	 * @param numero				Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesPontoTelessaude(String codigoPontoTelessaude, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_ponto", "codigo_ponto", codigoPontoTelessaude);
		Integra.addDictionary(dicionario, "solicitacoes_ponto", "numero", numero);
	}

	/**
	 * Adiciona o indicador "Número total de solicitações por profissional respondidadas".
	 * <p/>
	 * Observações: <p/>
	 * 		1. A indentificação do profisional de saúde será inicialmente pelo CPF (codigoCPF), caso não exista usar o identificador Cartão Nacional de Saúde (CNS) e como última opção o email.
	 * <p/>
	 * 		2. Ao menos codigo_equipe ou codigo_equipe_ine deve ser informado.
	 * @param CPF					CPF  do profissional (máximo 11 caracteres).	
	 * @param codigo_cns			Identificador CNS do profissional (máximo 15 caracteres).
	 * @param nome					Nome do profissional (máximo 60 caracteres).
	 * @param email					Email do profissional (máximo 60 caracteres).
	 * @param tipo					código do tipo do profissional, se Mais Médicos, PROVAB, consultar sistema para recuperar o código.
	 * @param codigo_cbo			Código de 6 digítos referente à ocupação na CBO2002.
	 * @param codigo_equipe			Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria.
	 * @param codigo_equipe_ine		Código Identificador Nacional de Equipe (INE).
	 * @param numero				Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesProfissional(String CPF, String codigo_cns, String nome, String email, String tipo, String codigo_cbo, String codigo_equipe, String codigo_equipe_ine, String cnes_estabelecimento, int numero)
	{
		Integra.addDictionary(true, dicionario, "solicitacoes_profissional", "profissional_email", email);
		Integra.addDictionary(dicionario, "solicitacoes_profissional", "nome", nome);
		Integra.addDictionary(dicionario, "solicitacoes_profissional", "codigo_tipo_profissional", tipo);
		Integra.addDictionary(dicionario, "solicitacoes_profissional", "numero", numero);
		Integra.addDictionary(dicionario, "solicitacoes_profissional", "codigo_cbo", codigo_cbo);
		Integra.addDictionary(dicionario, "solicitacoes_profissional", "codigo_cnes_estabelecimento", cnes_estabelecimento);
		if (!Utils.isBlankOrNull(codigo_equipe))
			Integra.addDictionary(dicionario, "solicitacoes_profissional", "codigo_equipe", codigo_equipe);
		if (!Utils.isBlankOrNull(codigo_equipe_ine))
			Integra.addDictionary(dicionario, "solicitacoes_profissional", "codigo_equipe_ine", codigo_equipe_ine);
		if (!Utils.isBlankOrNull(CPF))
			Integra.addDictionary(dicionario, "solicitacoes_profissional", "profissional_cpf", CPF);
		if (!Utils.isBlankOrNull(codigo_cns))	
			Integra.addDictionary(dicionario, "solicitacoes_profissional", "profissional_codigo_cns", codigo_cns);
	}
	
	
	/**
	 * Adiciona o indicador "Número total de solicitações por Profissional de saúde e por tema (CID e/ou CIAP2) respondidas".
	 * <p/>
	 * Observações: <p/>
	 * 		1. codigo_cpf, codigo_cns e email são utilizados para localizar o profissional de saúde, ao menos um dos três deve ser informado.
	 * <p/>
	 * 		2. Ao menos codigo_cid ou codigo_ciap deve ser informado.
	 * @param profissional_codigo_cpf	CPF  do profissional	
	 * @param profissional_codigo_cns	Identificador CNS do profissional
	 * @param profissional_email		Email do profissional
	 * @param codigo_cid				Código CID (Classificação Internacional de Doenças).
	 * @param codigo_ciap				Código CIAP (Classificação Internacional de Assistência Primária).
	 * @param numero					Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesProfissionalTema(String profissional_cpf, String profissional_codigo_cns, String profissional_email, String codigo_cid, String codigo_ciap, int numero)
	{
		Integra.addDictionary(true, dicionario, "solicitacoes_tema_profissional", "numero", numero);
		if (!Utils.isBlankOrNull(codigo_cid))
			Integra.addDictionary(dicionario, "solicitacoes_tema_profissional", "codigo_cid", codigo_cid);
		if (!Utils.isBlankOrNull(codigo_ciap))
			Integra.addDictionary(dicionario, "solicitacoes_tema_profissional", "codigo_ciap", codigo_ciap);
		if (!Utils.isBlankOrNull(profissional_cpf))
			Integra.addDictionary(dicionario, "solicitacoes_tema_profissional", "profissional_cpf", profissional_cpf);
		if (!Utils.isBlankOrNull(profissional_codigo_cns))	
			Integra.addDictionary(dicionario, "solicitacoes_tema_profissional", "profissional_codigo_cns", profissional_codigo_cns);
		if (!Utils.isBlankOrNull(profissional_email))	
			Integra.addDictionary(dicionario, "solicitacoes_tema_profissional", "profissional_email", profissional_email);
	}

	/**
	 * Adiciona o indicador "Número de solicitações de solicitações por categoria profissional respondidas".
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddSolicitacoesCatProfissional(String codigoFamiliaCBO, int numero)
	{
		Integra.addDictionary(true,dicionario, "solicitacoes_cat_profissional", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "solicitacoes_cat_profissional", "numero", numero);
	}

	Map<String, Object> ToDictionary()
	{
		return this.dicionario;
	}
}

