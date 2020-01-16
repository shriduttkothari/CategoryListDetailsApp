package com.categoryapp.utils

import kotlinx.coroutines.*

/**
 * Coroutines switching helper
 *
 * @author Shridutt.Kothari
 */
object Coroutines {

    /**
     * In the kotlinx.coroutines library, you can start new coroutine using either launch or async function.
     * Conceptually, async is just like launch. It starts a separate coroutine which is a light-weight thread that works concurrently with all the other coroutines.
     * The difference is that launch returns a Job and does not carry any resulting value, while async returns a Deferred - a light-weight non-blocking future that
     * represents a promise to provide a result later. You can use .await() on a deferred value to get its eventual result, but Deferred is also a Job,
     * so you can cancel it if needed.
     *
     * If the code inside the launch terminates with exception, then it is treated like uncaught exception in a thread crashes Android applications.
     * An uncaught exception inside the async code is stored inside the resulting Deferred and is not delivered anywhere else, it will get silently dropped unless processed.
     *
     * rt@ is for retry
     */

    /**
     * This function is a higher level function, it takes function as argument (ex: it's taking 2 functions work, callback as input)
     *
     * returns cancellable Job
     */
    fun<T: Any> ioThenMain(work: suspend (() -> T?), callback: ((T?)->Unit)): Job {
        var job = CoroutineScope(Dispatchers.Main).launch {
            val task = CoroutineScope(Dispatchers.IO).async rt@{
                // background thread
                // your blocking call
                rt@ work()
            }
            //MAIN continues here
            val result = task.await()
            callback(result)
        }
        return job
    }
}