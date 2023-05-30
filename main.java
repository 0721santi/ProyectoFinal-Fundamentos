import java.util.ArrayList;
import java.util.Scanner;
public class main{
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.print("Ingrese nombre del archivo: ");
        String path = myScan.nextLine();
        // Se crea y se organiza el arrayList
        ArrayList<String> listaPalabras = new ArrayList<>();
        MainSopaLetras.creaArrayPalabras(path, listaPalabras);
        MainSopaLetras.organizaLista(listaPalabras);
        // Se instancia la clase "Palabra" y se crean los objetos.
        Palabra p[] = new Palabra[listaPalabras.size()];
        int tamanoSopa = listaPalabras.get(0).length() * 2;
        char[][] sopa = MainSopaLetras.creaSopaDeLetras(listaPalabras, tamanoSopa, p);
        MainSopaLetras.llenaVacios(sopa, tamanoSopa);
        for(int i = 0;i<tamanoSopa;i++){
            for(int j = 0;j<tamanoSopa;j++){
                System.out.print(sopa[j][i] + " ");
            }
            System.out.println(" ");
        }
        MainSopaLetras.guardaSopa(sopa, tamanoSopa);
    }
}