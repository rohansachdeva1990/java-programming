package com.rohan.java.concurrency.threads.scheduling;

import com.rohan.concurrency.common.Utils;
import com.rohan.concurrency.common.tasks.ScheduledTaskA;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


/**
 * 1. Zero task delays, because we need to emulate very short lived tasks
 * 2. When we set daemon flag to true, main() has the control and will exit the program and vice versa.
 * 3. If this task overruns the scheduled execution times of some other tasks all those task will
 * execute in quick succession when this long running task terminates. This may or may not be a desirable
 * behavior depending upon the needs of your application.
 * 4. So, you must exercise caution while scheduling long running tasks.
 * 5. If your application needs to execute tasks at the right scheduled time always then
 * it may be better to use different timer objects for different long running tasks.
 * 6. If your tasks are shot lived however then a single time object may fulfill your
 * requirements.
 * 7. Now what if you want to cancel some task after you have scheduled it for that. We use the
 * the timer tasks cancel method let us try canceling the fourth task.
 * 8. Cancelling the timer task, wont start the timer task execution.
 * 9. There might be some delay in ms due to context switch delays.
 *
 */
public class SchedulingTasksForOneTimeExecution {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        // Refer to README.md
//        Timer timer = new Timer("Timer Thread", false);
        Timer timer = new Timer("Timer Thread", true);

        Date currentTime = new Date();
        Date scheduledTime = Utils.getFutureTime(currentTime, 5000);

        System.out.println("[" + currentThreadName + "] Current time: " + dateFormat.format(currentTime));

        //timer.schedule(new ScheduledTaskA(0), scheduledTime);
        timer.schedule(new ScheduledTaskA(8000), scheduledTime);

        System.out.println("[" + currentThreadName + "] Task-1 scheduled for running at specified time: " + dateFormat.format(scheduledTime));

        // ********************************************** //
        // Second task with an initial delay of 10 seconds

        long delayMillis = 10000;
        //ScheduledTaskA task2 = new ScheduledTaskA(0);
        ScheduledTaskA task2 = new ScheduledTaskA(4000);

        timer.schedule(task2, delayMillis);

        System.out.println("[" + currentThreadName + "] Task-2 scheduled to run "
                + delayMillis / 1000 + " seconds INITIAL-DELAY after current time at specified time: "
                + dateFormat.format(new Date(task2.scheduledExecutionTime())));

        // ********************************************** //
        // Third task with an initial delay of 12 seconds

        long delayMillis2 = 12000;
        ScheduledTaskA task3 = new ScheduledTaskA(0);

        timer.schedule(task3, delayMillis2);

        System.out.println("[" + currentThreadName + "] Task-3 scheduled to run "
                + delayMillis2 / 1000 + " seconds INITIAL-DELAY after current time at specified time: "
                + dateFormat.format(new Date(task3.scheduledExecutionTime())));


        // ********************************************** //
        // Fourth task specific time

        Date scheduledTime2 = Utils.getFutureTime(currentTime, 30000);
        ScheduledTaskA task4 = new ScheduledTaskA(0);
        timer.schedule(task4, scheduledTime2);

        System.out.println("[" + currentThreadName + "] Task-4 scheduled for running at specified time: "
                + dateFormat.format(new Date(task4.scheduledExecutionTime())));

        task4.cancel();

        // ********************************************** //
        // Intentional delay, This will make the timer thread to die and the program
        // predominate gracefully ....

        TimeUnit.MILLISECONDS.sleep(32000);
        System.out.println("[" + currentThreadName + "] Cancelling the timer now...");
        timer.cancel();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}

/*

[main] Main thread starts here...
[main] Current time: 10-12-2017 18:19:01.719
[main] Task-1 scheduled for running at specified time: 10-12-2017 18:19:06.719
[main] Task-2 scheduled to run 10 seconds INITIAL-DELAY after current time at specified time: 10-12-2017 18:19:11.724
[main] Task-3 scheduled to run 12 seconds INITIAL-DELAY after current time at specified time: 10-12-2017 18:19:13.724
[main] Task-4 scheduled for running at specified time: 10-12-2017 18:19:31.719
###### [Timer Thread] <TASK-ScheduledTaskA-1> SCHEDULED TO RUN AT: 10-12-2017 18:19:06.719, ACTUALLY STARTED AT: 10-12-2017 18:19:06.720 #####
###### [Timer Thread] <TASK-ScheduledTaskA-1> FINISHED AT: 10-12-2017 18:19:14.721 #####

###### [Timer Thread] <TASK-ScheduledTaskA-2> SCHEDULED TO RUN AT: 10-12-2017 18:19:11.724, ACTUALLY STARTED AT: 10-12-2017 18:19:14.721 #####
###### [Timer Thread] <TASK-ScheduledTaskA-2> FINISHED AT: 10-12-2017 18:19:18.722 #####

###### [Timer Thread] <TASK-ScheduledTaskA-3> SCHEDULED TO RUN AT: 10-12-2017 18:19:13.724, ACTUALLY STARTED AT: 10-12-2017 18:19:18.722 #####
###### [Timer Thread] <TASK-ScheduledTaskA-3> FINISHED AT: 10-12-2017 18:19:18.722 #####

###### [Timer Thread] <TASK-ScheduledTaskA-4> SCHEDULED TO RUN AT: 10-12-2017 18:19:31.719, ACTUALLY STARTED AT: 10-12-2017 18:19:31.720 #####
###### [Timer Thread] <TASK-ScheduledTaskA-4> FINISHED AT: 10-12-2017 18:19:31.720 #####

[main] Cancelling the timer now...
[main] Main thread ends here...

Process finished with exit code 0

// Cancelling the timer task - 4

[main] Main thread starts here...
[main] Current time: 10-12-2017 18:38:32.315
[main] Task-1 scheduled for running at specified time: 10-12-2017 18:38:37.315
[main] Task-2 scheduled to run 10 seconds INITIAL-DELAY after current time at specified time: 10-12-2017 18:38:42.330
[main] Task-3 scheduled to run 12 seconds INITIAL-DELAY after current time at specified time: 10-12-2017 18:38:44.330
[main] Task-4 scheduled for running at specified time: 10-12-2017 18:39:02.315
###### [Timer Thread] <TASK-ScheduledTaskA-1> SCHEDULED TO RUN AT: 10-12-2017 18:38:37.315, ACTUALLY STARTED AT: 10-12-2017 18:38:37.316 #####
###### [Timer Thread] <TASK-ScheduledTaskA-1> FINISHED AT: 10-12-2017 18:38:45.319 #####

###### [Timer Thread] <TASK-ScheduledTaskA-2> SCHEDULED TO RUN AT: 10-12-2017 18:38:42.330, ACTUALLY STARTED AT: 10-12-2017 18:38:45.319 #####
###### [Timer Thread] <TASK-ScheduledTaskA-2> FINISHED AT: 10-12-2017 18:38:49.320 #####

###### [Timer Thread] <TASK-ScheduledTaskA-3> SCHEDULED TO RUN AT: 10-12-2017 18:38:44.330, ACTUALLY STARTED AT: 10-12-2017 18:38:49.320 #####
###### [Timer Thread] <TASK-ScheduledTaskA-3> FINISHED AT: 10-12-2017 18:38:49.320 #####

[main] Cancelling the timer now...
[main] Main thread ends here...


 */