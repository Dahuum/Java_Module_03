#include <pthread.h>
#include <stdio.h>

void* thread_func(void* arg) {
    while (1) { printf("Thread running\n"); }  // Infinite loop
}

int main() {
    pthread_t thread;
    pthread_create(&thread, NULL, thread_func, NULL);
	pthread_join(thread, NULL);
    printf("Main exiting\n");
    return 0;  // Kills ALL threads (including the infinite one)
}
