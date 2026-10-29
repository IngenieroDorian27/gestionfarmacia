
package Control;

/**
 *
 * @author Grupo1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ControlDatos {
    
    public int leerEntero(String mensaje){
        int x = 0;
        boolean bandera = false;
        Scanner entry = new Scanner(System.in);
        
        do{
            System.out.println(mensaje);
            try{
                x = entry.nextInt();
                bandera = true;
            }
            catch(InputMismatchException ex){
                System.out.println("Error: se esperaba un numero entero");
                bandera = false;
                entry.nextLine();
            }
        }while(!bandera);
        return x;
    }
    
    public double leerReal(String mensaje){
        double x = 0;
        boolean bandera = false;
        Scanner entry = new Scanner(System.in);
        
        do{
            System.out.println(mensaje);
            try{
                x = entry.nextDouble();
                bandera = true;
            }
            catch(InputMismatchException ex){
                System.out.println("Error: se esperaba un numero real");
                bandera = false;
                entry.nextLine();
            }
        }while(!bandera);
        return x;
        
    }
    private Scanner scanner;
    
    public ControlDatos() {
        this.scanner = new Scanner(System.in);
    }
    
public char leerLetra(String mensaje, char opcion1, char opcion2, InputStream inputStream) {
        char letra = 0;
        boolean entradaValida = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        while (!entradaValida) {
            try {
                System.out.print(mensaje);
                letra = (char) br.read();
                br.readLine(); // Consumir el salto de línea
                if (letra == opcion1 || letra == opcion2) {
                    entradaValida = true;
                } else {
                    System.out.println("Ingrese una letra válida.");
                }
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
        return letra;
    }
}