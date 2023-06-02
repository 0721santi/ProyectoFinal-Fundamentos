import java.util.ArrayList;
import java.util.Scanner;
public class main{
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        // Se instancia "MainSopaLetras" de la clase MainSopaLetras para poder manejar los metodos que son heredados de la interfaz.
        ArrayList<String> listaPalabras = new ArrayList<>();
        Palabra[] p = new Palabra[listaPalabras.size()];
        int tamanoSopa = 0, opc;
        char[][] sopa = new char[tamanoSopa][tamanoSopa];
        int[] pasos;
        boolean check = false;
        /* 
        * Switch para crear el menu, si se ingresa 1 se crea la sopa apartir de un archivo txt , si se ingresa 2 
        * se resuelve la sopa con los datos de la opcion 1 y si se ingresa 3 se resuelve la sopa pero con datos externos
        */
        while(true){
            opc = MainSopaLetras.getMenu();
            switch(opc){
                case 1:
                    // Se usa el .clear() para que no se sumen las mismas palabras luego de ejecutar varias veces el programa, (2 eafit, 2 transforma...).
                    listaPalabras.clear();
                    System.out.print("Ingrese nombre del archivo: ");
                    String path = myScan.nextLine();
                    // Se crea y se organiza el arrayList.
                    MainSopaLetras.lecturaArchivo(path, listaPalabras);
                    MainSopaLetras.organizaLista(listaPalabras);
                    p = new Palabra[listaPalabras.size()];
                    tamanoSopa = listaPalabras.get(0).length() * 2;
                    sopa = MainSopaLetras.creaSopaDeLetras(listaPalabras, tamanoSopa, p);
                    MainSopaLetras.guardaArchivo(sopa, tamanoSopa);
                    System.out.println("La sopa de letras ha sido creada con éxito.\n");
                    check = true;
                    break;
                case 2:
                    Solucion1 solve = new Solucion1(p, sopa);
                    // Se crea un if para evitar un bug en el cual resuelve una sopa sin crearla.
                    if(!check){
                        System.out.println("No puedes usar esta opción sin haber leido y creado una sopa de letras.");
                        continue;
                    }
                    pasos = solve.solucionSopa();
                    solve.imprimeSopa();
                    solve.imprimePosiciones(p);
                    System.out.println("La sopa se resolvió en: "+pasos[0]+" pasos."); 
                    break;
                case 3:
                    // Se usa el case 3 para resolver la sopa de letras con otro archivo.
                    System.out.print("Ingrese el nombre del archivo con la sopa de letras: ");
                    String pathSopa = myScan.nextLine();
                    System.out.print("Ingrese el nombre del archivo con la lista de palabras a encontrar: ");
                    String pathLista = myScan.nextLine();
                    Solucion2 soluciona = new Solucion2(pathSopa, pathLista);
                    soluciona.imprimeSopa();
                    pasos = soluciona.solucionSopa();
                    System.out.println("La sopa se resolvió en: "+pasos[0]+" pasos.");
                    break;
                case 4:
                    // Se usa el case 4 para salir del programa.
                    break;
                default:
                    // Caso default para evitar bugs en caso de escribir un numero diferente de 0 - 4.
                    System.out.println("Número no válido, por favor ingrese un valor válido.");
                    opc = myScan.nextInt();
                    continue;
            }
        }
    }
}