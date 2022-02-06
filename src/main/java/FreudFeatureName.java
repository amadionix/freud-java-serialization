public class FreudFeatureName {
    private short VarNameLen;
    private String VarName;

    public FreudFeatureName(short varNameLen, String varName) {
        VarNameLen = varNameLen;
        VarName = varName;
    }

    public short getVarNameLen() {
        return VarNameLen;
    }

    public String getVarName() {
        return VarName;
    }
}
