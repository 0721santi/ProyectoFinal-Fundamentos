import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.*; // se importan todas las libreria del paquete .io (input output)
public class main{
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
    // este metodo tiene como funcion llenar el arreglo con las palabras que habian en el archivo .txt
    static void llenarClase(Palabra p[], ArrayList<String> lista){
        for(int i=0;i<lista.size();i++){
            p[i] = new Palabra(lista.get(i).length(), lista.get(i));
            System.out.println(lista.get(i));
        }
    }
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.print("Ingrese nombre del archivo: ");
        String path = myScan.nextLine();
        // Se crea y se organiza el arrayList
        ArrayList<String> listaPalabras = new ArrayList<>();
        creaArrayPalabras(path, listaPalabras);
        organizaLista(listaPalabras);
        // Se instancia la clase "Palabra" y se crean los objetos.
        Palabra p[] = new Palabra[listaPalabras.size()];
        llenarClase(p, listaPalabras);
        int maxlong = p[0].getLongitude();
        //crear la sopa de letras
        Random myRandPos = new Random();
        Random myRandDirection = new Random(4);
        char[][] sopa = new char[maxlong+2][maxlong+2];
        int x, y, direccion;
        for(int i = 0;i<listaPalabras.size();i++){
            direccion = myRandDirection.nextInt();
            switch(direccion){
                case 1:
                    y = myRandPos.nextInt(maxlong);
                    p[i].setYpos(y);
                    do{
                        x = myRandPos.nextInt(maxlong);
                        for(int j = x;i<p[i].getLongitude();j++){
                            if(sopa[y][j] == ' ' || sopa[y][j] == p[i].getName().charAt(j)){
                                sopa[y][j] = p[i].getName().charAt(j);
                            }
                            else{
                                x = maxlong + 1;
                                break;
                            }
                        }
                    }while(x+p[i].getLongitude()>maxlong);
                    p[i].setXpos(x);
                    break;
                case 2:
                    y = myRandPos.nextInt(maxlong);
                    p[i].setYpos(y);
                    do{
                        x = myRandPos.nextInt(maxlong);
                        for(int j = x;i<p[i].getLongitude();j--){
                            if(sopa[y][j] == ' ' || sopa[y][j] == p[i].getName().charAt(j)){
                                sopa[y][j] = p[i].getName().charAt(j);
                            }
                            else{
                                x = -1;
                                break;
                            }
                        }
                    }while(x-p[i].getLongitude()<0);
                    p[i].setXpos(x);
                case 3:
                    x = myRandPos.nextInt(maxlong);
                    p[i].setXpos(x);
                    do{
                        y = myRandPos.nextInt(maxlong);
                        for(int j = y;i<p[i].getLongitude();j++){
                            if(sopa[j][x] == ' ' || sopa[j][x] == p[i].getName().charAt(j)){
                                sopa[j][x] = p[i].getName().charAt(j);
                            }
                            else{
                                y = maxlong + 1;
                                break;
                            }
                        }
                    }while(y+p[i].getLongitude()>maxlong);
                    p[i].setYpos(y);
                case 4:
                    x = myRandPos.nextInt(maxlong);
                    p[i].setXpos(x);
                    do{
                        y = myRandPos.nextInt(maxlong);
                        for(int j = y;i<p[i].getLongitude();j--){
                            if(sopa[j][x] == ' ' || sopa[j][x] == p[i].getName().charAt(j)){
                                sopa[j][x] = p[i].getName().charAt(j);
                            }
                            else{
                                y = -1;
                                break;
                            }
                        }
                    }while(y+p[i].getLongitude()<0);
                    p[i].setYpos(y);
            }
        }
        for(int i = 0;i<maxlong;i++){
            for(int j = 0;j<maxlong;j++){
                System.out.print(sopa[j][i]);
            }
            System.out.println(" ");
        }
    }
}