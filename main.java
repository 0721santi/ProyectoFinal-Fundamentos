import java.util.ArrayList;
import java.util.Scanner;
public class main{
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        /*
         * Se instancMainSopaLetras "MainSopaLetras" de la clase MainSopaLetras para poder manejar los metodos que son heredados
         * de la interfaz.
         */
        ArrayList<String> listaPalabras = new ArrayList<>();
        Palabra[] p = new Palabra[listaPalabras.size()];
        int tamanoSopa = 0, opc;
        char[][] sopa = new char[tamanoSopa][tamanoSopa];
        boolean check = false;
        while(true){
            opc = MainSopaLetras.getMenu();
            switch(opc){
                case 1:
                    listaPalabras.clear();
                    System.out.print("Ingrese nombre del archivo: ");
                    String path = myScan.nextLine();
                    // Se crea y se organiza el arrayList
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
                    if(!check){
                        System.out.println("No puedes usar esta opción sin haber leido y creado una sopa de letras.");
                        continue;
                    }
                    int[] pasos = solve.solucionSopa();
                    for(int i = 0;i<sopa.length;i++){
                        for(int j=0;j<sopa[0].length;j++){
                            System.out.print(sopa[i][j]+" ");
                        }
                        System.out.println(" ");
                    }
                    solve.imprimePosiciones(p);
                    System.out.println("La sopa se resolvió en: "+pasos[0]+" pasos."); 
                    break;
                case 3:
                    
                    break;
            }
        }
    }
}