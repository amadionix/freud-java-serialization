public class FreudFeature {
    private long nameOffset;  // must be same as
    private long typeOffset;
    private long value;

    public FreudFeature(long value) {
        this.typeOffset = 0; // system feature
        this.value = value;
    }

    public void setNameOffset(long nameOffset) {
        this.nameOffset = nameOffset;
    }


    public long getTypeOffset() {
        return typeOffset;
    }

    public long getValue() {
        return value;
    }
}
