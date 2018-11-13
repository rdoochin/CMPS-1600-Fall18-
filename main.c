//
//  project1.c
//  Project 1
//
//  Created by Rebekah Doochin & Mason O'Connor
//  Copyright © 2018 Rebekah. All rights reserved.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//NODE STRUCTURES AND FUNCTIONS
typedef struct node_struct {
    struct profile_struct* data;    //stores a pointer to a profile
    struct node_struct* next_node;  //stores the pointer to the next node
} node;

struct node_struct** head_p;            //global head for list of profiles
struct friendship_struct** friend_head; //global head for list of friendships


void push(struct profile_struct* new_person, size_t data_size) {        // pushes a new person into the profile linked list
    
    struct node_struct* new_node = (struct node_struct*)malloc(sizeof(struct node_struct)); // allocates memory for new node
    
    new_node->data = malloc(sizeof(data_size));         //allocates memory for profile
    new_node->data = new_person;                        //sets new person as
    new_node->next_node = (*head_p);                    //copies new_person to newly allocated memory
    
    (*head_p) = new_node;                               //set head to new node
}


//PROFILE AND FRIENDSHIP STRUCTURES
typedef struct profile_struct {         //has all necessary variables for a profile
    char email[50];
    char first_name[50];
    char last_name[50];
    int age;
    char hometown[50];
    char hobby[50];
} profile;

typedef struct friendship_struct {      // friendship has pointers to two friends and a pointer to the next node.
    struct profile_struct* friend_A;
    struct profile_struct* friend_B;
    struct friendship_struct* next_connection;
} friendship;

_Bool email_exists(struct node_struct* head, char* email) { //checks recursively to see if an email exists in a linked list
    if (head == NULL) // end case
        return 0;
    
    if (strcmp(head->data->email, email) == 0) // base case checks for the email
        return 1;
    
    return email_exists(head->next_node, email); // recursive step
}

void print_profile(profile* profPoint) {        //prints each element of a profile on its own line
    printf("Email: %s\n", profPoint->email);
    printf("First Name: %s\n", profPoint->first_name);
    printf("Last Name: %s\n", profPoint->last_name);
    printf("Age: %d\n", profPoint->age);
    printf("Hometown: %s\n", profPoint->hometown);
    printf("Hobby: %s\n", profPoint->hobby);
}

//STEP 1 FUNCTIONS

//add function takes in a string for an email and adds a new person
void add(char* newEmail){
    if(email_exists(*head_p, newEmail)){        //checks to see if the email is already in the linked list
        printf("%s already exists\n", newEmail);
        
    }
    else{                                       //if the email doesn't exist, the user is prompted for each other variable
        struct profile_struct* newProf = malloc(sizeof(profile));
        strcpy(newProf -> email, newEmail);
        printf("Please enter a first name: ");
        scanf("%s", newProf -> first_name);
        printf("Please enter a last name: ");
        scanf("%s", newProf -> last_name);
        printf("Please enter an age: ");
        scanf("%d", &newProf -> age);
        printf("Please enter a hometown: ");
        scanf("%s", newProf -> hometown);
        printf("Please enter a hobby: ");
        scanf("%s", newProf -> hobby);
        print_profile(newProf);
        push(newProf, sizeof(profile));
    }
}

