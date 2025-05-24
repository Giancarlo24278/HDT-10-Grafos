package main.java;

public class CentroGrafo {

    public static int calcularCentro(double[][] distancias) {
        int n = distancias.length;
        double[] excentricidades = new double[n];

        for (int i = 0; i < n; i++) {
            double maxDistancia = 0;
            for (int j = 0; j < n; j++) {
                if (distancias[i][j] != Double.POSITIVE_INFINITY && i != j) {
                    maxDistancia = Math.max(maxDistancia, distancias[i][j]);
                }
            }
            excentricidades[i] = maxDistancia;
        }

        int centro = 0;
        double minExcentricidad = excentricidades[0];

        for (int i = 1; i < n; i++) {
            if (excentricidades[i] < minExcentricidad) {
                minExcentricidad = excentricidades[i];
                centro = i;
            }
        }

        return centro;
    }
}
