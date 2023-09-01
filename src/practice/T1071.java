package practice;

/**
 * @author bk
 */
public class T1071 {

    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int minLen = Math.min(len1, len2);
        for (int i = minLen; i >0 ; i--) {
            String sub = str1.substring(0,i);
            if (len1 % sub.length() == 0 && len2 % sub.length() == 0) {
                if (check(sub,str1) && check(sub,str2)) {
                    return sub;
                }
            }
        }
        return "";
    }

    private boolean check(String sub,String target) {
        StringBuilder builder = new StringBuilder();
        for (int i = target.length() / sub.length(); i > 0 ; i--) {
            builder.append(sub);
        }
        return builder.toString().equals(target);
    }


    public static void main(String[] args) {
        T1071 t1071 = new T1071();
        System.out.println(t1071.gcdOfStrings("ABCABC", "ABC"));
    }
}
