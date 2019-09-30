package org.task;

import org.task.model.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processor {
    private static final String TEMPLATE = "Job\\s*\\{(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*\\[]int\\{(\\d+\\s*(?:,\\s*\\d+)*)*\\s*}\\s*}";
    private List<Job> storage = new ArrayList<>();

    public void initFromList(List<Job> jobs){
        storage = jobs;
    }

    public void initFromArray(Job[] jobs){
        storage = new ArrayList<>(Arrays.asList(jobs));
    }

    public void initFromString(String statement){
        Matcher matcher = Pattern.compile(TEMPLATE).matcher(statement);
        while (matcher.find()){
            int[] jobs = new int[0];
            if(matcher.group(3) != null){
                jobs = Arrays.stream(matcher.group(3).split("\\s*,\\s*")).map(Integer::parseInt).mapToInt(x->x).toArray();
            }
            storage.add(new Job(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), jobs));
        }
    }

    private Job findJobInStorage(int id){
        for(Job job : storage)
            if(job.getId() == id)
                return job;
        return null;
    }

    public int calculateDuration(int id){
        for(Job job : storage){
            if (job.getId() == id){
                return job.getFullJobTime(this::findJobInStorage);
            }
        }
        return 0;
    }
}
