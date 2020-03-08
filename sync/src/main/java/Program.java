import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Program {
    private static ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
    private static ReentrantLock locker = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        List<Thread> pushThreads = new ArrayList<Thread>();
        List<Thread> popThreads = new ArrayList<Thread>();
        int threadsCount = 3;
        int elementsCount = 1000000;

        for (int i = 0; i<threadsCount; i++) {
            pushThreads.add(new Thread(getPushRun(elementsCount)));
            popThreads.add(new Thread(getPopRun()));
        }

        System.out.println("[Push] Start...");
        pushThreads.forEach(thread -> thread.start());

        System.in.read();
        System.out.println(queue.size());

        System.out.println("[Pop] Start...");
        popThreads.forEach(thread -> thread.start());
        System.in.read();

    }

    public static Runnable getPushRun(int count) {
        return () -> {
            locker.lock();
            try {
                for (int i = 0; i < count; i++) {
                    queue.push(i);
                }
                System.out.println("[Push] Done. Queue size: " + queue.size());
            }
            finally {
                locker.unlock();
            }
        };
    }

    public static Runnable getPopRun() {
        return () -> {
            locker.lock();
            try {
                while (queue.size() > 0) {
                    queue.pop();
                }
                System.out.println("[Pop] Done");
            }
            finally {
                locker.unlock();
            }
        };
    }

}
