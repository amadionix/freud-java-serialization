import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import org.apache.commons.lang.ArrayUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;


public class Main {

    static String PATHNAME = "";
    static String RANDOMNUMBER = "12493";

    public static void main(String[] args) throws IOException {
        String symbolName = args[0];
        PATHNAME = "my-symbols/_nicola_test/idcm_" + symbolName + "_" + RANDOMNUMBER + ".bin";
        TestClassToSerialize myObject = new TestClassToSerialize(symbolName);
        createBinaryFile(myObject);
    }

    private static void createBinaryFile(TestClassToSerialize myObject) throws IOException {
        File myFile = new File(PATHNAME);
        myFile.createNewFile();
        writeObjectToFile(new FileOutputStream(myFile), myObject);
    }

    private static void writeObjectToFile(FileOutputStream fos, TestClassToSerialize myObject) throws IOException {
        fos.write(intToByteArray(myObject.getSymbolNameLen()));
        fos.write(myObject.getSymbolName().getBytes());

        Path path = Paths.get(PATHNAME);
        LinkedList<Long> featureOffsets = new LinkedList<>();

        fos.write(intToByteArray(myObject.getFeatureNamesCount()));
        for (FreudFeatureName featureName: myObject.getFeatureNamesList()) {
            SeekableByteChannel ch = Files.newByteChannel(path); // Defaults to read-only
            featureOffsets.add(ch.position());
            fos.write(shortToByteArray(featureName.getVarNameLen()));
            fos.write(featureName.getVarName().getBytes());
        }
        fos.write(intToByteArray(myObject.getTypeNamesCount()));
        fos.write(intToByteArray(myObject.getSamplesCount()));
        for (Sample sample: myObject.getSampleList()) {
            fos.write(intToByteArray(sample.getUidR()));
            fos.write(longToByteArray(sample.getTime()));
            fos.write(longToByteArray(sample.getMem()));
            fos.write(longToByteArray(sample.getLockHoldingTime()));
            fos.write(longToByteArray(sample.getWaitingTime()));
            fos.write(longToByteArray(sample.getMinorPageFaults()));
            fos.write(longToByteArray(sample.getMajorPageFaults()));
            fos.write(intToByteArray(sample.getNumOfFeatures()));
            for (FreudFeature feature: sample.getFeatureList()) {
                fos.write(longToByteArray(20));
                fos.write(longToByteArray(feature.getTypeOffset()));
                fos.write(longToByteArray(feature.getValue()));
            }
            fos.write(intToByteArray(sample.getNumOfBranches()));
            fos.write(intToByteArray(sample.getNumOfChildren()));
        }
        fos.close();
    }

    public static byte[] shortToByteArray(short x) {
        byte[] bytes = Shorts.toByteArray(x);
        ArrayUtils.reverse(bytes);
        return bytes;
    }

    public static byte[] intToByteArray(int x) {
        byte[] bytes = Ints.toByteArray(x);
        ArrayUtils.reverse(bytes);
        return bytes;
    }

    public static byte[] longToByteArray(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        byte[] array = buffer.array();
        ArrayUtils.reverse(array);
        return array;
    }

}