void removePerson(char* oldEmail) {                                 //removes a node from the profile list given the identifying email
    if(!email_exists(*head_p, oldEmail)){                           //checks to see if the email is in the list
        printf("%s is not in the list\n", oldEmail);
    }
    else {
        if (strcmp((*head_p)->data->email, oldEmail) == 0) {        //checking to see if the head needs to be removed
            struct node_struct* temp = (*head_p);                   //set temp variable to store head node for memory clearing
            (*head_p) = (*head_p)->next_node;                       //set head to 2nd node
            free(temp);                                             //free memory storing old head
        }
        else {
            struct node_struct* last_node = (*head_p);              //temp variable for node before the node that must be removed
            struct node_struct* curr_node = (*head_p)->next_node;   //temp variable for the node that must be removed
            while(strcmp(curr_node->data->email, oldEmail) != 0) {  //checks to see if each node in the list is the target node
                last_node = curr_node;                              //these two lines iterate the objective nodes
                curr_node = curr_node->next_node;
            }
            last_node->next_node = curr_node->next_node;            //sets the pointer to curr_node to the next element
            free(curr_node);                                        //frees memory used by the removed node
        }
        
        //This section removes any connections associated with the removed email
        if (*friend_head != NULL) {
            
            struct friendship_struct* curr_connection = (*friend_head);                         //Sets the current connection to the first in the list
            while (((strcmp(curr_connection->friend_A->email, oldEmail)) == 0) ||               //checks if the old email is in the head
                   ((strcmp(curr_connection->friend_B->email, oldEmail)) == 0)) {               //checks if the old email is in the head
                *friend_head = curr_connection->next_connection;                                //moves head, removing 1st connection from the list
                curr_connection = curr_connection->next_connection;                             //iterates curr_connection
            }
            struct friendship_struct* last_connection = curr_connection;                        //sets the 1st node without the email to last_connnection
            curr_connection = curr_connection->next_connection;                                 //sets curr_connection to follow last_connection
            while (curr_connection != NULL) {                                                   //iterates through the rest of the linked list
                if ((strcmp(curr_connection->friend_A->email, oldEmail)) ||                     //checks to see if the old email is in the curr_connection
                    (strcmp(curr_connection->friend_B->email, oldEmail))) {
                    last_connection->next_connection = curr_connection->next_connection;        //changes the last_connection pointer to skip over curr_connection
                    free(curr_connection);                                                      //frees memory in the removed node
                    curr_connection = *friend_head;                         //iterates curr_connection
                }
                else {                                                                          //if curr_connection doesn't need to be removed
                    last_connection = curr_connection;                                          //iterates last_connection
                    curr_connection = curr_connection->next_connection;                         //iterates curr_connection
                }
            }
        }
    }
}

void edit(char* email) {                                        //edits elements of a profile
    if(!email_exists(*head_p, email)){                          //checks to see if email exists
        printf("%s is not in the list\n", email);
    }
    else {
        struct node_struct* curr_node = (*head_p);              //these 3 lines find the node to be edited
        while(strcmp(curr_node->data->email, email) != 0) {
            curr_node = curr_node->next_node;
        }
        
        char menu_choice;
        printf("To edit email, type E\nTo edit first name, type F\nTo edit last name, type L\nTo edit age, type A\nTo edit hometown, type H\nTo edit hobby, type B\n");
        scanf(" %c", &menu_choice);                             //prompts user to choose which element should be edited
        
        if (menu_choice == 'E') {                               //checks menu choice
            printf("Enter a new email address: ");              //prompts user for new value
            char new_email[50];                                 //temp variable to store new value
            scanf("%s", new_email);                             //scans new value
            strcpy(curr_node->data->email, new_email);          //assigns new value to node
        }
        else if (menu_choice == 'F'){                           //the rest of thes if statements do the same thing for each variable
            printf("Enter a new first name: ");
            char new_name[50];
            scanf("%s", new_name);
            strcpy(curr_node->data->first_name, new_name);
        }
        else if (menu_choice == 'L'){
            printf("Enter a new last name: ");
            char new_last[50];
            scanf("%s", new_last);
            strcpy(curr_node->data->last_name, new_last);
        }
        else if (menu_choice == 'A'){
            printf("Enter a new age: ");
            int new_age;
            scanf("%d", &new_age);
            curr_node->data->age = new_age;
        }
        else if (menu_choice == 'H'){
            printf("Enter a new hometown: ");
            char new_home[50];
            scanf("%s", new_home);
            strcpy(curr_node->data->hometown, new_home);
        }
        else if (menu_choice == 'B'){
            printf("Enter a new hobby: ");
            char new_hobby[50];
            scanf("%s", new_hobby);
            strcpy(curr_node->data->hobby, new_hobby);
        }
        else {
            printf("Please select one of the listed options");
            edit(email);
        }
    }
}

