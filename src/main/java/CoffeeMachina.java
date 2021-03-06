import lombok.extern.java.Log;
    @Log
    public class CoffeeMachina{

        private static Integer selectionCoffee; // селектор выбора кофе
        private static Integer water = 400; //остаток воды
        private static Integer coffee = 400; // остаток кофе
        private static Integer milk = 400; // остаток молока
        private static Integer clean = 10; // загрязнение
        private static Integer numMugs = 0; // колличество кружек кофе
        private static Integer numCapppuccinoCoffee = 0; //колличество готового капучино;
        private static Integer numEspressoCoffee = 0; //колличество готового экспрессо;
        private static String UserName; //имя пользователя


        // объекты для рецептов
        static CoffeeRecipe recipeEspresso = new CoffeeRecipe(100, 0, 10);
        static CoffeeRecipe recipeCapppuccino = new CoffeeRecipe(60, 40, 10);

        static Menu menu = new Menu();
        static Profiles profiles = new Profiles();



        public static void setUserName(String name){Profiles.user.add(name);}
        public static void setDrink(Integer name){Profiles.drink.add(name);}

        public static void checkSetDrink (String drink){
            try{
                String newString = drink.replace(" ", "");
                setDrink((Integer.valueOf(newString)));
            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                menu.setMenu(0);
                k.printStackTrace();
            }
        }
        public static void setNumberDrink (Integer in){Profiles.numberDrink.add(in);}


        public static void checkNumberDrink (String numberDrink){
            try{
                String newString =  numberDrink.replace(" ", "");
                setNumberDrink((Integer.valueOf(newString)));

            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                menu.setMenu(0);
                k.printStackTrace();
            }
        }


        public static Integer getSelectionCoffee() {
            return selectionCoffee;
        }

        public static void setSelectionCoffee(Integer n)
        {
            selectionCoffee = n;
        }

        public static void checkSelectionCoffee(String a){
            try{
                String b =  a.replace(" ", "");
                setSelectionCoffee((Integer.valueOf(b)));

            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                setSelectionCoffee(0);
                k.printStackTrace();
            }
        }

        public static Integer getWater()
        {
            return water;
        }


        public static void setWater(Integer water) {
                if (CoffeeMachina.water + water > 400) Error_1();
                else CoffeeMachina.water += water;
        }


        public static void checkWater(String water){
            try{
                setWater((Integer.valueOf(water)));
            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                menu.setMenu(0);
                k.printStackTrace();
            }
        }

        public static Integer getCoffee()
        {
            return coffee;
        }
        public static void setCoffee(Integer coffee) {
            if (CoffeeMachina.coffee + coffee > 400) Error_2();
            else CoffeeMachina.coffee += coffee;
        }

        public static void checkCoffee(String coffee){
            try{
                setCoffee((Integer.valueOf(coffee)));
            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                menu.setMenu(0);
                k.printStackTrace();
            }
        }

        public static Integer getMilk()
        {
            return milk;
        }
        public static void setMilk(Integer milk) {
            if (CoffeeMachina.milk + milk > 400) Error_1();
            else CoffeeMachina.milk += milk;
        }

        public static void checkMilk(String milk){
            try{
                setMilk((Integer.valueOf(milk)));
            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                menu.setMenu(0);
                k.printStackTrace();
            }
        }


        public static Integer getClean()
        {
            return clean;
        }
        public static void setClean(Integer clean) {
            if (clean != 1) Error();
            else CoffeeMachina.clean = 0;
        }

        public static void checkClean(String clean){
            try{
                setClean((Integer.valueOf(clean)));
            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                menu.setMenu(0);
                k.printStackTrace();
            }
        }

        public static void setNumMugs(Integer numMugs) {
            CoffeeMachina.numMugs = numMugs ;
        }

        public static Integer getNumMugs()
        {
            return numMugs;
        }

        public static void checkNumMugs(String numMugs){
            try{
                setNumMugs((Integer.valueOf(numMugs)));
            }catch (NumberFormatException k){
                log.warning("Неверное значение");
                menu.setMenu(0);
                k.printStackTrace();
            }
        }




        public static void accumulationClean(){
            CoffeeMachina.clean += 10;
        }
        public static void accumulationMakeCapCoffee(){CoffeeMachina.numCapppuccinoCoffee += 1;}
        public static void accumulationMakeEspCoffee(){CoffeeMachina.numEspressoCoffee += 1;}


        public enum Machine_OFF {
            ON
        }

        public enum Machine_ON {
            OFF,
            CONTAINER,
            MAKE_COFFEE,
            USERS,
            HISTORY
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

        public enum Number_Servings{
            ONE_SERVING,
            THREE_SERVINGS,
            NUMBER_SERVINGS,
            RECIPE
        }

        public enum Users{
            SELECT_USER,
            ADD_USER
        }

        public static void Error(){
            log.warning("**** Ошибка операции ****");
        };
        public static void Error_1(){
            log.warning("**** Перелив ****");
        };
        public static void Error_2(){
            log.warning("**** Лимит зерен превышен ****");
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
                    log.info("!!! Кофе готово !!!");
                    accumulationMakeEspCoffee();
                } else{
                    log.warning("!!! ТРЕБУЕТСЯ ЧИСТКА !!!");
                }
            }
            else {
                log.warning("!!! НЕ ХВАТАЕТ ИНГРЕДИЕНТОВ !!!");
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
                    log.info("!!! Кофе готово !!!");
                    accumulationMakeCapCoffee();
                } else{
                    log.warning("!!! ТРЕБУЕТСЯ ЧИСТКА !!!");
                }
            }
            else {
                log.warning("!!! НЕ ХВАТАЕТ ИНГРЕДИЕНТОВ !!!");
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

        public static void NumberServings(){
            Integer i = 0;
            System.out.println("Кнопка 0 - BACK");
            for (Number_Servings kol_koffee:Number_Servings.values()){
                System.out.println( "Кнопка " + (++i) + " - " + kol_koffee);
            }
        }

        public static void ActionsCrawlers(){
            Integer i = 0;
            System.out.println("Кнопка 0 - BACK");
            for (Users actUser:Users.values()){
                System.out.println( "Кнопка " + (++i) + " - " + actUser);
            }
        }

        //методы
        public static void History(){
            System.out.println("!!! Приготовленные напитки !!!");
            System.out.println("Капучино " + CoffeeMachina.numCapppuccinoCoffee + " шт");
            System.out.println("Эспрессо " + CoffeeMachina.numEspressoCoffee + " шт" + "\n");
            switchMachineOn();
        }


        public static void RecipeEspresso(){
            System.out.println( "!!! Рецепт приготовления экспрессо !!!" );
            System.out.println(recipeEspresso.water + "мл. " + "воды;");
            System.out.println(recipeEspresso.coffee + "г. " + "кофе." + "\n");
        }

        public static void RecipeCappuccino(){
            System.out.println( "!!! Рецепт приготовления экспрессо !!!" );
            System.out.println(recipeCapppuccino.water + "мл. " + "воды;");
            System.out.println(recipeCapppuccino.coffee + "г. " + "кофе;");
            System.out.println(recipeCapppuccino.milk + "мл. " + "молока." + "\n");
        }

        public static void switchMachineOff(){
            MachineOff();
            menu.checkMenu(menu.in.nextLine());
            switch (menu.getMenu()){
                case 1 -> switchMachineOn();
                default -> {Error();switchMachineOff();}
            }
        }

        public static void switchMachineOn(){
            MachineOn();
            menu.checkMenu(menu.in.nextLine());
            switch (menu.getMenu()){
                case 1 -> switchMachineOff();
                case 2 -> switchMachineContainers();
                case 3 -> switchMachineMenu();
                case 4 -> switchActionsCrawlers();
                case 5 -> History();
                default -> {Error(); switchMachineOn();}
            }
        }

        public static void switchMachineContainers(){
            MachineContainers();
            menu.checkMenu(menu.in.nextLine());
            switch (menu.getMenu()){
                case 0 -> {
                    switchMachineOn();
                }
                case 1 -> {
                    System.out.println("До полного необходимо добавить " + (400 - getWater()) +" мл");
                    System.out.println("Сколько " + CoffeeMachina.Machine_Container.WATER.name() +" добавить?");
                    checkWater(menu.in.nextLine());
                    switchMachineContainers();
                }
                case 2 -> {
                    System.out.println("До полного необходимо добавить " + (400 - getCoffee()) +" г");
                    System.out.println("Сколько " + CoffeeMachina.Machine_Container.COFFEE.name() +" добавить?");
                    checkCoffee(menu.in.nextLine());
                    switchMachineContainers();
                }

                case 3 -> {
                    System.out.println("До полного необходимо добавить " + (400 - getMilk()) +" мл");
                    System.out.println("Сколько " + CoffeeMachina.Machine_Container.MILK.name() +" добавить?");
                    checkMilk(menu.in.nextLine());
                    switchMachineContainers();
                }
                case 4 -> {
                    System.out.println("Кнопка 1 - " + CoffeeMachina.Machine_Container.CLEAN.name());
                    checkClean(menu.in.nextLine());
                    switchMachineContainers();
                }

                default -> {Error(); switchMachineOn();}
            }
        }

        public static void switchMachineMenu(){
            MachineMenu();
            checkSelectionCoffee(menu.in.nextLine());
            switch (getSelectionCoffee()){
                case 0 -> {
                    switchMachineOn();
                }
                case 1, 2 -> {
                    switchNumberServings();
                    switchMachineMenu();
                }
                default -> {Error(); switchMachineMenu();}
            }
        }

        public static void switchNumberServings() {
            if (getSelectionCoffee() == 1) {
                NumberServings();
                menu.checkMenu(menu.in.nextLine());
                switch (menu.getMenu()) {
                    case 0 -> {
                        switchMachineMenu();
                    }
                    case 1 -> {
                        MakeEspresso();
                        switchNumberServings();
                    }
                    case 2 -> {
                        for(int i=1; i<=3; i++){
                            MakeEspresso();
                        }
                        switchNumberServings();
                    }
                    case 3 -> {
                        System.out.print("Укажите нужное колличество напитка: ");
                        checkNumMugs(menu.in.nextLine());
                        for(int i = 1; i <= getNumMugs(); i++){
                            MakeEspresso();
                        }
                        switchNumberServings();
                    }
                    case 4 -> {
                        RecipeEspresso();
                        switchNumberServings();
                    }
                    default -> {
                        Error();
                        switchNumberServings();
                    }
                }
            }
            else {
                NumberServings();
                menu.checkMenu(menu.in.nextLine());
                switch (menu.getMenu()) {
                    case 0 -> {
                        switchMachineMenu();
                    }
                    case 1 -> {
                        MakeCappuccino();
                        switchNumberServings();
                    }
                    case 2 -> {
                        for(int i=1; i<=3; i++){
                            MakeCappuccino();
                        }
                        switchNumberServings();
                    }
                    case 3 -> {
                        System.out.print("Укажите нужное колличество напитка: ");
                        checkNumMugs(menu.in.nextLine());
                        for(int i = 1; i <= getNumMugs(); i++){
                            MakeCappuccino();
                        }
                        switchNumberServings();
                    }
                    case 4 -> {
                        RecipeCappuccino();
                        switchNumberServings();
                    }
                    default -> {
                        Error();
                        switchNumberServings();
                    }
                }


            }
        }

        public static void switchActionsCrawlers(){
            ActionsCrawlers();
            menu.checkMenu(menu.in.nextLine());
            switch (menu.getMenu()){
                case 0 -> {
                    switchMachineOn();
                }
                case 1 -> {
                    profiles.GetUser();
                    switchActionsCrawlers();
                }
                case 2 -> {
                    profiles.SetUser();
                    switchActionsCrawlers();
                }
                default -> {Error(); switchMachineMenu();}
            }
        }


        public static void main(String[] args) {
            log.info("Запуск программы");
            switchMachineOff();
        }
    }

