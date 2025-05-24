package proyecto-grafos.srctst.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PruebasGrafoTest {

    private Grafo grafo;
    private FloydWarshall floyd;

    @BeforeEach
    public void configurar() {
        grafo = new Grafo(10);
        grafo.agregarCiudad("A");
        grafo.agregarCiudad("B");
        grafo.agregarCiudad("C");

        grafo.agregarConexion("A", "B", new double[]{3, 4, 5, 6});
        grafo.agregarConexion("B", "C", new double[]{2, 3, 4, 5});
        grafo.agregarConexion("A", "C", new double[]{10, 12, 15, 20});

        floyd = new FloydWarshall();
        floyd.aplicarFloyd(grafo.getMatrizActual());
    }

    @Test
    public void testAgregarCiudad() {
        assertEquals(3, grafo.getCiudades().size());
        grafo.agregarCiudad("D");
        assertEquals(4, grafo.getCiudades().size());
    }

    @Test
    public void testEliminarConexion() {
        grafo.eliminarConexion("A", "B");
        double[][] matriz = grafo.getMatrizActual();
        int i = grafo.getIndice("A");
        int j = grafo.getIndice("B");
        assertEquals(Double.POSITIVE_INFINITY, matriz[i][j]);
    }

    @Test
    public void testFloydDistancias() {
        int i = grafo.getIndice("A");
        int j = grafo.getIndice("C");
        double[][] dist = floyd.getDistancias();
        assertEquals(5.0, dist[i][j]); // A -> B -> C = 3 + 2
    }

    @Test
    public void testRutaIntermedia() {
        int i = grafo.getIndice("A");
        int j = grafo.getIndice("C");
        List<Integer> ruta = floyd.reconstruirRuta(i, j);
        assertFalse(ruta.isEmpty());
        assertEquals(grafo.getIndice("B"), ruta.get(0)); // Intermedio debe ser B
    }
}
