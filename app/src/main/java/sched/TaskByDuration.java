
package sched;

/**
 * TaskByDuration
 */
public class TaskByDuration extends Task {

    public TaskByDuration(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    public int compareTo(Task task) {
        if (this.duration < task.duration) {
            return -1;
        } else if (this.duration > task.duration) {
            return 1;
        } else {
            return 0;
        }
    }

    // @Override
    // public String toString() {
    // return super.toString() + ", Duration: " + duration;
    // }
}
