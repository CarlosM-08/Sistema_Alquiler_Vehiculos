/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_sistemaalquilervehiculos;

import java.util.Scanner;
import java.time.LocalDate; //Uso de Clase LocalDate (aspecto no visto en clase)

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

        //Declaracion de variables MENU PRINCIPAL
        int eleccion = 0;

        //Declaracion de variables para CLIENTES
        String[] cliente = new String[10];
        int[] edad = new int[10];
        String[] identidad = new String[10];
        String[] licencia = new String[10];
        int cantidadClientes = 0;
        int capacidadMaxClientes = 10;

        //Declaracion de variables para VEHICULOS
        String[] vehiculo = {"Toyota Corolla", "Honda Civic", "Hyundai Elantra", "Kia Rio", "Toyota RAV4", "Ford Explorer", "Honda CR-V", "Hyundai Tucson", "Toyota Hilux", "Ford Ranger", "Nissan Frontier", "Mitsubishi L200"};
        String[] categoria = {"Economico", "Economico", "Economico", "Economico", "SUV", "SUV", "SUV", "SUV", "Pickup", "Pickup", "Pickup", "Pickup"};
        double[] tarifa = {30, 35, 32, 28, 50, 60, 52, 48, 55, 58, 53, 50};
        boolean[] disponibles = {true, true, true, true, true, true, true, true, true, true, true, true};

        //variables para los ALQUILERES
        String[] clientesAlquiler = new String[12];
        String[] vehiculosAlquilados = new String[12];
        int[] diasAlquiler = new int[12];
        double[] subtotalesAlquiler = new double[12];
        boolean[] alquilerActivo = new boolean[12];
        int[] posicionVehiculoAlquiler = new int[12];
        int cantidadAlquileres = 0;
        double totalMoras = 0;

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
                           6. Mostrar Resumen General
                           7. Salir
                           """);

            do {
                System.out.print("Eleccion: ");

                if (input.hasNextInt()) {
                    eleccion = input.nextInt();
                    input.nextLine();

                    if (eleccion < 1 || eleccion > 7) {
                        System.out.println("Opcion no valida. Ingrese un numero entre 1 y 7.\n");
                    }//Fin IF

                } else {
                    System.out.println("Opcion no valida. Ingrese un numero entre 1 y 7.\n");
                    input.nextLine();
                    eleccion = 0;
                }//Fin IF/ELSE

            } while (eleccion < 1 || eleccion > 7);

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
                    //Declaracion de variables                     
                    String categoriaSeleccionada = "No asignada";
                    double totalPagarCliente = 0;
                    double subtotalPagar = 0;
                    String continuar = "NO";
                    int inicioAlquilerCliente = cantidadAlquileres;
                    int posicionCliente;
                    int posicionVehiculo = 0;
                    int dias = 0;
                    String metodoPago = "";

                    //Declaracion de variables de la clase LocalDate                    
                    LocalDate fechaAlquiler;
                    LocalDate fechaDevolucion;

                    posicionCliente = buscarCliente(input, cliente, edad, identidad, licencia, cantidadClientes);

                    if (posicionCliente != -1) {
                        System.out.printf("Bienvenido %s\n", cliente[posicionCliente]);
                        do {

                            // Llama a la funcion SeleccionarCategoria
                            categoriaSeleccionada = seleccionarCategoria(input);

                            // Llama a la funcion mostrarVehiculosDisponibles
                            mostrarVehiculosDisponibles(categoria, disponibles, categoriaSeleccionada, vehiculo, tarifa);

                            //Llama a la funcion seleccionarVehiculo
                            posicionVehiculo = seleccionarVehiculo(input, vehiculo, categoria, categoriaSeleccionada, disponibles);

                            System.out.println(vehiculo[posicionVehiculo]);
                            System.out.println(categoria[posicionVehiculo]);
                            System.out.println(tarifa[posicionVehiculo]);

                            dias = solicitarDias(input);// Llama a la funcion solicitarDias 

                            //Obtener la fecha actual del alquiler
                            fechaAlquiler = LocalDate.now();

                            //Calcular la fecha prevista de devolucion
                            fechaDevolucion = fechaAlquiler.plusDays(dias);

                            subtotalPagar = tarifa[posicionVehiculo] * dias;

                            //Guardar los datos del alquiler
                            clientesAlquiler[cantidadAlquileres] = cliente[posicionCliente];
                            vehiculosAlquilados[cantidadAlquileres] = vehiculo[posicionVehiculo];
                            diasAlquiler[cantidadAlquileres] = dias;
                            subtotalesAlquiler[cantidadAlquileres] = subtotalPagar;
                            alquilerActivo[cantidadAlquileres] = true;
                            posicionVehiculoAlquiler[cantidadAlquileres] = posicionVehiculo;

                            //Acumular el total del cliente actual
                            totalPagarCliente += subtotalPagar;

                            //Aumentar la cantidad general de alquileres
                            cantidadAlquileres++;

                            //El vehículo deja de estar disponible
                            disponibles[posicionVehiculo] = false;

                            System.out.println("\nVEHICULO AGREGADO AL ALQUILER");
                            System.out.println("=================================");
                            System.out.printf("Vehiculo: %s\n", vehiculo[posicionVehiculo]);
                            System.out.printf("Cantidad de dias: %d\n", dias);
                            System.out.printf("Fecha de alquiler: %s\n", fechaAlquiler);
                            System.out.printf("Fecha prevista de devolucion: %s\n", fechaDevolucion);
                            System.out.printf("Subtotal: %.2f\n", subtotalPagar);
                            System.out.println("=================================\n");

                            System.out.print("Desea Alquilar otro vehiculo: ");
                            input.nextLine();
                            continuar = input.nextLine().toUpperCase();
                        } while (continuar.equals("SI"));

                        metodoPago = seleccionarMetodoPago(input);

                        mostrarResumenAlquiler(cliente[posicionCliente], vehiculosAlquilados, tarifa, posicionVehiculoAlquiler, diasAlquiler, subtotalesAlquiler, inicioAlquilerCliente, cantidadAlquileres, totalPagarCliente, metodoPago);

                    }//Fin IF/ELSE

                    break;
                case 4:
                    //SECCION REGISTRO DE DEVOLUCION

                    int posicionClienteDevolucion = 0;
                    int posicionAlquiler = -1;
                    int diasRetraso;
                    double mora;
                    double tarifaMora = 100.00;
                    boolean tieneAlquileres = false;
                    String confirmarDevolucion;

                    System.out.println("\n== REGISTRO DE DEVOLUCION ==");
                    System.out.println();

                    //Buscar cliente
                    posicionClienteDevolucion = buscarCliente(input, cliente, edad, identidad, licencia, cantidadClientes);

                    if (posicionClienteDevolucion != -1) {

                        tieneAlquileres = mostrarAlquileresActivos(cliente[posicionClienteDevolucion], clientesAlquiler, vehiculosAlquilados, alquilerActivo, cantidadAlquileres);

                        if (tieneAlquileres == true) {

                            //Seleccionar el alquiler que se desea devolver                            
                            posicionAlquiler = seleccionarAlquilerDevolucion(input, cliente[posicionClienteDevolucion], clientesAlquiler, alquilerActivo, cantidadAlquileres);

                            //Solicitar dias de retraso
                            diasRetraso = solicitarDiasRetraso(input);

                            //Calcular mora
                            mora = diasRetraso * tarifaMora;

                            //Mostrar resumen antes de confirmar
                            System.out.println("\nRESUMEN DE DEVOLUCION");
                            System.out.println("========================================");
                            System.out.printf("Cliente: %s\n", cliente[posicionClienteDevolucion]);
                            System.out.printf("Vehiculo: %s\n", vehiculosAlquilados[posicionAlquiler]);
                            System.out.printf("Dias de retraso: %d\n", diasRetraso);
                            System.out.printf("Mora por retraso: %.2f\n", mora);
                            System.out.println("========================================");

                            //Confirmar devolucion
                            input.nextLine();

                            System.out.print("\nConfirma la devolucion? (SI/NO): ");
                            confirmarDevolucion = input.nextLine().toUpperCase();

                            if (confirmarDevolucion.equals("SI")) {

                                //Cambiar estado del alquiler
                                alquilerActivo[posicionAlquiler] = false;

                                //Obtener la posicion original del vehiculo
                                int posicionVehiculoDevuelto = posicionVehiculoAlquiler[posicionAlquiler];

                                //El vehiculo vuelve a estar disponible
                                disponibles[posicionVehiculoDevuelto] = true;

                                //Acumular el valor de la mora
                                totalMoras += mora;

                                System.out.println("\n======================================");
                                System.out.println("DEVOLUCION REGISTRADA CORRECTAMENTE");
                                System.out.println("======================================");
                                System.out.printf("Vehiculo: %s\n", vehiculosAlquilados[posicionAlquiler]);

                                if (mora > 0) {
                                    System.out.printf("Total a pagar por mora: %.2f\n", mora);
                                } else {
                                    System.out.println("El vehiculo fue devuelto sin mora.");
                                }//Fin IF/ELSE

                                System.out.println("======================================\n");

                            } else {

                                System.out.println("\nLa devolucion ha sido cancelada.\n");
                            }//Fin IF/ELSE

                        } else {

                            System.out.println("\nEl cliente no tiene vehiculos pendientes de devolucion.\n");
                        }//FIn IF/ELSE

                    }//Fin IF

                    break;
                case 5:

                    mostrarEstadoVehiculos(vehiculo, categoria, tarifa, disponibles);

                    break;
                case 6:

                    mostrarResumenGeneral(cantidadClientes, cantidadAlquileres, alquilerActivo, disponibles, subtotalesAlquiler, totalMoras);

                    break;

                case 7:

                    //SALIR DEL SISTEMA
                    System.out.println("======================================");
                    System.out.println("Gracias por utilizar nuestro sistema");
                    System.out.println("======================================");

                    break;
                default:

                    System.out.println("Opcion no valida");

            }//Fin SWITCH 

        } while (eleccion != 7);

    }//Fin Main

    /**
     * Esta funcion permite registrar los datos de nuevos clientes dentro de los
     * arreglos correspondientes. Valida que el cliente tenga una edad minima de
     * 18 años para poder realizar el registro. Al finalizar el registro,
     * devuelve la cantidad actualizada de clientes registrados.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @param cliente recibe el arreglo que almacena los nombres de los
     * clientes.
     * @param edad recibe el arreglo que almacena las edades de los clientes.
     * @param identidad recibe el arreglo que almacena las identidades de los
     * clientes.
     * @param licencia recibe el arreglo que almacena las licencias de los
     * clientes.
     * @param cantidadClientes recibe la cantidad actual de clientes
     * registrados.
     * @param capacidadMaxClientes recibe la capacidad maxima de clientes.
     * @return int Devuelve la cantidad actualizada de clientes registrados.
     */
    public static int registrarCliente(Scanner input, String[] cliente, int[] edad, String[] identidad, String[] licencia, int cantidadClientes, int capacidadMaxClientes) {

        //Declaracion de variables temporales
        String respuestaRegistro = "";

        System.out.println("== REGISTRO DE CLIENTES ==");
        System.out.println();

        for (int i = cantidadClientes; i < capacidadMaxClientes; i++) {

            System.out.print("Desea registrar un nuevo cliente? (SI/NO): ");
            respuestaRegistro = input.nextLine().toUpperCase();

            if (respuestaRegistro.equals("SI")) {

                System.out.print("Ingrese el nombre del cliente: ");
                cliente[i] = input.nextLine();

                do {
                    System.out.print("Ingrese la edad del cliente: ");
                    edad[i] = input.nextInt();

                    if (edad[i] < 18) {
                        System.out.println("El cliente debe ser mayor de edad para alquilar un vehiculo.");
                    }//Fin IF

                } while (edad[i] < 18);

                input.nextLine();//Limpieza de buffer

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
            }//Fin IF /ELSE

        }//Fin FOR

        return cantidadClientes;

    }//Fin Funcion registrarCliente

    /**
     * Esta funcion permite consultar la informacion de un cliente registrado en
     * el sistema. Utiliza la funcion buscarCliente para localizar al cliente
     * por medio de su numero de identidad.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @param cliente recibe el arreglo que almacena los nombres de los
     * clientes.
     * @param edad recibe el arreglo que almacena las edades de los clientes.
     * @param identidad recibe el arreglo que almacena las identidades de los
     * clientes.
     * @param licencia recibe el arreglo que almacena las licencias de los
     * clientes.
     * @param cantidadClientes recibe la cantidad actual de clientes
     * registrados.
     */
    public static void consultarCliente(Scanner input, String[] cliente, int[] edad, String[] identidad, String[] licencia, int cantidadClientes) {

        //Delcaracion de variables temporales
        int posicion;

        System.out.println("== CONSULTA DE CLIENTES ==");
        System.out.println("");

        posicion = buscarCliente(input, cliente, edad, identidad, licencia, cantidadClientes);

        if (posicion != -1) {
            System.out.println("Nombre del cliente: " + cliente[posicion]);
            System.out.println("edad del cliente: " + edad[posicion]);
            System.out.println("identidad del cliente: " + identidad[posicion]);
            System.out.println("licencia del cliente: " + licencia[posicion]);
            System.out.println();
        }//Fin IF

    }//Fin Funcion consultarCliente

    /**
     * Esta funcion permite buscar un cliente registrado utilizando su numero de
     * identidad. Recorre los registros existentes y devuelve la posicion del
     * cliente dentro de los arreglos.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @param cliente recibe el arreglo que almacena los nombres de los
     * clientes.
     * @param edad recibe el arreglo que almacena las edades de los clientes.
     * @param identidad recibe el arreglo que almacena las identidades de los
     * clientes.
     * @param licencia recibe el arreglo que almacena las licencias de los
     * clientes.
     * @param cantidadClientes recibe la cantidad actual de clientes
     * registrados.
     * @return int Devuelve la posicion del cliente encontrado o -1 si no
     * existe.
     */
    public static int buscarCliente(Scanner input, String[] cliente, int[] edad, String[] identidad, String[] licencia, int cantidadClientes) {

        //Declaracion de variables temporales
        String valorBuscado;
        boolean valorEncontrado = false;
        int posicionClienteTem = -1;

        if (cantidadClientes == 0) {
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
                }//Fin IF 

            }//Fin FOR

            if (valorEncontrado == false) {
                System.out.println("==========================================");
                System.out.println("|| No se encuentra registro del cliente ||");
                System.out.println("==========================================");
            }//Fin IF

        }//Fin IF/ELSE

        return posicionClienteTem;

    }//Fin Funcion consultarCliente

    /**
     * Esta funcion permite seleccionar la categoria del vehiculo que el cliente
     * desea alquilar. Valida que la opcion ingresada se encuentre entre las
     * categorias disponibles.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @return String Devuelve el nombre de la categoria seleccionada.
     */
    public static String seleccionarCategoria(Scanner input) {

        //Declaracion de valariables temporales
        int respuestaCategoriaVehiculo = 0;
        String categoriaElegida = "@";

        System.out.println("""
                           Seleccione la categoria de vehiculo que desea alquilar
                           1. Economico
                           2. SUV
                           3. Pickup
                           """);

        do {
            System.out.print("Eleccion: ");
            respuestaCategoriaVehiculo = input.nextInt();
            System.out.println();

            if (respuestaCategoriaVehiculo < 1 || respuestaCategoriaVehiculo > 3) {
                System.out.println("Eleccion no valida!!");
            }//Fin IF

        } while (respuestaCategoriaVehiculo < 1 || respuestaCategoriaVehiculo > 3);  // Valida que el usuario ingrese un valor entre 1 y 3                                            

        switch (respuestaCategoriaVehiculo) {
            case 1:
                categoriaElegida = "Economico";
                break;
            case 2:
                categoriaElegida = "SUV";
                break;
            case 3:
                categoriaElegida = "Pickup";
                break;
            default:
                System.out.println("Opcion no valida");
        }// Fin SWITCH

        return categoriaElegida;

    }//Fin funcion SeleccionarCategoria

    /**
     * Esta funcion muestra los vehiculos que se encuentran disponibles de
     * acuerdo con la categoria seleccionada por el cliente. Recorre los
     * arreglos de vehiculos y muestra solamente aquellos que pertenecen a la
     * categoria indicada y estan disponibles.
     *
     * @param categoria recibe el arreglo que almacena las categorias de los
     * vehiculos.
     * @param disponibles recibe el arreglo que almacena la disponibilidad de
     * los vehiculos.
     * @param categoriaSeleccionada recibe la categoria seleccionada por el
     * cliente.
     * @param vehiculo recibe el arreglo que almacena los nombres de los
     * vehiculos.
     * @param tarifa recibe el arreglo que almacena las tarifas de los
     * vehiculos.
     */
    public static void mostrarVehiculosDisponibles(String[] categoria, boolean[] disponibles, String categoriaSeleccionada, String[] vehiculo, double[] tarifa) {

        for (int i = 0; i < categoria.length; i++) {

            if (categoria[i].equals(categoriaSeleccionada) && disponibles[i] == true) {
                System.out.println((i + 1) + ") " + vehiculo[i] + " - " + tarifa[i]);
            }  //Fin IF  

        }//Fin FOR    

    }//Fin Funcion mostrarVehiculosDisponibles

    /**
     * Esta funcion permite seleccionar el vehiculo que el cliente desea
     * alquilar. Valida que el vehiculo pertenezca a la categoria seleccionada y
     * que se encuentre disponible.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @param vehiculo recibe el arreglo que almacena los nombres de los
     * vehiculos.
     * @param categoria recibe el arreglo que almacena las categorias de los
     * vehiculos.
     * @param categoriaSeleccionada recibe la categoria seleccionada por el
     * cliente.
     * @param disponibles recibe el arreglo que almacena la disponibilidad de
     * los vehiculos.
     * @return int Devuelve la posicion del vehiculo seleccionado.
     */
    public static int seleccionarVehiculo(Scanner input, String[] vehiculo, String[] categoria, String categoriaSeleccionada, boolean[] disponibles) {

        //Declaracion de variables temporales
        int seleccionVehiculo = 0;
        int posicion = 0;

        do {
            System.out.print("\nSeleccione el vehiculo que desea alquilar: ");
            seleccionVehiculo = input.nextInt();

            posicion = seleccionVehiculo - 1;

            if (posicion < 0 || posicion >= vehiculo.length || !categoria[posicion].equals(categoriaSeleccionada) || disponibles[posicion] == false) {
                System.out.println("Seleccion de vehiculo no valida");
            }//Fin IF

        } while (posicion < 0 || posicion >= vehiculo.length || !categoria[posicion].equals(categoriaSeleccionada) || disponibles[posicion] == false);

        return posicion;

    }//Fin funcion SeleccionarVehiculo 

    /**
     * Esta funcion solicita la cantidad de dias que el cliente desea alquilar
     * un vehiculo. Valida que la cantidad ingresada sea mayor a cero.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @return int Devuelve la cantidad de dias del alquiler.
     */
    public static int solicitarDias(Scanner input) {

        //Declaracion de variables temporales
        int diasTem = 0;

        do {
            System.out.print("Cuantos dias desea alquilar el vehiculo: ");
            diasTem = input.nextInt();

            if (diasTem <= 0) {
                System.out.println("Cantidad de dias no valida");
            }//Fin IF 

        } while (diasTem <= 0);//Fin DO/WHILE

        return diasTem;

    }//Fin funcion 

    /**
     * Esta funcion muestra el resumen final del alquiler realizado por un
     * cliente, incluyendo los vehiculos alquilados, cantidad de dias,
     * subtotales, metodo de pago y total a pagar.
     *
     * @param nombreCliente recibe el nombre del cliente.
     * @param vehiculosAlquilados recibe el arreglo de vehiculos alquilados.
     * @param diasAlquiler recibe el arreglo con los dias de cada alquiler.
     * @param subtotalesAlquiler recibe el arreglo con los subtotales de los
     * alquileres.
     * @param inicioAlquilerCliente recibe la posicion donde inicia el alquiler
     * actual.
     * @param cantidadAlquileres recibe la cantidad actual de alquileres
     * registrados.
     * @param totalPagarCliente recibe el total que debe pagar el cliente.
     * @param metodoPago recibe el metodo de pago seleccionado.
     * @param tarifa recibe el arreglo con las tarifas por dia de los vehiculos.
     * @param posicionVehiculoAlquiler recibe la posicion original de cada
     * vehiculo alquilado.
     */
    public static void mostrarResumenAlquiler(String nombreCliente, String[] vehiculosAlquilados, double[] tarifa, int[] posicionVehiculoAlquiler, int[] diasAlquiler, double[] subtotalesAlquiler, int inicioAlquilerCliente, int cantidadAlquileres, double totalPagarCliente, String metodoPago) {

        System.out.println("\n\nRESUMEN FINAL DEL ALQUILER");
        System.out.println("============================================================");
        System.out.printf("Cliente: %s\n", nombreCliente);
        System.out.printf("metodo de pago: %s\n\n", metodoPago);
        System.out.printf("%-25s %-15s %-10s %-12s\n", "Vehiculo", "Precio/dia", "Dias", "Subtotal");
        System.out.println("------------------------------------------------------------");

        for (int i = inicioAlquilerCliente; i < cantidadAlquileres; i++) {

            System.out.printf("%-25s %-15.2f %-10d %-12.2f\n", vehiculosAlquilados[i], tarifa[posicionVehiculoAlquiler[i]], diasAlquiler[i], subtotalesAlquiler[i]);

        }//Fin FOR

        System.out.println("------------------------------------------------------------");
        System.out.printf("%-36s %12.2f\n",
                "TOTAL A PAGAR:", totalPagarCliente);
        System.out.println("============================================================\n");

    }//Fin Funcion mostrarResumenAlquiler

    /**
     * Esta funcion permite seleccionar el metodo de pago que utilizara el
     * cliente. Valida que la opcion ingresada corresponda a uno de los metodos
     * de pago disponibles.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @return String Devuelve el metodo de pago seleccionado.
     */
    public static String seleccionarMetodoPago(Scanner input) {

        //Declaracion de variables temporales
        int opcionPago = 0;
        String metodoPago = "";

        System.out.println("""
                       \nSeleccione el metodo de pago
                       1. Efectivo
                       2. Tarjeta
                       3. Transferencia
                       """);

        do {
            System.out.print("Eleccion: ");
            opcionPago = input.nextInt();

            if (opcionPago < 1 || opcionPago > 3) {
                System.out.println("Metodo de pago no valido!!");
            }//Fin IF

        } while (opcionPago < 1 || opcionPago > 3);

        switch (opcionPago) {
            case 1:
                metodoPago = "Efectivo";
                break;

            case 2:
                metodoPago = "Tarjeta";
                break;

            case 3:
                metodoPago = "Transferencia";
                break;
        }

        return metodoPago;

    }//fin Funcion seleccionarMetodoPago

    /**
     * Esta funcion busca y muestra los alquileres que se encuentran activos
     * para un cliente determinado. Tambien identifica si el cliente posee al
     * menos un alquiler pendiente de devolucion.
     *
     * @param nombreCliente recibe el nombre del cliente.
     * @param clientesAlquiler recibe el arreglo de clientes con alquileres
     * registrados.
     * @param vehiculosAlquilados recibe el arreglo de vehiculos alquilados.
     * @param alquilerActivo recibe el arreglo que indica el estado de cada
     * alquiler.
     * @param cantidadAlquileres recibe la cantidad actual de alquileres
     * registrados.
     * @return boolean Devuelve true si encuentra alquileres activos y false si
     * no encuentra.
     */
    public static boolean mostrarAlquileresActivos(String nombreCliente, String[] clientesAlquiler, String[] vehiculosAlquilados, boolean[] alquilerActivo, int cantidadAlquileres) {

        //Declaracion de variables temporales
        boolean alquilerEncontrado = false;

        System.out.printf("\nCliente: %s\n", nombreCliente);

        System.out.println("\nVEHICULOS ALQUILADOS");
        System.out.println("==============================");

        //Mostrar solamente los alquileres activos del cliente
        for (int i = 0; i < cantidadAlquileres; i++) {

            if (clientesAlquiler[i].equals(nombreCliente) && alquilerActivo[i] == true) {
                System.out.printf("%d) %s\n", (i + 1), vehiculosAlquilados[i]);
                alquilerEncontrado = true;
            }//Fin IF

        }//Fin FOR

        return alquilerEncontrado;
    }//Fin funcion mostrarAlquileresActivos

    /**
     * Esta funcion permite seleccionar el alquiler que se desea devolver.
     * Valida que el alquiler pertenezca al cliente y que se encuentre activo
     * antes de aceptar la seleccion.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @param nombreCliente recibe el nombre del cliente.
     * @param clientesAlquiler recibe el arreglo de clientes con alquileres
     * registrados.
     * @param alquilerActivo recibe el arreglo que indica el estado de cada
     * alquiler.
     * @param cantidadAlquileres recibe la cantidad actual de alquileres
     * registrados.
     * @return int Devuelve la posicion del alquiler seleccionado para
     * devolucion.
     */
    public static int seleccionarAlquilerDevolucion(Scanner input, String nombreCliente, String[] clientesAlquiler, boolean[] alquilerActivo, int cantidadAlquileres) {

        //Declaracion de variables temporales
        int seleccionAlquiler = 0;
        int posicionAlquilerTem = -1;

        do {
            System.out.print("\nSeleccione el vehiculo que desea devolver: ");
            seleccionAlquiler = input.nextInt();

            posicionAlquilerTem = seleccionAlquiler - 1;

            if (posicionAlquilerTem < 0 || posicionAlquilerTem >= cantidadAlquileres || !clientesAlquiler[posicionAlquilerTem].equals(nombreCliente) || alquilerActivo[posicionAlquilerTem] == false) {
                System.out.println("Seleccion de vehiculo no valida");
            }//Fin IF

        } while (posicionAlquilerTem < 0 || posicionAlquilerTem >= cantidadAlquileres || !clientesAlquiler[posicionAlquilerTem].equals(nombreCliente) || alquilerActivo[posicionAlquilerTem] == false);

        return posicionAlquilerTem;

    }//Fin Funcion seleccionarAlquilerDevolucion

    /**
     * Esta funcion solicita la cantidad de dias de retraso en la devolucion de
     * un vehiculo. Valida que la cantidad ingresada no sea un numero negativo.
     *
     * @param input recibe un parametro del objeto Scanner.
     * @return int Devuelve la cantidad de dias de retraso.
     */
    public static int solicitarDiasRetraso(Scanner input) {

        //Declaracion de variables temporales
        int diasRetrasoTem = 0;

        do {
            System.out.print("Ingrese la cantidad de dias de retraso: ");
            diasRetrasoTem = input.nextInt();

            if (diasRetrasoTem < 0) {
                System.out.println(
                        "La cantidad de dias no puede ser negativa");
            }//Fin IF

        } while (diasRetrasoTem < 0);

        return diasRetrasoTem;
    }//FIn funcion solicitarDiasRetraso

    /**
     * Esta funcion muestra la informacion de todos los vehiculos registrados en
     * el sistema. Presenta el nombre, categoria, tarifa y estado actual de cada
     * vehiculo.
     *
     * @param vehiculo recibe el arreglo que almacena los nombres de los
     * vehiculos.
     * @param categoria recibe el arreglo que almacena las categorias de los
     * vehiculos.
     * @param tarifa recibe el arreglo que almacena las tarifas de los
     * vehiculos.
     * @param disponibles recibe el arreglo que almacena la disponibilidad de
     * los vehiculos.
     */
    public static void mostrarEstadoVehiculos(String[] vehiculo, String[] categoria, double[] tarifa, boolean[] disponibles) {

        //Declaracion de variables temporales
        String estadoVehiculoTem = "";

        System.out.println("\nCONSULTA DE VEHICULOS");
        System.out.println("==========================================================================");
        System.out.printf("%-5s %-25s %-15s %-10s %-12s\n", "No.", "Vehiculo", "Categoria", "Tarifa", "Estado");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < vehiculo.length; i++) {

            if (disponibles[i] == true) {
                estadoVehiculoTem = "Disponible";
            } else {
                estadoVehiculoTem = "Alquilado";
            }//Fin IF/ELSE

            //Mostrar informacion del vehiculo
            System.out.printf("%-5d %-25s %-15s %-10.2f %-12s\n", (i + 1), vehiculo[i], categoria[i], tarifa[i], estadoVehiculoTem);

        }//Fin FOR

        System.out.println("==========================================================================\n");

    }//Fin Funcion mostrarEstadoVehiculos

    /**
     * Esta funcion muestra un resumen general de la informacion registrada en
     * el sistema. Calcula la cantidad de alquileres activos, vehiculos
     * disponibles, vehiculos alquilados, ingresos por alquileres y total
     * acumulado por moras.
     *
     * @param cantidadClientes recibe la cantidad de clientes registrados.
     * @param cantidadAlquileres recibe la cantidad de alquileres registrados.
     * @param alquilerActivo recibe el arreglo que indica el estado de cada
     * alquiler.
     * @param disponibles recibe el arreglo que indica la disponibilidad de los
     * vehiculos.
     * @param subtotalesAlquiler recibe el arreglo con los subtotales de los
     * alquileres.
     * @param totalMoras recibe el total acumulado por concepto de moras.
     */
    public static void mostrarResumenGeneral(int cantidadClientes, int cantidadAlquileres, boolean[] alquilerActivo, boolean[] disponibles, double[] subtotalesAlquiler, double totalMoras) {

        //Declaracion de variables temporales
        int alquileresActivosTem = 0;
        int vehiculosDisponiblesTem = 0;
        int vehiculosAlquiladosTem = 0;
        double ingresosAlquileresTem = 0;

        //Contar alquileres activos
        for (int i = 0; i < cantidadAlquileres; i++) {

            if (alquilerActivo[i] == true) {
                alquileresActivosTem++;
            }

            //Acumular ingresos por alquileres
            ingresosAlquileresTem += subtotalesAlquiler[i];

        }//Fin FOR

        //Contar vehiculos disponibles y alquilados
        for (int i = 0; i < disponibles.length; i++) {

            if (disponibles[i] == true) {
                vehiculosDisponiblesTem++;
            } else {
                vehiculosAlquiladosTem++;
            }//Fin IF/ELSE

        }//Fin FOR

        //Mostrar resumen general
        System.out.println("\nRESUMEN GENERAL DEL SISTEMA");
        System.out.println("==============================================");
        System.out.printf("Clientes registrados:           %d\n", cantidadClientes);
        System.out.printf("Alquileres registrados:         %d\n", cantidadAlquileres);
        System.out.printf("Alquileres activos:             %d\n", alquileresActivosTem);
        System.out.printf("Vehiculos disponibles:          %d\n", vehiculosDisponiblesTem);
        System.out.printf("Vehiculos alquilados:           %d\n", vehiculosAlquiladosTem);
        System.out.printf("Ingresos por alquileres:        %.2f\n", ingresosAlquileresTem);
        System.out.printf("Total cobrado por mora:         %.2f\n", totalMoras);
        System.out.println("==============================================\n");

    }//Fin Funcion mostrarResumenGeneral

}//Fin CLASS
