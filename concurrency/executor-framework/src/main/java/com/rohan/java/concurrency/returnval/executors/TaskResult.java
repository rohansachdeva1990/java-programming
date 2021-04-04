package com.rohan.java.concurrency.returnval.executors;

import java.util.Objects;

public class TaskResult <S, R> {

    private S taskId;
    private R result;

    public TaskResult(S taskId, R result) {
        this.taskId = taskId;
        this.result = result;
    }

    public S getTaskId() {
        return taskId;
    }

    public R getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResult<?, ?> that = (TaskResult<?, ?>) o;
        return Objects.equals(taskId, that.taskId) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, result);
    }

    @Override
    public String toString() {
        return "~~~~ TaskResult{" +
                "taskId=" + taskId +
                ", result=" + result +
                '}';
    }
}
