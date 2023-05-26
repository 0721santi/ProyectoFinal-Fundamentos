import java.util.Scanner;
import java.util.ArrayList;
import java.io.*; // se importan todas las libreria del paquete .io (input output)
public class MainSopaLetras {
    // se crea un metodo para buscar el archivo .txt para llenar el arreglo
    // se busco en: (url) para lograr realizar la busqueda de un archivo
    static void creaArrayPalabras(String path, ArrayList<String> lista){
        // se usa un try - catch para evitar que salga un error tipo java exception como out bound 
        try {
            File doc = new File(path+".txt");
            Scanner readDoc = new Scanner(doc);       
            while(readDoc.hasNextLine()){
                lista.add(readDoc.nextLine());
            }
        } catch (Exception e) {
            System.out.println("No existe un archivo con nombre "+path+".");
        }
    }
    // se usa otro metodo para organizar el arreglo de mayor a menor
    static void organizaLista(ArrayList<String> lista){
        int n = lista.size();
        for(int i = 0;i<n-1;i++){
            int maxIndex = i;
            for(int j = i;j<n;j++){
                if(lista.get(j).length() > lista.get(maxIndex).length()){
                    maxIndex = j;
                }
            }
            String temp = lista.get(maxIndex);
            lista.set(maxIndex, lista.get(i));
            lista.set(i, temp);
        }
    }
    static char[][] creaSopaDeLetras(ArrayList<String> lista){
        int tamañoSopa = lista.get(0).length() * 2;
        char[][] sopaLetras = new char[tamañoSopa][tamañoSopa];

        return sopaLetras;
    }
}
