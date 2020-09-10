import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner scanner = new Scanner(System.in);
        String firstWordList = scanner.nextLine();
        String secondWordList = scanner.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) throws RuntimeException{
        //在这编写实现代码
        List<String> validFirstWordList = isValid(firstWordList);
        List<String> validSecondWordList = isValid(secondWordList);
        return validFirstWordList.stream()
                .filter(validSecondWordList::contains)
                .distinct()
                .map(s -> String.join(" ", s.split("")))
                .sorted(Comparator.comparing(a -> a))
                .collect(Collectors.toList());
    }

    public static List<String> isValid(String input) throws RuntimeException{
        if(input.contains(",,")) {
            throw new RuntimeException("input not valid");
        }
        return Arrays.stream(input.split(",")).map(s -> {
            if(s.matches("[a-zA-Z]+")) {
                return s.toUpperCase();
            } else {
                throw new RuntimeException("input not valid");
            }
        }).collect(Collectors.toList());
    }
}
