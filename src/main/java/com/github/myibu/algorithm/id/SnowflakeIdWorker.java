package com.github.myibu.algorithm.id;

/**
 * Twitter Snowflake
 * see <a href="https://github.com/twitter-archive/snowflake/blob/scala_28/src/main/scala/com/twitter/service/snowflake/IdWorker.scala">snowflake's IdWorker</a>
 * @author myibu
 * Created on 2022/10/12
 */
public class SnowflakeIdWorker {
    private final static long twepoch = 1598598185157L;

    private long sequence = 0L;
    private final static long workerIdBits = 5L;
    private final static long datacenterIdBits = 5L;
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final static long sequenceBits = 12L;

    private final static long workerIdShift = sequenceBits;
    private final static long datacenterIdShift = sequenceBits + workerIdBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    private final long workerId;
    private final long datacenterId;

    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}