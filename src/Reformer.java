import java.util.TreeMap;

public class Reformer {

    static TreeMap<Character, Integer> RomanNum = new TreeMap<>(); //Делаем словарь, чтобы по ключам искать числа
    static TreeMap<Integer, String> ArabicNum = new TreeMap<>(); //Делаем словарь, чтобы по ключам искать числа

    public Reformer () {

        RomanNum.put('I', 1);
        RomanNum.put('V', 5);
        RomanNum.put('X', 10);
        RomanNum.put('L', 50);
        RomanNum.put('C', 100);

        ArabicNum.put(1, "I");
        ArabicNum.put(4, "IV");
        ArabicNum.put(5, "V");
        ArabicNum.put(9, "IX");
        ArabicNum.put(10, "X");
        ArabicNum.put(40, "XL");
        ArabicNum.put(50, "L");
        ArabicNum.put(90, "XC");
        ArabicNum.put(100, "C");
    }

    public static boolean CheckRoman(String num){

        return RomanNum.containsKey(num.charAt(0)); //Ищём ключ в словаре, для проверки на одну систему исчисления
    }

    public static int Arabic (String roman) throws Exception{ // Перевод из римских чисел в арабские
        int result = 0;
        int previous = 0;
        int current = 0;
        for (int i = roman.length() - 1; i >=0; i--){ //Начинаем переводить с конца строки, чтобы не потерять такие числа как "IX, XC"
            try {                                     //Проверка что римское число введено верно, без знаков между цифрами
                current = RomanNum.get(roman.charAt(i)); //Находим текущее число, по ключу
            } catch (Exception e){
                throw new Exception("Вы ввели некорректное римское число");
            }
            if (current < previous){ //Если текущее число меньше предыдущего, то нужно вычесть
                result -= current;
            } else {
                result += current;
            }
            previous = current; //Обновляем предыдущее число, чтобы не было случайных вычитаний
        }
        if (result < 11 & result >0){ //Проверка, что числа входят в диапазон
            return result;
        } else {
            throw new Exception("Введенные операнды не в даипазоне от 0 до 10(включительно).");
        }
    }

    public static String Roman (int number){ //Перевод из арабских чисел в римские
        String roman = "";
        int key;
        while (number != 0){
            key = ArabicNum.floorKey(number); //Получаем наименьший ближайший ключ к нашему числу
            roman += ArabicNum.get(key); //Получаем значение по ключу и добавляем в конец строки
            number -= key; //Сокращаем наше число, чтобы остался остаток
        }
        return roman;

    }
}
