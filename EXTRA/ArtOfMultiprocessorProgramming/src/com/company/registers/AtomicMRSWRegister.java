package com.company.registers;

public class AtomicMRSWRegister<T> implements Register<T> {
    ThreadLocal<Long> lastStamp;
    private StampedValue<T>[][] a_table; // each entry is SRSW atomic
    public AtomicMRSWRegister(T init, int readers) {
         lastStamp = new ThreadLocal<Long>() {
            protected Long initialValue() { return 0L; };
         };
         a_table = (StampedValue<T>[][]) new StampedValue[readers][readers];
         StampedValue<T> value = new StampedValue<T>(init);
         for (int i = 0; i < readers; i++) {
             for (int j = 0; j < readers; j++) {
                 a_table[i][j] = value;
             }
         }
    }

    @Override
    public T read() {
        int me = (int) Thread.currentThread().getId();
        StampedValue<T> value = a_table[me][me];
        for (int i = 0; i < a_table.length; i++) {
            value = StampedValue.max(value, a_table[i][me]);
        }
        for (int i = 0; i < a_table.length; i++) {
            a_table[me][i] = value;
        }
        return value.value;
    }
    @Override
    public void write(T value) {
        long stamp = lastStamp.get() + 1;
        lastStamp.set(stamp);
        StampedValue<T> w_value = new StampedValue<T>(stamp, value);
        for (int i = 0; i < a_table.length; i++) {
            a_table[i][i] = w_value;
        }
    }
}
