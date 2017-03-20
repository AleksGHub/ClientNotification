/*
 * The MIT License
 *
 * Copyright 2017 aleksdem.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.wikiadmin.clientnotification;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * class for send mail.
 * @author aleksdem
 */
public class SendMain {

    private String userS;
    private String passS;
    private String hostS;
    private String portS;
    private String sslS;
    private String fromS;
    private String toS;
    private int intPortS;
    private boolean sslBoolean;
    private String themeText;
    private String bodyText;

    /** sendMainText. */
    void sendMainText(String cMail, String cName, String cCredit){

        /* i need your data!!! :D */
        XmlProp readData = new XmlProp("mailsettings.xml");
            readData.readData();

                userS = readData.getUser();
                passS = readData.getpass();
                hostS = readData.gethost();
                portS = readData.getport();
                sslS = readData.getssl();
                fromS = readData.getfrom();
                toS = cMail;
                intPortS = Integer.parseInt(portS);
                sslBoolean = Boolean.parseBoolean(sslS);
                themeText = readData.getTheme();
                bodyText = readData.getBody()+cName+".\n"+
                        readData.getBodyP2()+cCredit+readData.getBodyP3()+
                        "\n"+readData.getBodyContacts();

        try {
            Email email = new SimpleEmail();
                email.setHostName(hostS);
                email.setAuthenticator(new DefaultAuthenticator(userS, passS));
                email.setSSLOnConnect(sslBoolean);
                email.setSmtpPort(intPortS);
                email.setFrom(fromS);
                email.setSubject(themeText);
                email.setMsg(bodyText);
                email.addTo(toS);
                email.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
            System.out.println("ошибка");
        }
    }
}
