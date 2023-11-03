public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y)
    {
        return x == y || Math.abs(x - y) == 1;
    }
}
