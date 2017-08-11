package integra;

import integra.exceptions.ValidationDataException;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe responsável por armazenar os cursos oferecidos por meio da Tele-educação
 * @author Jailton Carlos
 */
public class TeleeducacaoCurso extends IntegraSerializer
{
	//repesenta o json que irá ser enviando
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	/**
	 * Construtor da classe
	 * @param codigoNucleo	Código CNES de identificação do núcleo cadastrado no SMART.
	 * @param mesReferencia	Mês de referência para os indicadores informados. 
	 * @throws ValidationDataException	Ocorre se o mês de referência não estiver no formato MMyyyy.
	 */
	public TeleeducacaoCurso(String codigoNucleo, String mesReferencia) {
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		if (Utils.isBlankOrNull(codigoNucleo))
			throw new ValidationDataException("Código do núcleo é obrigatório.");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);
	}
	
	/**
	 * Adiciona/atualiza o curso oferecido pela tele-educação
	 * O SMART considera uma curso único pela chave  (identificacaoCurso e dataInicio)
	 * 
	 * @param identificacaoCurso	Código único utilizado pelo núcleo para identificar a disponibilização do curso
	 * @param dataInicio			Data/hora no qual o curso foi disponibilizado formato d/MM/yyyy HH:mm:ss
	 * @param dataFim				(opcional)Data/hora no qual o curso foi encerrado formato d/MM/yyyy HH:mm:ss
	 * @param vagasOfertadas		Quantidade de vagas ofertas
	 * @param tema					Código da classificação do Descritores em Ciências da Saude (DeCS) da BIREME
	 * 								Consultar lista de DeCS dispnonível no SMART através no menu "Cadastros Gerais > deSc BIREME - Descritores".
	 * @param cargaHoraria			Duração do curso em minutos
	 * @param listaCPFsMatriculados	(opcional) Lista de CPFs dos alunos matriculados.
	 * 								Quando encerrar o período de matriculas do curso, deve-se enviar novamente o curso com a relação dos alunos matriculados.
	 * @param listaCPFsFormados		(opcional) Lista de CPFs dos alunos formados
	 * 								Quando o curso tiver sido encerrado, deve-se enviar novamente o curso com a relação dos alunos formado.
	 * @param listaCPFsEvadidos		(opcional) Lista de CPFs dos alunos evadidos
	 * 								Quando o curso tiver sido encerrado, deve-se enviar novamente o curso com a relação dos alunos evadidos.
	 * @param listaCPFsReprovados	(opcional) Lista de CPFs dos alunos reprovados	
	 * 								Quando o curso tiver sido encerrado, deve-se enviar novamente o curso com a relação dos alunos reprovados.
	 */
	public void AdicionarCurso(String identificacaoCurso, 
									 String dataInicio,
									 String dataFim,
									 String vagasOfertadas,
									 String tema,
									 String cargaHoraria,
									 String[] listaCPFsMatriculados,
									 String[] listaCPFsFormados,
									 String[] listaCPFsEvadidos,
									 String[] listaCPFsReprovados)
	{		
		if (Utils.getDate(dataInicio) == null)
			throw new ValidationDataException("A data de início do curso informada não está no formato dd/MM/yyyy HH:mm:ss.");
		if (Utils.getDate(dataFim) == null)
			throw new ValidationDataException("A data de encerramento do curso informada não está no formato dd/MM/yyyy HH:mm:ss.");

		Integra.addDictionary(true, dicionario, "cursos_teleeducacao", "id", identificacaoCurso);
		Integra.addDictionary(dicionario, "cursos_teleeducacao", "dtini", dataInicio);
		Integra.addDictionary(dicionario, "cursos_teleeducacao", "dtfim", dataFim);
		Integra.addDictionary(dicionario, "cursos_teleeducacao", "vagas", vagasOfertadas);
		Integra.addDictionary(dicionario, "cursos_teleeducacao", "decs", tema);
		Integra.addDictionary(dicionario, "cursos_teleeducacao", "cargah", cargaHoraria);		
		
		
		if (listaCPFsMatriculados != null)
			Integra.addDictionary(dicionario, "cursos_teleeducacao", "lista_cpf_matriculados", listaCPFsMatriculados);
		if (listaCPFsFormados != null)
			Integra.addDictionary(dicionario, "cursos_teleeducacao", "lista_cpf_formados", listaCPFsFormados);
		if (listaCPFsEvadidos != null)
			Integra.addDictionary(dicionario, "cursos_teleeducacao", "lista_cpf_evadidos", listaCPFsEvadidos);
		if (listaCPFsReprovados != null)
			Integra.addDictionary(dicionario, "cursos_teleeducacao", "lista_cpf_reprovados", listaCPFsReprovados);
		
	}

	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
