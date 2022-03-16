
package sched;

/**
 * TaskByDeadline
 */
public class TaskByDeadline extends Task {

    public TaskByDeadline(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    public int compareTo(Task task) {
        if (this.deadline < task.deadline) {
            return -1;
        } else if (this.deadline >= task.deadline) {
            return 1;
        } else {
            return 0;
        }
    }
}
