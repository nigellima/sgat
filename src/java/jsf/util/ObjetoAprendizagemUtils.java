/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import service.URLConnector;

/**
 *
 * @author nigel
 */
public class ObjetoAprendizagemUtils {
    
    
    public static int findVideoViewCount(String url)
    {
        System.out.println(url);
        URLConnector connector = new URLConnector(url);
        try{
            connector.Connect();
            String html  = connector.getPageContent();
            //System.out.println(html);
            return findTagCount(html);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        return 0;
    }
    
    private static int findTagCount(String html) throws JDOMException, IOException
    {
        org.jsoup.nodes.Document doc =  Jsoup.parse(html);
        Elements elements = (Elements) doc.select("div[class=watch-view-count]");
        String value = "";
        value = elements.html().replaceAll("\\D+","");
        return Integer.parseInt(value);
    }
}
