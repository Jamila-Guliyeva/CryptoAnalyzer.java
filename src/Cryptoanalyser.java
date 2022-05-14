import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Cryptoanalyser {

    public static void main(String[] args) {
        ArrayList<Character> symbols = new ArrayList<>();
        System.out.println("Please, enter the key");
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();

        Collections.addAll(symbols, 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
                'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',
                'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
                'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '”', ':', '-', '!', '?', ' ');

        File inputFile = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\resources\\inputFile.txt");
        File outPutFile = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\resources\\outPutFile.txt");
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);
             FileWriter writer = new FileWriter(outPutFile)) {

            char[] buffer = new char[1024];
            while (reader.ready()) {
                int real = reader.read(buffer);
                Caesar.encrypt(symbols, key, buffer, real);
                writer.write(buffer, 0, real);
            }

        } catch (IOException e) {
            System.out.println("File Reading/ File Writing Error");
        }
//-------------------------------------------------------------------------------------------------
        System.out.println("Доступна опция дешифрования. Введите \"Да\" чтобы продолжить");
        Scanner scanner1 = new Scanner(System.in);
        String option = scanner1.nextLine();

        if (option.equalsIgnoreCase("Да")) {
            System.out.println("Начинается процесс дешифрования");
            int keyReverse = 1;

            File inputFile2 = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\resources\\outPutFile.txt");
            File outPutFile2 = new File("C:\\Users\\Jama\\Desktop\\Java\\Java_Syntax\\resources\\outPutFileReverse.txt");

            try (InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFile2), StandardCharsets.UTF_8);
                 FileWriter writer = new FileWriter(outPutFile2)) {
                // создаем массив символов и считываем содержимое зашифрованного файла в него
                char[] buffer = new char[4096];
                int real = 0;
                while (reader.ready()) {
                    real = reader.read(buffer);
                }
                // начинаем работать с массивом, сожержащим зашифрованный текст
                for (int j = 0; j < symbols.size(); j++) {
                    Caesar.decrypt(symbols, keyReverse, buffer, real);
                    // получили новый "расшифрованный массив символов"
                    boolean check = Caesar.findCorrectVersion(buffer);
                    if (check) {
                        writer.write(buffer, 0, real);
                        break;
                    }
                }
            } catch (IOException ex) {
                System.out.println("File Reading/ File Writing Error");
            }
        }



    }
}
