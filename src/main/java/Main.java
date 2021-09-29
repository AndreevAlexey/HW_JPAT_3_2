import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // вывод поля с положением лягушки в консоль
    static void printField(Frog frog) {
        for(int i = Frog.MIN_POSITION; i <= Frog.MAX_POSITION; i++){
            if(i == frog.position) System.out.print('X');
            else System.out.print('_');
        }
        System.out.print("\n");
    }
    public static void main(String[] args) {
        List<FrogCommand> comands = new ArrayList<>();
        Frog frog = new Frog();
        FrogCommand cmd;
        int curCommand = -1;
        int steps;
        Scanner scan = new Scanner(System.in);
        String input;
        // текст меню пользователя
        String menu = "Введите команду:\n" +
                "+N - прыгни на N шагов направо\n" +
                "-N - прыгни на N шагов налево\n" +
                "<< - Undo (отмени последнюю команду)\n" +
                ">> - Redo (повтори отменённую команду)\n" +
                "!! - повтори последнюю команду\n" +
                "0 - выход";
        // начало работы программы
        while(true) {
            // вывод поля
            printField(frog);
            // вывод меню
            System.out.println(menu);
            // считываем введенные данные
            input = scan.nextLine();
            // проверка на выход
            if("0".equals(input)) break;
            // Undo (отмени последнюю команду)
            if("<<".equals(input)) {
                // не было еще комманд
                if(curCommand == -1) System.out.println("Нечего отменять!");
                else {
                    // отмена
                    comands.get(curCommand).undo();
                    curCommand--;
                }
            // Redo (повтори отменённую команду)
            } else if(">>".equals(input)) {
                // нет отмененной команды
               if(curCommand == comands.size()-1) System.out.println("Нечего повторять!");
               else {
                   curCommand++;
                   comands.get(curCommand).doit();
               }
            // прыжок
            } else {
                // текущая команда не последняя(были отмены)
                if(curCommand != comands.size()-1) {
                    // удаляем отмены
                    for(int i = curCommand + 1; i < comands.size(); i++) {
                        comands.remove(i);
                    }
                }
                // !! - повтори последнюю команду
                if("!!".equals(input)) {
                    cmd = comands.get(curCommand);
                } else {
                    // количество шагов с направлением
                    try {
                        steps = Integer.parseInt(input);
                    } catch (Exception exp) {
                        System.out.println("Неверно указано число для прыжка!");
                        continue;
                    }
                    // новая команда
                    cmd = new FrogJump(frog, steps);
                }
                // выполнение
                if(cmd.doit()) {
                    curCommand++;
                    comands.add(cmd);
                } else {
                    System.out.println("Выход за пределы поля!");
                }
            }
        }
    }
}
