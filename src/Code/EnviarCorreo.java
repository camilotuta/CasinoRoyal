// cSpell:ignore optyxhhzjrbgnikz bwaj ehrc hgly starttls zjbc 
package Code;

import Code.env.EnvArchivo;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

public class EnviarCorreo {

    private static final String EMAIL_FROM = EnvArchivo.dotenv.get("EMAIL_FROM");;
    private static final String PASSWORD_FROM = EnvArchivo.dotenv.get("PASSWORD_FROM");;
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public EnviarCorreo(String correoEnviar, String asunto, String mensaje) {
        createEmail(correoEnviar, asunto, mensaje);
        sendEmail(correoEnviar);
    }

    private void createEmail(String correoEnviar, String asunto, String mensaje) {
        mProperties = new Properties();
        emailTo = correoEnviar;
        subject = asunto;
        content = mensaje;

        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", EMAIL_FROM);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(EMAIL_FROM));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "UTF-8", "html");

        } catch (AddressException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendEmail(String emailTo) {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(EMAIL_FROM, PASSWORD_FROM);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            JOptionPane.showMessageDialog(
                    null, "EL CÓDIGO HA SIDO ENVIADO A " + emailTo.toUpperCase()
                            + ".\n\nPor favor revise su carpeta de spam o correos no deseados y\nasegúrese de que el correo esté correctamente escrito.");
        } catch (NoSuchProviderException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}