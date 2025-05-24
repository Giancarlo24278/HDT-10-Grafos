package main.java;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo(50); // hasta 50 ciudades
        leerArchivo("logistica.txt", grafo);

        FloydWarshall floyd = new FloydWarshall();
        floyd.aplicarFloyd(grafo.getMatrizActual());

        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ruta más corta entre dos ciudades");
            System.out.println("2. Ciudad centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.print("Ciudad origen: ");
                    String origen = sc.nextLine();
                    System.out.print("Ciudad destino: ");
                    String destino = sc.nextLine();
                    mostrarRutaMasCorta(origen, destino, grafo, floyd);
                    break;
                }
                case 2: {
                    int centro = CentroGrafo.calcularCentro(floyd.getDistancias());
                    System.out.println("Centro del grafo: " + grafo.getCiudades().get(centro));
                    break;
                }
                case 3: {
                    modificarGrafo(sc, grafo);
                    floyd.aplicarFloyd(grafo.getMatrizActual());
                    break;
                }
                case 4: {
                    System.out.println("Programa finalizado.");
                    return;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void leerArchivo(String nombre, Grafo grafo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String ciudad1 = partes[0], ciudad2 = partes[1];
                double[] tiempos = new double[4];
                for (int i = 0; i < 4; i++) tiempos[i] = Double.parseDouble(partes[i + 2]);
                grafo.agregarCiudad(ciudad1);
                grafo.agregarCiudad(ciudad2);
                grafo.agregarConexion(ciudad1, ciudad2, tiempos);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    private static void mostrarRutaMasCorta(String origen, String destino, Grafo grafo, FloydWarshall floyd) {
        int i = grafo.getIndice(origen);
        int j = grafo.getIndice(destino);
        if (i == -1 || j == -1) {
            System.out.println("Una o ambas ciudades no existen.");
            return;
        }
        double[][] dist = floyd.getDistancias();
        double distancia = dist[i][j];
        if (distancia == Double.POSITIVE_INFINITY) {
            System.out.println("No hay ruta entre las ciudades.");
        } else {
            System.out.println("Distancia más corta: " + distancia + " horas");
            System.out.print("Ruta: " + origen + " -> ");
            List<Integer> intermedios = floyd.reconstruirRuta(i, j);
            for (int idx : intermedios) {
                System.out.print(grafo.getCiudades().get(idx) + " -> ");
            }
            System.out.println(destino);
        }
    }

    private static void modificarGrafo(Scanner sc, Grafo grafo) {
        System.out.println("a) Interrupción entre ciudades");
        System.out.println("b) Nueva conexión");
        switch (opcion.toLowerCase()) {
            case "a": {
                System.out.print("Ciudad origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = sc.nextLine();
                grafo.eliminarConexion(origen, destino);
                System.out.println("Conexión eliminada.");
                break;
            }
            case "b": {
                System.out.print("Ciudad origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = sc.nextLine();
                System.out.print("Tiempo en clima normal: ");
                double normal = sc.nextDouble();
                System.out.print("Tiempo en lluvia: ");
                double lluvia = sc.nextDouble();
                System.out.print("Tiempo en nieve: ");
                double nieve = sc.nextDouble();
                System.out.print("Tiempo en tormenta: ");
                double tormenta = sc.nextDouble();
                sc.nextLine();
                grafo.agregarCiudad(origen);
                grafo.agregarCiudad(destino);
                grafo.agregarConexion(origen, destino, new double[]{normal, lluvia, nieve, tormenta});
                System.out.println("Conexión agregada.");
                break;
            }
            case "c": {
                System.out.print("Nuevo clima (0: normal, 1: lluvia, 2: nieve, 3: tormenta): ");
                int clima = sc.nextInt();
                sc.nextLine();
                grafo.setClimaActivo(clima);
                System.out.println("Clima cambiado.");
                break;
            }
            default:
                System.out.println("Opción inválida.");
        }
                System.out.println("Clima cambiado.");
            }
            default -> System.out.println("Opción inválida.");
        }
    }
}
