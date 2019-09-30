package org.task.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Job {
    private int id;
    private int jobTime;
    private int[] childJobs;

    public Job(int id, int jobTime, int[] childJobs) {
        this.id = id;
        this.jobTime = jobTime;
        this.childJobs = childJobs;
    }

    public Job() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobTime() {
        return jobTime;
    }

    public void setJobTime(int jobTime) {
        this.jobTime = jobTime;
    }

    public List<Job> getChildJobs(Function<Integer, Job> convert){
        List<Job> result = new ArrayList<>();
        Arrays.stream(childJobs).forEach(i->result.add(convert.apply(i)));
        return result;
    }

    public int getFullJobTime(Function<Integer, Job> convert){
        int childJobsTime = 0;
        for(Job job : getChildJobs(convert)){
            childJobsTime += job.getFullJobTime(convert);
        }
        return jobTime + childJobsTime;
    }
}
