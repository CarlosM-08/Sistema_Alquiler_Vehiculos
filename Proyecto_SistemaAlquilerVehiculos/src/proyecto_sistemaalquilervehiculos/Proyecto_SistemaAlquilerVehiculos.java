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
        String[] licencia = new String [10];   
        int respuestaTipoVehiculo = 0;
        String respuestaRegistro = "";
        int eleccion = 0;
        
        
        //MENU PRINCIPAL        
        System.out.println("BIENVENIDOS AL SISTEMA DE ALQUILER DE VEHICULOS");
        System.out.println("-----------------------------------------------");
        System.out.println();
        System.out.println("""
                           Que gestion desea realizar?
                           1. Registrar Cliente
                           2. Registrar Alquiler
                           3. Registrar Devolucion
                           4. Consulta de Vehiculos
                           5. Mostrar resumen general
                           6. Salir
                           """);
        
        System.out.print("Eleccion: ");
        eleccion = input.nextInt();
        System.out.println();

        switch (eleccion) {
            case 1:
                //SECCION REGISTRO DE CLIENTE
                System.out.println("== REGISTRO DE CLIENTES==");
                System.out.println("");

                for (int i = 0; i < 10; i++) {
                    System.out.print("Desea registrar un nuevo cliente? (SI/NO): ");
                    input.nextLine();
                    respuestaRegistro = input.nextLine().toUpperCase();
                    if (respuestaRegistro.equals("SI")) {
                        System.out.print("Ingrese el nombre del cliente: ");
                        cliente[i] = input.nextLine();
                        System.out.print("Ingrese la edad del cliente: ");
                        edad[i] = input.nextInt();
                        System.out.print("Ingrese el numero de identidad del cliente: ");
                        input.nextLine();
                        identidad[i] = input.nextLine();
                        System.out.print("Ingrese el numero de licencia del cliente: ");
                        licencia[i] = input.nextLine();

                        System.out.println();                        
                        System.out.println("== RESUMEN DE REGISTRO ==");
                        System.out.println("-------------------------");
                        System.out.println("Nombre del cliente: " + cliente[i]);
                        System.out.println("edad del cliente: " + edad[i]);
                        System.out.println("identidad del cliente: " + identidad[i]);
                        System.out.println("No de licencia del cliente: " + licencia[i]);
                        System.out.println();
                    } else {
                        break;
                    }//Fin if/else
                }//Fin for 

                break;
            case 2:
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
            case 3:

                break;
            case 4:

                break;
            default:

        }//Fin Switch        
  
    }//Fin Main
    
}//Fin Class
