package org.task;

import org.junit.Test;
import org.task.model.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ProcessorTest {
    private static final String STRING_STATEMENT_OF_JOBS = "{\n" +
            "    Job{1, 30, []int{2, 4}},\n" +
            "    Job{2, 10, []int{3}},\n" +
            "    Job{4, 60, []int{}},\n" +
            "    Job{3, 20, []int{}},\n" +
            "}";
    private static final Job[] JOB_ARRAY = new Job[] {new Job(1, 30, new int[]{2,4}),
                                    new Job(2, 10, new int[]{3}),
                                    new Job(4, 60, new int[0]),
                                    new Job(3, 20, new int[0])};

    private static final List<Job> JOB_LIST = new ArrayList<>(Arrays.asList(JOB_ARRAY));

    @Test
    public void testFullJobsScan(){
        Processor processor = new Processor();
        processor.initFromString(STRING_STATEMENT_OF_JOBS);
        assertEquals(processor.calculateDuration(1), 120);
    }

    @Test
    public void testFullJobsSingleScan(){
        Processor processor = new Processor();
        processor.initFromString(STRING_STATEMENT_OF_JOBS);
        assertEquals(processor.calculateDuration(3), 20);
    }

    @Test
    public void testFullJobsSingleScanWrongId(){
        Processor processor = new Processor();
        processor.initFromString(STRING_STATEMENT_OF_JOBS);
        assertEquals(processor.calculateDuration(5), 0);
    }

    @Test
    public void testFullJobsScanOneStepInto(){
        Processor processor = new Processor();
        processor.initFromString(STRING_STATEMENT_OF_JOBS);
        assertEquals(processor.calculateDuration(2), 30);
    }

    @Test
    public void testFullJobsScanArray(){
        Processor processor = new Processor();
        processor.initFromArray(JOB_ARRAY);
        assertEquals(processor.calculateDuration(1), 120);
    }

    @Test
    public void testFullJobsScanList(){
        Processor processor = new Processor();
        processor.initFromList(JOB_LIST);
        assertEquals(processor.calculateDuration(1), 120);
    }

}
