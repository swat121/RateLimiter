package algorithm.RateLimiter.SlidingWindow;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class RateLimiter {
    public final int maxRequestPerMin;

    protected RateLimiter(int maxRequestPerMin) {
        this.maxRequestPerMin = maxRequestPerMin;
    }

    public abstract int allow(AtomicInteger preCount, long curWindowKey);

    public abstract long getCurWindowKey();
}
