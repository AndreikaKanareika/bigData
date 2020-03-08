package lab1;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LogInfo implements Writable {
    public String Ip;
    public int Count;
    public int Length;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(Ip);
        dataOutput.writeInt(Count);
        dataOutput.writeInt(Length);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        Ip = dataInput.readUTF();
        Count = dataInput.readInt();
        Length = dataInput.readInt();
    }
}
