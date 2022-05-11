import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TestTwo {

    public static void main(String[] args) {
        ArrayList<Character> symbols = new ArrayList<>();
        System.out.println("Please, enter the key");
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();

        Collections.addAll(symbols, 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
                'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',
                'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
                'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '”', ':', '-', '!', '?', ' ');

        File inputFile = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\src\\inputFile.txt");
        File outPutFile = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\src\\outPutFile.txt");
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);
             FileWriter writer = new FileWriter(outPutFile)) {

            char[] buffer = new char[1024];
            while (reader.ready()) {
                int real = reader.read(buffer);

                String text = new String(buffer, 0, real);

                Caesar.encrypt(symbols, key, buffer, real);
//                for (int i = 0; i < real; i++) {
//                    if (symbols.contains(buffer[i])) {
//
//                        int orderNumber = symbols.indexOf(buffer[i]);
//                        int newOrderNumber = orderNumber + key;
//
//                        if (newOrderNumber < symbols.size()){
//                            buffer[i] = symbols.get(newOrderNumber);
//                        }
//
//                        if (newOrderNumber >= symbols.size()){
//                            newOrderNumber = (newOrderNumber % symbols.size());
//                            buffer[i] = symbols.get(newOrderNumber);
//                        }
//                    }
//
//                }
                writer.write(buffer, 0, real);
            }

        } catch (IOException e) {
            System.out.println("File Reading/ File Writing Error");
        }
//-------------------------------------------------------------------------------------------------
        System.out.println("Доступна опция дешифрования. Введите \"Да\" чтобы продолжить или \"Нет\" чтобы остановить программу");
        Scanner scanner1 = new Scanner(System.in);
        String option = scanner1.nextLine();

        while (option.equalsIgnoreCase("Нет")) {
            break;
        }

        System.out.println("Начинается процесс дешифрования");
        int keyReverse = 1;

        File inputFile2 = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\src\\outPutFile.txt");
        File outPutFile2 = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\src\\outPutFileReverse.txt");

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFile2), StandardCharsets.UTF_8);
             FileWriter writer = new FileWriter(outPutFile2)) {
            // создаем массив символов и считываем содержимое зашифрованного файла в него
            char[] buffer = new char[4096];
            int real = 0;
            while (reader.ready()) {

                real = reader.read(buffer);
            }
            String textFromFile = new String(buffer, 0, real);
            // начинаем работать с массивом, сожержащим зашифрованный текст
            for (int j = 0; j < symbols.size(); j++) {


                Caesar.decrypt(symbols, keyReverse, buffer, real);
                // присваиваем ключу значение j и перебираем все возможные вариианты
//                for (int i = 0; i < real; i++) {
//                    if (symbols.contains(buffer[i])) {
//                        int orderNumber = symbols.indexOf(buffer[i]); // порядковый номер зашифрованной буквы
//                        int newOrderNumber = orderNumber - keyReverse; // порядковый номер после дешифрования
//
//                        if ((newOrderNumber >= 0) && (newOrderNumber < symbols.size())) {
//                            buffer[i] = symbols.get(newOrderNumber);
//                        } else {
//                            newOrderNumber = symbols.size() + newOrderNumber ;
//                            buffer[i] = symbols.get(newOrderNumber);
//                        }
//                    }
//
//                }

                // получили новый "расшифрованный массив символов"
                int count = 0;
                final int MAX = 35;
                for (int i = 0; i < real; i++) {

                    // проходимся по массиву с целью выявить наличие пробелов " "
                    if (buffer[i] == ' ' )
                    {
                        // если пробелы есть то увеличиваем счетчик
                        count = count + 1;

                    }
                    if (count > MAX){
                        writer.write(buffer,0, real);
                        break;
                    }
                    // если значение счетчика переваливает за 35, то записываем массив в файл
                }
            }
        } catch (IOException ex) {
            System.out.println("File Reading/ File Writing Error");
        }

    }
}
