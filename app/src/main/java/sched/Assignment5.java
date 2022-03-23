
package sched;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        simpleQueueTest();
        scheduleTasks("taskset1.txt");
        scheduleTasks("taskset2.txt");
        scheduleTasks("taskset3.txt");
        scheduleTasks("taskset4.txt");
        scheduleTasks("taskset5.txt");
    }

    public static void scheduleTasks(String taskFile) {
        // DONE: Uncomment code here as you complete the tasks and scheduling algorithm
        ArrayList<Task> tasksByDeadline = new ArrayList<>();
        ArrayList<Task> tasksByStart = new ArrayList<>();
        ArrayList<Task> tasksByDuration = new ArrayList<>();

        readTasks(taskFile, tasksByDeadline, tasksByStart, tasksByDuration);

        System.out.println("Deadline Priority : " + taskFile);
        schedule("Deadline Priority : " + taskFile, tasksByDeadline);

        System.out.println("Startime Priority : " + taskFile);
        schedule("Startime Priority : " + taskFile, tasksByStart);

        System.out.println("Duration Priority : " + taskFile);
        schedule("Duration priority : " + taskFile, tasksByDuration);
    }

    public static void simpleQueueTest() {
        // DONE: Uncomment code here for a simple test of your priority queue code
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(9);
        queue.enqueue(7);
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(10);

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }

    /**
     * Reads the task data from a file, and creates the three different sets of
     * tasks for each
     */
    public static void readTasks(String filename,
            ArrayList<Task> tasksByDeadline,
            ArrayList<Task> tasksByStart,
            ArrayList<Task> tasksByDuration) {
        // DONE: Write your task reading code here
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            int count = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] priorities = line.split("\\s+");
                int start = Integer.parseInt(priorities[0]);
                int deadline = Integer.parseInt(priorities[1]);
                int duration = Integer.parseInt(priorities[2]);
                tasksByStart.add(new TaskByStart(count, start, deadline, duration));
                tasksByDeadline.add(new TaskByDeadline(count, start, deadline, duration));
                tasksByDuration.add(new TaskByDuration(count, start, deadline, duration));
                count += 1;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a set of tasks, schedules them and reports the scheduling results
     */
    public static void schedule(String label, ArrayList<Task> tasks) {
        // DONE: Write your scheduling algorithm here
        var queue = new PriorityQueue<Task>();
        int clock = 0;
        var tasksLate = 0;
        var totalLate = 0;
        var lates = new HashMap<Task, Integer>();
        while (!tasks.isEmpty() || !queue.isEmpty()) {
            clock += 1;
            while (!tasks.isEmpty() && clock >= tasks.get(0).start) {
                var tempTask = tasks.remove(0);
                lates.put(tempTask, 0);
                queue.enqueue(tempTask);
            }
            if (!queue.isEmpty()) {
                var taskout = queue.dequeue();
                if (clock > taskout.deadline) {
                    lates.put(taskout, lates.get(taskout) + 1);
                    // taskout.late += 1;
                }
                // Fix the second tab
                System.out.print("\tTime\t" + clock + ": " + taskout.toString());
                taskout.duration -= 1;
                if (taskout.duration > 0) {
                    queue.enqueue(taskout);
                } else {
                    System.out.print(" **");
                    if (lates.get(taskout) > 0) {
                        totalLate += lates.get(taskout);
                        tasksLate += 1;
                        System.out.print(" Late " + lates.get(taskout));
                    }
                }
                System.out.println();
            } else {
                System.out.println("\tTime\t" + clock + ": ---");
            }
        }
        System.out.println("Tasks late " + tasksLate + " Total Late " + totalLate + "\n");
    }
}
