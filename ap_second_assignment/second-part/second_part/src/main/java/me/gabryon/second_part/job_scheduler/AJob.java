package me.gabryon.second_part.job_scheduler;

import java.util.stream.Stream;

public abstract class AJob<K, V>{
    public abstract Stream<Pair<K, V>> execute();
}
