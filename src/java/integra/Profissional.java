package integra;

import integra.enumeracoes.Sexo;
import integra.exceptions.ValidationDataException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Classe responsável por cadastrar/atualizar os dados de profissional de saúde.
 * Quando o SMART recebe um CPF ele tentará busca na base e se não encontrado irá busca na base do CNES. 
 * Esse serviço só será utilizado caso o SMART não consiga encontrar o profissional pelo CPF informado nos outros serviços
 * @author Jailton Carlos
 */
public class Profissional extends IntegraSerializer
{
	//repesenta o json que irá ser enviando
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	
	/**
	 * Construtor da classe
	 * @param codigoNucleo	Código CNES de identifiação do núcleo cadastrado no SMART.
	 * @param mesReferencia	Mês de referência para os indicadores informados. 
	 * @throws ValidationDataException	Ocorre se o mês de referência não estiver no formato MMyyyy.
	 */
	public Profissional(String codigoNucleo, String mesReferencia) {
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		if (Utils.isBlankOrNull(codigoNucleo))
			throw new ValidationDataException("Código do núcleo é obrigatório.");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);
	}
	
	/**
	 * Adicona os dados básicos do profissional de saúde e seu respectivo vínculo.
	 * @param codigoCNS					(opcional) CNS do profissional
	 * @param codigoCPF					CPF do profissional
	 * @param nome						Nome do profissional
	 * @param codigoCNES				Código CNES do estabelecimento de saúde no qual o profissional solicitante atua
	 * @param codigoCBO					Código CBO da ocupação. 
	 * 									Consultar lista de CBOs dispnonível no SMART através no menu "Cadastros Gerais > Especialidades (CBO)".
	 * @param codigoINEEquipe			(opcional) Código INE da equipe de saúde na qual o profissional faz parte
	 * @param codigoTipoProfissional 	Código do tipo de profissional. 
	 * 									Consultar lista de tipos deprofissionais dispnonível no SMART através no menu "Cadastros Gerais > Tipos de Profissionais".
	 * @param sexo
	 */
	public void CadastrarProfissional(String codigoCNS, 
									 String codigoCPF,
									 String nome,
									 String codigoCNES,
									 String codigoCBO,
									 String codigoINEEquipe,
									 String codigoTipoProfissional,
									 Sexo sexo)
	{		
		Integra.addDictionary(true, dicionario, "profissionais", "cns", codigoCNS);
		Integra.addDictionary(dicionario, "profissionais", "cpf", codigoCPF);
		Integra.addDictionary(dicionario, "profissionais", "nome", nome);
		Integra.addDictionary(dicionario, "profissionais", "cnes", codigoCNES);
		Integra.addDictionary(dicionario, "profissionais", "cbo", codigoCBO);
		Integra.addDictionary(dicionario, "profissionais", "ine", codigoINEEquipe);
		Integra.addDictionary(dicionario, "profissionais", "tprof", codigoTipoProfissional);
		Integra.addDictionary(dicionario, "profissionais", "sexo", sexo.getValor());		
		
	}

	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
