/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_sistemaalquilervehiculos;

import java.util.Scanner;

/**
 *
 * @author cmu08_12mttuz
 */
public class Proyecto_SistemaAlquilerVehiculos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // INICIO DEL PROYECTO
        Scanner input = new Scanner(System.in);

        //Declaracion de variables
        String[] cliente = new String[10];
        int[] edad = new int[10];
        String[] identidad = new String[10];
        String[] licencia = new String[10];
        int respuestaTipoVehiculo = 0;
        String respuestaRegistro = "";
        int eleccion = 0;
        int cantidadClientes = 0;
        int capacidadMaxClientes = 10;        

        do {

            //MENU PRINCIPAL        
            System.out.println("\nBIENVENIDOS AL SISTEMA DE ALQUILER DE VEHICULOS");
            System.out.println("-----------------------------------------------");
            System.out.println();
            System.out.println("""
                           Que gestion desea realizar?
                           1. Registrar Cliente
                           2. Consultar Cliente
                           3. Registrar Alquiler
                           4. Registrar Devolucion
                           5. Consulta de Vehiculos
                           6. Mostrar resumen general
                           7. Salir
                           """);

            System.out.print("Eleccion: ");
            eleccion = input.nextInt();
            input.nextLine();
            System.out.println();

            switch (eleccion) {
                case 1:
                    //SECCION REGISTRO DE CLIENTE

                    cantidadClientes = registrarCliente(input, cliente, edad, identidad, licencia, cantidadClientes, capacidadMaxClientes);

                    break;
                case 2:
                    //SECCION CONSULTA DE CLIENTE

                    consultarCliente(input, cliente, edad, identidad, licencia, cantidadClientes);

                    break;
                case 3:
                    //SECCION DE ALQUILER DE VEHICULO
                    System.out.println("""
                           Seleccione el tipo de vehiculo que desea alquilar
                           1. Economico
                           2. SUV
                           3. Pickup
                           """);
                    System.out.print("Eleccion: ");
                    respuestaTipoVehiculo = input.nextInt();
                    System.out.println();

                    switch (respuestaTipoVehiculo) {
                        case 1:
                            System.out.println("== Tipo de vehiculo seleccionado: ECONOMICO ==");

                            break;
                        case 2:
                            System.out.println("== Tipo de vehiculo seleccionado: SUV ==");

                            break;
                        case 3:
                            System.out.println("== Tipo de vehiculo seleccionado: Pickup ==");

                            break;
                        default:
                    }//Fin Switch

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:

            }//Fin Switch 

        } while (eleccion != 7);

    }//Fin Main
    
    public static int registrarCliente(
        Scanner input,
        String[] cliente,
        int[] edad,
        String[] identidad,
        String[] licencia,
        int cantidadClientes,
        int capacidadMaxClientes) {

    String respuestaRegistro = "";

    System.out.println("== REGISTRO DE CLIENTES ==");
    System.out.println();

    for (int i = cantidadClientes; i < capacidadMaxClientes; i++) {

        System.out.print("Desea registrar un nuevo cliente? (SI/NO): ");
        respuestaRegistro = input.nextLine().toUpperCase();

        if (respuestaRegistro.equals("SI")) {

            System.out.print("Ingrese el nombre del cliente: ");
            cliente[i] = input.nextLine();

            System.out.print("Ingrese la edad del cliente: ");
            edad[i] = input.nextInt();
            input.nextLine();

            System.out.print("Ingrese el numero de identidad del cliente: ");
            identidad[i] = input.nextLine();

            System.out.print("Ingrese el numero de licencia del cliente: ");
            licencia[i] = input.nextLine();

            System.out.println();
            System.out.println("== CLIENTE REGISTRADO, RESUMEN DE REGISTRO ==");
            System.out.println("---------------------------------------------");
            System.out.println("Nombre del cliente: " + cliente[i]);
            System.out.println("Edad del cliente: " + edad[i]);
            System.out.println("Identidad del cliente: " + identidad[i]);
            System.out.println("No de licencia del cliente: " + licencia[i]);
            System.out.println();

            cantidadClientes++;

        } else {
            break;
        }//Fin If/Else
    }//Fin For

    return cantidadClientes;
}//FIn Funcion registrarCliente
    
    public static void consultarCliente(
            Scanner input,
            String[] cliente,
            int[] edad,
            String[] identidad,
            String[] licencia,
            int cantidadClientes) {
        System.out.println("== CONSULTA DE CLIENTES ==");
        System.out.println("");

        String valorBuscado;
        boolean valorEncontrado = false;

        if (cantidadClientes == 0) {
            System.out.println("NO hay clientes registrados\n");
        } else {
            System.out.print("Ingrese el numero de identidad del cliente: ");
            valorBuscado = input.nextLine();
            for (int i = 0; i < cantidadClientes; i++) {
                if (valorBuscado.equals(identidad[i])) {
                    System.out.println("Nombre del cliente: " + cliente[i]);
                    System.out.println("edad del cliente: " + edad[i]);
                    System.out.println("identidad del cliente: " + identidad[i]);
                    System.out.println("licencia del cliente: " + licencia[i]);
                    System.out.println();
                    valorEncontrado = true;
                    break;
                }//Fin if 
            }//Fin For

            if (valorEncontrado == false) {
                System.out.println("No se encuentra registro del cliente");

            }//Fin If
        }//Fin if/else
    }//Fin Funcion consultarCliente
}//Fin Class
