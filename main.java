import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
public class main{
    static void creaArrayPalabras(String path, ArrayList<String> lista){
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
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.print("Ingrese nombre del archivo: ");
        String path = myScan.nextLine();
        ArrayList<String> listaPalabras = new ArrayList<>();
        creaArrayPalabras(path, listaPalabras);
        for(int i=0;i<listaPalabras.size();i++){
            System.out.println(listaPalabras.get(i));
        }
    }
}