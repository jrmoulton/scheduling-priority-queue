
package sched;

public abstract class Task implements Comparable<Task> {
    private int ID;
    public int start;
    public int deadline;
    public int duration;
    public int late;

    public Task(int ID, int start, int deadline, int duration) {
        this.ID = ID;
        this.start = start;
        this.deadline = deadline;
        this.duration = duration;
        this.late = 0;
    }

    @Override
    public String toString() {
        return "Task " + this.ID;
    }
}
