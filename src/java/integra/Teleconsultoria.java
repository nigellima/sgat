package integra;

import integra.enumeracoes.CanalAcesso;
import integra.enumeracoes.GrauSatisfacao;
import integra.enumeracoes.ResolucaoDuvidaTeleconsultoria;
import integra.enumeracoes.TipoTeleconsultoria;
import integra.exceptions.ValidationDataException;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe responsável por armazenar as solicitações de teleconsultoria
 * @author Jailton Carlos
 */
public class Teleconsultoria extends IntegraSerializer
{
	//repesenta o json que irá ser enviando
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Construtor da classe
	 * @param codigoNucleo	Código CNES de identifiação do núcleo cadastrado no SMART.
	 * @param mesReferencia	Mês de referência para os indicadores informados. 
	 * @throws ValidationDataException	Ocorre se o mês de referência não estiver no formato MMyyyy.
	 */
	public Teleconsultoria(String codigoNucleo, String mesReferencia) {
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		if (Utils.isBlankOrNull(codigoNucleo))
			throw new ValidationDataException("Código do núcleo é obrigatório.");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);
	}
	
	/** Adiciona a solicitação de teleconsultoria.
	 * O SMART considera uma teleconsultoria única pela chave  (dataSolicitacao e cpfSolicitante)
	 * 
	 * @param dataSolicitacao						Data/hora da solicitação da teleconsultoria no formato dd/MM/yyyy HH:MM:SS
	 * @param tipo									Tipo da solicitação, veja {@link integra.enumeracoes.TipoTeleconsultoria}
	 * @param canalAcesso							Canal de acesso, veja {@link integra.enumeracoes.CanalAcesso}
	 * @param cpfSolicitante						CPF do profissional que solicitou a teleconsultoria
	 * @param codgioCBOSolicitante					Código CBO da ocupação do solicitante no momento da solicitação da teleconsultoria. 
	 * 												Consultar lista de CBOs dispnonível no SMART através no menu "Cadastros Gerais > Especialidades (CBO)".
	 * @param codigoCNESSolicitante					Código CNES do estabelecimento de saúde no qual o profissional solicitante atua
	 * @param codigoINEEquipe						(opcional) Código INE da equipe de saúde na qual o profissional solicitante faz parte	
	 * @param codigoTipoProfissionalSolicitante		Código do tipo de profissional. 
	 * 												Consultar lista de tipos deprofissionais dispnonível no SMART através no menu "Cadastros Gerais > Tipos de Profissionais".
	 * @param codigosCIDS							Lista com os códigos CID (Classificação Internacional de Doenças).			
	 * 												Consultar lista de CIDs dispnonível no SMART através no menu "Cadastros Gerais > CID 10 - Classificação Internacional de Doenças".
	 * @param codigosCIAPS							Lista com os códigos CIAP (Classificação Internacional de Assistência Primária).
	 * 												Consultar lista de CIAPS dispnonível no SMART através no menu "Cadastros Gerais > CIAP 2 - Classificação Internacional de Atenção Primária". 
	 * @param dataResposta							Data/hora da resposta da solicitação no formato dd/MM/yyyy HH:MM:SS
	 * @param evitouEncaminhamento					Se a teleconsultoria evitou o encaminhamento de paciente
	 * @param intensaoEncaminha						Se o profissional registrou na teleconsultoria que tinha intenção de encaminhar o paciente
	 * @param grauSatisfacaoSolicitante				Grau de satisfação do solicitante quanto a resposta da sua teleconsultoria, veja {@link integra.enumeracoes.GrauSatisfacao}
	 * @param resolucaoDuvida						Se a resposta da teleconsultoria atendeu ou não a teleconsultoria, veja {@link integra.enumeracoes.ResolucaoDuvidaTeleconsultoria}
	 * @param potencialSOF							Se a teleconsultoria tem pontencial para transforma em SOF
	 */				
	public void AdicionarSolicitacao(String dataSolicitacao, 
									 TipoTeleconsultoria tipo,
									 CanalAcesso canalAcesso,
									 String cpfSolicitante,
									 String codgioCBOSolicitante,
									 String codigoCNESSolicitante,
									 String codigoINEEquipe,
									 String codigoTipoProfissionalSolicitante,
									 String[] codigosCIDS,
									 String[] codigosCIAPS,
									 String dataResposta,
									 Boolean evitouEncaminhamento,
									 Boolean intensaoEncaminha,
									 GrauSatisfacao grauSatisfacaoSolicitante,
									 ResolucaoDuvidaTeleconsultoria resolucaoDuvida,
									 Boolean potencialSOF)
	{		
		if (Utils.getDate(dataSolicitacao) == null)
			throw new ValidationDataException("A data da solicitação informada não está no formato dd/MM/yyyy HH:mm:ss.");
		if (Utils.getDate(dataResposta) == null)
			throw new ValidationDataException("A data da resposta informada não está no formato dd/MM/yyyy HH:mm:ss.");
		if (codigosCIDS == null &&codigosCIAPS ==null)
			throw new ValidationDataException("Deve ser fornecida a relação de códigos CIAPS e/ou CIDS");

		Integra.addDictionary(true, dicionario, "teleconsultorias", "dtsol", dataSolicitacao);
		Integra.addDictionary(dicionario, "teleconsultorias", "tipo", tipo.getValor());
		Integra.addDictionary(dicionario, "teleconsultorias", "canal", canalAcesso.getValor());
		Integra.addDictionary(dicionario, "teleconsultorias", "scpf", cpfSolicitante);
		Integra.addDictionary(dicionario, "teleconsultorias", "scbo", codgioCBOSolicitante);
		Integra.addDictionary(dicionario, "teleconsultorias", "scnes", codigoCNESSolicitante);		
		Integra.addDictionary(dicionario, "teleconsultorias", "stipo", codigoTipoProfissionalSolicitante);
		if (!Utils.isBlankOrNull(codigoINEEquipe))
			Integra.addDictionary(dicionario, "teleconsultorias", "sine", codigoINEEquipe);
		if (codigosCIDS != null)
			Integra.addDictionary(dicionario, "teleconsultorias", "cids", codigosCIDS);
		if (codigosCIAPS != null)
			Integra.addDictionary(dicionario, "teleconsultorias", "ciaps", codigosCIAPS);
		Integra.addDictionary(dicionario, "teleconsultorias", "dtresp", dataResposta);
		Integra.addDictionary(dicionario, "teleconsultorias", "evenc", evitouEncaminhamento ? 1 : 0);
		Integra.addDictionary(dicionario, "teleconsultorias", "inenc", intensaoEncaminha ? 1 : 0);
		Integra.addDictionary(dicionario, "teleconsultorias", "satisf", grauSatisfacaoSolicitante.getValor());
		Integra.addDictionary(dicionario, "teleconsultorias", "rduvida", resolucaoDuvida.getValor());
		Integra.addDictionary(dicionario, "teleconsultorias", "psof", potencialSOF ? 1 : 0);
	}

	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
