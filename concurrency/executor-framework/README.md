# Concurrency

## Basics - Running

- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/running)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/running)

## Naming

- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/naming)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/naming)

## Returning values

- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/returnval)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/returnval)

## Daemon threads

- Background / service providing threads
- Killed by the JVM as soon as no user threads is running any longer. 
    - Can be stopped normally too - just like normal threads.
- "main" thread is a "user" thread - not a "daemon".
- Any kind of logic as desired can be put in daemon threads. Eg.:
    - Directory watcher thread
    - Socket-reader thread
    - Long calculation related thread
- Depends upon the need of the application
- For threads, we use void Thread.setDaemon(boolean on), which is also used in Executors
- For executors, implement a ThreadFactory and manipulate the thread there to make it a "daemon"
- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/daemon)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/daemon)

## Alive Check

- A thread is live when it has started but not terminated yet.
- If thread is terminated, it cannot be reused again or cannot be started using the same reference.
- Executor threads never dies instantaneously, they go into wait state, if no other task is available.
- Only when an executor service is shutdown or threads are forced shutdown or in case of uncaught exception.
- We use isAlive() method for thread from external
- Are we really interested in a thread being live?
    - No, We are actually interested in task completion!
    - A task can be finished, Only if:
        - task is complete
        - some interrupt
        - some exception
- For executors we use, boolean Future.isDone()
    - Will return true if:
        - The task execution is completed - whether normally or with exception or with cancellation
    - Will return false if:
        - The task execution has not been completed yet. In fact, may not even have started yet.  
- FutureTask, concrete for Future Itf, with additional functionality
    - Implements runnable 
    - Constructor can accept runnable and callables.
- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/alivecheck)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/alivecheck)


## Terminating Threads

- Our intention is how to terminate task rather than thread.
- Few ways to terminate the threads
    - Terminating at non-blocked portions of the task:
        - Terminate task are not blocked on anything, they are running normally
            - Use a flag and check the flag and do the clean up.
        - Using interrupts:
            - void Thread.interrupt() - usually called by another thread on the thread to be interrupted
            - static boolean Thread.interrupted() - to be called from inside the interrupted thread
            - boolean Thread.isInterrupted() - to be called by another thread on the interrupted thread
            - Interrupting a thread, just sets a flag inside a thread and its our duty to check if its interrupted and act on it.
            - The interrupted method resets the flag inside the thread value once interrupted. So, it is better to save the state somewhere.
    - Terminating at points in the task where the thread is blocked.
        - For eg. sleeping
        - Using interrupts as discussed above
        - Q. Can we receive the interrupt at blocking point but exit the task later at some point later?
            - A: Yes, Catch interrupted flag at the exception and set a flag, do not exit from there.. Let it progress and at some strategic point check the flag and exit accordingly
            - Be careful, you need to check isInterrupted // FIXME: looptask H
            
- Few ways to terminate threads in executors
    - Terminate at non-blocked portion in the tasks
        - Using a method coded for this purpose of the task
        - Using interrupts:
            - boolean Future.cancel(boolean mayInterruptIfRunning) - to be called by the class holding the Future reference.
                - check API, when "mayInterruptIfRunning" is set to true, then it will interrupt the active running task as well.
            - static boolean Thread.interrupted() - to be called from inside the interrupted task.
            - boolean Future.isCancelled() - to be called on the future outside the interrupted task.
            - boolean cancel(boolean mayInterruptIfRunning)
                - Attempts to cancel execution of this task. This attempt will fail if the task has already completed, has already been cancelled, or could not be cancelled for some other reason. If successful, and this task has not started when cancel is called, this task should never run. If the task has already started, then the mayInterruptIfRunning parameter determines whether the thread executing this task should be interrupted in an attempt to stop the task.
                - After this method returns, subsequent calls to isDone() will always return true. Subsequent calls to isCancelled() will always return true if this method returned true.
                - mayInterruptIfRunning - true if the thread executing this task should be interrupted; otherwise, in-progress tasks are allowed to complete
                - Returns: false if the task could not be cancelled, typically because it has already completed normally; true otherwise
    - Terminating at points in the tasks where the thread is blocked
        - Using interrupts - same methods as above.
    - Terminating all executor tasks in one shot
        - Use: List<Runnable> ExecutorService.shutdownNow()
            - Uses interrupt under the hood.
            - Returns a list of tasks that were awaiting execution yet.
            - the shutdown(), does not attempt to terminate task.
        - Interrupting may terminate blocked as well as non blocked tasks, but these task must be coded properly to handle interrupts
        - To await termination after shutting down, use
            - boolean ExecService.awaitTermination(long timeout, TimeUnit unit)
                - it blocks until all task terminated
                - after shutdown occurs
                - timeout occurs
                - or some interrupt
                - true, only if all tasks are terminated.
        - Learn more about exception thrown by the the get() method of the future class.
        - // Need to make more experiments on this.
        
- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/terminating)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/terminating)

## Handling Uncaught Exceptions

