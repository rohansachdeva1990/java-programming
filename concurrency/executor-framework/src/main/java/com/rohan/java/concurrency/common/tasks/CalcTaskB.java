package com.rohan.java.concurrency.common.tasks;


import com.rohan.java.concurrency.returnval.executors.TaskResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalcTaskB implements Callable<TaskResult<String, Integer>> {

    private int a;
    private int b;
    private long sleeptTime;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    public CalcTaskB(int a, int b, long sleeptTime) {
        this.a = a;
        this.b = b;
        this.sleeptTime = sleeptTime;

        this.instanceNumber = ++count;
        this.taskId = "CalcTaskB-" + instanceNumber;
    }

    public TaskResult<String, Integer> call() throws Exception {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("###### [" + currentThreadName + "] <" + taskId + "> STARTING ######");
        System.out.println("[" + currentThreadName + "] <" + taskId + "> Sleeping for " + sleeptTime + " millis");

        TimeUnit.MILLISECONDS.sleep(sleeptTime);

        System.out.println("###### [" + currentThreadName + "] <" + taskId + "> DONE ######");

        return new TaskResult<String, Integer>(taskId, a + b);
    }
}


/*



[main] Main thread starts here...
###### [MyThread-2] <TASK-loopTaskF-1> STARTING ######
###### <TASK-1> STARTING ######
<TASK-1> TICK TICK 0
###### [MyThread-3] <TASK-CalcTaskC-1> STARTING ######
###### [MyThread-3] <TASK-CalcTaskC-1> CURRENT RUNNING AVERAGE = 0
###### [MyThread-4] <TASK-FactorialTaskB-1> STARTING ######
###### [MyThread-4] <TASK-FactorialTaskB-1> iteration - 1. Intermediate Result = 1
###### [MyThread-5] <CalcTaskB-1> STARTING ######
[MyThread-5] <CalcTaskB-1> Sleeping for 9000 millis
###### [MyThread-3] <TASK-CalcTaskC-1> CURRENT RUNNING AVERAGE = 119
###### [MyThread-3] <TASK-CalcTaskC-1> CURRENT RUNNING AVERAGE = 89
###### [MyThread-4] <TASK-FactorialTaskB-1> iteration - 2. Intermediate Result = 2
###### [MyThread-3] <TASK-CalcTaskC-1> CURRENT RUNNING AVERAGE = 92
<TASK-1> TICK TICK 1
###### [MyThread-3] <TASK-CalcTaskC-1> CURRENT RUNNING AVERAGE = 92
<TASK-1> TICK TICK 2
###### [MyThread-3] <TASK-CalcTaskC-1> CURRENT RUNNING AVERAGE = 88
<TASK-1> TICK TICK 3
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at java.lang.Thread.sleep(Thread.java:340)
	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
	at com.rohan.concurrency.common.tasks.LoopTaskA.run(LoopTaskA.java:21)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at java.lang.Thread.sleep(Thread.java:340)
	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
	at com.rohan.concurrency.common.Utils.sleepInMillis(Utils.java:12)
	at com.rohan.concurrency.common.tasks.CalcTaskB.call(CalcTaskB.java:32)
	at com.rohan.concurrency.common.tasks.CalcTaskB.call(CalcTaskB.java:7)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
###### [MyThread-4] <TASK-FactorialTaskB-1> Sleep interrupted. Cancelling ...
###### [MyThread-4] <TASK-FactorialTaskB-1> ENDING ######
###### <TASK-1> DONE ######
###### [MyThread-5] <CalcTaskB-1> DONE ######
###### [MyThread-3] <TASK-CalcTaskC-1> Interrupted, Cancelling ....
###### [MyThread-3] <TASK-CalcTaskC-1> Retrieving 'INTERRUPTED' status again: false
###### [MyThread-3] <TASK-CalcTaskC-1> DONE ######
[main] All threads terminated = false
[main] Main thread ends here...
###### [MyThread-2] <TASK-loopTaskF-1> Interrupted, Cancelling ....
###### [MyThread-2] <TASK-loopTaskF-1> Retrieving 'INTERRUPTED' status again: false
###### [MyThread-2] <TASK-loopTaskF-1> DONE ######

 */