import java.util.*;

public class Valid {

    // Содержит арифметическую строку разбитую на лексемы
    private String []arr;

    // Содержит набор арифметических символов
    private Set<String> arithmeticSymbols = new HashSet<>();

    // арифм. знак
    private String sign = "";

    // содержит true в случае успешного решения
    private String iResult = "";


    private boolean isArabicSymbol;
//=====================================================================


    Valid(){

        // Создаём набор арифметических символов
        arithmeticSymbols.add("+");
        arithmeticSymbols.add("-");
        arithmeticSymbols.add("*");
        arithmeticSymbols.add("/");

        // Запускаем работу класса
//        start(x);
    }
//=====================================================================




    // Запускаем работу класса
    boolean start(String x){
        boolean bResult = false;
        isArabicSymbol = true;

        x = x.toUpperCase();

        if(parseArithmeticProblem(x)){
            if(isDigitValid(arr)){
                bResult = true;
            }
        }
        return bResult;
    }
//=====================================================================



    // получаем результат вычисления
    String getResult(){    return iResult; }


    // Дробим строку на числовые символы и арифмет. знак
    private String[] triming(String str, String sign){
        return str.split(sign);
    }
//=====================================================================



// проверяем строку на возможность арифметического действия с учётом ТЗ
    private boolean parseArithmeticProblem(String x){

        // count - количество арифм. знаков в строке
        int count = 0;

        // проходим по строке проверяя каждый символ
        for (char smb:x.toCharArray()) {
            if( arithmeticSymbols.contains( String.valueOf(smb) ) ) {
                sign = String.valueOf(smb);
                count += 1;
            }
        }

        // Провыряем результат. Должен быть один арифм. знак и он не должен стоять первым и последним
        if(count == 1 && x.indexOf(sign) > 0 && x.indexOf(sign) < x.length()-1){
            arr = triming(x, "\\" + sign);
            return true;
        }


        // Если арифм. действие не удовлетворяет условию
        return false;
    }
//=====================================================================



    private boolean isDigitValid(String... x){

        Map<String, Integer> digit = new HashMap<String, Integer>(){{
            put("0", 0);
            put("1", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("I",   1);
            put("II",  2);
            put("III", 3);
            put("IV",  4);
            put("V",   5);
            put("VI",  6);
            put("VII", 7);
            put("VIII",8);
            put("IX",  9);
        }};

        int num1, num2 = -1;
        final String VALUE = "0-9";

        if (x.length != 2)  return false;

        try {
            num1 = digit.get(x[0]);
            num2 = digit.get(x[1]);

            // Проверяем одинаковый языковой ввод или нет
            if(isDigit(x[0]) != isDigit(x[1])) return false;


            try {
                switch (sign){
                    case "+": iResult = String.valueOf(num1+num2);  break;
                    case "-": iResult = String.valueOf(num1-num2);  break;
                    case "*": iResult = String.valueOf(num1*num2);  break;
                    case "/":
                        if(num2 == 0)   System.out.println("Деление на нуль не допустимо!");
                        else            iResult = String.valueOf(num1 / num2);
                        break;
                }

                // если латинские цифры, то выполняем условие задания
                if(!isArabicSymbol){
                    if(Integer.valueOf(iResult)<1) return false;
                    for(String key : digit.keySet()) {
                        String value = String.valueOf( digit.get(key) );
                        if(iResult.equals(value)){
                            iResult = key;
                            if(!isDigit(key)) break;
                        }
                    }
                }


            }catch(ArithmeticException e){
                    System.out.println("Ошибка арифметической операции");
            }


        }catch (NullPointerException err){
            System.out.println(err.getMessage() + "\nВведено неправильное значение. Цифра находится вне диапазона '" + VALUE +
                    "' или не является латинской цифрой в том же диапазоне: 1)'" + x[0] + "' 2)'" + x[1] + "'!");
            return false;
        }

        return true;
    }
//=====================================================================



    private boolean isDigit(String str){
        try{
            Integer.valueOf(str);
        }catch (NumberFormatException e){
            isArabicSymbol=false;
            return false;
        }

        return true;
    }

}