- Handling uncaught or leaked exceptions in multithreading.
- Uncaught Exceptions in Threads
    - Using a try-catch block, wont solve a problem, the exceptions will still leak.
    - Implement Thread.UncaughtExceptionHandler interface
        - Only one method: void uncaughtException(Thread t, Throwable e)
    - Three ways to use UncaughtExceptionHandler
        - Set as default for all the threads in the system
            - static void Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh)
        - Set different handlers for different threads
            - void Thread.setUncaughtExceptionHandler(UncaughtExceptionHandler eh)
        - Use a combination of default-handler and thread specific handler
        
- Uncaught Exception in Executors
    - Implement Thread.UncaughtExceptionHandler interface
        - Only one method: void uncaughtException(Thread t, Throwable e)
    - Three ways to use UncaughtExceptionHandler
        - Set as default for all the threads in the system
            - static void Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh)
        - Set specific handler for specific threads
            - Implement a ThreadFactory and
                - Use void Thread.setUncaughtExceptionHandler(UncaughtExceptionHandler eh)
        - Use a combination of default-handler and thread specific handler
        
- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/handlingexceptions)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/handlingexceptions)

## Waiting For Threads To Finish

- Waiting for Other Normal-Threads to Finish
    - Suspend the current thread so that it continues only after some other thread(s) have finished.
        - Previously discussed techniques:
            - boolean Thread.isAlive()
                - To be called multiple times till you get a 'false'
                - Wastage of CPU cycles
            - wait() and notify() mechanism
                - To be coded manually in the task by the programmer
                - A little difficult to get right
         - Third Technique
            - void Thread.join()
                - In build mechanism -works just like wait() and notify()
                - Easy to use
- Waiting for Executor Tasks to Finish
    - Suspend the current task so that it continues only after some other task(s) have finished
        - Use java.util.concurrent.CountDownLatch
        - An object of CountDownLatch is shared by the waiting tasks and the awaited tasks
            - Waiting-tasks call void await() on the latch
            - Awaited-tasjs call void countdown() on the latch after finishing their executions
            - When the counter reached zero, waiting tasks are released, thereby, bringing them out of the suspended state
            and enabling their execution again.
       
- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/waittofinish)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/waittofinish)
              
## Scheduling Tasks

- Scheduling task for future executions
    - For all scheduling needs, Threads-API provide two classes
        - Only one task at a time is supported.
        - java.util.Timer
            - Spawns a thread for executing the tasks
            - Also contains scheduling logic
            - check api
        - java.util.TimerTask
            - Implements runnable
            - represents the task
            - should be short liced for repeated executions
            - read about 
                - long scheduledExecutionTime()
                    - undefined, if yet to commence
                    - gives the most recent time of live or completed timer task(need to check)
                - boolean cancel()
                    - If task is for one time execution, it wont run when cancel() is called
                    - if running, it will cancel and stop the timer.
                    
    - Few things __worth stressing__ upon about Timer class
        - Single task execution-thread per Timer object
            - For each timer object a single background thread is used to execute all of the timer task
            - The task be executed sequentially only
        - Timer tasks should complete quickly
            - If timer task takes excessive time to complete, it hugs the timer execution thread, This can in-turn delay
            the execution of subsequent tasks which may bunch up and execute in rapid succession when and if the offending
            task finally completes
        - Always call Timer.cancel() when application is shutting down; otherwise it may cause memory leak
            - Never forget to call cancel on the timer when your application is shutting down, Especially
            more so if you have not made the timer thread as daemon. Non-daemon threads can keep your
            application from terminating.
            - Its is better to make the timer thread daemon so that it automatically terminates when the last
            non-daemon thread in the application has ended
        - Don't schedule any more task on the Timer after cancelling it.
            - If timer has been cancelled or the timers task execution thread terminates unexpectedly then
            any further attempt to scheduler task on the timer will result in an illegal state exception
        - Timer class is thread safe
            - So multiple threads can share a single timer object without the need of synchronization.
        - Timer class does not offer any real time guarantees
            - It schedules task using the object classes wait() method only.

- One-Time Execution in Future - Threads API
    - There are two overloaded methods, both represent the time we want to schedule are task for
        - void schedule(TimerTask task, Date time)
        - void schedule(TimerTask task, long delay)
    - The java documentation says that once all the tasks have finished execution and no more tasks to be executed the 
    timer thread will terminate gracefully but with a catch.
    - It may take a long time and there is no guarantee as to when will it terminate, This is the reason, 
    why timer thread should not be run as a normal user thread.
                
