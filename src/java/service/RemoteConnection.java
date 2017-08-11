/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.faces.context.FacesContext;
import org.apache.commons.net.ftp.FTPClient;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author HUUFMA
 */
public class RemoteConnection {
    Session session;
    Channel channel = null;
    ChannelSftp sftpChannel = null;
    public RemoteConnection()
    {
        session = null;
    }
    
    public void connectToRemoteServer(UploadedFile file, String folder) throws SftpException, FileNotFoundException, IOException
    {
        JSch jsch = new JSch();
        Session session = null;
        try {
            
            session = jsch.getSession("root", "10.16.0.25", 22);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword("Nut1s2012");
            session.connect();

            this.channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) this.channel;
            //sftpChannel.cd("../opt/AtasNTS");
            sftpChannel.cd(folder);
            sftpChannel.put(file.getInputstream(), file.getFileName());
            
            
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection()
    {
        sftpChannel.exit();
        this.session.disconnect();
    }
    
    public InputStream getAtaFileFromServer(String fileName, String folder)
    {
        JSch jsch = new JSch();
       
        InputStream file = null;
        try {
            this.session = jsch.getSession("root", "10.16.0.25", 22);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            this.session.setConfig(config);
            this.session.setPassword("Nut1s2012");
            this.session.connect();

            channel = this.session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
            //sftpChannel.cd("../opt/AtasNTS");
            sftpChannel.cd(folder);
            
            file = sftpChannel.get(fileName);
            System.out.println(fileName + " " + file);
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        
        return file;
    }
    
}
