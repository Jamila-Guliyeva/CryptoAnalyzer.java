import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Caesar {

//    int count = 0;
//    final int MAX = 35;

    public static void encrypt (ArrayList<Character> listTemplate, int key, char[] charTemplate, int real) {


        for (int i = 0; i < real; i++) {

            if (listTemplate.contains(charTemplate[i])) {

                int orderNumber = listTemplate.indexOf(charTemplate[i]);
                int newOrderNumber = orderNumber + key;

                if (newOrderNumber < listTemplate.size()){
                    charTemplate[i] = listTemplate.get(newOrderNumber);
                }

                if (newOrderNumber >= listTemplate.size()){
                    newOrderNumber = (newOrderNumber % listTemplate.size());
                    charTemplate[i] = listTemplate.get(newOrderNumber);
                }
            }
        }
    }

    public static void decrypt (ArrayList<Character> listTemplate, int keyReverse, char[] charTemplate, int real) {
        for (int i = 0; i < real; i++) {
            if (listTemplate.contains(charTemplate[i])) {
                int orderNumber = listTemplate.indexOf(charTemplate[i]); // порядковый номер зашифрованной буквы
                int newOrderNumber = orderNumber - keyReverse; // порядковый номер после дешифрования

                if ((newOrderNumber >= 0) && (newOrderNumber < listTemplate.size())) {
                    charTemplate[i] = listTemplate.get(newOrderNumber);
                } else {
                    newOrderNumber = listTemplate.size() + newOrderNumber ;
                    charTemplate[i] = listTemplate.get(newOrderNumber);
                }
            }
        }
    }

    public static boolean findCorrectVersion (char[] charTemplate){
        int count = 0;
        int max = 25;
        boolean check = false;

        for (char c : charTemplate) {

            // проходимся по массиву с целью выявить наличие пробелов " "
            if (c == ' ') {
                // если пробелы есть то увеличиваем счетчик
                count = count + 1;
            }
            if (count >= max) {
                //writer.write(buffer,0, real);
                check = true;
                break;
            }
            // если значение счетчика переваливает за 35, то записываем массив в файл
        }
        return check;
    }

}
