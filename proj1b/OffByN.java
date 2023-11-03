public class OffByN implements CharacterComparator {
    private int offset;
    public OffByN(int o) {
        offset = o;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == offset;
    }
}
