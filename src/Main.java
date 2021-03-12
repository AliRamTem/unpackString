public class Main {
    public static void main(String[] args) {
        for(String arg: args) {
            System.out.println(unpackString(arg));
        }
    }

    public static String unpackString(String s) {
        StringBuilder result = new StringBuilder();
        int openCount = 0;
        int closeCount = 0;
        int startIndex = 0;
        boolean lastIsDigit = false;

        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i)) && openCount == closeCount) {
                if(!lastIsDigit) startIndex = i;
                lastIsDigit = true;
            } else {
                lastIsDigit = false;
                if(s.charAt(i) == '[') {
                    openCount++;
                    continue;
                }
                if(s.charAt(i) == ']') {
                    closeCount++;
                    if(openCount == closeCount) {
                        int n = Integer.parseInt(s.substring(startIndex, s.indexOf('[', startIndex)));
                        for (int j = 0; j < n; j++) {
                            result.append(unpackString(s.substring(s.indexOf('[', startIndex) + 1, i)));
                        }
                    }
                    continue;
                }
                if(closeCount == openCount) {
                    result.append(s.charAt(i));
                    startIndex = i + 1;
                }
            }
        }
        return result.toString();
    }

    public static void tests() {

        test("3[xyz]4[xy]z", "xyzxyzxyzxyxyxyxyz");

        test("2[3[x]y]", "xxxyxxxy");

        test("3[a]b4[c]", "aaabcccc");

        test("2[a3[j]]b1[c]f", "ajjjajjjbcf");

        test("10[a]bc", "aaaaaaaaaabc");

    }

    public static void test(String test, String answer) {
        String result = unpackString(test);
        System.out.println("test : " + test + " = " + result.equals(answer));

        System.out.println(answer);
        System.out.println(result);
    }
}
