package integra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa uma coleção de dados cadastrais de equipes de saúde.
 * @author Jailton Carlos
 *
 */
public class EquipeSaude {
	
	List<Map<String, Object>> list = new ArrayList <Map<String, Object>>();

	/**
	 * Adiciona os dados cadastrais de uma equipe de saúde.
	 * <p/>
	 * Observação: Pelo menos um dos dois (codigo ou codigo_ine) deve ser informado.
	 * 
	 * @param codigo_nucleo 		Código de identificação do Núcleo de Telessaúde. Consulta código no sistema INTEGRA. 
	 * @param codigo				Código de identificação de equipe de saúde utilizada pelo sistema de teleconsultoria (máximo 12 caracteres).
	 * @param codigo_ine			Código Identificador Nacional de Equipe (INE) (máximo 12 caracteres).
	 * @param nome					Nome da equipe de saúde (máximo 60 caracteres).
	 * @param cnes_estabelecimento	Código Cadastro Nacional de Estabelecimento de Saúde (CNES).
	 * @param codigo_tipo_equipe	Código do tipo de equipe. Consulta código no sistema.
	 */
    public void add (String codigo_nucleo, String codigo, String codigo_ine, String nome, String cnes_estabelecimento, String codigo_tipo_equipe) {
    	Map<String, Object> dicionario = new HashMap<String, Object>();
		
    	if (!Utils.isBlankOrNull(codigo))
    		dicionario.put("codigo_equipe", codigo);    	
    	if (!Utils.isBlankOrNull(codigo_ine))
    		dicionario.put("codigo_equipe_ine", codigo_ine);
    	if (!Utils.isBlankOrNull(codigo_tipo_equipe))
    		dicionario.put("codigo_tipo_equipe", codigo_tipo_equipe);	
    	
    	dicionario.put("codigo_nucleo", codigo_nucleo);
		dicionario.put("nome", nome);
		dicionario.put("cnes_estabelecimento", cnes_estabelecimento);		
			
		list.add(dicionario);
    }
    List<Map<String, Object>> getDicionario()
	{
		return this.list;
	}
 
  
}
