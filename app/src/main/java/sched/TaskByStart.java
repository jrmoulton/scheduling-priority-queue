
package sched;

/**
 * TaskByStart
 */
public class TaskByStart extends Task {

    public TaskByStart(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    public int compareTo(Task task) {
        if (this.start == task.start) {
            if (this.deadline < task.deadline) {
                return -1;
            } else if (this.deadline >= task.deadline) {
                return 1;
            } else {
                return 0;
            }
        }
        if (this.start < task.start) {
            return -1;
        } else {
            return 1;
        }
    }
}
