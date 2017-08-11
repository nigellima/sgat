
package integra;

import integra.exceptions.ValidationDataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Determina quais são os indicadore (quadros) a serem enviados.
 * @author Jailton Carlos
 *
 */
public class IndicadorGeral extends IntegraSerializer
{
	private Map<String, Object> dicionario = new HashMap<String, Object>();

	QuadroUm quadroUm = null;
	QuadroDois quadroDois = null;
	QuadroTres quadroTres = null;
	QuadroQuatro quadroQuatro = null;
	QuadroCinco quadroCinco = null;
	QuadroSeis quadroSeis = null;

	/**
	 * Inicializa uma nova instância da classe com os indicadores passados nos parâmetros.
	 * Observação: É possível enviar os indicadores (quadros) de forma separada, para isso, basta informa null no respectivo parâmetro.
	 * @param codigoNucleo	É possível enviar os indicadores (quadros) de forma separada, para isso, basta informa null no respectivo parâmetro.
	 * @param mesReferencia	Mês de referência para os indicadores informados.
	 * @param qum			Mês de referência para os indicadores informados.
	 * @param qdois			Informa os indicadores relativos ao Quadro 2 da nota técnica.
	 * @param qtres			Informa os indicadores relativos ao Quadro 3 da nota técnica.
	 * @param qquatro		Informa os indicadores relativos ao Quadro 4 da nota técnica.
	 * @param qcinco		Informa os indicadores relativos ao Quadro 5 da nota técnica.
	 * @param qseis			Informa os indicadores relativos ao Quadro 6 da nota técnica.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public IndicadorGeral (String codigoNucleo, String mesReferencia, QuadroUm qum, QuadroDois qdois, QuadroTres qtres, QuadroQuatro qquatro, QuadroCinco qcinco, QuadroSeis qseis) {
		this.setQuadroUm(qum);
		this.setQuadroDois(qdois);
		this.setQuadroTres(qtres);
		this.setQuadroQuatro(qquatro);
		this.setQuadroCinco(qcinco);
		this.setQuadroSeis(qseis);
		if (Utils.getDate(mesReferencia, "MMyyyy") == null)
			throw new ValidationDataException("Mês de referência inválido, usar o formato MMyyyy");
		dicionario.put("codigo_nucleo", codigoNucleo);
		dicionario.put("mes_referencia", mesReferencia);		
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public QuadroUm getQuadroUm() {
		return quadroUm;
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void setQuadroUm(QuadroUm quadroUm) {
		this.quadroUm = quadroUm;
		if (quadroUm != null)
			dicionario.put("quadro_um", quadroUm.ToDictionary());
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public QuadroDois getQuadroDois() {
		return quadroDois;
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void setQuadroDois(QuadroDois quadroDois) {
		this.quadroDois = quadroDois;
		if (quadroDois != null)
			dicionario.put("quadro_dois", quadroDois.ToDictionary());
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public QuadroTres getQuadroTres() {
		return quadroTres;
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void setQuadroTres(QuadroTres quadroTres) {
		this.quadroTres = quadroTres;
		if (quadroTres != null)
			dicionario.put("quadro_tres", quadroTres.ToDictionary());
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public QuadroQuatro getQuadroQuatro() {
		return quadroQuatro;
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void setQuadroQuatro(QuadroQuatro quadroQuatro) {
		this.quadroQuatro = quadroQuatro;
		if (quadroQuatro != null)
			dicionario.put("quadro_quatro", quadroQuatro.ToDictionary());
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public QuadroCinco getQuadroCinco() {
		return quadroCinco;
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void setQuadroCinco(QuadroCinco quadroCinco) {
		this.quadroCinco = quadroCinco;
		if (quadroCinco != null)
			dicionario.put("quadro_cinco", quadroCinco.ToDictionary());
		
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public QuadroSeis getQuadroSeis() {
		return quadroSeis;
	}

	/**
	 * 
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public void setQuadroSeis(QuadroSeis quadroSeis) {
		this.quadroSeis = quadroSeis;
		if (quadroSeis != null)
			dicionario.put("quadro_seis", quadroSeis.ToDictionary());
	}
	
	Map<String, Object> getDicionario()
	{
		return this.dicionario;
	}
}
