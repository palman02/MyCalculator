import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение в строку разделяя числа и занки пробелами:");
        Reformer reformer = new Reformer(); // Создание словаря
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine(); //Ввод с клавиатуры
        System.out.println(Calc(expression)); //Вывод ответа
    }

    public static String Calc (String input) throws Exception{ // Запуск калькулятора
        String[] str = input.split(" ");
        if(StringCheck(input) & CheckArabicOrRoman(str)){ //Всё проверки и вычисления
            boolean romanOrNot = Reformer.CheckRoman(str[0]); //Создаю переменную, чтобы узнать в какой системе у меня идёт подсчёт
            int result = 0;
            if (romanOrNot){    //Если числа в римской системе, перевожу их в арабскую чтобы сделать подсчёт.
                int a = Reformer.Arabic(str[0]);
                String operator = str[1];
                int b = Reformer.Arabic(str[2]);
                result = Answer(operator, a, b); //Получаю ответ по вычислениям
            } else {                             // Если были в арабских, сразу перехожу к проверке что число Int и в пределах.
                if (CheckInt(str) & LimitCheck(str)){
                    int a = Integer.parseInt(str[0]);
                    String operator = str[1];
                    int b = Integer.parseInt(str[2]);
                    result = Answer(operator, a, b); //Получаю ответ
                }
            }
            if (romanOrNot) { //Если выражение было введено в римских числах -> преобразую ответ в римские числа
                if (result > 0){
                    return Reformer.Roman(result);
                } else {
                    throw new Exception("Ответ в римских числах не может быть с отрицательным числом или нулём.");
                }
            } else { //Если в арабских то сразу ответ
                return "" + result;
            }
        } else { //Надюсь это else никогда не сработает и мой калькулятор решает верно
            return "Мой калькулятор не смог решить вашу задачу.";
        }
    }

    public static boolean CheckInt(String[] str) throws NumberFormatException{ //Проверка, что операнды являются
        try {                                                                  // целыми числами, если нет - Ошибка
            Integer.parseInt(str[0]);
            Integer.parseInt(str[2]);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Операнды введены не в виде целых чисел.");
        }

    }

    public static boolean StringCheck(String expression) throws Exception{ //Проверка на операнды и оператор
        String[] str = expression.split(" ");
        String[] operators = {"+", "-", "*", "/"};
        boolean OperatorResponse = false; //Проверка что есть оператор
        short ScoreOperator = 0; //Проверка что оператор введён верно, "+*" - ошибка
        for (int i = 0; i < operators.length; i++){
            if (expression.contains(operators[i])){
                ScoreOperator++;//Если введенно два оператора, то ScoreOperator будет больше 1 и далее не пройдёт проверку
            }                   //и выйдет ошибка
        }
        if (ScoreOperator == 1){
            OperatorResponse = true;
        }
        if (str.length > 3){
            throw new Exception("Строка не удовлетворяет заданию - два операнда и один оператор.");
        } else if(str.length < 3){
            throw new Exception("Строка не является математической операцией.");
        } else if(OperatorResponse){
            return true;
        } else {
            throw new Exception("Неверно введён оператор.");
        }

    }

    public static boolean LimitCheck(String[] str) throws Exception{ //Проверка на то что числа 1 < x < 11
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[2]);
        if ((a > 0 & a < 11) & (b > 0 & b < 11)) {
            return true;
        } else {
            throw new Exception("Введенные операнды не в даипазоне от 0 до 10(включительно).");
        }
    }

    public static boolean CheckArabicOrRoman(String[] str) throws Exception{ //Проверка что операнды в одной система исчисления
        if (Reformer.CheckRoman(str[0]) == Reformer.CheckRoman(str[2])){// Если числа в одной системе то будет true == true -> true
            return true;                                                // или false == false = True
        } else {
            throw new Exception("Используются одновременно разные системы исчисления.");
        }
    }

    public static int Answer (String operator, int a, int b) throws Exception{ //Метод подсчёта
        int answer = 0;
        switch (operator) {
            case "+":
                answer = a + b;
                break;
            case "-":
                answer = a - b;
                break;
            case "*":
                answer = a * b;
                break;
            case "/":
                answer = a / b;
                break;
            default:
                throw new Exception("Оператор ввёден некорректно, возможно вы написали \"++\"" );

        }
        return answer;
    }
}






