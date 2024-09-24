/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Contador;
import Vista.frmRegistroContador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiante
 */
public class ctrlRegistroContador implements MouseListener, KeyListener {
    
    private Contador modelo;
    private frmRegistroContador vista;
    
    
    public ctrlRegistroContador(Contador modelo, frmRegistroContador vista ) {
        
        this.vista = vista;
        this.modelo = modelo;
        
        vista.btnActualizar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnIngresar.addMouseListener(this);
        vista.jtContador.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        
        modelo.Mostrar(vista.jtContador);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.jtContador) {
            
            modelo.cargarDatosTabla(vista);
            
        }
        
       if (e.getSource() == vista.btnIngresar) {
    modelo.setNombreContador(vista.txtNombre.getText());
    modelo.setEdadContador(Integer.parseInt(vista.txtEdad.getText()));
    modelo.setCorreoContador(vista.txtCorreo.getText());
    modelo.setPesoContador(Double.parseDouble(vista.txtPeso.getText()));
    
    modelo.Guardar();
    
    JOptionPane.showMessageDialog(vista, "Registro guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
    limpiarCampos();
    
    modelo.Mostrar(vista.jtContador);

}
       
       if(e.getSource() == vista.btnEliminar) {
           
           modelo.Eliminar(vista.jtContador);
           JOptionPane.showMessageDialog(vista, "Registro eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
               modelo.Mostrar(vista.jtContador);

           
       }
       
       if(e.getSource() == vista.btnActualizar) {
              modelo.setNombreContador(vista.txtNombre.getText());
    modelo.setEdadContador(Integer.parseInt(vista.txtEdad.getText()));
    modelo.setCorreoContador(vista.txtCorreo.getText());
    modelo.setPesoContador(Double.parseDouble(vista.txtPeso.getText()));
    
    modelo.Actualizar(vista.jtContador);
    
        limpiarCampos();
        
            modelo.Mostrar(vista.jtContador);

        
                   JOptionPane.showMessageDialog(vista, "Registro actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);


       }

    }
    
    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vista.txtBuscar) {
           modelo.Buscar(vista.jtContador, vista.txtBuscar);
        }
    }
    
}
