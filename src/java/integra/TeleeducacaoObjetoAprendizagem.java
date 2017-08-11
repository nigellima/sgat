package integra;

import integra.enumeracoes.TipoObjetoAprendizagem;
import integra.exceptions.ValidationDataException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por armazenar os dados de objetos de aprendizagem de teleeducação e seus respectivos acessos
 * para monitoramento e avaliação do Programa Nacional Telessaúde Brasil Redes).
 * @author Jailton Carlos
 */
public class TeleeducacaoObjetoAprendizagem extends IntegraSerializer
{
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Construtor da classe
	 * @param codigoNucleo	Código CNES de identificação do núcleo cadastrado no SMART.
	 * @param mesReferencia	Mês de referência para os indicadores informados.
	 * @throws ValidationDataException	Ocorre se o mês de referência não estiver no formato MMyyyy.
	 */
	public TeleeducacaoObjetoAprendizagem(String codigoNucleo, String mesReferencia) {
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		if (Utils.isBlankOrNull(codigoNucleo))
			throw new ValidationDataException("Código do núcleo é obrigatório.");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);
	}

	/**
	 * Adiciona os objetos de aprendizagem disponibilizados.
	 *
	 * São considerados objetos de aprendizagem as ofertas de tele-educação  disponibilizadas de forma assíncronas em documento texto
	 * ou audiovisual  para acesso de profissional de saúde (vide nota técnica 50/2015 DEGES/SGTES/MS)
	 * @param codigoIdentificacao						Código único utilizado pelo núcleo para identificar o objeto de aprendizagem
	 * @param dataDisponibilizacao						Data/hora em que o objeto de aprendizagem foi disponibilizado no formato d/MM/yyyy HH:mm:ss
	 * @param disponibilizadoPlataformaTelessaude		Se disponibilizado na plataforma de telessaúde do próprio núcleo
	 * @param disponibilizadoARES						Se disponibilizado Biblioteca Virtual, Cletâne Telessaúde no ARES/UNA-SUS
	 * @param disponibilizadoAVASUS						Se disponibilizado no AVA-SUS - Ambiente Virtual de Aprendizagem do Sistema Único de Saúde (SUS),
	 * @param disponibilizadoRedesSociais				Se disponibilizado em alguma rede social
	 * @param disponibilizadoOutros						Se disponibilizado em outro repositório
	 * @param tipoObjeto								Classificação do objeto de aprendizagem, veja {@link integra.enumeracoes.TipoObjetoAprendizagem}
	 * @param codigoTema								Código da classificação do Descritores em Ciências da Saude (DeCS) da BIREME
	 * 													Consultar lista de DeCS dispnonível no SMART através no menu "Cadastros Gerais > deSc BIREME - Descritores".
	 * @param url										Endereço de rede para acesso ao recurso quanto este for público, não necessita de credencias para acesso.
	 * @param numeroAcessos								Número de acesso ao objeto de aprendizagem
	 */
	public void AdicionarObjetoAprendizagem(String codigoIdentificacao, 
											String dataDisponibilizacao, 
											Boolean disponibilizadoPlataformaTelessaude, 
											Boolean disponibilizadoARES, 
											Boolean disponibilizadoAVASUS, 
											Boolean disponibilizadoRedesSociais, 
											Boolean disponibilizadoOutros, 
											TipoObjetoAprendizagem tipoObjeto, 
											String codigoTema, 
											String url, 
											int numeroAcesso) {
		if (Utils.isBlankOrNull(codigoIdentificacao))
			throw new ValidationDataException("Código de identificação da atividade é obrigatório.");

		if (Utils.getDate(dataDisponibilizacao) == null)
			throw new ValidationDataException("A data de disponibilização informada não está no formato dd/MM/yyyy HH:MM:SS.");

		Integra.addDictionary(true, dicionario, "objetos_aprendizagem", "id", codigoIdentificacao);
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "dtdispo", dataDisponibilizacao);
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "dplataf", disponibilizadoPlataformaTelessaude ? "1" : "0");
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "dares", disponibilizadoARES ? "1" : "0");
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "davasus", disponibilizadoAVASUS ? "1" : "0");
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "drsociais", disponibilizadoRedesSociais ? "1" : "0");
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "doutros", disponibilizadoOutros ? "1" : "0");
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "tipo", tipoObjeto.getValor());
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "decs", codigoTema);
		if (!Utils.isBlankOrNull(url))
			Integra.addDictionary(dicionario, "objetos_aprendizagem", "url", url);
		Integra.addDictionary(dicionario, "objetos_aprendizagem", "num", numeroAcesso);
	}

	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
