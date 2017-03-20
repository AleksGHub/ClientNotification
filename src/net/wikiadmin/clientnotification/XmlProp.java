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

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * class for read settings from xml-file.
 * @author aleksdem
 */

class XmlProp {

	private final String fileP;
        private String userSmtp;
        private String passwordSmtp;
        private String hostSmtp;
        private String portSmtp;
        private String sslSmtp;
        private String fromMail;
        private String toMail;
        private String messageTheme;
        private String messageHelloBody;
        private String messageBody2;
        private String messageBody3;
        private String messageContacts;
        private String mBody;

	XmlProp(String fileprep){
			fileP = fileprep;
		}

	void readData() {

            try {
                File fxmlFile = new File(fileP);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fxmlFile);
                doc.getDocumentElement().normalize();

        	NodeList nList = doc.getElementsByTagName("mail");

                for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
                        Element eElement = (Element) nNode;
                        userSmtp = eElement.getElementsByTagName("userSmtp").item(0).getTextContent();
                        passwordSmtp = eElement.getElementsByTagName("passwordSmtp").item(0).getTextContent();
                        hostSmtp = eElement.getElementsByTagName("hostSmtp").item(0).getTextContent();
                        portSmtp = eElement.getElementsByTagName("portSmtp").item(0).getTextContent();
                        sslSmtp = eElement.getElementsByTagName("SSLSmtp").item(0).getTextContent();
                        fromMail = eElement.getElementsByTagName("fromMail").item(0).getTextContent();
                        toMail = eElement.getElementsByTagName("toMail").item(0).getTextContent();
                        messageTheme = eElement.getElementsByTagName("messageTheme").item(0).getTextContent();
                        messageHelloBody = eElement.getElementsByTagName("messageHelloBody").item(0).getTextContent();
                        messageBody2 = eElement.getElementsByTagName("messageBody2").item(0).getTextContent();
                        messageBody3 = eElement.getElementsByTagName("messageBody3").item(0).getTextContent();
                        messageContacts = eElement.getElementsByTagName("messageContacts").item(0).getTextContent();
                    }
                } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
                System.out.println("i need mailsettings.xml");
        }
    }

    String getUser() {
           return userSmtp;
        }

    String getpass(){
        return passwordSmtp;
        }

    String gethost(){
        return hostSmtp;
        }

    String getport(){
        return portSmtp;
        }

    String getssl(){
        return sslSmtp;
        }

    String getfrom(){
        return fromMail;
        }

    String getto(){
        return toMail;
        }

    String getTheme(){
        return messageTheme;
        }

    String getBody(){
        return messageHelloBody;
        }

    String getBodyP2(){
        return messageBody2;
        }

    String getBodyP3(){
        return messageBody3;
        }

    String getBodyContacts(){
        return messageContacts;
        }
}