import lombok.extern.java.Log;

import java.util.ArrayList;

@Log
public class Profiles extends CoffeeMachina {
    public static ArrayList<String> user = new ArrayList<>();
    public static ArrayList<Integer> drink = new ArrayList<>();
    public static ArrayList<Integer> numberDrink = new ArrayList<>();

    public static void GetUser(){
        if (user.size() != 0) {
            for (int i = 0; i < user.size(); i++) {
                System.out.println(i + " - " + user.get(i));
            }
            System.out.println("Вы кто?");
            setMenu(in.nextInt());
            for (int i = 0; i < user.size(); i++) {
                if (user.indexOf(user.get(i)) == getMenu()) {
                    if (drink.get(i) == 1) {
                        int j = 0;
                        while (j < numberDrink.get(i)) {
                            MakeEspresso();
                            j++;
                        }
                    } else if (drink.get(i) == 2) {
                        int j = 0;
                        while (j < numberDrink.get(i)) {
                            MakeCappuccino();
                            j++;
                        }
                    }
                }
            }
        } else {
            log.info("!!! Пользователи отсутствуют !!! \n");
            switchActionsCrawlers();
        }

    }
    public static void SetUser(){
        System.out.print("Введите имя: ");
        var name = (in.next());
        var coincidence = false;
        for (int i = 0; i < user.size(); i++) {
            if (name.equals(user.get(i))) {
                coincidence = true;
                break;
            }
        }

        if (coincidence == false){
            setUserName(name);
            System.out.print("Выберите напиток: \n" + "Кнопка 1 - ESPRESSO \n" + "Кнопка 2 - CAPPUCCINO \n");
            setDrink(in.nextInt());
            System.out.print("Укажите колличество напитков: ");
            setNumberDrink(in.nextInt());
        } else {
            log.warning("Пользователь с таким именем существует \n");
        }
    }

}
