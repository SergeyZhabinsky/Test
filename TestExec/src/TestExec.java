import java.util.Arrays;
import java.util.Scanner;

public class TestExec {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String s;
        Valid v = new Valid();
        while (true){
            System.out.println("\n\nВведите символ 'Q' для выхода");
            s = scan.nextLine();

            if(s.toUpperCase().equals("Q")) break;

            if(v.start(s))
                System.out.println("Ответ: " + v.getResult());

        }
    }
//=====================================================================



    public static String calc(String input){
        String result = "";
        return result;
    }
}
