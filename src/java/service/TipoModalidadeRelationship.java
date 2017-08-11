package service;

import entities.Modalidade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HUUFMA
 */
public class TipoModalidadeRelationship {
    
    public static List<Integer> chooseModalidade(Modalidade m){
        if(m.getId()==1){
            return getVideoTipos();
        }
        if(m.getId()==2){
            return getWebTipos();
        }
        if(m.getId()==3){
            return getPresenceTipos();
        }
        return null;
    }
    
    public static List<Integer> getVideoTipos(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1); //Defesa de Mestrado
        ids.add(2); //Defesa de Doutorado
        ids.add(3); //Defesa de Monografia
        ids.add(4); //Qualificacao Mestrado
        ids.add(5); //Reunicao
        ids.add(6); //Aula
        ids.add(7); //Transmissao de Evento
        ids.add(8); //Transmissao de Cirurgia
        ids.add(13); //SIG
        ids.add(14); //Teste
        ids.add(16); //Oficina
        ids.add(17); //Treinamento
        ids.add(18); //Curso
        ids.add(19); //Reuniao
        ids.add(21); //Congresso
        ids.add(22); //Seminario
        ids.add(23); //Edicao de Video
        ids.add(24); //Mesa Redonda
        ids.add(25); //Apresentação
        ids.add(26); //Qualificação Doutorado
        return ids;
    }
    
    public static List<Integer> getWebTipos(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1); //Defesa de Mestrado
        ids.add(2); //Defesa de Doutorado
        ids.add(3); //Defesa de Monografia
        ids.add(4); //Qualificacao Mestrado
        ids.add(5); //Reunicao
        ids.add(6); //Aula
        ids.add(7); //Transmissao de Evento
        ids.add(8); //Transmissao de Cirurgia
        ids.add(13); //SIG
        ids.add(14); //Teste
        ids.add(16); //Oficina
        ids.add(17); //Treinamento
        ids.add(18); //Curso
        ids.add(19); //Reuniao
        ids.add(21); //Congresso
        ids.add(22); //Seminario
        ids.add(24); //Mesa Redonda
        ids.add(25); //Apresentação
        ids.add(26); //Qualificação Doutorado
        return ids;
    }
    
    public static List<Integer> getPresenceTipos(){
        List<Integer> ids = new ArrayList<>();
        
        ids.add(9); //Gravacao de Cirurgia
        ids.add(10); //Gravacao de Videoaula
        ids.add(11); //Gravacao de Audio
        ids.add(12); //Gravacao de Video
        ids.add(14); //Teste
        ids.add(15); //Visita Tecninca
        ids.add(16); //Oficina
        ids.add(17); //Treinamento
        ids.add(18); //Curso
        ids.add(19); //Reuniao - comite gestor
        ids.add(5); //Reuniao
        ids.add(21); //Congresso
        ids.add(22); //Seminario
        ids.add(23); //Edicao de Video
        ids.add(25); //Apresentação
        return ids;
    }
}
