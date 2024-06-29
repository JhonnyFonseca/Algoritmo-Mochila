
package mochila;

import java.util.ArrayList;
import java.util.Scanner;

class Articulo {
    String nombre;
    int beneficio;
    int volumen;

    public Articulo(String nombre, int beneficio, int volumen) {
        this.nombre = nombre;
        this.beneficio = beneficio;
        this.volumen = volumen;
    }
}
public class Mochila {
      public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la cantidad de artículos:");
        int cantidadArticulos = scanner.nextInt();

        ArrayList<Articulo> articulos = new ArrayList<>();

        int capacidadMochila = 0;

        for (int i = 0; i < cantidadArticulos; i++) {
            System.out.println("Ingrese el nombre del artículo " + (i + 1) + ":");
            String nombre = scanner.next();

            System.out.println("Ingrese el beneficio del artículo " + (i + 1) + ":");
            int beneficio = scanner.nextInt();

            System.out.println("Ingrese el volumen del artículo " + (i + 1) + ":");
            int volumen = scanner.nextInt();

            articulos.add(new Articulo(nombre, beneficio, volumen));

            // Actualizar la capacidad de la mochila si es necesario
            if (volumen > capacidadMochila) {
                capacidadMochila = volumen;
            }
        }

        int[][] matrizBeneficio = new int[cantidadArticulos + 1][capacidadMochila + 1];

        for (int i = 0; i <= cantidadArticulos; i++) {
            for (int j = 0; j <= capacidadMochila; j++) {
                if (i == 0 || j == 0)
                    matrizBeneficio[i][j] = 0;
                else if (articulos.get(i - 1).volumen <= j)
                    matrizBeneficio[i][j] = Math.max(articulos.get(i - 1).beneficio + matrizBeneficio[i - 1][j - articulos.get(i - 1).volumen], matrizBeneficio[i - 1][j]);
                else
                    matrizBeneficio[i][j] = matrizBeneficio[i - 1][j];
            }
        }

        System.out.println("\nEl peso de la mochila es: "+ capacidadMochila);
        int beneficioMaximo = matrizBeneficio[cantidadArticulos][capacidadMochila];
        System.out.println("El beneficio máximo que se puede obtener es: " + beneficioMaximo);

        System.out.println("Los artículos seleccionados son:");
        int j = capacidadMochila;
        for (int i = cantidadArticulos; i > 0 && beneficioMaximo > 0; i--) {
            if (beneficioMaximo != matrizBeneficio[i - 1][j]) {
                System.out.println(articulos.get(i - 1).nombre);
                beneficioMaximo -= articulos.get(i - 1).beneficio;
                j -= articulos.get(i - 1).volumen;
            }
        }

        scanner.close();
    }
    
}
