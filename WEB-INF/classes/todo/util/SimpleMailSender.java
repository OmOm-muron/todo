
package todo.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author OmOm-muron
 * 
 * 簡易メール機能
 */
public class SimpleMailSender {
    /** SMTPサーバ接続ポート：25を指定 */
    private final String SMTP_PORT = "25";
    
    /** SMTPメールホスト */
    private static final String smtpHost = "127.0.0.1";
    
    private static final String AUTH_USER_NAME = "";
    private static final String AUTH_PASSWORD = "";
    
    public static void main(String argv[]) throws Exception {
        SimpleMailSender mail = new SimpleMailSender();
        
        mail.sendMessage("ToAddress@example.com", "FromAddress@example.com", "USERNAME", "件名", "メッセージ本文");
    }
    
    /**
     * メールを送信する
     * @param toAddr 送信先メールアドレス
     * @param fromAddr
     * @param personName
     * @param subject
     * @param message
     * @throws Exception
     */
    public void sendMessage(String toAddr, String fromAddr, String personName, String subject, String message) throws Exception {
        //メール送信プロパティの作成
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost); //SMTPサーバ名
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.host", smtpHost);
        props.put("mail.from", fromAddr);

        //SMTP認証設定
        props.put("mail.smtp.auth", "false");
        
        //SMTPソケットポート
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        
        //フォールバック
        props.put("mail.smtp.socketFactory.fallback", String.valueOf(false));
        
        //メールセッション確立
        //セッション確立設定はpropsに各層される
        Session session = Session.getDefaultInstance(props, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthenticatin()
            {
                return ( new PasswordAuthentication(AUTH_USER_NAME, AUTH_PASSWORD));
            }
        });
        session.setDebug(true);
        
        //送信メッセージを生成
        MimeMessage mimeMsg = new MimeMessage(session);
        
        //送信先
        mimeMsg.setRecipients(Message.RecipientType.TO, toAddr);
        //FROMヘッダ
        InternetAddress fromHeader = new InternetAddress(fromAddr, personName);
        mimeMsg.setFrom(fromHeader);
        //件名
        mimeMsg.setSubject(subject, "ISO-2022-JP");
        //送信時刻
        mimeMsg.setSentDate(new Date());
        //本文
        mimeMsg.setText(message, "ISO-2022-JP");
        
        //メールを送信する
        Transport.send(mimeMsg);
    }
}
