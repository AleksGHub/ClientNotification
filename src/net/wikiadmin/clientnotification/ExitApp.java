/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wikiadmin.clientnotification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * App for EXIT
 * @author aleksdem
 */
public class ExitApp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        System.exit(0);
        }
}