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

#define PORT 1234

// sleep(1) has been added such that we
// can see what exactly is happening

int main(){
	struct sockaddr_in Address_1;
	char Int_Arr[1024];
	int Soc_Client = socket(AF_INET, SOCK_STREAM, 0);
	if(Soc_Client < 0){
		printf("***CONNECTION ERROR***\n");
		sleep(1);
		exit(1);
	}
	sleep(1);
	printf("***SOCKET CREATED BY THE CLIENT***\n");
	sleep(1);
	memset(&Address_1, '\0', sizeof(Address_1));
	Address_1.sin_family = AF_INET;
	Address_1.sin_port = htons(PORT);
	Address_1.sin_addr.s_addr = inet_addr("127.0.0.1");
	int val = connect(Soc_Client, (struct sockaddr*)&Address_1, sizeof(Address_1));
	if(val < 0){
		printf("***CONNECTION ERROR***\n");
		sleep(1);
		exit(1);
	}
	printf("***CONNECTION ESTABLISHED WITH THE SERVER***\n");
	sleep(1);
	while(1){
		printf("SEND REQUEST TO THE SERVER (ENTER INTEGER): ");
		scanf("%s", &Int_Arr[0]);
		send(Soc_Client, Int_Arr, strlen(Int_Arr), 0);
		sleep(1);
		if(strcmp(Int_Arr, "end") == 0){
			sleep(1);
			close(Soc_Client);
			printf("***CONNECTION DISCONNECTED FROM THE SERVER***\n");
			sleep(1);
			exit(1);
		}
		if(recv(Soc_Client, Int_Arr, 1024, 0) < 0){
			printf("***DATA RECEPTION ERROR***\n");
			sleep(1);
		}
		else{
			printf("ACKNOWLEDGEMENT FROM SERVER: %llu\n", atoll(Int_Arr));
			sleep(1);
		}
	}
	return 0;
}