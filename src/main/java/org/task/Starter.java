package org.task;


import org.task.model.Job;

public class Starter {
    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.initFromString("{\n" +
                "    Job{1, 30, []int{2, 4}},\n" +
                "    Job{2, 10, []int{3}},\n" +
                "    Job{4, 60, []int{}},\n" +
                "    Job{3, 20, []int{}},\n" +
                "}");
        System.out.println(processor.calculateDuration(5));
    }
}
