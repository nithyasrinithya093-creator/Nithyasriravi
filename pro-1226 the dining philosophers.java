class DiningPhilosophers {

    private final ReentrantLock[] forks = new ReentrantLock[5];
    private final Semaphore semaphore = new Semaphore(4);

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    public void wantsToEat(
            int philosopher,
            Runnable pickLeftFork,
            Runnable pickRightFork,
            Runnable eat,
            Runnable putLeftFork,
            Runnable putRightFork) throws InterruptedException {

        semaphore.acquire();

        ReentrantLock left = forks[philosopher];
        ReentrantLock right = forks[(philosopher + 1) % 5];

        left.lock();
        right.lock();

        try {
            pickLeftFork.run();
            pickRightFork.run();

            eat.run();

            putRightFork.run();
            putLeftFork.run();
        } finally {
            right.unlock();
            left.unlock();
            semaphore.release();
        }
    }
}
