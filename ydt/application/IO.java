package ydt.application;

import java.io.*;

public class IO{
    public static String saisieChaine(){
        try {
            BufferedReader buff = new BufferedReader (new InputStreamReader(System.in));
            String chaine=buff.readLine();
            return chaine;
        }
        catch(IOException e) {
            System.out.println(" impossible de travailler"+e);
            return null;
        }
    }

    public static int saisieEntier(){
        try{
            BufferedReader buff = new BufferedReader
                (new InputStreamReader(System.in));
            String chaine=buff.readLine();
            int num = Integer.parseInt(chaine);
            return num;
        }
        catch(IOException e){return 0;}
    }
}
