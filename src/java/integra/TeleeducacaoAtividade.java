package integra;

import integra.enumeracoes.GrauSatisfacao;
import integra.enumeracoes.TipoAtividade;
import integra.exceptions.ValidationDataException;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe responsável por armazenar os dados de atividades de tele-educação bem como as participações nessas atividades 
 * @author Jailton Carlos
 */
public class TeleeducacaoAtividade extends IntegraSerializer
{
	//repesenta o json que irá ser enviando
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Construtor da classe
	 * @param codigoNucleo	Código CNES de identifiação do núcleo cadastrado no SMART.
	 * @param mesReferencia	Mês de referência para os indicadores informados. 
	 * @throws ValidationDataException	Ocorre se o mês de referência não estiver no formato MMyyyy.
	 */
	public TeleeducacaoAtividade(String codigoNucleo, String mesReferencia) {
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		if (Utils.isBlankOrNull(codigoNucleo))
			throw new ValidationDataException("Código do núcleo é obrigatório.");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);
	}
	
	/**
	 * Adiciona a atividade de tele-educação
	 * @param codigoIdentificacao		Código único utilizado pelo núcleo para identificar atividade
	 * @param dataDisponibilizacao		Data/hora em que a atividade foi disponibilizada no formato d/MM/yyyy HH:mm:ss
	 * @param cargaHoraria				Duração da atividade em minutos
	 * @param tipoAtividade				Tipos de atividades educacional, veja {@link integra.enumeracoes.TipoAtividade}
	 * @param codigoTema				Código da classificação do Descritores em Ciências da Saude (DeCS) da BIREME
	 * 									Consultar lista de DeCS dispnonível no SMART através no menu "Cadastros Gerais > deSc BIREME - Descritores".
	 */
	public void AdicionarAtividade(String codigoIdentificacao, 
											   String dataDisponibilizacao,
											   String cargaHoraria,
											   TipoAtividade tipoAtividade,
											   String codigoTema)
	{				
		if (Utils.getDate(dataDisponibilizacao) == null)
			throw new ValidationDataException("A data de disponibilização informada não está no formato dd/MM/yyyy HH:mm:ss.");
		
		Integra.addDictionary(true, dicionario, "atividades_teleeducacao", "id", codigoIdentificacao);
		Integra.addDictionary(dicionario, "atividades_teleeducacao", "dtdispo", dataDisponibilizacao);
		Integra.addDictionary(dicionario, "atividades_teleeducacao", "cargah", cargaHoraria);
		Integra.addDictionary(dicionario, "atividades_teleeducacao", "tipo", tipoAtividade.getValor());
		Integra.addDictionary(dicionario, "atividades_teleeducacao", "decs", codigoTema);
	}
	
	/**
	 * Adiciona as participações em atividades de tele-educação
 	 * Observação: deve-se antes registrar a atividade de tele-educação, veja {@link #AdicionarAtividade(String, String, String, TipoAtividade, String) AdicionarAtividade} method.
	 * @param codigoIdentificacao		Código único utilizado pelo núcleo para identificar atividade
	 * @param dataParticipacao			Data/hora da participação no formato dd/MM/yyyy HH:MM:SS
	 * @param cpfParticipante			CPF do participante da atividade
	 * @param codgioCBOParticipante		Código CBO da ocupação do participante no momento da participação da atividade
	 * @param codigoCNESParticipante	Código CNES do estabelecimento de saúde no qual o participante atua no momento da participação da atividade
	 * @param codigoINEEquipe			(opcional) Código INE da equipe de saúde na qual o participante faz parte 
	 * @param tipoGrauSatisfacao		Grau de satisfação do participante quanto à atividade
	 */
	
	//Código Cadastro Nacional de Estabelecimento de Saúde (CNES)
	public void AdicionarParticipacaoAtividade(String codigoIdentificacao, 
											   String dataParticipacao,
											   String cpfParticipante,
											   String codgioCBOParticipante,
											   String codigoCNESParticipante,
											   String codigoINEEquipe,
											   GrauSatisfacao tipoGrauSatisfacao)
	{		
		Map<String, Object> dic_atividades_teleeducacao = Integra.getDictionaryInList(dicionario, "atividades_teleeducacao", "id", codigoIdentificacao);
		if (dic_atividades_teleeducacao == null)
			throw new ValidationDataException("Deve existir ao menos uma atividade registrada para o código de identificação fornecido.");
		
		if (Utils.isBlankOrNull(codigoIdentificacao))
			throw new ValidationDataException("Código de identificação da atividade é obrigatório.");

		if (Utils.getDate(dataParticipacao) == null)
			throw new ValidationDataException("A data de disponibilização informada não está no formato dd/MM/yyyy HH:MM:SS.");
		
		Integra.addDictionary(true, dic_atividades_teleeducacao, "participacoes_teleeducacao", "id", codigoIdentificacao);
		Integra.addDictionary(dic_atividades_teleeducacao, "participacoes_teleeducacao", "dtparti", dataParticipacao);
		Integra.addDictionary(dic_atividades_teleeducacao, "participacoes_teleeducacao", "cpf", cpfParticipante);
		Integra.addDictionary(dic_atividades_teleeducacao, "participacoes_teleeducacao", "cbo", codgioCBOParticipante);
		Integra.addDictionary(dic_atividades_teleeducacao, "participacoes_teleeducacao", "cnes", codigoCNESParticipante);
		if (!Utils.isBlankOrNull(codigoINEEquipe))
			Integra.addDictionary(dic_atividades_teleeducacao, "participacoes_teleeducacao", "ine", codigoINEEquipe);
		Integra.addDictionary(dic_atividades_teleeducacao, "participacoes_teleeducacao", "satisf", tipoGrauSatisfacao.getValor());
	}	

	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
