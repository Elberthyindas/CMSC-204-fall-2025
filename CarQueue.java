import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
    private Queue<Integer> queue;
    private Random random;
    
    public CarQueue() {
        queue = new LinkedList<>();
        random = new Random();
        
        // Initialize queue with 5-6 random directions
        int initialCount = 5 + random.nextInt(2); // 5 or 6
        for (int i = 0; i < initialCount; i++) {
            queue.add(random.nextInt(4)); // 0, 1, 2, or 3
        }
    }
    
    /**
     * Adds 0,1,2 or 3 to queue
     *  0 = up
     *  1 = down
     *  2 = right
     *  3 = left
     */
    public void addToQueue() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (queue) {
                        int direction = random.nextInt(4);
                        queue.add(direction);
                    }
                    try {
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        };
        
        Thread thread = new Thread(runnable);
        thread.start();
    }
    
    public Integer deleteQueue() {
        synchronized (queue) {
            return queue.poll(); // Returns null if queue is empty
        }
    }
}