_Bool are_friends(char* friend_1, char* friend_2) {             //checks to see if two email addresses refer to nodes that are friends
    struct friendship_struct* curr_connection = (*friend_head); //creates connection to iterate through
    while (curr_connection != NULL) {                           //iterates through connection list
        if (((strcmp(curr_connection->friend_B->email, friend_1) == 0) && (strcmp(curr_connection->friend_A->email, friend_2) == 0)) ||//checks of they're friends
            ((strcmp(curr_connection->friend_B->email, friend_2) == 0) && (strcmp(curr_connection->friend_A->email, friend_1) == 0))) {
            return 1;                                           //returns true
        }
        curr_connection = curr_connection->next_connection;     //updates current connection
    }
    return 0;                                                   //if none of the nodes meet the condition, then returns false
}

void connect_friends(char* friend_1, char* friend_2) {                  //creates a connection between two friends and adds it to the beginning of the list
    if (are_friends(friend_1, friend_2)) {                              //checks to see if connection already exists
        printf("%s and %s are already friends", friend_1, friend_2);
    }
    else {
        struct node_struct* first_node = (*head_p);                     //searches for first node with email friend_1
        while(strcmp(first_node->data->email, friend_1) != 0) {
            first_node = first_node->next_node;
        }
        
        struct node_struct* second_node = (*head_p);                    //searches for second node with email friend_2
        while(strcmp(second_node->data->email, friend_2) != 0) {
            second_node = second_node->next_node;
        }
        
        struct friendship_struct* new_friendship = malloc(sizeof(friendship*));     //allocates memory for new friendship
        new_friendship->friend_A = first_node->data;                                //sets first friend
        new_friendship->friend_B = second_node->data;                               //sets 2nd friend
        new_friendship->next_connection = (*friend_head);                           //sets pointer to old head
        (*friend_head) = new_friendship;                                            //sets head to new connection
    }
}

//MAIN EXECUTION
int main(int argc, const char * argv[]) {
    head_p = malloc(sizeof(node*));             //initializes profile list
    friend_head = malloc(sizeof(friendship*));  //initializes friendship list
    
    //fprintf(stderr, "%p\n", *head_p);
    /*                          //1st round of testing for add and remove
     add("masonemail.com");
     add("masonemail.com");
     removePerson("bekah@hotmail.com");
     add("masonmail.com");
     add("masonmail.com");
     removePerson("masonemail.com");
     add("masonemail.com");
     
     print_profile((*head_p)->data);
     print_profile((*head_p)->next_node->data);
     */
    
    /*                  //2nd round of testing for connect friends
     add("masonemail.com");
     add("bekahemail.com");
     connect_friends("masonemail.com", "bekahemail.com");
     
     print_profile((*friend_head)->friend_B);
     
     print_profile((*head_p)->data);
     */
    
    /*                          //Testing The connect and remove functions fully
     add("mason@email.com");
     add("bekah@email.com");
     add("parisa@email.com");
     connect_friends("mason@email.com", "bekah@email.com");
     connect_friends("mason@email.com", "parisa@email.com");
     connect_friends("bekah@email.com", "parisa@email.com");
     removePerson("mason@email.com");
     struct friendship_struct* curr_ship = *friend_head;
     while (curr_ship != NULL) {
     print_profile(curr_ship->friend_A);
     print_profile(curr_ship->friend_B);
     curr_ship = curr_ship->next_connection;
     }
     */
    
    //to test this code, simply call add, removePerson, edit, and connect_friends inside of main.
    //to check if functions are behaving as expected, you can pass print_profile a pointer to a profile and it will print the expected information
    
    return 0;
}

