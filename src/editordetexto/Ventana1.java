/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editordetexto;

import javax.swing.JFrame;

/**
 *
 * @author LujanFerraro
 */
public class Ventana1 extends JFrame{

    public Ventana1() {
        
        setSize(600, 500); //tamaño ventana
           
        setTitle("Editor de texto"); //titulo ventana
        setLocationRelativeTo(null); //centramos ventana
    
        Lamina1 menu = new Lamina1();
        add(menu);
           
        setVisible(true);
                               
        }
       
                               
    }


    
    
    
    
    
