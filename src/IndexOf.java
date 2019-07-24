public class IndexOf {
    public static int indexOf(char[] l, char[] p) {
        int idx = -1;
        int len = l.length - p.length + 1;
        for (int i = 0; i < len; i++) {
            boolean flag = true;
            for (int j = 0; j < p.length; j++) {
                if (l[i + j] != p[j]) {
                    flag = false;
                }
            }
        }
        if (flag) {
            idx = i;
            break;
        }
        return idx;
    }

    public static void main(String[] args) {
        char[] s = "HelloWorld".toCharArray();
        char[] t = "llo".toCharArray();

        int idx = indexOf(s, t);
        System.out.println(idx);
    }
}