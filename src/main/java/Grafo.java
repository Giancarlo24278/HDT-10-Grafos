package main.java;

import java.util.*;

public class Grafo {
    private final List<String> ciudades = new ArrayList<>();
    private double[][][] matriz; // [clima][i][j]
    private int climaActivo = 0; // 0: Normal, 1: Lluvia, 2: Nieve, 3: Tormenta

    public Grafo(int tamañoMax) {
        matriz = new double[4][tamañoMax][tamañoMax];
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < tamañoMax; i++) {
                for (int j = 0; j < tamañoMax; j++) {
                    matriz[k][i][j] = (i == j) ? 0 : Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    public void agregarCiudad(String nombre) {
        if (!ciudades.contains(nombre)) {
            ciudades.add(nombre);
        }
    }

    public void agregarConexion(String origen, String destino, double[] tiempos) {
        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);
        for (int k = 0; k < 4; k++) {
            matriz[k][i][j] = tiempos[k];
        }
    }

    public void eliminarConexion(String origen, String destino) {
        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);
        for (int k = 0; k < 4; k++) {
            matriz[k][i][j] = Double.POSITIVE_INFINITY;
        }
    }

    public void setClimaActivo(int clima) {
        this.climaActivo = clima;
    }

    public double[][] getMatrizActual() {
        return matriz[climaActivo];
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public int getIndice(String ciudad) {
        return ciudades.indexOf(ciudad);
    }
}
