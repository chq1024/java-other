package 刷题;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 对于字符串 s 和 t，只有在 s = t + ... + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。
 * 给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。
 *
 * @author bk
 */
public class T1071 {



    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        List<String> str = new ArrayList<>();
        str.add("1");
        str.add("1");
        str.add("1");
        str.add("1");
        int len1 = str1.length();
        int len2 = str2.length();
        for (int i = Math.min(len1,len2);; --i) {
            if (len1 % i == 0 && len2 % i == 0) {
                String sub = str1.substring(0,i);
                if (check(sub,str1) && check(sub,str2)) {
                    return sub;
                }
            }
        }


    }

    private boolean check(String str,String source) {
        int len = source.length() / str.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(str);
        }
        return builder.toString().equals(source);
    }


    public static void main(String[] args) {
        T1071 t1071 = new T1071();
        System.out.println(t1071.gcdOfStrings("ABCABC", "ABC"));
    }
}
