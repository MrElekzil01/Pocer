package Pocer;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private Pack pa;
    private int numberPlayers;
    private List<Player> players = new ArrayList<>();

    public Menu(Pack pack, int numberP) {
        // начальные настройки
        pa = pack;
        numberPlayers = numberP;
    }

    private void printFirstMenu() {
        System.out.println("1. Начать игру");
        System.out.println("2. Выбрать колоду");
        System.out.println("3. Выбрать количество игроков");
        System.out.println("4. Выход");
    }

    private void printSecondMenu() {
        System.out.println("1. Перемешать колоду");
        System.out.println("2. Заменить карты");
        System.out.println("3. Вскрыть карты с другими игроками");
        System.out.println("4. Возврат в предыдущее меню");
    }

    private void printAdditionalMenu() {
        System.out.println("1. 36 карт");
        System.out.println("2. 52 карты");
        System.out.println("3. Назад");
    }

    public void start() {
        boolean isExit = true;
        while (isExit) {
            printFirstMenu();
            System.out.print("Введите номер меню: ");
            try {
                InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input);
                int choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1: {
                        startGame(reader);
                        break;
                    }
                    case 2: {
                        processAdditionalMenu(reader);
                        break;
                    }
                    case 3: {
                        System.out.print("Введите количество игроков: ");
                        numberPlayers = Integer.parseInt(reader.readLine());
                        break;
                    }
                    case 4: {
                        isExit = false;
                        input.close();
                        break;
                    }
                    default: {
                        System.out.println("Вы ввели неверное значение меню...");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Вы ввели не число");
            }
            System.out.println();
        }
    }

    private void startGame(BufferedReader reader) {
        for (int i = 0; i < numberPlayers; i++) { // создание игроков
            Player player = new Player();
            player.getCarts(pa);
            players.add(player);
        }
        for (int i = 0; i < numberPlayers; i++) { // определение комбинаций
            Combination comb = new Combination(players.get(i));
        }
        System.out.println();
        boolean isExit = true;
        while (isExit) {
            try {
                System.out.println("Ваши карты: ");
                showCarts(0);
                printSecondMenu();
                System.out.print("Введите номер меню: ");
                int choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1: {
                        pa.mixColoda();
                        System.out.println("Колода перемешана");
                        break;
                    }
                    case 2: {
                        switchCarts(reader);
                        break;
                    }
                    case 3: {
                        win();
                        isExit = false;
                        removeCartsAndPlayers();
                        pa.mixColoda();
                        break;
                    }
                    case 4: {
                        isExit = false;
                        removeCartsAndPlayers();
                        pa.mixColoda();
                        break;
                    }
                    default: {
                        System.out.println("Вы ввели неверное значение меню...");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Вы ввели не число");
            }
            System.out.println();
        }
    }

    private void showCarts(int i) {
        for(int j = 0 ; j < players.get(i).getHand().size();j++){
            System.out.println((j+1) + ". " + players.get(i).getHand().get(j).getWeight() + " " + players.get(i).getHand().get(j).getColor());
        }
        System.out.println();
        System.out.println("Комбинация: "+players.get(i).getCombination());
        System.out.println();
    }

    private void switchCarts(BufferedReader reader) {
        ArrayList<Integer> carts = new ArrayList<>();
        System.out.print("Введите номера карт через пробел: ");
        try {
            String numbers = reader.readLine();
            for (String oneNumber : numbers.split(" ")) {
                carts.add(Integer.parseInt(oneNumber)-1);
            }
            players.get(0).switchCarts(carts, pa);
            new Combination(players.get(0));
        } catch (IOException e) {
            System.out.println("Ошибка ввода, карты не были заменены");
        }
    }

    private void removeCartsAndPlayers(){
        for (int i = 0; i < numberPlayers; i++) { // удаление карт и игроков
            players.get(0).removeCarts(pa);
            players.remove(0);
        }
    }

    private void processAdditionalMenu(BufferedReader reader) {
        printAdditionalMenu();
        System.out.print("Выберите колоду: ");
        try {
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1: {
                    pa = new Pack_36();
                    break;
                }
                case 2: {
                    pa = new Pack_52();
                    break;
                }
                case 3: {
                    System.out.println("Вы вернулись назад...");
                    break;
                }
                default: {
                    System.out.println("Вы ввели неверное значение меню, колода останется прежней...");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Вы ввели не число");
        }
    }

    private void win() {
        System.out.println();
        System.out.println("Ваши карты: ");
        showCarts(0);
        for (int i = 1; i < numberPlayers; i++) {
            System.out.println("Карты " + (i + 1) + "-того игрока");
            showCarts(i);
        }
            Win wins = new Win();
            StringBuilder draw = new StringBuilder();
            int win = wins.win(players, draw);
            if (!draw.toString().isEmpty()) {
                System.out.println(draw.toString());
            } else {
                if(win==0){
                    System.out.println("Поздравляю, вы выиграли!");
                }else {
                    System.out.println("Победитель: " + (win + 1) + " игрок");
                }
                System.out.println();
                showCarts(win);
            }
    }
}
