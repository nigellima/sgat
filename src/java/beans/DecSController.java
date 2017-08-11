/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import jsf.util.CodigoDecS;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.primefaces.event.SelectEvent;
import org.xml.sax.InputSource;
import service.URLConnector;

/**
 *
 * @author nlima.huufma
 */
@ManagedBean(name = "decsController")
@ViewScoped
public class DecSController implements Serializable{
    String nomeDescritor;
    List<CodigoDecS> codigos;
    String codigo;
    CodigoDecS selectedCodigo;

    public CodigoDecS getSelectedCodigo() {
        return selectedCodigo;
    }

    public void setSelectedCodigo(CodigoDecS selectedCodigo) {
        this.selectedCodigo = selectedCodigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<CodigoDecS> getCodigos() {
        return codigos;
    }

    public void setCodigos(List<CodigoDecS> codigos) {
        this.codigos = codigos;
    }
    
    public String getNomeDescritor() {
        return nomeDescritor;
    }

    public void setNomeDescritor(String nomeDescritor) {
        this.nomeDescritor = nomeDescritor;
    }
    
    public void findDescritor()
    {
        System.out.println(nomeDescritor);
        URLConnector connector = new URLConnector("http://decs.bvsalud.org/cgi-bin/mx/cgi=@vmx/decs/?words="+nomeDescritor);
        try{
            connector.Connect();
            String xml  = connector.getPageContent();
            System.out.println(xml);
            codigos = findDecSinXML(xml);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
       
    }
    
    public List<CodigoDecS> findDecSinXML(String xml) throws JDOMException, IOException
    {
        //XMLUnit.setIgnoreWhitespace(true);
        List<CodigoDecS> cods = new ArrayList();
        Document document = new SAXBuilder().build(new InputSource(new StringReader(xml)));
        List<Element> elements = document.getRootElement().getChildren("decsws_response");
        for(Element el : elements)
        {
            String codigo = el.getAttributeValue("tree_id");
            String nome = el.getChild("tree").getChild("self").getChild("term_list").getChild("term").getValue();
            System.out.println(codigo + nome);
            cods.add(new CodigoDecS(codigo, nome));
        }
        return cods;
    }
    
    public CodigoDecS findDescritorByCod(String cod)
    {
        System.out.println(nomeDescritor);
        URLConnector connector = new URLConnector("http://decs.bvsalud.org/cgi-bin/mx/cgi=@vmx/decs/?tree_id="+cod);
        try{
            connector.Connect();
            String xml  = connector.getPageContent();
            //System.out.println(xml);
            codigos = findDecSinXML(xml);
            return codigos.get(0);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void onRowSelect(SelectEvent select)
    {
        this.codigo = ((CodigoDecS)select.getObject()).getCodigo();
    }
}
