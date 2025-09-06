import time, statistics, threading, json
from functools import wraps

def bench(n_threads=1, seq_iter=1, iter=1):
    """
    A benchmarking decorator that runs a function in parallel threads,
    multiple times, and returns stats in a dictionary.

    Parameters:
        n_threads (int): Number of threads (default: 1)
        seq_iter  (int): Number of times fun is invoked sequentially in each thread (default: 1)
        iter      (int): Number of repetitions of the whole parallel execution (default: 1)
    """
    def decorator(func):
        """
        Decorator function to wrap the target function.
        """
        @wraps(func)
        def wrapper(*args, **kwargs):
            timings = []

            # Worker function to execute the target function sequentially for a specified number of iterations.
            def worker():
                for _ in range(seq_iter):
                    func(*args, **kwargs)

            for _ in range(iter):
                threads = []

                start = time.perf_counter()

                # Launch threads
                for _ in range(n_threads):
                    t = threading.Thread(target=worker)
                    t.start()
                    threads.append(t)

                # Wait for all threads to finish
                for t in threads:
                    t.join()

                end = time.perf_counter()
                timings.append(end - start)

            # Calculate mean and variance of the recorded timings
            mean_time = statistics.mean(timings)
            var_time = statistics.variance(timings) if len(timings) > 1 else 0.0

            return {
                'fun': func.__name__,
                'args': args,
                'n_threads': n_threads,
                'seq_iter': seq_iter,
                'iter': iter,
                'mean': mean_time,
                'variance': var_time
            }
        return wrapper
    return decorator

def test(iter, fun, args):
    """
    A function to test the benchmarking decorator with different thread counts.

    Parameters:
        iter (int): Number of repetitions for the benchmark
        fun (callable): The function to be benchmarked
        args (tuple): Arguments to be passed to the function
    """
    # Different configurations of threads and sequential iterations to test
    configs = [
        (1, 16),
        (2, 8),
        (4, 4),
        (8, 2),
    ]
    for n_threads, seq_iter in configs:
        result = bench(n_threads=n_threads, seq_iter=seq_iter, iter=iter)(fun)(*args)

        # Build filename
        args_str = "_".join(map(str, args))
        filename = f"./output/{result['fun']}_{args_str}_{n_threads}_{seq_iter}.json"

        with open(filename, "w") as f:
            json.dump(result, f, indent=4)

        print(f"Saved results to {filename}")


# Example functions
def just_wait(n):
    # Simulate I/O (sleep)
    time.sleep(n * 0.1)

def grezzo(n):
    # CPU intensive loop
    for _ in range(2**n):
        pass


if __name__ == "__main__":
    print("Running test on just_wait...")
    test(iter=5, fun=just_wait, args=(5,))  # Wait 0.5s each call

    print("Running test on grezzo...")
    test(iter=5, fun=grezzo, args=(20,))  # CPU heavy

# Experimentation Results:
# The benchmarking experiments show different behaviors depending on the function tested:
#
# 1. just_wait(n): This function simulates I/O by sleeping. The total execution time increases linearly with the sleep duration.
#    When using multiple threads, the sleep operations can overlap, so wall-clock time decreases as thread count increases.
#    Minor variations may occur due to thread management overhead.
#
# 2. grezzo(n): This function is CPU-bound, performing a large number of computations in a loop.
#    Due to Python's Global Interpreter Lock (GIL), threads cannot execute Python bytecode in parallel.
#    As a result, increasing the number of threads does not improve performance for CPU-bound tasks; execution time remains similar.
#
# Summary: Multithreading provides significant benefits for I/O-bound tasks like sleeping, allowing concurrent execution.
# For CPU-bound tasks, the GIL prevents true parallelism, so multithreading does not yield performance improvements.