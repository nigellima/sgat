/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.PresencaKeys;
import java.io.File;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lucas
 */

public class Mailler {
        
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_AUTH_USER = "nutis@huufma.br";
    private static final String SMTP_AUTH_PWD  = "m2Wh=HPD";
    
    public static void send(String email, String filePath, File slides, PresencaKeys key, boolean isSendCert){  
        try {
            boolean debug = false;

            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(debug);

            Message msg = new MimeMessage(session);

            InternetAddress addressFrom = new InternetAddress("nutis@huufma.br");
//            msg.setFrom(addressFrom);
//            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
//            msg.setSubject("Teste");
//
//            msg.setContent("Teste de email..", "text/plain");

             // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(addressFrom);

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Set Subject: header field
            if(isSendCert)
                message.setSubject("Certificado - Nucleo de Telessaúde MA");
            else
                message.setSubject("Avaliação - Nucleo de Telessaúde MA");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            //messageBodyPart.setText("Por favor, não responda neste e-mail. Caso seja necessário entrar em contato com o Núcleo, utilize o email: telessaude.ma@huufma.br");
            
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            
            BodyPart surveyBodyPart = new MimeBodyPart();
            
            if(key != null)
            {
                surveyBodyPart.setContent("<center style=\"background-color:#E1E1E1;\">\n" +
                                            "	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\n" +
                                            "		<tr>\n" +
                                            "			<td align=\"center\" valign=\"top\" id=\"bodyCell\">\n" +
                                            "				<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailHeader\">\n" +
                                            "\n" +
                                            "					<!-- HEADER ROW // -->\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\">\n" +
                                            "							<!-- CENTERING TABLE // -->\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "								<tr>\n" +
                                            "									<td align=\"center\" valign=\"top\">\n" +
                                            "										<!-- FLEXIBLE CONTAINER // -->\n" +
                                            "										<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "\n" +
                                            "													<!-- CONTENT TABLE // -->\n" +
                                            "													<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"left\" valign=\"middle\" id=\"invisibleIntroduction\" class=\"flexibleContainerBox\" style=\"display:none !important; mso-hide:all;\">\n" +
                                            "																<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">\n" +
                                            "																	<tr>\n" +
                                            "																		<td align=\"left\" class=\"textContent\">\n" +
                                            "																			<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\n" +
                                            "																				\n" +
                                            "																			</div>\n" +
                                            "																		</td>\n" +
                                            "																	</tr>\n" +
                                            "																</table>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "										<!-- // FLEXIBLE CONTAINER -->\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "							<!-- // CENTERING TABLE -->\n" +
                                            "						</td>\n" +
                                            "					</tr>\n" +
                                            "					<!-- // END -->\n" +
                                            "\n" +
                                            "				</table>\n" +
                                            "				<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\">\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\n" +
                                            "								<tr>\n" +
                                            "									<td align=\"center\" valign=\"top\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" valign=\"top\" class=\"textContent\">\n" +
                                            "																<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">Núcleo de Telessaúde -HUUFMA</h1>\n" +
                                            "																<h2 style=\"text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#205478;line-height:135%;\">Confirmação de participação</h2>\n" +
                                            "																<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">Agradecemos a sua participação na atividade "+key.getPresenca().getAtividade().getTema()+". "+((isSendCert)? "Segue em anexo o seu certificado.":"")+"</div>\n" +
                                            "																</br>\n" +
                                            "																<div>\n" +
                                            "																	Por favor, não responda este e-mail. Caso necessite entrar em contato, utilize: telessaude.ma@huufma.br\n" +
                                            "																</div>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "						</td>\n" +
                                            "					</tr>\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\">\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\n" +
                                            "								<tr>\n" +
                                            "									<td align=\"center\" valign=\"top\">\n" +
                                            "										<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" valign=\"top\">\n" +
                                            "\n" +
                                            "																<!-- CONTENT TABLE // -->\n" +
                                            "																<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "																	<tr>\n" +
                                            "																		<td valign=\"top\" class=\"textContent\">\n" +
                                            "																			<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">Questionário de Avaliação</h3>\n" +
                                            "																			<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\n" +
                                            "																				A sua avaliação é importante para que possamos melhor planejar nossas ações e garantir um conteúdo de qualidade para você.\n" +
                                            "																			</div>\n" +
                                            "																		</td>\n" +
                                            "																	</tr>\n" +
                                            "																</table>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "						</td>\n" +
                                            "					</tr>\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\">\n" +
                                            "							<!-- CENTERING TABLE // -->\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "								<tr style=\"padding-top:0;\">\n" +
                                            "									<td align=\"center\" valign=\"top\">\n" +
                                            "										<!-- FLEXIBLE CONTAINER // -->\n" +
                                            "										<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td style=\"padding-top:0;\" align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color: #3498DB;\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\">\n" +
                                            "																<a style=\"color:#FFFFFF;text-decoration:none;font-family:Helvetica,Arial,sans-serif;font-size:20px;line-height:135%;\" href=\"http://presenca.telessaude.huufma.br/avaliacao/"+key.getKeyAuth() +"\" target=\"_blank\">Avalie</a>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "						</td>\n" +
                                            "					</tr>\n" +
                                            "					\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\">\n" +
                                            "							<!-- CENTERING TABLE // -->\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "								<tr>\n" +
                                            "									<td align=\"center\" valign=\"top\">\n" +
                                            "										<!-- FLEXIBLE CONTAINER // -->\n" +
                                            "										<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table class=\"flexibleContainerCellDivider\" border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" valign=\"top\" style=\"padding-top:0px;padding-bottom:0px;\">\n" +
                                            "\n" +
                                            "																<!-- CONTENT TABLE // -->\n" +
                                            "																<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "																	<tr>\n" +
                                            "																		<td align=\"center\" valign=\"top\" style=\"border-top:1px solid #C8C8C8;\"></td>\n" +
                                            "																	</tr>\n" +
                                            "																</table>\n" +
                                            "																<!-- // CONTENT TABLE -->\n" +
                                            "\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "										<!-- // FLEXIBLE CONTAINER -->\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "							<!-- // CENTERING TABLE -->\n" +
                                            "						</td>\n" +
                                            "					</tr>\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\">\n" +
                                            "							<!-- CENTERING TABLE // -->\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "								<tr>\n" +
                                            "									<td align=\"center\" valign=\"top\">\n" +
                                            "										<!-- FLEXIBLE CONTAINER // -->\n" +
                                            "										<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table class=\"flexibleContainerCellDivider\" border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" valign=\"top\" style=\"padding-top:0px;padding-bottom:0px;\">\n" +
                                            "\n" +
                                            "																<!-- CONTENT TABLE // -->\n" +
                                            "																<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "																	<tr>\n" +
                                            "																		<td align=\"center\" valign=\"top\" style=\"border-top:1px solid #C8C8C8;\"></td>\n" +
                                            "																	</tr>\n" +
                                            "																</table>\n" +
                                            "																<!-- // CONTENT TABLE -->\n" +
                                            "\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "										<!-- // FLEXIBLE CONTAINER -->\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "							<!-- // CENTERING TABLE -->\n" +
                                            "						</td>\n" +
                                            "					</tr>\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\" style=\"margin-bottom: 30px\">\n" +
                                            "							<!-- CENTERING TABLE // -->\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "								<tr>\n" +
                                            "									<td align=\"center\" valign=\"top\">\n" +
                                            "										<!-- FLEXIBLE CONTAINER // -->\n" +
                                            "										<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" valign=\"top\">\n" +
                                            "																<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "																	<tr>\n" +
                                            "																		<td valign=\"top\" class=\"textContent\">\n" +
                                            "																			<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:22px;margin-bottom:0;margin-top:3px;color:#5F5F5F;line-height:135%;\">\n" +
                                            "																					Contate-nos!\n" +
                                            "																			</div>\n" +
                                            "																		</td>\n" +
                                            "																	</tr>\n" +
                                            "																</table>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"left\" valign=\"top\" class=\"flexibleContainerBox\">\n" +
                                            "													<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"210\" style=\"max-width:100%;\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" class=\"textContent\">\n" +
                                            "																<a href=\"facebook.com/telessaudema\"><img src=\"https://www.facebook.com/images/fb_icon_325x325.png\" width=\"50\" class=\"flexibleImage\" style=\"max-width:100%;\" alt=\"Text\" title=\"Text\" /></a>\n" +
                                            "																<div style=\"padding: 5px;\">\n" +
                                            "																	Página do Facebook\n" +
                                            "																</div>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "												<td align=\"left\" valign=\"top\" class=\"flexibleContainerBox\">\n" +
                                            "													<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"210\" style=\"max-width:100%;\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" class=\"textContent\">\n" +
                                            "																<a href=\"https://www.youtube.com/channel/UC3nn1vapYSBvO2LpyqvM1Dg\"><img src=\"https://developers.google.com/youtube/images/YouTube-icon-full_color.png\" width=\"70\" class=\"flexibleImage\" style=\"max-width:100%;\" alt=\"Text\" title=\"Text\" /></a>\n" +
                                            "																<div style=\"padding: 5px;\">\n" +
                                            "																	Canal do Youtube\n" +
                                            "																</div>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "											<tr>\n" +
                                            "												<td  style=\"padding-top: 10px;\" align=\"left\" valign=\"top\" class=\"flexibleContainerBox\">\n" +
                                            "													<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"210\" style=\"max-width:100%;\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" class=\"textContent\">\n" +
                                            "																<a href=\"http://telessaude.huufma.br\"><img src=\"http://telessaude.huufma.br/site/images/logo.png\" width=\"75\" class=\"flexibleImage\" style=\"max-width:100%;\" alt=\"Text\" title=\"Text\" /></a>\n" +
                                            "																<div >\n" +
                                            "																	Web Site\n" +
                                            "																</div>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "												<td  style=\"padding-top: 10px;\" align=\"right\" valign=\"top\" class=\"flexibleContainerBox\">\n" +
                                            "													<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"210\" style=\"max-width:100%;\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" class=\"textContent\">\n" +
                                            "																<img src=\"http://icons.iconarchive.com/icons/dtafalonso/android-l/512/WhatsApp-icon.png\" width=\"50\" class=\"flexibleImage\" style=\"max-width:100%;\" alt=\"Text\" title=\"Text\" />\n" +
                                            "																<div >\n" +
                                            "																	(98) 99209-0503\n" +
                                            "																</div>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td align=\"center\" valign=\"top\">\n" +
                                            "																<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "																	<tr>\n" +
                                            "																		<td valign=\"top\" class=\"textContent\">\n" +
                                            "																			<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;margin-top:3px;color:#5F5F5F;line-height:135%;\">\n" +
                                            "																				\n" +
                                            "																			</div>\n" +
                                            "																		</td>\n" +
                                            "																	</tr>\n" +
                                            "																</table>\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "						</td>\n" +
                                            "						</br></br></br>\n" +
                                            "					</tr>\n" +
                                            "				</table>\n" +
                                            "				<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailFooter\">\n" +
                                            "					<tr>\n" +
                                            "						<td align=\"center\" valign=\"top\">\n" +
                                            "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "								<tr>\n" +
                                            "									<td align=\"center\" valign=\"top\">\n" +
                                            "										<!-- FLEXIBLE CONTAINER // -->\n" +
                                            "										<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                                            "											<tr>\n" +
                                            "												<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                                            "													<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                                            "														<tr>\n" +
                                            "															<td valign=\"top\" bgcolor=\"#E1E1E1\">\n" +
                                            "\n" +
                                            "																<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\n" +
                                            "																	<div>R. Barão de Itapari, 227 - Centro, São Luís - MA, 65020-070</div>\n" +
                                            "																</div>\n" +
                                            "\n" +
                                            "															</td>\n" +
                                            "														</tr>\n" +
                                            "													</table>\n" +
                                            "												</td>\n" +
                                            "											</tr>\n" +
                                            "										</table>\n" +
                                            "										<!-- // FLEXIBLE CONTAINER -->\n" +
                                            "									</td>\n" +
                                            "								</tr>\n" +
                                            "							</table>\n" +
                                            "							<!-- // CENTERING TABLE -->\n" +
                                            "						</td>\n" +
                                            "					</tr>\n" +
                                            "\n" +
                                            "				</table>\n" +
                                            "				<!-- // END -->\n" +
                                            "\n" +
                                            "			</td>\n" +
                                            "		</tr>\n" +
                                            "	</table>\n" +
                                            "</center>", "text/html; charset=utf8");
                
                
                //surveyBodyPart.setText("O Núcleo de Telessaúde do Hospital Universitário da Universidade Federal do Maranhão quer que você responda isso aqui " 
                  //      + "http://presenca.telessaude.huufma.br/avaliacao/"+key.getKeyAuth());
                multipart.addBodyPart(surveyBodyPart);
            }
            
            // Set text message part
            //multipart.addBodyPart(messageBodyPart);
            
            if(isSendCert)
            {
                // Part two is attachment. Add certificate
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName("CERTIFICADO.pdf");
                multipart.addBodyPart(messageBodyPart);
            }
            if(slides != null)
            {
                //Add slides presentation
                messageBodyPart = new MimeBodyPart();
                DataSource source2 = new FileDataSource(slides);
                messageBodyPart.setDataHandler(new DataHandler(source2));
                messageBodyPart.setFileName("APRESENTAÇÃO.pdf");
                multipart.addBodyPart(messageBodyPart);
            }
            

            // Send the complete message parts
            message.setContent(multipart);
            
            System.out.println("Sending email to ..." + email);
            Transport.send(message);
            System.out.println("Done. Email sent to " + email);

        } 
        catch (Exception e){
            System.out.println("Error sending email. " + e);
        }
    }
    /**
    * SimpleAuthenticator is used to do simple authentication
    * when the SMTP server requires it.
    */
    private static class SMTPAuthenticator extends javax.mail.Authenticator{
        @Override
        public PasswordAuthentication getPasswordAuthentication(){
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
    
  
}