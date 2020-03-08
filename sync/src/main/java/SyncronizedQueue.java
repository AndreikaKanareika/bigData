import java.util.ArrayDeque;
import java.util.concurrent.locks.ReentrantLock;

public class SyncronizedQueue {
    private ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
    private ReentrantLock locker = new ReentrantLock();

    public void push(int val) {
        locker.lock();
        try {
            queue.push(val);
        }
        finally {
            locker.unlock();
        }
    }

    public int pop() {
        locker.lock();
        try {
            return queue.pop();
        }
        finally {
            locker.unlock();
        }
    }

    public int size() {
        locker.lock();
        try {
            return queue.size();
        }
        finally {
            locker.unlock();
        }
    }
}
