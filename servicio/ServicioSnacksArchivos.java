package maquina_snacks_archivos.servicio;

import maquina_snacks_archivos.dominio.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivos implements IServicioSnacks{
    private final String NOMBRE_ARCHIVO = "snacks.txt";
    //Crear la lista de snacks

    private List<Snack> snacks = new ArrayList<>();

    //Constructor clase

    public ServicioSnacksArchivos(){
        //Creamos el archivo si no existe
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try{
            existe = archivo.exists();
            if (existe){
                this.snacks = obtenerSnacks();
            }
            else{
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo!");
            }
        }catch (Exception e){
            System.out.println("Error al crear el archivo" + e.getMessage());
        }

        if(!existe){
            cargarSnacksIniciales();
        }
    }

    private void cargarSnacksIniciales(){
        this.agregarSnack(new Snack("Papas", 70));
        this.agregarSnack(new Snack("Palitos", 50));
        this.agregarSnack(new Snack("Chizitos", 60));

    }

    private List<Snack> obtenerSnacks(){
        var snacks = new ArrayList<Snack>();
        try{
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for(String linea : lineas){
                String[] lineaSnack = linea.split(",");//parseo separado por coma
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre, precio);
                snacks.add(snack);

            }

        }catch(Exception e){
            System.out.println("Error al leer archivo de snacks" + e.getMessage());
            e.printStackTrace();
        }
        return snacks;

    }

    @Override
    public void agregarSnack(Snack snack) {
        //Agregamos el nuevo snack, 1. a la lista en memoria
        this.snacks.add(snack);
        //2. Guardamos el nuevo snack en el archivo
        this.agregarSnackArchivo(snack);
    }

    private void agregarSnackArchivo(Snack snack){
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();
        }catch (Exception e){
            System.out.println("Error al agregar el archivo" + e.getMessage());
        }
    }

    @Override
    public void mostrarSnacks() {
        System.out.println("--- Snacks en el Inventario ---");
        var invetarioSnacks = "";
        for(var snack : this.snacks){
            invetarioSnacks += snack.toString() + "\n";
        }
        System.out.println(invetarioSnacks);

    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
