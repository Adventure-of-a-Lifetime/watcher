package com.bosssoft.watcher.utils;

public class CreateKeyUtils {
    private final long epoch = 1420041600000L;

    private final long workerIdBits = 5L;

    private final long dataCenterIdBits = 5L;

    private final long maxWorkerId = ~(-1L << workerIdBits);

    private final long maxDataCenterId = ~(-1L << dataCenterIdBits);

    private final long sequenceBits = 12L;

    private final long workerIdShift = sequenceBits;

    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    private final long sequenceMask = ~(-1L << sequenceBits);

    private volatile long workerId;

    private volatile long dataCenterId;

    private volatile long sequence = 0L;

    private volatile long lastTimestamp = -1L;

    public CreateKeyUtils(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long nextId() throws RuntimeException {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException((String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp)));

        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - epoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
