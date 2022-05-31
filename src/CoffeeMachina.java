import java.util.Scanner;

    public class CoffeeMachina{
        static Scanner in = new Scanner(System.in);

        private static Integer menu;
        private static Integer water = 30; //остаток воды
        private static Integer coffee = 10; // остаток кофе
        private static Integer milk = 35; // остаток молока
        private static Integer clean = 40; // загрязнение

        // объекты для рецептов
        static CoffeeRecipe recipeEspresso = new CoffeeRecipe(100, 0, 10);
        static CoffeeRecipe recipeCapppuccino = new CoffeeRecipe(60, 40, 10);

        public static Integer getMenu() {
            return menu;
        }
        public static void setMenu(Integer n)
        {
            menu = n;
        }

        public static Integer getWater()
        {
            return water;
        }

        public static void setWater(Integer water) {
            if (CoffeeMachina.water + water > 140) Error_1();
            else CoffeeMachina.water += water;
        }

        public static Integer getCoffee()
        {
            return coffee;
        }
        public static void setCoffee(Integer coffee) {
            if (CoffeeMachina.coffee + coffee > 40) Error_2();
            else CoffeeMachina.coffee += coffee;
        }

        public static Integer getMilk()
        {
            return milk;
        }
        public static void setMilk(Integer milk) {
            if (CoffeeMachina.milk + milk > 60) Error_1();
            else CoffeeMachina.milk += milk;
        }


        public static Integer getClean()
        {
            return clean;
        }
        public static void setClean(Integer clean) {
            if (clean != 1) Error();
            else CoffeeMachina.clean = 0;
        }

        public static void accumulationClean(){
            CoffeeMachina.clean += 10;
        }


        public enum Machine_OFF {
            ON
        }

        public enum Machine_ON {
            OFF,
            CONTAINER,
            MAKE_COFFEE,
            USERS,
            HISTORY,
        }

        public enum Machine_Container {
            WATER,
            COFFEE,
            MILK,
            CLEAN
        }

        public enum Machine_Menu{
            ESPRESSO,
            CAPPUCCINO
        }

        public static void Error(){
            System.out.println("**** Ошибка операции ****");
        };
        public static void Error_1(){
            System.out.println("**** Перелив ****");
        };

        public static void Error_2(){
            System.out.println("**** Лимит зерен превышен ****");
        };


        //приготовление кофе

        public static void MakeEspresso(){
            if (CoffeeMachina.water >= recipeEspresso.water && CoffeeMachina.coffee >= recipeEspresso.coffee) {
                if (getClean() < 70) {
                    int ostWater = getWater() - recipeEspresso.water;
                    int ostCoffee = getCoffee() - recipeEspresso.coffee;
                    CoffeeMachina.water = getWater() - getWater();
                    CoffeeMachina.coffee = getCoffee() - getCoffee();
                    setWater(ostWater);
                    setCoffee(ostCoffee);
                    accumulationClean();
                    System.out.println("!!! Кофе готово !!!");
                } else{
                    System.out.println("!!! ТРЕБУЕТСЯ ЧИСТКА !!!");
                }
            }
            else {
                System.out.println("!!! НЕ ХВАТАЕТ ИНГРЕДИЕНТОВ !!!");
            }
        }

        public static void MakeCappuccino(){
            if (CoffeeMachina.water >= recipeCapppuccino.water && CoffeeMachina.coffee >= recipeCapppuccino.coffee && CoffeeMachina.milk >= recipeCapppuccino.milk){
                if (getClean() < 70) {
                    int ostWater = getWater() - recipeCapppuccino.water;
                    int ostCoffee = getCoffee() - recipeCapppuccino.coffee;
                    int ostMilk = getMilk() - recipeCapppuccino.milk;
                    CoffeeMachina.water = getWater() - getWater();
                    CoffeeMachina.coffee = getCoffee() - getCoffee();
                    CoffeeMachina.milk = getMilk() - getMilk();
                    setWater(ostWater);
                    setCoffee(ostCoffee);
                    setMilk(ostMilk);
                    accumulationClean();
                    System.out.println("!!! Кофе готово !!!");
                } else{
                    System.out.println("!!! ТРЕБУЕТСЯ ЧИСТКА !!!");
                }
            }
            else {
                System.out.println("!!! НЕ ХВАТАЕТ ИНГРЕДИЕНТОВ !!!");
            }
        }


        //Выводы меню

        public static void MachineOff(){
            Integer i = 0;
            for (Machine_OFF mac_off:Machine_OFF.values()){
                System.out.println( "Кнопка " + (++i) + " - " + mac_off);
            }
        }

        public static void MachineOn(){
            Integer i = 0;
            for (Machine_ON mac_on:Machine_ON.values()){
                System.out.println( "Кнопка " + (++i) + " - " + mac_on);
            }
        }

        public static void MachineContainers(){
            Integer i = 0;
            System.out.println("Кнопка 0 - BACK");
            for (Machine_Container mac_status:Machine_Container.values()){
                System.out.print( "Кнопка " + (++i) + " - " + mac_status + " = ");
                switch (i){
                    case 1 -> System.out.println(getWater());
                    case 2 -> System.out.println(getCoffee());
                    case 3 -> System.out.println(getMilk());
                    case 4 -> System.out.println(getClean());
                }
            }
        }

        public static void MachineMenu(){
            Integer i = 0;
            System.out.println("Кнопка 0 - BACK");
            for (Machine_Menu mac_make:Machine_Menu.values()){
                System.out.println( "Кнопка " + (++i) + " - " + mac_make);
            }
        }

        //методы

        public static void switchMachineOff(){
            MachineOff();
            setMenu(in.nextInt());
            switch (getMenu()){
                case 1 -> switchMachineOn();
                default -> {Error();switchMachineOff();}
            }
        }

        public static void switchMachineOn(){
            MachineOn();
            setMenu(in.nextInt());
            switch (getMenu()){
                case 1 -> switchMachineOff();
                case 2 -> switchMachineContainers();
                case 3 -> switchMachineMenu();
                default -> {Error(); switchMachineOn();}
            }
        }

        public static void switchMachineContainers(){
            MachineContainers();
            setMenu(in.nextInt());
            switch (getMenu()){
                case 0 -> {
                    switchMachineOn();
                }
                case 1 -> {
                    System.out.println("До полного необходимо добавить " + (140 - getWater()) +" мл");
                    System.out.println("Сколько " + CoffeeMachina.Machine_Container.WATER.name() +" добавить?");
                    setWater(in.nextInt());
                    switchMachineContainers();
                }
                case 2 -> {
                    System.out.println("До полного необходимо добавить " + (40 - getCoffee()) +" г");
                    System.out.println("Сколько " + CoffeeMachina.Machine_Container.COFFEE.name() +" добавить?");
                    setCoffee(in.nextInt());
                    switchMachineContainers();
                }

                case 3 -> {
                    System.out.println("До полного необходимо добавить " + (60 - getMilk()) +" мл");
                    System.out.println("Сколько " + CoffeeMachina.Machine_Container.MILK.name() +" добавить?");
                    setMilk(in.nextInt());
                    switchMachineContainers();
                }
                case 4 -> {
                    System.out.println("Кнопка 1 - " + CoffeeMachina.Machine_Container.CLEAN.name());
                    setClean(in.nextInt());
                    switchMachineContainers();
                }

                default -> {Error(); switchMachineOn();}
            }
        }

        public static void switchMachineMenu(){
            MachineMenu();
            setMenu(in.nextInt());
            switch (getMenu()){
                case 0 -> {
                    switchMachineOn();
                }
                case 1 -> {
                    MakeEspresso();
                    switchMachineMenu();
                }
                case 2 -> {
                    MakeCappuccino();
                    switchMachineMenu();
                }
                default -> {Error(); switchMachineOn();}
            }
        }


        public static void main(String[] args) {
            switchMachineOff();
        }

    }

