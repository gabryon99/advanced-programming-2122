import functools, math, logging, json
from threading import Thread
from time import perf_counter, sleep
from statistics import mean, variance

def bench(n_threads = 1, seq_iter = 1, iter = 1):
    """
    n_threads:  number of threads
    seq_iter:   number of times fun must be invoked in each thread
    iter:       number of times the whole execution of the n_threads threads,
                each invoking seq_iter times fun, is repeated. For each execution, the execution time
                has to be computed 
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
                # Add a new timee inside the times array
                times.append(ending - starting)

            computed_mean = mean(times)
            computed_variance = variance(times, computed_mean)

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

def test(iter, fun, *args):

    n_threads = 1
    seq_iter = 16

    logging.info("Starting `test` utility...")

    for _ in range(int(math.log2(seq_iter))):

        logging.info(f"Creating new bench [n_threads={n_threads},seq_iter={seq_iter},iter={iter}]")
        new_bench = bench(n_threads=n_threads, seq_iter=seq_iter, iter=iter)
        decorated_fun = new_bench(fun)

        filename = f"{fun.__name__}_{args}_{n_threads}_{seq_iter}.json"
        with open(filename, "w") as f:
            logging.info("Executing bench...")
            f.write(json.dumps(decorated_fun(*args)))
            logging.info(f"Writing results into file `{filename}`...")
        
        n_threads *= 2
        seq_iter = int(seq_iter / 2)
    logging.info("Ending `test` utility...")

logging.basicConfig(level=logging.INFO)
test(1, fib, 25)