- Repeated Fixed-Delay Executions - Thread API
    - As discussed, timer task classes only are used for repeated executions using the thread API whether for fixed delay
    or fixed rate.
    - The timer class provides two overloaded methods for this purpose:-
        - void schedule(TimerTask task, long delay, long period)
        - void schedule(TimerTask task, Date firstTime, long period)
    - In repeated executions the next execution of the task is scheduled when the current execution begins. That is
    every time a task starts to execute the Timer class calculates the next execution time for this task and schedule
    accordingly.
    - Q: What are fixed delay executions in threads API? 
        - A: In fixed delay executions each execution is scheduled relative to the actual start time of the previous 
        execution. But, schedule time of an execution and its actual start time may differ by a few milliseconds (Due 
        to context switch delays). So, if an execution is delayed for any reason then all  the subsequent executions 
        will be delayed as well. And there will be a drift in the start times over long runs. Also, sometimes we may 
        observe that the first run of the task is also scheduled few milliseconds later than what was specified.
    - Timer class does not give any real time guarantee. In general if a task is scheduled to run at time X then due to 
     some overhead or the other reasons it may many times start a little later than X.
    - Hence, in the long run the frequency of the executions will be slightly lower than the specified while calling 
    the schedule method. For eg:-
        - If a task should run every 2 seconds then its frequency is 31 times per minute. But in long runs it appears
        that the frequency comes out to be lower than 31 times per minute because of some millisecond difference between
        the schedule execution time and the actual execution time.
    - If the current actual execution is delayed then the next scheduled execution time will also be delayed.
    - Fixed delay execution is appropriate for the recurring activities that require smoothness. An activity appears to
    be smooth to a human eye. If its frequency stays relatively constant in small runs. 
    - This type of scheduling is good for - blinking cursor at regular intervals and automatically repeating character
    as long as key is held down.
    
- Repeated Fixed-Rate Executions - Thread API
    - The timer class provides two overloaded methods for this purpose:-
        - void scheduleAtFixedRate(TimerTask task, long delay, long period)
        - void scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
    - Next execution of the task is scheduled when the current one begins.
    - But here the next execution is scheduled relative to the scheduled start time of the very first execution. So, the
    second executions scheduled start time will be one period later than the first scheduled start time. Similarly, the
    fourth execution start time will be three times the period later than the first execution scheduled start time and so
    on.
    - No drift in the scheduled start times and hence the actual start time over long runs.
        - Frequency of execution remains constant
    - Appropriate for activities that are sensitive to the 'absolute time' and accurate frequency of executions. Eg countdown 
     
- Scheduling Task using Executor-API
    - Special thread-pool(s) provided by Executors for scheduling tasks ...
        - Available pools:
            - Single thread scheduled executor
            - Scheduled-thread-pool
        - Created using Executors(C - Concrete)
            - static ScheduledExecutorService newSingleThreadScheduledExecutor()
            - static ScheduledExecutorService newScheduleThreadPool(int corePoolSize)
        - ScheduledExecutorService(I - Interface)
            - Extends ExecutorService(I - Interface)  
                - ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
                - <V> ScheduledFuture<?> schedule(Callable<V> callable, long delay, TimeUnit unit)
                - ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
                - ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long delay, TimeUnit unit)
        - ScheduledFuture(I). Extends
            - Future(I)
            - Delayed(I)
                - long getDelay(TimeUnit unit)
    - One Time Execution in Future - Executors API
        - Two methods:
            - ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
            - <V> ScheduledFuture<V> schedule(Callbale<V> command, long delay, TimeUnit unit)
        - Executions can be scheduled using single-thread-executor or scheduled-thread-pool
        - No need to extend TimerTask. Normal Runnable and Callable are only needed
    - Repeated Fixed-Delay Executions - Executors API
        - Only one method:
            - ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
        - Executions can be scheduled using a single-thread-executor or scheduled-thread-pool
        - Each executions is schedule relative to the termination-time of the previous execution
            - Start times drift toward over long runs
            - Hence, frequency of executions becomes lower than that specified
        - Only runnable can be used, No callable!
        - Keep in mind that if execution od task is explicitly cancelled or if the 
        execution encounters any exception then all the subsequent execution of that task are suppressed.
    - Repeated Fixed-Rate Executions - Executors API
        - Only one method:
            - ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long delay, TimeUnit unit)
        - Executions can be scheduled using single-thread-executor or scheduled-thread-pool
        - Each execution is schedule relative to the scheduled start time of the first execution
            - Start time DO NOT drift forward over long runs
            - Hence, frequency of executions remains constant
        - Only runnable can be scheduled - no Callable!
        - If you need your frequency of executions to stay constant then the execution duration of your task should be less
        than the specified period.
    - General
        - By "the task will hog the only thread", mean that if a task is long-lived (i.e. it executes for a loooong 
        duration), then it will continue running in the thread for that duration and will not allow any other scheduled 
        tasks to run in that thread at their scheduled times. This will result in the other tasks getting delayed 
        because of this long-lived task.
        - So, if you want that your tasks should run at their scheduled times, then make sure that the tasks are 
        short-lived i.e. their execution durations are small. Obviously, this means that the execution durations 
        of the tasks should be smaller than the interval-duration between two successive executions so that the 
        execution of one task does not over-shoot into the execution of the subsequent scheduled task.
            
- [Thread Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/threads/scheduling)
- [Executor Showcase](../concurrency-executors-basics/src/main/java/com/rohan/concurrency/executors/scheduling)
