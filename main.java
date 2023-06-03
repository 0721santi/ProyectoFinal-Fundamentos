import java.util.ArrayList;
import java.util.Scanner;
public class main{
    public static void main(String[] args){
        Scanner myScan = new Scanner(System.in);
        ArrayList<String> listaPalabras = new ArrayList<>();
        Palabra[] p = new Palabra[listaPalabras.size()];
        int tamanoSopa = 0, opc;
        char[][] sopa = new char[tamanoSopa][tamanoSopa];
        int[] pasos;
        boolean check = false;
        /*
         * Se usa "Switch" para crear el menu, si se ingresa 1, se crea la sopa a partir de una archivo .txt, si se
         * ingresa 2, se resuelve la sopa con los datos precargados en la opción 1 y,
         * si se ingresa 3, se resuelve una sopa de letras que el usuario ingresa con una lista de palabras que
         * también ingresa el usuario.
         */
        while(true){
            opc = MainSopaLetras.getMenu();
            switch(opc){
                case 1:
                /*
                 * Se usa el método .clear() de ArrayList en listapalabras, para que cuando se vuelva a pedir
                 * crear una sopa de letras, se haga 100% con las palabaras del nuevo archivo y no que las 
                 * agregue a las de una sopa anterior.
                 */
                    listaPalabras.clear();
                    System.out.print("Ingrese nombre del archivo: ");
                    String path = myScan.nextLine();
                    // Se crea y se organiza el arrayList con las palabras que ingresó el usuario.
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
                    /*
                     * Se crea este condicional con el fin de asegurar que para poder ejecutar la solución de la sopa
                     * ya haya previamente creado una el usuario.
                     */
                    if(!check){
                        System.out.println("No puedes usar esta opción sin haber leido y creado una sopa de letras.");
                        continue;
                    }
                    Solucion1 solve = new Solucion1(p, sopa);
                    pasos = solve.solucionSopa();
                    solve.imprimeSopa();
                    solve.imprimePosiciones(p);
                    System.out.println("La sopa se resolvió en: "+pasos[0]+" pasos.\n"); 
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del archivo con la sopa de letras: ");
                    String pathSopa = myScan.nextLine();
                    System.out.print("Ingrese el nombre del archivo con la lista de palabras a encontrar: ");
                    String pathLista = myScan.nextLine();
                    Solucion2 soluciona = new Solucion2(pathSopa, pathLista);
                    soluciona.imprimeSopa();
                    pasos = soluciona.solucionSopa();
                    System.out.println("La sopa se resolvió en: "+pasos[0]+" pasos.\n");
                    break;
                case 4:
                    System.out.println("Gracias por utilizar el programa.");
                    System.exit(0);
                default:
                    System.out.println("Número no válido, por favor ingrese un valor válido.\n");
                    break;
            }
        }
        
    }
}