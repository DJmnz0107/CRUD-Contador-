/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import Vista.frmRegistroContador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiante
 */
public class Contador {

    /**
     * @return the idContador
     */
    public int getIdContador() {
        return idContador;
    }

    /**
     * @param idContador the idContador to set
     */
    public void setIdContador(int idContador) {
        this.idContador = idContador;
    }

    /**
     * @return the nombreContador
     */
    public String getNombreContador() {
        return nombreContador;
    }

    /**
     * @param nombreContador the nombreContador to set
     */
    public void setNombreContador(String nombreContador) {
        this.nombreContador = nombreContador;
    }

    /**
     * @return the edadContador
     */
    public int getEdadContador() {
        return edadContador;
    }

    /**
     * @param edadContador the edadContador to set
     */
    public void setEdadContador(int edadContador) {
        this.edadContador = edadContador;
    }

    /**
     * @return the pesoContador
     */
    public Double getPesoContador() {
        return pesoContador;
    }

    /**
     * @param pesoContador the pesoContador to set
     */
    public void setPesoContador(Double pesoContador) {
        this.pesoContador = pesoContador;
    }

    /**
     * @return the correoContador
     */
    public String getCorreoContador() {
        return correoContador;
    }

    /**
     * @param correoContador the correoContador to set
     */
    public void setCorreoContador(String correoContador) {
        this.correoContador = correoContador;
    }
    
    private int idContador;
    private String nombreContador;
    private int edadContador;
    private Double pesoContador;
    private String correoContador;
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addContador = conexion.prepareStatement("INSERT INTO tbContador(nombre_contador, edad_contador, peso_contador, correo_contador) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addContador.setString(1, getNombreContador());
            addContador.setInt(2, getEdadContador());
            addContador.setDouble(3, getPesoContador());
            addContador.setString(4, getCorreoContador());
            //Ejecutar la consulta
            addContador.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
 
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"idContador", "Nombre del contador", "Edad del contador", "Peso del contador", "Correo del contador"});
 
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
 
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbContador");
 
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getInt("id_contador"),
                    rs.getString("nombre_contador"),
                    rs.getInt("edad_contador"),
                    rs.getDouble("peso_contador"),
                rs.getString("correo_contador")});
            }
 
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbContador where id_contador = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
      public void cargarDatosTabla(frmRegistroContador vista) {
    int filaSeleccionada = vista.jtContador.getSelectedRow();
    
    if (filaSeleccionada != -1) {
        int idContador = (Integer) vista.jtContador.getValueAt(filaSeleccionada, 0);
        String nombre = vista.jtContador.getValueAt(filaSeleccionada, 1).toString();
        int edad = (Integer) vista.jtContador.getValueAt(filaSeleccionada, 2);
        Double peso = (Double) vista.jtContador.getValueAt(filaSeleccionada, 3);
        String correo = vista.jtContador.getValueAt(filaSeleccionada, 4).toString();

        vista.txtNombre.setText(nombre);
        vista.txtEdad.setText(String.valueOf(edad));
        vista.txtPeso.setText(String.valueOf(peso));
        vista.txtCorreo.setText(correo);
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
}
      
      public void Actualizar(JTable tabla) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = ClaseConexion.getConexion();
    // Obtenemos qué fila seleccionó el usuario
    int filaSeleccionada = tabla.getSelectedRow();
    
    if (filaSeleccionada != -1) {
        // Obtenemos el id de la fila seleccionada
        int idContador = (Integer) tabla.getValueAt(filaSeleccionada, 0);
        
        try {
            // Ejecutamos la Query
            PreparedStatement updateContador = conexion.prepareStatement(
                "UPDATE tbContador SET nombre_contador = ?, edad_contador = ?, peso_contador = ?, correo_contador = ? WHERE id_contador = ?"
            );
            updateContador.setString(1, getNombreContador()); // Método para obtener el nombre
            updateContador.setInt(2, getEdadContador()); // Método para obtener la edad
            updateContador.setDouble(3, getPesoContador()); // Método para obtener el peso
            updateContador.setString(4, getCorreoContador()); // Método para obtener el correo
            updateContador.setInt(5, idContador); // ID del contador a actualizar
            
            updateContador.executeUpdate();
            System.out.println("Actualización exitosa.");
        } catch (Exception e) {
            System.out.println("Este es el error en el método de actualizar: " + e);
        }
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
}
      
      public void Buscar(JTable tabla, JTextField txtBuscar) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = ClaseConexion.getConexion();
    // Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"id_contador", "Nombre del contador", "Edad del contador", "Peso del contador", "Correo del contador"});
    
    try {
        // Preparamos la consulta para buscar por nombre
        PreparedStatement buscarContador = conexion.prepareStatement("SELECT * FROM tbContador WHERE nombre_contador LIKE ?");
        buscarContador.setString(1, txtBuscar.getText() + "%");
        ResultSet rs = buscarContador.executeQuery();

        // Recorremos el ResultSet y llenamos el modelo
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_contador"),
                rs.getString("nombre_contador"),
                rs.getInt("edad_contador"),
                rs.getDouble("peso_contador"),
                rs.getString("correo_contador")
            });
        }
        
        // Asignamos el nuevo modelo lleno a la tabla
        tabla.setModel(modelo);

    } catch (Exception e) {
        System.out.println("Este es el error en el método de buscar: " + e);
    }
}



    
}
