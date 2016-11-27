/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celulao.gui.utils;

import javax.swing.text.JTextComponent;

/**
 *
 * @author SYSTEM
 */
public class ComponentBehavior {
    public static void completeAndEnable(JTextComponent component, String value){
        if(value.equals("")) completeAndEnable(component,value,true);
        else completeAndEnable(component,value,false);
    }
    public static void completeAndEnable(JTextComponent component, String value, Boolean enable){
        component.setEnabled(enable);
        component.setText(value);
    }
}
