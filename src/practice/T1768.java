package practice;

/**
 * @author bk
 */
public class T1768 {

    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int len = Math.min(len1, len2);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String c1 = word1.substring(i, i + 1);
            builder.append(c1);
            String c2 = word2.substring(i, i + 1);
            builder.append(c2);
        }
        String append = len1 > len2 ? word1.substring(len) : word2.substring(len);
        builder.append(append);
        return builder.toString();
    }


    public String mergeAlternately2(String word1, String word2) {
        String[] word1Array = word1.split("");
        String[] word2Array = word2.split("");
        int min = Math.min(word1Array.length, word2Array.length);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < min; i++) {
            String append = word1Array[i] + word2Array[i];
            builder.append(append);
        }
        String append = word1Array.length > word2Array.length ? word1.substring(min) : word2.substring(min);
        builder.append(append);
        return builder.toString();
    }

    public String mergeAlternately3(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int i = 0;
        StringBuilder builder = new StringBuilder();
        while (i < len1 || i < len2) {
            if (i < len1) {
                builder.append(word1.charAt(i));
            }
            if (i < len2) {
                builder.append(word2.charAt(i));
            }
            i++;
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        T1768 t1768 = new T1768();
        String res = t1768.mergeAlternately3("hello4444", "hello123");
        System.out.println(res);
    }
}
