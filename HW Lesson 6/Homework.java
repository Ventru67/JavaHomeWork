import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*1. Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
2. Создать множество ноутбуков.
3. Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например: “Введите цифру, соответствующую необходимому критерию:

1 - ОЗУ

2 - Объем ЖД

3 - Операционная система

4 - Цвет …

Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям. https://javarush.com/groups/posts/1939-comparator-v-java
*/

public class Homework_1 {
    public static void main(String[] args) {
        Laptop laptop1 = new Laptop("Samsung", 16, 512, "windows", "cherry");
        Laptop laptop2 = new Laptop("Lenovo", 8, 256, "windows", "black");
        Laptop laptop3 = new Laptop("Macbook", 32, 512, "macos", "silver");

        Set<Laptop> laptops = new HashSet<>();
        laptops.add(laptop1);
        laptops.add(laptop2);
        laptops.add(laptop3);

        Map<Integer, String> filter = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        // выбор критериев
        while (flag) {
            System.out.println("Задайте критерии фильтрации: \n" +
                    "1 - ОЗУ\n" +
                    "2 - Объём ЖД\n" +
                    "3 - Операционная система\n" +
                    "4 - Цвет\n" +
                    "5 - Запустить поиск");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("Введите минимальный объем памяти: ");
                        filter.put(1, scanner.next());
                        break;
                    case 2:
                        System.out.println("Введите минимальный объем жесткого диска:");
                        filter.put(2, scanner.next());
                        break;
                    case 3:
                        System.out.println("Введите операционную систему: ");
                        filter.put(3, scanner.next().toLowerCase());
                        break;
                    case 4:
                        System.out.println("Введите цвет ноутбука:");
                        filter.put(4, scanner.next().toLowerCase());
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        System.out.println("Неверное число");
                }
            } else {
                flag = false;
                System.out.println("Вы ввели не число");
            }
            //System.out.println(filter);
        }

        Set<Laptop> results = new HashSet<>();

        for (Laptop element : laptops) {
            if ((filter.containsKey(1) && Integer.parseInt(filter.get(1)) > element.getRam())
                    || (filter.containsKey(2) && Integer.parseInt(filter.get(2)) > element.getHdd())
                    || (filter.containsKey(3) && !filter.get(3).equals(element.getOs()))
                    || (filter.containsKey(4) && !filter.get(4).equals(element.getColor()))) {
                results.add(element);
            }
        }
        laptops.removeAll(results); // разность двух множеств

        for (Laptop laptop : laptops) {
            System.out.println(laptop.getModel());
        }
    }
}