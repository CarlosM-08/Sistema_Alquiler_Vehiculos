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
        int posicionCliente;
        String[] vehiculo = {"Toyota Corolla", "Honda Civic", "Hyundai Elantra", "Kia Rio", "Toyota RAV4", "Ford Explorer", "Honda CR-V", "Hyundai Tucson", "Toyota Hilux", "Ford Ranger", "Nissan Frontier", "Mitsubishi L200"};
        String[] categoria = {"Economico", "Economico", "Economico", "Economico", "SUV", "SUV", "SUV", "SUV", "Pickup", "Pickup", "Pickup", "Pickup"};
        double[] tarifa = {30, 35, 32, 28, 50, 60, 52, 48, 55, 58, 53, 50};
        boolean[] disponibles = {true, true, true, true, true, true, true, true, true, true, true, true};
        int seleccionVehiculo = 0;

        do {

            //MENU PRINCIPAL        
            System.out.println("\nBIENVENIDOS AL SISTEMA DE ALQUILER DE VEHICULOS");
            System.out.println("===============================================");
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
                    String categoriaSeleccionada = "No asignada";

                    posicionCliente = buscarCliente(input, cliente, edad, identidad, licencia, cantidadClientes);

                    if (posicionCliente != -1) {
                        System.out.printf("Bienvenido %s\n", cliente[posicionCliente]);
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
                                categoriaSeleccionada = "Economico";
                                break;
                            case 2:
                                categoriaSeleccionada = "SUV";
                                break;
                            case 3:
                                categoriaSeleccionada = "Pickup";
                                break;
                            default:
                                System.out.println("Opcion no valida");
                        }

                        for (int i = 0; i < categoria.length; i++) {
                            if (categoria[i].equals(categoriaSeleccionada) && disponibles[i] == true) {
                                System.out.println((i + 1) + ") " + vehiculo[i] + " - " + tarifa[i]);
                            }  //Fin if                          
                        }//Fin For

                        System.out.println("Seleccione el vehiculo que desea alquilar: ");
                        seleccionVehiculo = input.nextInt();

                    }//Fin If/Else

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
    
    public static int registrarCliente(Scanner input, String[] cliente, int[] edad, String[] identidad, String[] licencia, int cantidadClientes, int capacidadMaxClientes) {

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
                System.out.println("CLIENTE REGISTRADO!, RESUMEN DE REGISTRO");
                System.out.println("========================================");
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

    public static void consultarCliente(Scanner input, String[] cliente, int[] edad, String[] identidad, String[] licencia, int cantidadClientes) {
        System.out.println("== CONSULTA DE CLIENTES ==");
        System.out.println("");
        int posicion;

        posicion = buscarCliente(input, cliente, edad, identidad, licencia, cantidadClientes);

        if (posicion != -1) {
            System.out.println("Nombre del cliente: " + cliente[posicion]);
            System.out.println("edad del cliente: " + edad[posicion]);
            System.out.println("identidad del cliente: " + identidad[posicion]);
            System.out.println("licencia del cliente: " + licencia[posicion]);
            System.out.println();
        }
    }//Fin Funcion consultarCliente

    public static int buscarCliente(Scanner input, String[] cliente, int[] edad, String[] identidad, String[] licencia, int cantidadClientes) {

        String valorBuscado;
        boolean valorEncontrado = false;
        int posicionClienteTem = -1;

        if (cantidadClientes == 0) {
            System.out.println("");
            System.out.println("=================================");
            System.out.println("|| NO hay clientes registrados ||");
            System.out.println("=================================");
        } else {
            System.out.print("Ingrese el numero de identidad del cliente: ");
            valorBuscado = input.nextLine();
            for (int i = 0; i < cantidadClientes; i++) {
                if (valorBuscado.equals(identidad[i])) {
                    valorEncontrado = true;
                    posicionClienteTem = i;
                    break;
                }//Fin if 
            }//Fin For

            if (valorEncontrado == false) {
                System.out.println("");
                System.out.println("==========================================");
                System.out.println("|| No se encuentra registro del cliente ||");
                System.out.println("==========================================");
            }//Fin If
        }//Fin if/else
        return posicionClienteTem;
    }//Fin Funcion consultarCliente
    

}//Fin Class
