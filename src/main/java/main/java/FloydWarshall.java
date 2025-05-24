package main.java;

import java.util.*;

public class FloydWarshall {
    private double[][] distancias;
    private int[][] intermedios;

    public void aplicarFloyd(double[][] matriz) {
        int n = matriz.length;
        distancias = new double[n][n];
        intermedios = new int[n][n];

        // Inicialización
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distancias[i][j] = matriz[i][j];
                intermedios[i][j] = -1;
            }
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        intermedios[i][j] = k;
                    }
                }
            }
        }
    }

    public double[][] getDistancias() {
        return distancias;
    }

    public List<Integer> reconstruirRuta(int i, int j) {
        List<Integer> ruta = new ArrayList<>();
        construirCamino(i, j, ruta);
        return ruta;
    }

    private void construirCamino(int i, int j, List<Integer> ruta) {
        int k = intermedios[i][j];
        if (k == -1) return;
        construirCamino(i, k, ruta);
        ruta.add(k);
        construirCamino(k, j, ruta);
    }
}
