package lab1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class IpComparator extends WritableComparator {
    protected IpComparator() {
        super(Text.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        String firstNumber = w1.toString().substring(2);
        String secondIpNumber = w2.toString().substring(2);
        return Integer.parseInt(firstNumber) - Integer.parseInt(secondIpNumber);
    }
}