import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class main{
    // este metodo tiene como funcion llenar el arreglo con las palabras que habian en el archivo .txt
    // static void llenarClase(Palabras p[], ArrayList<String> lista){
    //     for(int i=0;i<lista.size();i++){
    //         p[i] = new Palabras(lista.get(i).length(), lista.get(i));
    //         System.out.println(lista.get(i));
    //     }
    // }
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.print("Ingrese nombre del archivo: ");
        String path = myScan.nextLine();
        // Se crea y se organiza el arrayList
        ArrayList<String> listaPalabras = new ArrayList<>();
        MainSopaLetras.creaArrayPalabras(path, listaPalabras);
        MainSopaLetras.organizaLista(listaPalabras);
        // Se instancia la clase "Palabra" y se crean los objetos.
        Palabras p[] = new Palabras[listaPalabras.size()];
        //llenarClase(p, listaPalabras);
        int maxlong = p[0].getLongitude();
        //crear la sopa de letras
        Random myRandPos = new Random();
        Random myRandDirection = new Random();
        int tamañoSopa = maxlong+2;
        char[][] sopa = new char[tamañoSopa][tamañoSopa];
        for(int i = 0;i<tamañoSopa;i++){
            for(int j = 0;j<tamañoSopa;j++){
                sopa[i][j] = ' ';
            }
        }
        int x, direccion;
        int y = 0;
        // for(int i = 0;i<p[0].getLongitude();i++){
        //     if(sopa[0][i] == '\0'){
        //         sopa[0][i] = p[0].getName().charAt(i);
        //         System.out.print(sopa[0][i]);
        //     }
        // }
        for(int i = 0;i<tamañoSopa;i++){
            for(int j = 0;j<tamañoSopa;j++){
                System.out.print(sopa[j][i] + " ");
            }
            System.out.println(" ");
        }
    }
}