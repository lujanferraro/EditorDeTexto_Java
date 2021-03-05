/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editordetexto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledEditorKit;



/**
 *
 * @author LujanFerraro
 */
public class Lamina1 extends JPanel{
    
   private final JMenuBar barra; 
   private final JTextPane areaTexto;
   JMenuItem copiar, pegar, cortar;


    public Lamina1() {
        
       
        setLayout(new BorderLayout());
        
        barra = new JMenuBar();
        add(barra, BorderLayout.NORTH);
        
       //Elementos de la barra 
       JMenu archivo = new JMenu("Archivo");
       JMenu editar = new JMenu("Editar");
       JMenu opciones = new JMenu("Opciones");
       JMenu ayuda = new JMenu("Ayuda");
       
       barra.add(archivo);
       barra.add(editar);
       barra.add(opciones);
       barra.add(ayuda);
       
       
       //Elementos de archivo
       
       JMenuItem abrir = new JMenuItem("Abrir", new ImageIcon(getClass().getResource("/resources/open.gif")));
       JMenuItem guardar = new JMenuItem("Guardar", new ImageIcon(getClass().getResource("/resources/save.gif")));
       JMenuItem salir = new JMenuItem("Salir");
     
       archivo.add(abrir);
       archivo.add(guardar);
       JSeparator separar= new JSeparator();
       archivo.add(separar);
       archivo.add(salir);
       
       //Eventos de archivos
       salir.addActionListener(new Evento_salir());
       abrir.addActionListener(new Evento_abrir()); 
       guardar.addActionListener(new Evento_guardar());   
        
       
       //Elementos de editar
        copiar = new JMenuItem("Copiar", new ImageIcon(getClass().getResource("/resources/copy.gif")));
        pegar = new JMenuItem("Pegar", new ImageIcon(getClass().getResource("/resources/paste.gif")));
        cortar = new JMenuItem("Cortar", new ImageIcon(getClass().getResource("/resources/cut.gif")));
       editar.add(copiar);
       editar.add(pegar);
       editar.add(cortar);
       
       //Eventos de editar
       copiar.addActionListener(new Evento_editar());
       pegar.addActionListener(new Evento_editar());
       cortar.addActionListener(new Evento_editar());
       
       //Elementos de opciones
       
       JMenu estilo= new JMenu("Estilo");
       opciones.add(estilo);
       
       //Elementos de estilo
       
        JCheckBoxMenuItem negrita = new  JCheckBoxMenuItem("Negrita");
        JCheckBoxMenuItem cursiva = new  JCheckBoxMenuItem("cursiva");
        JCheckBoxMenuItem subrayado = new  JCheckBoxMenuItem("Subrayado");
        estilo.add(negrita);
        estilo.add(cursiva);
        estilo.add(subrayado);
        
        negrita.addActionListener(new StyledEditorKit.BoldAction());
        cursiva.addActionListener(new StyledEditorKit.ItalicAction());
        subrayado.addActionListener(new StyledEditorKit.UnderlineAction());

        
        
        //Elementos de ayuda
        JMenuItem acerca = new JMenuItem("Acerca de");
        ayuda.add(acerca);
       
        //Evento ayuda
        acerca.addActionListener(new Evento_ayuda());
       
        //Agrego area de texto
       areaTexto= new JTextPane();
       add(areaTexto, BorderLayout.CENTER);
       
       
       //Barra de abajo
	JPanel statusPanel = new JPanel();
        //crea las etiquetas para la barra de estado
	JLabel status1 = new JLabel("Editor 2021");
	statusPanel.add(status1, BorderLayout.EAST);
	add(statusPanel,BorderLayout.SOUTH);
        
        
        //Barra de herramientas
        JToolBar barr= new JToolBar();
        JButton izquierda= new JButton(new ImageIcon(getClass().getResource("/resources/left-text-alignment-option.gif")));
        JButton centrado= new JButton(new ImageIcon(getClass().getResource("/resources/centered-text-of-alignment-button.gif")));
        JButton derecha= new JButton(new ImageIcon(getClass().getResource("/resources/right-text-alignment.gif")));
        JButton justificado= new JButton(new ImageIcon(getClass().getResource("/resources/align-justify.gif")));
        JButton negritaBarra= new JButton(new ImageIcon(getClass().getResource("/resources/bold.gif")));
        JButton cursivaBarra= new JButton(new ImageIcon(getClass().getResource("/resources/italics.gif")));
        JButton subrayadoBarra= new JButton(new ImageIcon(getClass().getResource("/resources/underline.gif")));
       
        barr.add(negritaBarra);
        barr.add(cursivaBarra);
        barr.add(subrayadoBarra);
        barr.add(izquierda);
        barr.add(centrado);
        barr.add(derecha);
        barr.add(justificado);
        
        add(barr,BorderLayout.WEST);
        barr.setOrientation(1); //modificamos orientacion de la barra
        
        
        //Evento iconos de barra de herramientas
        negritaBarra.addActionListener(new StyledEditorKit.BoldAction());
        cursivaBarra.addActionListener(new StyledEditorKit.ItalicAction());
        subrayadoBarra.addActionListener(new StyledEditorKit.UnderlineAction());
        izquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda", 0));
        centrado.addActionListener(new StyledEditorKit.AlignmentAction("Centrado", 1));
        derecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha", 2));
        justificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado", 3));
        
        
        //Tooltip
        
        izquierda.setToolTipText("Alinear a la izquierda");
        derecha.setToolTipText("Alinear a la derecha");
        centrado.setToolTipText("Centrado");
        justificado.setToolTipText("Justificado");
        negrita.setToolTipText("Negrita");
        cursiva.setToolTipText("Cursiva");
        subrayado.setToolTipText("Subrayado");
        
        
    }
    
          
    public class Evento_salir implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            System.exit(0);
            
        }
        
        
        
    }
        
    public class Evento_abrir implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
                 JFileChooser fileChooser = new JFileChooser();
                 FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
                 fileChooser.setFileFilter(filtro);
                 int seleccion = fileChooser.showOpenDialog(areaTexto);
   

                 if(seleccion==JFileChooser.APPROVE_OPTION){

                       File fichero=fileChooser.getSelectedFile();
                       areaTexto.setText(fichero.getAbsolutePath());

                       try(FileReader fr=new FileReader(fichero)){
                           String cadena="";
                           int valor=fr.read();
                           while(valor!=-1){
                               cadena=cadena+(char)valor;
                               valor=fr.read();
                           }
                           areaTexto.setText(cadena);
                           fr.close();
                       } catch (IOException e1) {
                           e1.printStackTrace();
                       }
                   }
                
                
           
            
        }
    }  
        
    public class Evento_guardar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {

                     
            JFileChooser fc=new JFileChooser();
            int seleccion=fc.showSaveDialog(areaTexto);

            
            if(seleccion==JFileChooser.APPROVE_OPTION){

                File fichero=fc.getSelectedFile();

                try(FileWriter fw=new FileWriter(fichero)){
                    fw.write(areaTexto.getText());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
            
            
            
        }
    }
     
    public class Evento_ayuda implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            JOptionPane.showMessageDialog(null, "Lujan Ferraro 2021", "Acerca de", 3);
        }
        
        
    }
    
    public class Evento_editar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           
			
			if (e.getSource() == copiar)
				areaTexto.copy();
			
			if (e.getSource() == cortar)
				areaTexto.cut();
			
			if (e.getSource() == pegar)
				areaTexto.paste();
              
        }


    }
        
        
  

 
}


    
    
    

        
        
    
 
        
    
    
    
    
    

