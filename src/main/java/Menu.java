import lombok.extern.java.Log;

import java.util.Scanner;

@Log
public class Menu {
    private static Integer menu;
    static Scanner in = new Scanner(System.in);

    public static Integer getMenu() {
        return menu;
    }

    public static void setMenu(Integer n) { menu = n;}
    public  static void checkMenu(String a){
        try{
            String newString =  a.replace(" ", "");
            setMenu((Integer.valueOf(newString)));
        }catch (NumberFormatException k){
            log.warning("Неверное значение");
            setMenu(100);
            k.printStackTrace();
        }
    }
}
