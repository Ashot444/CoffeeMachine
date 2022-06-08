import lombok.extern.java.Log;

@Log
public class Menu {
    private static Integer menu;

    public static Integer getMenu() {
        return menu;
    }

    public static void setMenu(Integer n) { menu = n;}
    public  static void checkMenu(String a){
        try{
            setMenu((Integer.valueOf(a)));
        }catch (NumberFormatException k){
            log.warning("Неверное значение");
            setMenu(0);
            k.printStackTrace();
        }
    }
}
