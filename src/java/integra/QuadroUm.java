package integra;

import integra.exceptions.ValidationDataException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe com todas as funções necessárias para o recebimento do Quadro 1 (Indicadores de estrutura
 * para monitoramento e avaliação do Programa Nacional Telessaúde Brasil Redes). 
 * @author Jailton Carlos
 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
 */
@Deprecated
public class QuadroUm
{
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Inicializa uma nova instância da classe com os indicadores passados nos parâmetros.
	 * @param numeroDeProfissionaisQualificados	Numero de profissionais que foram qualificados para uso das ferramentas em Telessaúde.
	 * @param numeroDeDispositivosMoveis		Meio de acesso ao serviço de Telessaúde através de dispositivo móveis.
	 * @param numeroDeDispositivosFixos			Meio de acesso ao serviço de Telessaúde através de dispositivo fixos.
	 */
	public QuadroUm(int numeroDeProfissionaisQualificados, int numeroDeDispositivosMoveis, int numeroDeDispositivosFixos)
	{
		dicionario.put("num_profissionais_qualificados", numeroDeProfissionaisQualificados);
		dicionario.put("num_dispositivos_movel", numeroDeDispositivosMoveis);
		dicionario.put("num_dispositivos_fixo", numeroDeDispositivosFixos);
	}

	/**
	 * Adiciona indicadores (numPontosEmImplantacao, numPontosImplantados e numEquipesSaude) em cada município (codigoMunicipio).
	 * @param codigoMunicipio			Código IBGE do município.
	 * @param numPontosEmImplantacao	Número de unidades de saúde com pontos em implantação de Telessaúde em cada município.
	 * @param numPontosImplantados		Número de unidades de saúde com pontos implantados de Telessaúde em cada município.
	 * @param numEquipesSaude			Número de equipes de saúde atendidas por Telessaúde em cada município.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated	
	public void AddAvaliacaoEstrutura(String codigoMunicipio, int numPontosEmImplantacao, int numPontosImplantados, int numEquipesSaude)
	{
		if (codigoMunicipio.length() > 6 )
			throw new ValidationDataException("Código do Município não pode ser maior que 6.");
		
		Integra.addDictionary(true,dicionario, "avaliacao_estrutura_municipio", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "avaliacao_estrutura_municipio", "num_ubs_pontos_em_implantacao", numPontosEmImplantacao);
		Integra.addDictionary(dicionario, "avaliacao_estrutura_municipio", "num_ubs_pontos_implantados", numPontosImplantados);
		Integra.addDictionary(dicionario, "avaliacao_estrutura_municipio", "num_equipe_saude_atendidas", numEquipesSaude);
	}

	/**
	 * Adiciona o indicador "Número de profissionais registrados em cada município (codigoMunicipio) e em cada categoria profissional (codigoFamiliaCBO)".
	 * @param codigoMunicipio	Código IBGE do município.
	 * @param codigoFamiliaCBO	Código de 4 digítos referente à família ocupacional na CBO2002.
	 * @param numero			Valor do indicador.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void AddProfissionaisRegistrados(String codigoMunicipio, String codigoFamiliaCBO, int numero)
	{
		if (codigoMunicipio.length() > 6 )
			throw new ValidationDataException("Código do Município não pode ser maior que 6.");
		if (codigoFamiliaCBO.length() > 4 )
			throw new ValidationDataException("Código da família do CBO não pode ser maior que 4.");
		
		Integra.addDictionary(true, dicionario, "profissionais_registrados", "codigo_municipio", codigoMunicipio);
		Integra.addDictionary(dicionario, "profissionais_registrados", "codigo_familia_cbo", codigoFamiliaCBO);
		Integra.addDictionary(dicionario, "profissionais_registrados", "numero", numero);
	}

	Map<String, Object> ToDictionary()
	{
		return this.dicionario;
	}
}
