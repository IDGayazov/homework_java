package task2;

public class Main {

    // Задание 1
    public static String removeStringFromText(String str, String pattern){
        String[] s = str.split(" ");
        String res = "";
        boolean isDelete = false;
        for(String x: s){
            if(!isDelete){
                if(!x.equals(pattern)){
                    res += x + " ";
                }else{
                    isDelete = true;
                }
            }else{
                res += x + " ";
            }
        }
        return res.trim();
    }

    public static String longestStrChain(String sentence){
        String[] words = sentence.split(" ");
        int maxLength = Integer.MIN_VALUE;
        int count = 0;
        boolean isChain = false;
        String res = "";
        String tmp = "";
        for(int i = 1; i < words.length; ++i){
            if(words[i - 1].charAt(words[i - 1].length() - 1) == words[i].charAt(0)){
                count++;
                if(!isChain){
                    tmp += words[i - 1] + " " + words[i] + " ";
                    isChain = true;
                }else{
                    tmp += words[i] + " ";
                }
            }else{
                isChain = false;
                if(count > maxLength){
                    maxLength = count;
                    res = tmp;
                }
                tmp = "";
            }
        }
        return res.trim();
    }

    // Задание 3
    public static String reverse(String str){
        String res = "";
        for(int i = str.length() - 1; i >= 0; --i){
            res += str.charAt(i);
        }
        return res;
    }

    public static String reverseWordsInString(String sentence){
        String[] s = sentence.split(" ");
        String res = "";
        for(int i = 0; i < s.length; ++i){
            res += reverse(s[i]) + " ";
        }
        return res.trim();
    }


    // Задание 4
    public static boolean hasChar(String str, char c){
        for(int i = 0; i < str.length(); ++i){
            if(str.charAt(i) == c){
                return true;
            }
        }
        return false;
    }
    public static void printAllWordsContainsLetters(String sentence, char[] dict){
        String[] str = sentence.split(" ");
        for(String s: str){
            boolean isContain = true;
            for(char c: dict){
                if(!hasChar(s, c)){
                    isContain = false;
                    break;
                }
            }
            if(isContain){
                System.out.print(s + " ");
            }
        }
        System.out.println();
    }

    // 5 задание
    public static void swap(char[] ch, int i, int j){
        char tmp = ch[i];
        ch[i] = ch[j];
        ch[j] = tmp;
    }

    public static void reverseCharArrayFromIIndex(char[] ch, int i){
        int l = i;
        int r = ch.length - 1;
        while(l < r){
            swap(ch, l, r);
            l++;
            r--;
        }
    }
    public static String nextWord(String word){
        final int n = word.length();
        char[] letters = word.toCharArray();
        int l = -1;
        for(int i = 0; i < n - 1; ++i){
            if(letters[i] < letters[i + 1]){
                l = i;
            }
        }
        if(l == -1) return null;
        swap(letters, l, l+1);
        int i = l + 1;
        while(i + 1 < letters.length && letters[i] < letters[i + 1]){
            swap(letters, i, i + 1);
            i++;
        }
        reverseCharArrayFromIIndex(letters, l + 1);
        return new String(letters);
    }

    public static void main(String[] args) {
        System.out.println(nextWord("abcdef"));
    }
}

// word
// wrdo


//    String str = "safafas sdfas tgtg gefew wgtg qwefqwe wefwqe";
//    String pattern = "tgtg";
//    System.out.println(removeStringFromText(str, pattern));
//    System.out.println(reverseWordsInString(str));
//    System.out.println(longestStrChain(str));
//    char[] c = {'w', 'e', 'f', 'q'};
//    printAllWordsContainsLetters("safafas sdfas tgtg gefew wgtg qwefqwe wefwqe", c);
