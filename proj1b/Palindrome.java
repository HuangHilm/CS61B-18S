public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> retVal = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            retVal.addLast(word.charAt(i));
        }
        return retVal;
    }
    private boolean isPalindrome(Deque<Character> wd) {
        if (wd.size() == 1 || wd.isEmpty()) {
            return true;
        }
        return (wd.removeFirst() == wd.removeLast()) && isPalindrome(wd);
    }
    public boolean isPalindrome(String word) {
        Deque<Character> d;
        d = wordToDeque(word);
        return isPalindrome(d);
    }
    private boolean isOBOPalindrome(Deque<Character> wd, CharacterComparator cc) {
        if (wd.size() == 1 || wd.isEmpty()) {
            return true;
        }
        return cc.equalChars(wd.removeFirst(), wd.removeLast()) && isOBOPalindrome(wd, cc);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d;
        d = wordToDeque(word);
        return isOBOPalindrome(d, cc);
    }
}
