package com.company.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ProblemSolver {

    public static List<Future<Info>> solve(String path, int threadNum) throws IOException, InterruptedException {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        ConcurrentSkipListSet<Info> infoSet = new ConcurrentSkipListSet<>();
        ArrayList<Task> parseTasks = lines.parallelStream()
                .map(line -> new Task(line, infoSet))
                .collect(Collectors.toCollection(ArrayList::new));
        ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);
        return threadPool.invokeAll(parseTasks);
    }

}