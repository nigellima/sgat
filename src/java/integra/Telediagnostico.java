package integra;

import integra.exceptions.ValidationDataException;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe responsável por armazenar as solicitações de telediagnóstico
 * @author Jailton Carlos
 */
public class Telediagnostico extends IntegraSerializer{

	//repesenta o json que irá ser enviando
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Construtor da classe
	 * @param codigoNucleo	Código CNES de identificação do núcleo cadastrado no SMART.
	 * @param mesReferencia	Mês de referência para os indicadores informados. 
	 * @throws ValidationDataException	Ocorre se o mês de referência não estiver no formato MMyyyy.
	 */
	public Telediagnostico(String codigoNucleo, String mesReferencia) {
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		if (Utils.isBlankOrNull(codigoNucleo))
			throw new ValidationDataException("Código do núcleo é obrigatório.");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);
	}
	
	/**
	 * Adiciona a solicitação do telediagnóstico
	 * O SMART considera um telediagnóstico única pela chave  (dataSolicitacao e cpfSolicitante)
	 * @param dataSolicitacao					Data/hora da solicitação do telediagnóstico no formato dd/MM/yyyy HH:MM:SS
	 * @param tipoExame							Código SIA/SIH do tipo do exame
	 * @param codigoEquipamento					(opcional) Código do equipamento utilizado para realizar o exame de telediagnóstico. 
	 * 											Consultar lista de equipamentos dispnonível no SMART através no menu "Cadastros Gerais > Equipamentos".
	 * @param codigoTipoJustificativa			(opcional) Código da justificativa utilizada caso o código do equipamento não tenha sido informado
	 * 											Obrigatório se codigoEquipamento não foi fornecido
	 * 											Consultar lista de justificativas dispnonível no SMART através no menu "Cadastros Gerais > Tipos de Justificativa".
	 * @param codigoCNESDoPontoTelessaude		Código CNES do estabelecimento de saúde onde está o equipamento que realiza o exame
	 * @param cpfSolicitante					CPF do profissional que solicitou o telediagnóstico
	 * @param codigoCBOSolicitante				Código CBO da ocupação do solicitante no momento da solicitação do Telediagnóstico.
	 * 											Consultar lista de CBOs dispnonível no SMART através no menu "Cadastros Gerais > Especialidades (CBO)".
	 * @param codigoCNESSolicitante				Código CNES do estabelecimento de saúde no qual o profissional solicitante atua
	 * @param dataLaudo							Data/hora da disponibilização do laudo no formato dd/MM/yyyy HH:MM:SS
	 * @param cpfLaudista						CPF do especialista que elaborou o laudo
	 * @param codigoCBOLaudista					Código CBO da ocupação do laudista.
	 * @param codigoCNESLaudista				Código CNES do laudista
	 * @param cpfPaciente						CPF do paciente
	 * @param cnsPaciente						(opcional) CNS do paciente
	 * 											Obrigatório se CPF não foi fornecido
	 * @param codigoIBGECidadeMoradiaPaciente	Código IBGE sem o dígito verificador da cidade onde o paciente mora.
	 */
	public void AdicionarSolicitacao(String dataSolicitacao, 
									 String tipoExame,
									 String codigoEquipamento,
									 String codigoTipoJustificativa,
									 String codigoCNESDoPontoTelessaude,
									 String cpfSolicitante,
									 String codigoCBOSolicitante,
									 String codigoCNESSolicitante,
									 String dataLaudo,
									 String cpfLaudista,
									 String codigoCBOLaudista,
									 String codigoCNESLaudista,
									 String cpfPaciente,
									 String cnsPaciente,
									 String codigoIBGECidadeMoradiaPaciente)
	{		
		if (Utils.getDate(dataSolicitacao) == null)
			throw new ValidationDataException("A data da realização do exame informada não está no formato dd/MM/yyyy HH:mm:ss.");
		if (Utils.getDate(dataLaudo) == null)
			throw new ValidationDataException("A data de laudagem informada não está no formato dd/MM/yyyy HH:mm:ss.");
		if (Utils.isBlankOrNull(codigoEquipamento) && Utils.isBlankOrNull(codigoTipoJustificativa))
			throw new ValidationDataException("Código da justificativa deve ser informada quando código do equipamento não é fornecido");
		if (Utils.isBlankOrNull(cpfPaciente) && Utils.isBlankOrNull(cnsPaciente))
			throw new ValidationDataException("CPF e CNS do paciente não pode ser nulos, deve ser informar pelo menos um ou outro");
		
		Integra.addDictionary(true, dicionario, "telediagnosticos", "dhrexame", dataSolicitacao);
		Integra.addDictionary(dicionario, "telediagnosticos", "ctexame", tipoExame);
		Integra.addDictionary(dicionario, "telediagnosticos", "cequipa", codigoEquipamento);
		Integra.addDictionary(dicionario, "telediagnosticos", "tjust", codigoTipoJustificativa);
		Integra.addDictionary(dicionario, "telediagnosticos", "pnt", codigoCNESDoPontoTelessaude);
		Integra.addDictionary(dicionario, "telediagnosticos", "scpf", cpfSolicitante);
		Integra.addDictionary(dicionario, "telediagnosticos", "scbo", codigoCBOSolicitante);
		Integra.addDictionary(dicionario, "telediagnosticos", "scnes", codigoCNESSolicitante);		
		Integra.addDictionary(dicionario, "telediagnosticos", "dhla", dataLaudo);
		
		Integra.addDictionary(dicionario, "telediagnosticos", "lcpf", cpfLaudista);
		Integra.addDictionary(dicionario, "telediagnosticos", "lcbo", codigoCBOLaudista);
		Integra.addDictionary(dicionario, "telediagnosticos", "lcnes", codigoCNESLaudista);
		Integra.addDictionary(dicionario, "telediagnosticos", "pcpf", cpfPaciente);
		Integra.addDictionary(dicionario, "telediagnosticos", "pacns", cnsPaciente);
		Integra.addDictionary(dicionario, "telediagnosticos", "paibge", codigoIBGECidadeMoradiaPaciente);
	}

	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
