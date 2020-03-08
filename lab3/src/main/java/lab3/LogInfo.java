package lab3;

import java.io.Serializable;

public class LogInfo implements Serializable {
    public int Count = 1;
    public int Length;

    @Override
    public String toString() {
        return String.format("%.2f,%d", (float) Length / Count, Length);
    }
}
