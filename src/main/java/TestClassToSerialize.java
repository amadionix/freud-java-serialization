import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestClassToSerialize implements Serializable {

    String instrumentationDir = System.getProperty("user.dir") + "/../freud-java-instrumentation/";

    // FILE NAME
    private int symbolNameLen;
    private String symbolName;
    // FEATURE NAMES
    private int featureNamesCount;
    List<FreudFeatureName> featureNames;
    // TYPE NAMES
    int typeNamesCount;
    // SAMPLES
    int samplesCount;
    List<Sample> sampleList;

    /* CONSTRUCTOR */
    public TestClassToSerialize(String symbolName) throws IOException {
        this.symbolNameLen = symbolName.length();
        this.symbolName = symbolName;

        // feature names
        this.featureNamesCount = 1;
        String myVar = "_my_var";
        featureNames = new LinkedList<>();
        featureNames.add(new FreudFeatureName((short) myVar.length(), myVar));

        this.typeNamesCount = 0;

        BufferedReader logsReader = new BufferedReader(new FileReader(
                instrumentationDir + "param-logs.txt"));
        this.samplesCount = Integer.parseInt(logsReader.readLine());
        Long[] featureArray = new Long[samplesCount];
        String feature = logsReader.readLine();
        for (int i = 0; i < samplesCount; i++) {
            featureArray[i] = Long.valueOf(feature);
            feature = logsReader.readLine();
        }
        logsReader.close();

        this.sampleList = new ArrayList<>();

        BufferedReader tsReader = new BufferedReader(new FileReader(
                instrumentationDir + "time-logs.txt"));
        String line = tsReader.readLine();
        int i = 0;
        while (line != null) {
            sampleList.add(new Sample(featureArray[i], Long.parseLong(line)));
            line = tsReader.readLine();
            i++;
        }
        assertEquals(i, samplesCount);
        tsReader.close();
    }


    /* GETTERS & SETTERS */
    // FILE NAME
    public int getSymbolNameLen() {
        return symbolNameLen;
    }

    public String getSymbolName() {
        return symbolName;
    }

    // FEATURE NAMES
    public int getFeatureNamesCount() {
        return featureNamesCount;
    }

    public List<FreudFeatureName> getFeatureNamesList() {
        return featureNames;
    }

    // TYPE NAMES
    public int getTypeNamesCount() {
        return typeNamesCount;
    }

    // SAMPLES
    public int getSamplesCount() {
        return samplesCount;
    }

    public List<Sample> getSampleList() {
        return sampleList;
    }

}
