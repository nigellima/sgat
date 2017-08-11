package integra;

import integra.exceptions.ValidationDataException;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe responsável por atualizar os estabelecimento de saúde quanto ao serviço consumido
 * @author Jailton Carlos
 */
public class EstabelecimentoSaude extends IntegraSerializer
{
	//repesenta o json que irá ser enviando
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	
	/**
	 * Construtor da classe
	 * @param codigoNucleo	Código CNES de identificação do núcleo cadastrado no SMART.
	 * @param mesReferencia	Mês de referência para os indicadores informados. 
	 * @throws ValidationDataException	Ocorre se o mês de referência não estiver no formato MMyyyy.
	 */
	public EstabelecimentoSaude(String codigoNucleo, String mesReferencia) {
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		if (Utils.isBlankOrNull(codigoNucleo))
			throw new ValidationDataException("Código do núcleo é obrigatório.");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);
	}
	
	/**
	 * Adiciona o estabelecimento de saúde que será atualizado
	 * @param codigoCNES						Código CNES do estabelecimento de saúde no qual o profissional solicitante atua
	 * @param cadastradoServicoTeleconsultoria	Se o estabelecimento consome serviço de Teleconsultoria
	 * @param cadastradoServicoTeleeducacao		Se o estabelecimento consome serviço de tele-educação
	 * @param cadastradoServicoTelediagnostico	Se o estabelecimento consome serviço de Telediagnóstico
	 */
	public void AdicionarEstabelecimento(String codigoCNES, 
									 Boolean cadastradoServicoTeleconsultoria,
									 Boolean cadastradoServicoTeleeducacao,
									 Boolean cadastradoServicoTelediagnostico)
	{		
		Integra.addDictionary(true, dicionario, "estabelecimentos", "cnes", codigoCNES);
		Integra.addDictionary(dicionario, "estabelecimentos", "tconsul", cadastradoServicoTeleconsultoria ? 1 : 0);
		Integra.addDictionary(dicionario, "estabelecimentos", "teduca", cadastradoServicoTeleeducacao ? 1 : 0);
		Integra.addDictionary(dicionario, "estabelecimentos", "tdiagn", cadastradoServicoTelediagnostico ? 1 : 0);
	}

	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
