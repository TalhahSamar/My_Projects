#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <pthread.h>
#include <semaphore.h>
#include <errno.h>
#include <sys/select.h>
#include <sys/time.h>

#define MAX_CLIENTS 10
#define PORT 1234

// sleep(1) has been added such that we
// can see what exactly is happening

void Save_Details(char* a, int b){
	FILE *fptr;
    fptr = fopen("value.txt", "a");
    fprintf(fptr, "CLIENT  -->  IP ADDRESS: %s, & ", a);
    fprintf(fptr, "PORT NUMBER: %d\n", b);
    fclose(fptr);
}

// factorial function has been implemented,
// for calculating the factorial of the number,
// requested by the client.
unsigned long long Fact(char a[]);
unsigned long long Fact(char a[]){
    int n = atoi(a);
    unsigned long long fact = 1;
    if(n>0){
        for(int i = 1; i <= n; ++i) {
            fact *= i;
        }
    }
    return fact;
}

int main(){
    int x;
    struct timeval y;
	fd_set f_d;
    FD_ZERO(&f_d);
    FD_SET(0, &f_d);
    y.tv_sec = 1;
    y.tv_usec = 0;
    x = select(1, &f_d, NULL, NULL, &y);
	if(x==-1) perror("ERROR!!");
	else{
		struct sockaddr_in Address_1;
		int Soc_Server;
		struct sockaddr_in Address_2;
		socklen_t Size_Add;
		char Int_Arr[1024];
		pid_t Fork_Pro;
		int Socket_1 = socket(AF_INET, SOCK_STREAM, 0);
		if(Socket_1 < 0){
			printf("***CONNECTION ERROR***\n");
			sleep(1);
			exit(1);
		}
		sleep(1);
		printf("***SOCKET CREATED BY THE SERVER***\n");
		sleep(1);
		memset(&Address_1, '\0', sizeof(Address_1));
		Address_1.sin_family = AF_INET;
		Address_1.sin_port = htons(PORT);
		Address_1.sin_addr.s_addr = inet_addr("127.0.0.1");
		int val = bind(Socket_1, (struct sockaddr*)&Address_1, sizeof(Address_1));
		if(val < 0){
			printf("***BINDING ERROR***\n");
			sleep(1);
			exit(1);
		}
		printf("***BINDING TO THE PORT NUMBER: %d***\n", 1234);
		sleep(1);
		if(listen(Socket_1, 10) == 0){
			printf("***LISTENING.....***\n");
			sleep(1);
		}
		else{
			printf("***BINDING ERROR***\n");
			sleep(1);
		}
		while(1){
			Soc_Server = accept(Socket_1, (struct sockaddr*)&Address_2, &Size_Add);
			if(Soc_Server < 0) exit(1);
			printf("CONNECTION ACCEPTED FROM CLIENT <IP ADDRESS: %s> & <PORT NUMBER: %d>\n", inet_ntoa(Address_2.sin_addr), ntohs(Address_2.sin_port));
			Save_Details(inet_ntoa(Address_2.sin_addr), ntohs(Address_2.sin_port));
			sleep(1);
			if((Fork_Pro = fork()) == 0){
				close(Socket_1);
				while(1){
					recv(Soc_Server, Int_Arr, 1024, 0);
					sleep(1);
					if(strcmp(Int_Arr, "end") == 0){
						printf("DISCONNECTED FROM CLIENT <IP ADDRESS: %s> & <PORT NUMBER: %d>\n", inet_ntoa(Address_2.sin_addr), ntohs(Address_2.sin_port));
						sleep(1);
						break;
					}
					else{
						printf("CLIENT [IP ADDRESS: %s, PORT NUMBER: %d] ----> %llu\n", inet_ntoa(Address_2.sin_addr), ntohs(Address_2.sin_port), Fact(Int_Arr));
						sleep(1);
						sprintf(Int_Arr, "%llu", Fact(Int_Arr));
						send(Soc_Server, Int_Arr, strlen(Int_Arr), 0);
						sleep(1);
						bzero(Int_Arr, sizeof(Int_Arr));
					}
				}
			}
		}
		close(Soc_Server);
	}
	return 0;
}