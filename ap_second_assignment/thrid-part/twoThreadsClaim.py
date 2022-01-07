import functools, math, logging, json
from threading import Thread
from time import perf_counter, sleep
from statistics import mean, variance

def bench(n_threads = 1, seq_iter = 1, iter = 1):
    """
    Args:
        n_threads:   number of threads
        seq_iter:   number of times fun must be invoked in each thread
        iter:       number of times the whole execution of the n_threads threads,
                    each invoking seq_iter times fun, is repeated. For each execution, 
                    the execution time has to be computed 
    """
    def decorator_bench(fun):

        @functools.wraps(fun)
        def wrapper_bench(*args, **kwargs):
            def thread_target():
                for _ in range(seq_iter):
                    fun(*args, **kwargs)
                    
            times = [1, 1]

            for _ in range(iter):

                # Create n_threads inside a list, each thread has name associated to its number inside the list
                threads = [Thread(target=thread_target, name=f"Thread-{i}") for i in range(n_threads)]
                # Save starting time
                starting = perf_counter()
                # Starts the n_thread
                for t in threads:
                    t.start()
                # Wait for threads to finish their jobs
                for t in threads:
                    t.join()
                # Save ending time
                ending = perf_counter()
                # Add a new time inside the times array
                times.append(ending - starting)

            computed_mean = mean(times)
            computed_variance = variance(times)

            return {
                "fun": fun.__name__,
                "args": args,
                "n_threads": n_threads,
                "seq_iter": seq_iter,
                "iter": iter,
                "mean": computed_mean,
                "variance": computed_variance
            }

        return wrapper_bench

    return decorator_bench

def fib(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fib(n - 1) + fib(n - 2)

def just_wait(n):
    sleep(n * 0.1)

def grezzo(n):
    for _ in range (2 ** n):
        pass

def exp_by_squaring(x, n):
    if n < 0:
        x = 1 / x
        n = -n
    if n == 0:
        return 1
    y = 1
    while n > 1:
        if n % 2 == 0:
            x = x * x
            n = n / 2
        else:
            y = x * y
            x = x * x 
            n = (n - 1) / 2
    return x * y

def test(iter, fun, *args):

    """
    Args:
        iter: how many iterations
        fun: the function to test using the multi-threading
        args: the argumento to provide to the `fun` function in order to run correctly
    """

    n_threads = 1
    seq_iter = 16
    # Start test utility and inform the user
    logging.info("Starting `test` utility...")

    for _ in range(int(math.log2(seq_iter))):

        logging.info(f"Creating new bench [n_threads={n_threads},seq_iter={seq_iter},iter={iter}]")
        # Create a new bench with the given paramater
        new_bench = bench(n_threads=n_threads, seq_iter=seq_iter, iter=iter)
        # Decore the function `fun` using the new created bench
        decorated_fun = new_bench(fun)

        # Create a new json file using the function name and the given bench paramaters.
        filename = f"{fun.__name__}_{args}_{n_threads}_{seq_iter}.json"

        # Write the file in the same directory where the program is executed
        with open(filename, "w") as f:
            logging.info("Executing bench...")
            f.write(json.dumps(decorated_fun(*args)))
            logging.info(f"Writing results into file `{filename}`...")
        
        n_threads *= 2
        seq_iter = int(seq_iter / 2)

    # Inform the user test utility is ended...
    logging.info("Ending `test` utility...")


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO)
    try:
        test(1000, grezzo, 16)
    except KeyboardInterrupt:
        logging.info("Terminating test utility.")
    pass

# =======================================================================================================
# | Final Thoughts                                                                                       |
# =======================================================================================================
# | After several tests, I can say the claim stated during the lesson is true.                          |
# | Executing the function in a multi-thread environment doesn't change the running time                |
# | of the execution. We can see from the collected data that there is no gain at all                   |
# | when running the function in parallel, the single-thread execution performs (more or less)          |
# | identical to the one with 8 threads. Therefore, the Python Global Interepreter Lock acts as a       |
# | bottleneck for multi-thread applications.                                                           |
# | The benchmark was executed on a Intel Core i7 2,6 GHz 6-Core                                        |                                       
# =======================================================================================================