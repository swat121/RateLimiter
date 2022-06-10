package algorithm.RateLimiter.SlidingWindow;

import algorithm.RateLimiter.config.Config;
import algorithm.RateLimiter.model.Client;
import algorithm.RateLimiter.service.ClientService;
import algorithm.RateLimiter.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SlidingWindow extends RateLimiter {

    private long key;

    public SlidingWindow(Config config) {
        super(config.getMaxRequestPerMin());
    }

    @Override
    public int allow( AtomicInteger preCount, long curWindowKey) {
        ConcurrentMap<Long, AtomicInteger> windows = new ConcurrentHashMap<>();
        windows.putIfAbsent(curWindowKey,preCount);
        long curTime = System.currentTimeMillis();

        int time = 60000;

        curWindowKey = curTime / time * time;

        key = curWindowKey;

        windows.putIfAbsent(curWindowKey, new AtomicInteger(0));
        long preWindowKey = curWindowKey - time;


        preCount = windows.get(preWindowKey);


        if (preCount == null) {

            return windows.get(curWindowKey).incrementAndGet();
        }

        double preWeight = 1 - (curTime - curWindowKey) / (double) time;
        long count = (long) (preCount.get() * preWeight
                + windows.get(curWindowKey).incrementAndGet());
        return (int) count;
    }
    @Override
    public long getCurWindowKey(){
        return key;
    }
}


