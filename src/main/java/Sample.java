import java.util.LinkedList;
import java.util.List;

public class Sample {
    private final int uidR;
    private final long time;
    private final long mem;
    private final long lockHoldingTime;
    private final long waitingTime;
    private final long minorPageFaults;
    private final long majorPageFaults;
    private final int numOfFeatures;
    private final int numOfBranches;
    private final int numOfChildren;
    private final List<FreudFeature> featureList; // currently this list is always of one element

    // featureVal will become a list once freud-java will support multiple features
    public Sample(Long featureVal, long time) {
        this.time = time;
        uidR = (int) time;
        mem = 0;
        lockHoldingTime = 0;
        waitingTime = 0;
        minorPageFaults = 0;
        majorPageFaults = 0;
        numOfFeatures = 1;
        numOfBranches = 0;
        numOfChildren = 0;
        featureList = new LinkedList<>();
        featureList.add(new FreudFeature(featureVal));
    }

    public int getUidR() {
        return uidR;
    }

    public long getTime() {
        return time;
    }

    public long getMem() {
        return mem;
    }

    public long getLockHoldingTime() {
        return lockHoldingTime;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public long getMinorPageFaults() {
        return minorPageFaults;
    }

    public long getMajorPageFaults() {
        return majorPageFaults;
    }

    public int getNumOfFeatures() {
        return numOfFeatures;
    }

    public List<FreudFeature> getFeatureList() {
        return featureList;
    }

    public int getNumOfBranches() {
        return numOfBranches;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }
}
