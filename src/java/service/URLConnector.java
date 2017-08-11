package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

public class URLConnector {
    
    private static final String LOGIN = "01983676357";
    private static final String PASSWORD = "komeco22";
    
    private URLConnection urlConn;
    private URL myUrl;
    
    public URLConnector(String url){
        this.urlConn = null;
        try {
            this.myUrl = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public URLConnection Connect(){
        try {
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {          
                    return new PasswordAuthentication(LOGIN, PASSWORD.toCharArray());
                }
            });
            
            this.urlConn = this.myUrl.openConnection();
            this.urlConn.setDoInput( true );

            // stuff the Authorization request header
            byte[] encodedPassword = ( LOGIN + ":" + PASSWORD ).getBytes();
            BASE64Encoder encoder = new BASE64Encoder();
            this.urlConn.setRequestProperty( "Authorization", "Basic " + encoder.encode( encodedPassword ) );
            this.urlConn.connect();
            
        } catch (Exception ex) {
            Logger.getLogger(URLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.urlConn;
    }
    
    public String getPageContent() throws UnsupportedEncodingException {
        String outcome = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.Connect().getInputStream()))){
            String inputLine;
            
            while ((inputLine = br.readLine()) != null) {
                //System.out.println("xml line: " + inputLine);
                outcome += inputLine;
            }
        }
        catch (Exception ex){
            Logger.getLogger(URLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte ptext[] = outcome.getBytes(ISO_8859_1); 
        String value = new String(ptext, UTF_8); 
        return value;
    } 
    
}
