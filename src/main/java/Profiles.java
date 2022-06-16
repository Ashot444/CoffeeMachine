import lombok.extern.java.Log;

import java.util.ArrayList;

@Log
public class Profiles{
    public static Menu menu = new Menu();
    public static CoffeeMachina cofMac = new CoffeeMachina();
    public static ArrayList<String> user = new ArrayList<>();
    public static ArrayList<Integer> drink = new ArrayList<>();
    public static ArrayList<Integer> numberDrink = new ArrayList<>();

    public static void GetUser(){
        if (user.size() != 0) {
            for (int i = 0; i < user.size(); i++) {
                System.out.println(i + " - " + user.get(i));
            }
            System.out.println("Вы кто?");
            menu.checkMenu(menu.in.nextLine());
            if (menu.getMenu() != 100) {

                for (int i = 0; i < user.size(); i++) {
                    if (user.indexOf(user.get(i)) == menu.getMenu()) {
                        if (drink.get(i) == 1) {
                            int j = 0;
                            while (j < numberDrink.get(i)) {
                                cofMac.MakeEspresso();
                                j++;
                            }
                        } else if (drink.get(i) == 2) {
                            int j = 0;
                            while (j < numberDrink.get(i)) {
                                cofMac.MakeCappuccino();
                                j++;
                            }
                        }
                    }else {
                        log.warning("!!! Такого пользователя нет !!! \n");
                        cofMac.switchActionsCrawlers();
                    }
                }
            } else {
                log.warning("!!! Некорректный ввод !!! \n");
                cofMac.switchActionsCrawlers();
            }
        } else {
            log.warning("!!! Пользователи отсутствуют!!! \n");
            cofMac.switchActionsCrawlers();
        }
    }
    public static void SetUser(){
        System.out.print("Введите имя: ");
        var name = (menu.in.nextLine());
        if (name != "") {
            var coincidence = false;
            for (int i = 0; i < user.size(); i++) {
                if (name.equals(user.get(i))) {
                    coincidence = true;
                    break;
                }
            }

            if (coincidence == false){
                cofMac.setUserName(name);
                System.out.print("Выберите напиток: \n" + "Кнопка 1 - ESPRESSO \n" + "Кнопка 2 - CAPPUCCINO \n");
                cofMac.checkSetDrink(menu.in.nextLine());
                System.out.print("Укажите колличество напитков: ");
                cofMac.checkNumberDrink(menu.in.nextLine());
            } else {
                log.warning("Пользователь с таким именем существует \n");
            }

        }else {
            log.warning("Пустое имя");
        }
    }
}
