
package integra;

import integra.enumeracoes.TipoDeDados;
import integra.exceptions.IntegraException;
import integra.exceptions.ValidationDataException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
//https://sites.google.com/site/gson/gson-user-guide
import com.thoughtworks.xstream.XStream;
//http://xstream.codehaus.org/

/**
 * Empacota os dados e os envia para o webservice.
 * @author Jailton Carlos
 *
 */
public class Integra
{
	private String token_autenticacao = null;
	
	public Integra () {

	}
	
	/**
	 * Inicializa uma nova instância da classe com código do Token gerado pelo sistema
	 * @param token_autenticacao	Token gerado pelo sistema, representa um coordenador ou técnico de Núcleo de Telessaúde.
	 */
	public Integra (String token_autenticacao) {
		this.token_autenticacao = token_autenticacao;
	}
	
	/**
	 * 
	 * @return Token
	 */
	public String getToken_autenticacao() {
		return token_autenticacao;
	}

	/**
	 * Define o novo Token.
	 * @param token_autenticacao
	 */
	public void setToken_autenticacao(String token_autenticacao) {
		this.token_autenticacao = token_autenticacao;
	}
	
	/**
	 * Envia o os dados para o serviço especificado na url
	 * @param url						URL do webservice 
	 * @param objeto					Objeto com os dados a serem enviados
	 * @return							Texto com os dados retornados pelo webservice.
	 * @throws ValidationDataException	Exceção ocorre quando as informações enviados para o webservice contém dados não validos, tais como código CNES do estabelecimento, etc
	 * @throws IntegraException 		Exceção acontece se ocorrer alguma falha de comunicação com o webservice, tais como URL inexistente, etc.
	 */
	public String enviarDados(String url, IntegraSerializer objeto) throws IntegraException, ValidationDataException{
		TipoDeDados tipo = TipoDeDados.JSON;
		String dados = Serializar(tipo, objeto);
		return EnviarDados(tipo, url, dados);
	}
	/**
	 * Serializa o objeto do tipo IntegraSerializer para o formato tipo
	 * @param tipo		JSON ou XML.
	 * @param objeto	Objeto a ser serializado
	 * @return			Uma string no formato JSON ou XML conforme especificado no tipo
	 */
	public String Serializar(TipoDeDados tipo, IntegraSerializer objeto){
		return this.serializar_objeto(tipo, objeto);
	}	
	
	
	private String serializar_objeto(TipoDeDados tipo, IntegraSerializer objeto)
	{
		if (tipo == TipoDeDados.JSON)
		{
			Gson gson = new Gson();
			String json;
			json = gson.toJson(objeto.getDicionario());
			return json;
		}
		else if (tipo == TipoDeDados.XML)
		{
			XStream xstream= new XStream();
			String xml;
			xml = xstream.toXML(objeto.getDicionario());
			return xml;
		}
		return null;
	}	
	
	/**
	 * Método utilizado para enviar as informações de indicadores para o webservice.
	 * @param tipo						JSON ou XML, padrão JSON.
	 * @param url						URL do webservice de recebimento de indicadores.
	 * @param indicador					Conjunto de valores dos indicadores.
	 * @return							Texto com os dados retornados pelo webservice.
	 * @throws ValidationDataException	Exceção ocorre quando as informações enviados para o webservice contém dados não validos, tais como código IBGE do município não encontrado, código de identificação da família ocupacional na CBO2002 não encontrado, etc
	 * @throws IntegraException 		Exceção acontece se ocorrer alguma falha de comunicação com o webservice, tais como URL inexistente, etc.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public String EnviarDados(TipoDeDados tipo, String url, IndicadorGeral indicador) throws IntegraException, ValidationDataException{
		String dados = Serializar(tipo, indicador);
		return EnviarDados(tipo, url, dados);
	}
	
	/**
	 * Método utilizado para enviar os dados cadastrais da equipe de saúde para o webservice.
	 * @param tipo		JSON ou XML, padrão JSON.
	 * @param url		URL do webservice de recebimento de indicadores.
	 * @param equipe	Os dados cadastrais da(s) equipe(s).
	 * @return			Texto com os dados retornados pelo webservice.
	 * @throws ValidationDataException	Exceção ocorre quando as informações enviados para o webservice contém dados não validos
	 * @throws IntegraException 		Exceção acontece se ocorrer alguma falha de comunicação com o webservice, tais como URL inexistente, etc.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public String EnviarDados(TipoDeDados tipo, String url, EquipeSaude equipe) throws IntegraException, ValidationDataException{
		String dados = Serializar(tipo, equipe);
		return EnviarDados(tipo, url, dados);
	}

	/**
	 * Método utilizado para enviar dados no formato bruto (JSON ou XML) para o webservice.
	 * @param tipo	JSON ou XML, padrão JSON.
	 * @param url	URL do webservice de recebimento de indicadores.
	 * @param dados	Os dados formatodos em JSON OU XML
	 * @return		Contém os dados retornados pelo webservice.
	 * @throws ValidationDataException	Exceção ocorre quando as informações enviados para o webservice contém dados não valido
	 * @throws IntegraException 		Exceção acontece se ocorrer alguma falha de comunicação com o webservice, tais como URL inexistente, etc.
	 */
	public String EnviarDados(TipoDeDados tipo, String url, String dados) throws IntegraException, ValidationDataException
	{
		String sresponse = postJSON(url, dados);
		return sresponse;
	}
	
