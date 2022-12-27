import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = in.nextLine();
        in.close();

        input = input.replaceAll(" ",""); //убираем все пробелы

        int indexPlus = input.indexOf("+"); // определяем оператора
        int indexMinus = input.indexOf("-");
        int indexMult = input.indexOf("*");
        int indexDiv = input.indexOf("/");


        input = input.replaceAll("\\+",";"); //заменяем знаки операторов на ";"
        input = input.replaceAll("-",";");
        input = input.replaceAll("\\*",";");
        input = input.replaceAll("/",";");


        String[] arr = input.split(";"); //создаем массив из частей введенного выражения
        if(arr.length<2){
            System.out.println("Некорректное выражение");
            System.exit(0);
        }




        // создаем массивы римских и арабских чисел
        String[] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C","LL", "CC", "LCC", "CCC", "LLL", "MM", "M", "MMM", "CL", "CLX", "CX", "CXXX", "CXX", "CLL", "MC", "MCC", "MCL", "MX"};
        int[] arabic = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};


        int a=0;
        int b=0;

        int i;

        for(i=0; i<118; i++){
            if(romans[i].equals(arr[0])) a = arabic[i]; //проверяем являются ли введенные числа римскими не больше 10
            if(romans[i].equals(arr[1])) b = arabic[i];
        }

        if(a>0 & b==0 | a==0 & b>0){ // проверка на соответствие систем исчисления
            System.out.println("Используются одновременно разные системы счисления");
            System.exit(0);
        }


        for(i=10; i<118; i++){ //выходим, если введенные числа римские числа больше 10
            if(romans[i].equals(arr[0]) | romans[i].equals(arr[1])){
                System.out.println("Введите выражение с числами не больше X");
                System.exit(0);
            }
        }


        if(a>0 & b>0) {

            int ans = 0;
            if(indexPlus>0) ans = a+b; // решаем выражение, если введенные числа - римские
            if(indexMinus>0) ans = a-b;
            if(indexMult>0) ans = a*b;
            if(indexDiv>0) ans = a/b;

            if(ans<0){
                System.out.println("Ответ: в римской системе нет отрицательных чисел");
                System.exit(0);
            }

            if(ans<1){
                System.out.println("Ответ: в системе римских цифр отсутствует ноль, но ранее использовалось обозначение нуля как nulla");
                System.exit(0);
            }

            String romAns=null; //преобразуем ответ в римское число
            for(i=0; i<100; i++){
                if(ans == arabic[i]) romAns = romans[i];
            }

            System.out.println("Ответ: "+romAns);
        }
        else { //если введенные числа не являются римскими


            int[] arr2 = new int[2]; // преобразуем массив строк в массив целых чисел
            try {
                for (i = 0; i < arr.length; i++) {
                    try {
                        arr2[i] = Integer.parseInt(arr[i]);
                    } catch (NumberFormatException e) { // выход при неудачном преобразовании
                        System.out.println("Некорректное выражение");
                        System.exit(0);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) { // выход при длинном выражении
                System.out.println("Слишком длинное выражение");
                System.exit(0);
            }

            if (arr2[0] > 10 | arr2[1] > 10 | arr2[0] < 1 | arr2[1] < 1) { // проверка чисел на величину
                System.out.println("Введите выражение с числами от 1 до 10");
                System.exit(0);
            }

            int ans = 0;
            if (indexPlus > 0) ans = arr2[0] + arr2[1]; // находим ответ, оперируя элементами массива
            if (indexMinus > 0) ans = arr2[0] - arr2[1];
            if (indexMult > 0) ans = arr2[0] * arr2[1];
            if (indexDiv > 0) ans = arr2[0] / arr2[1];

            System.out.println("Ответ: "+ans);
        }
    }
}
