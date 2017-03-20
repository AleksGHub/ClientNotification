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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;

/**
 * GUI для программы рассылки
 * @author aleksdem
 */
public class cnGui extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    public static String setText;

    static void setText(String connected) {
        setText = ("Соединение с сетью: ОК (автопроверка)");
    }


    public cnGui() {
        initComponents();
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jFileMenu1 = new javax.swing.JMenu();
        jHelpMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);
        setVisible(true);

        jButton1.setText("Загрузить список клиентов");
        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1ActionPerformed(evt);
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 10, 240, 25);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(780, 77));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Список клиентов не загружен...");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 110, 760, 420);

        jButton2.setText("Проверить соединение");

        getContentPane().add(jButton2);
        jButton2.setBounds(20, 40, 240, 25);
        jButton2.setEnabled(false);

        jLabel1.setText("Список клиентов не загружен");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 15, 510, 20);

        jLabel3.setText(setText);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(280, 45, 510, 20);

        jButton3.setText("Начать рассылку");
        jButton3.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton3ActionPerformed(evt);
        });

        getContentPane().add(jButton3);
        jButton3.setBounds(20, 70, 240, 25);
        jButton3.setEnabled(false);

        jLabel4.setText("2017 © Алексей Убоженко, wikiadmin.net");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 535, 540, 10);

        jFileMenu1.setText("Файл");
        jMenuBar1.add(jFileMenu1);

        JMenuItem exitMenu = new JMenuItem("Выход");
        jFileMenu1.add(exitMenu);
        exitMenu.addActionListener(new ExitApp());

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FileToArray test = new FileToArray();
                mails = test.newList.size()-1;
                jLabel1.setText("Найдено строк: "+mails);
                jTextArea1.append("\nНайдено строк (с пустыми): "+mails+"\nПримерное время отправки (секунд): "+mails*2);
                jButton3.setEnabled(true);
            } catch (IOException ex) {
            Logger.getLogger(ClientNotification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        jTextArea1.append("\nНачало отправки: "+dateFormat.format(new Date()));
        jTextArea1.append("\nПожалуйста, подождите...\n");
        java.awt.EventQueue.invokeLater(() -> {
            try {
                if(mails == 0){
                    jTextArea1.append("\nНет данных, загрузите список контактов.");
                }

                FileToArray test2 = new FileToArray();

                for(int i = 0; i<mails-1;i++){
                    String fullLine = test2.newList.get(i);
                    mails = test2.newList.size()-1;
                    String[] partsLine = fullLine.split(":");

                    clientMail = partsLine[0]; // тут из файла получаем адрес почты
                    clientName = partsLine[1]; // тут получаем имя
                    clientCredit = partsLine[2]; // а вот тут уже сумма задолженности
                    SendMain sendm = new SendMain();
                    sendm.sendMainText(clientMail, clientName, clientCredit);
                            jTextArea1.append("\nОтправлено: "+clientMail);
                    TimeUnit.SECONDS.sleep(2);
                }

                jTextArea1.append("\n\nОтправка завершена: "+dateFormat.format(new Date()));
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ClientNotification.class.getName()).log(Level.SEVERE, null, ex);
                jTextArea1.append("\nОтправка прервана");
            }
        });

    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    private int mails;
    private String clientMail;
    public String clientName;
    public String clientCredit;
    public javax.swing.JMenu jFileMenu1;
    public javax.swing.JMenu jHelpMenu2;
}