	/**
	 * Contém os dados retornados pelo webservice.
	 * @param tipo		JSON ou XML.
	 * @param indicador	Conjunto de valores dos indicadores.
	 * @return			Uma string no formato tipo
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public String Serializar(TipoDeDados tipo, IndicadorGeral indicador){
		return this.serializar_objeto(tipo, indicador, null);
	}
	
	/**
	 * Converte a(s) equipe(s) no formato tipo.
	 * @param tipo		JSON ou XML.
	 * @param equipe	Conjunto de valores das equipes.
	 * @return			Uma string no formato tipo.
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	public String Serializar(TipoDeDados tipo, EquipeSaude equipe){
		return this.serializar_objeto(tipo, null, equipe);
	}
	
	/**
	 * 
	 * @param tipo
	 * @param indicador
	 * @param equipe
	 * @return
	 * @deprecated  Usado somente na versão 1.x que foi baseada na nota técnica 005/2014.
	 */
	@Deprecated
	private String serializar_objeto(TipoDeDados tipo, IndicadorGeral indicador, EquipeSaude equipe)
	{
		if (tipo == TipoDeDados.JSON)
		{
			Gson gson = new Gson();
			String json;
			if (indicador !=null)
				json = gson.toJson(indicador.getDicionario());
			else
				json = gson.toJson(equipe.getDicionario());
			return json;
		}
		else if (tipo == TipoDeDados.XML)
		{
			XStream xstream= new XStream();
			String xml;
			if (indicador !=null)
				xml = xstream.toXML(indicador.getDicionario());
			else
				xml = xstream.toXML(equipe.getDicionario());
			return xml;
		}
		return null;
	}

	static void addDictionary (Map<String, Object> dicionario, String chave, String novoItem_chave, Object novoItem_valor) {
		Integra.addDictionary(false, dicionario, chave, novoItem_chave, novoItem_valor);
	}
	
	static void addDictionary (Boolean cria_nova_lista, Map<String, Object> dicionario, String chave, String novoItem_chave, Object novoItem_valor) {
		Map<String, Object> novo_dict;

		if (!dicionario.containsKey(chave))
			dicionario.put(chave, new ArrayList<Map<String, Object>>());
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>)dicionario.get(chave) ;

		if (cria_nova_lista)
			novo_dict = new HashMap<String, Object>();
		else
			novo_dict = list.get(list.size()-1);

		novo_dict.put(novoItem_chave, novoItem_valor);

		if (cria_nova_lista)
			list.add(novo_dict);	
	}	
	/**
	 * 
	 * @param chave			chave do dicionário
	 * @param dicionario	recebe um diconário onde cada item correspondente a uma chave representa uma lista (List<Map<String, Object>>)
	 * @param chaveItem		A chave do dicionário contido na lista 
	 * @param valorItem		O valor correspondente a chave chaveItem a ser localizado na lista
	 * @return				retorna o dicionário da lista correspondente a chaveItem, valorItem
	 */
	static Map<String, Object> getDictionaryInList(Map<String, Object> dicionario, String chave, String chaveItem, String valorItem) {
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>)dicionario.get(chave);
		for (Map<String, Object> map : list) {
			if (map.get(chaveItem).equals(valorItem))
				return map;
		}
		return null;
	}
	
	private  String getJSON(String url, int timeout) {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty ("Authorization", " Token " + this.token_autenticacao);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.connect();

			Scanner s;
			if (conn.getResponseCode() != 201) {
				s = new Scanner(conn.getErrorStream());
			} else {
				s = new Scanner(conn.getInputStream());
			}
			s.useDelimiter("\\Z");
			String response = s.next();		

			int status = conn.getResponseCode();

			s.close();

			return response;

		} catch (MalformedURLException ex) {
			System.out.println("MalformedURLException"+ex.getMessage() );
		} catch (IOException ex) {
			System.out.println("IOException"+ex.getMessage() );
		}
		return null;
	}

	private  String postJSON(String url, String json) throws IntegraException, ValidationDataException {
		HttpURLConnection conn = null;

		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			//c.setRequestProperty("Accept", "application/json");
			conn.setDoInput (true);
			conn.setDoOutput (true);
			conn.setRequestProperty ("Authorization", " Token " + this.token_autenticacao);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", "" + Integer.toString(json.getBytes().length));
			conn.setUseCaches(false);
			conn.connect();

			//setup send
			OutputStream os = new BufferedOutputStream(conn.getOutputStream());
			os.write(json.getBytes());
			//clean up
			os.flush();

			Scanner s;
			if (conn.getResponseCode() != 201) {
				s = new Scanner(conn.getErrorStream());
			} else {
				s = new Scanner(conn.getInputStream());
			}
			s.useDelimiter("\\Z");
			String response = s.next();		

			int status = conn.getResponseCode();

			s.close();
			os.close();

			if (conn.getResponseCode() != 201)
				throw new ValidationDataException(response);
			
			return response;

		} catch (MalformedURLException ex) {
			throw new IntegraException(ex.getMessage());
		} catch (IOException ex) {
			throw new IntegraException(ex.getMessage());
		} finally {
			conn.disconnect();
		}
	}
	

}

