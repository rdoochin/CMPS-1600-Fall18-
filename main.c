//
//  project1.c
//  Project 1
//
//  Created by Rebekah Doochin & Mason O'Connor
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

//NODE STRUCTURES AND FUNCTIONS
typedef struct node_struct {
    struct profile_struct* data;    //stores a pointer to a profile
    struct node_struct* next_node;  //stores the pointer to the next node
} node;

struct node_struct** head_p = NULL;            //global head for list of profiles
struct friendship_struct** friend_head = NULL; //global head for list of friendships


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
        scanf(" %[^\n]s", newProf -> first_name);
        printf("Please enter a last name: ");
        scanf(" %[^\n]s", newProf -> last_name);
        printf("Please enter an age: ");
        scanf("%d", &newProf -> age);
        printf("Please enter a hometown: ");
        scanf(" %[^\n]s", newProf -> hometown);
        printf("Please enter a hobby: ");
        scanf(" %[^\n]s", newProf -> hobby);
        
        printf("New profile added. Here is their information:\n");
        print_profile(newProf);
        push(newProf, sizeof(profile));
    }
}

void remove_friend(char* oldEmail) {                                 //removes a node from the profile list given the identifying email
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
                free(curr_connection);
                curr_connection = *friend_head;                                                 //iterates curr_connection
            }
            struct friendship_struct* last_connection = curr_connection;                        //sets the 1st node without the email to last_connnection
            curr_connection = curr_connection->next_connection;                                 //sets curr_connection to follow last_connection
            while (curr_connection != NULL) {                                                   //iterates through the rest of the linked list
                if ((strcmp(curr_connection->friend_A->email, oldEmail)) ||                     //checks to see if the old email is in the curr_connection
                    (strcmp(curr_connection->friend_B->email, oldEmail))) {
                    last_connection->next_connection = curr_connection->next_connection;        //changes the last_connection pointer to skip over curr_connection
                    free(curr_connection);                                                      //frees memory in the removed node
                    curr_connection = last_connection->next_connection;                         //iterates curr_connection
                }
                else {                                                                          //if curr_connection doesn't need to be removed
                    last_connection = curr_connection;                                          //iterates last_connection
                    curr_connection = curr_connection->next_connection;                         //iterates curr_connection
                }
            }
        }
        printf("%s was successfully removed.\n", oldEmail);
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
        
        printf("Here is their information:\n");
        print_profile(curr_node->data);
        
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
            scanf(" %[^\n]s", new_name);
            strcpy(curr_node->data->first_name, new_name);
        }
        else if (menu_choice == 'L'){
            printf("Enter a new last name: ");
            char new_last[50];
            scanf(" %[^\n]s", new_last);
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
            scanf(" %[^\n]s", new_home);
            strcpy(curr_node->data->hometown, new_home);
        }
        else if (menu_choice == 'B'){
            printf("Enter a new hobby: ");
            char new_hobby[50];
            scanf(" %[^\n]s", new_hobby);
            strcpy(curr_node->data->hobby, new_hobby);
        }
        else {
            printf("Please select one of the listed options\n");
            edit(email);
        }
    }
}

_Bool are_friends(char* friend_1, char* friend_2) {             //checks to see if two email addresses refer to nodes that are friends
    struct friendship_struct* curr_connection = (*friend_head); //creates connection to iterate through
    while ((curr_connection != NULL)) {                         //iterates through connection list
        if (((strcmp(curr_connection->friend_B->email, friend_1) == 0) && (strcmp(curr_connection->friend_A->email, friend_2) == 0)) ||//checks of they're friends
            ((strcmp(curr_connection->friend_B->email, friend_2) == 0) && (strcmp(curr_connection->friend_A->email, friend_1) == 0))) {
            return 1;                                           //returns true
        }
        curr_connection = curr_connection->next_connection;     //updates current connection
    }
    return 0;                                                   //if none of the nodes meet the condition, then returns false
}

void connect_friend(char* friend_1, char* friend_2) {                   //creates a connection between two friends and adds it to the beginning of the list
    if (strcmp(friend_2, friend_1) == 0) {                              // checks to make sure two separate emails were added
        printf("%s cannot be friends with themself.\n", friend_2);
    }
    else if (are_friends(friend_1, friend_2)) {                         //checks to see if connection already exists
        printf("%s and %s are already friends.\n", friend_1, friend_2);
    }
    else if (!email_exists(*head_p, friend_1)) {                        //checks to verify the emails exist
        printf("%s does not exist.\n", friend_1);
    }
    else if (!email_exists(*head_p, friend_2)) {
        printf("%s does not exist.\n", friend_2);
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
        printf("%s and %s are now Friends.\n", new_friendship->friend_A->email, new_friendship->friend_B->email);
    }
}

//Part 2 funcitons

void disconnect(char* friend_1, char* friend_2) {
    struct friendship_struct* curr_connection = *friend_head;
    if (curr_connection == NULL) {
        printf("No connections exist.\n");
        return;
    }
    if (((strcmp(curr_connection->friend_A->email, friend_1) == 0) && (strcmp(curr_connection->friend_B->email, friend_2) == 0)) ||
        ((strcmp(curr_connection->friend_A->email, friend_2) == 0) && (strcmp(curr_connection->friend_B->email, friend_1) == 0))) {
        *friend_head = (*friend_head)->next_connection;
        free(curr_connection);
        return;
    }
    if (curr_connection->next_connection == NULL) {
        printf("%s and %s were not connected.\n", friend_1, friend_1);
        return;
    }
    struct friendship_struct* last_connection = curr_connection;
    curr_connection = curr_connection->next_connection;
    while(curr_connection != NULL) {
        if (((strcmp(curr_connection->friend_A->email, friend_1) == 0) && (strcmp(curr_connection->friend_A->email, friend_2) == 0)) ||
            ((strcmp(curr_connection->friend_A->email, friend_2) == 0) && (strcmp(curr_connection->friend_A->email, friend_1) == 0))) {
            last_connection->next_connection = curr_connection->next_connection;
            free(curr_connection);
            printf("%s and %s are no longer friends", friend_1, friend_2);
            return;
        }
        else {
            last_connection = curr_connection;
            curr_connection = curr_connection->next_connection;
        }
    }
    printf("%s and %s were not connected.\n", friend_1, friend_1);
}

/*
 Displays all the friends of a person whose         *******test getFriends and display tomorrow, too sleepy********
 email you enter.
 */
void getFriends(char* email){
    if(!email_exists(*head_p, email)) {
        printf("%s is not valid.\n", email);
    }
    else {
        struct friendship_struct* curr_connect = (*friend_head);
        while (curr_connect != NULL) {
            
            if(strcmp(curr_connect ->friend_A -> email, email) == 0){                   //checks to see if the email is contained in the friendship node.
                printf("%s\n", curr_connect -> friend_B -> first_name);
            }
            if(strcmp(curr_connect ->friend_B -> email, email) == 0){                   //checks to see if the email is contained in the friendship node.
                printf("%s\n", curr_connect -> friend_A -> first_name);
            }
            curr_connect = curr_connect -> next_connection;
        }
    }
}

void display(){
    struct node_struct* curr_person = (*head_p);
    while(curr_person != NULL){
        printf("%s: {\n", curr_person -> data -> first_name);
        getFriends(curr_person ->data ->email);
        printf("}\n");
        
        curr_person = curr_person -> next_node;
    }
}

void saveNetwork(char* peopleFilename, char* connectionsFilename) {             //given names for the files, this function will save the networks
    
    FILE* proFile;                                                              //creates a file for the profiles
    proFile = fopen(peopleFilename, "w");                                       //names the file and opens it for writing
    
    struct node_struct* curr_node = (*head_p);                                  //temporary value equal to head node
    while (curr_node != NULL) {                                                 //iterates through profile list
        fprintf(proFile, "%s, %s, %s, %d, %s, %s\n", curr_node->data->email, curr_node->data->first_name, curr_node->data->last_name, curr_node->data->age, curr_node->data->hometown, curr_node->data->hobby);                 //prints each element of the profile separated by commas
        curr_node = curr_node->next_node;                                       //iterates curr_node
    }
    
    fclose(proFile);                                                            //closes file
    printf("Profile File successfully saved\n");
    
    FILE* connectionFile;                                                       //creates a file for the connections
    connectionFile = fopen(connectionsFilename, "w");                           //names file and opens for reading
    
    struct friendship_struct* curr_connection = *friend_head;                   //temporary variable equal to head connection
    while (curr_connection != NULL) {                                           //iterates through connection list
        fprintf(connectionFile, "%s, %s\n", curr_connection->friend_A->email, curr_connection->friend_B->email);      //saves both emails to a line
        curr_connection = curr_connection->next_connection;                     //iterates curr_connection
    }
    
    fclose(connectionFile);                                                     //closes connection file
    printf("Connections sucessfully saved\n");
}

void retrieveNetwork(char* peopleFilename, char* connectionsFilename){
    
    if(access(peopleFilename, F_OK)==-1){                               //checks to see that the file exists
        printf("File does not exist\n");
        return;
    }
    if(access(connectionsFilename, F_OK)==-1){                         //checks to see that the file exists
        printf("File does not exist\n");
        return;
    }
    
    //RETRIEVING INPUT FROM PEOPLE FILE
    FILE* proFile;                                                      //file to open
    
    //TO FIX: add functionality to check if file exists before attempting to read
    
    proFile = fopen(peopleFilename, "r");                               //opens file for reading
    int items_scanned = 0;                                              //instantiates variable to check for sucessful scanning
    do {
        struct profile_struct* new_people = malloc(sizeof(profile));    //instantiates profile to add to linked list
        char new_string[500];                                           //declares variable to scan line of file into
        char new_email[50];                                             //temp variables to write to new profile
        char new_first[50];
        char new_last[50];
        char new_age_string[50];
        char new_home[50];
        char new_hobby[50];
        items_scanned = fscanf(proFile, " %[^\n]s" , new_string);       //scans in a line of the file. Items_scanned = 1 if the scan was successful
        
        //printf("items_scanned = %d\n", items_scanned);                //these lines are used to test value of items_scanned and new_string
        //printf("new_string = %s\n", new_string);
        
        //This while loop assigns all of the variables for the profile with their approriate values in new_string
        int current_element = 1;                                        //instantiates variable that counts commas
        int i = 0;                                                      //instantiates variable to traverse new_string
        int curr_index = 0;                                             //instantiates variable to write to profile elements
        while (new_string[i] != '\0') {                                 //loop iterates through new_string
            if (new_string[i] == ',') {                                 //checks to see if current char is a comma
                current_element++;                                      //iterates current_element value (to indicate that the previous element is complete)
                i++;                                                    //iterates i an extra time to avoid writing space
                curr_index = -1;                                        //resets curr_index so it will be 0 at the start of next loop
            }
            else if (current_element == 1) {                            //writes to new_email
                new_email[curr_index] = new_string[i];                  //assigns curr_index element of new_email with ith value of string
                new_email[curr_index + 1] = '\0';                       //sets next value of new_email to null, ensures that no extra characters will be included
            }
            else if (current_element == 2) {                            //all of these else if statements act the same as the previous one for each element
                new_first[curr_index] = new_string[i];
                new_first[curr_index + 1] = '\0';
            }
            else if (current_element == 3) {
                new_last[curr_index] = new_string[i];
                new_last[curr_index + 1] = '\0';
            }
            else if (current_element == 4) {
                new_age_string[curr_index] = new_string[i];
                new_age_string[curr_index + 1] = '\0';
            }
            else if (current_element == 5) {
                new_home[curr_index] = new_string[i];
                new_home[curr_index + 1] = '\0';
            }
            else if (current_element == 6) {
                new_hobby[curr_index] = new_string[i];
                new_hobby[curr_index + 1] = '\0';
            }
            i++;                                                        //iterates i
            curr_index++;                                               //iterates curr_index
        }
        //printf("New items: %s, %s, %s, %s, %s, %s\n", new_email, new_first, new_last, new_age_string, new_home, new_hobby); prints values to check for correctness
        strcpy(new_people->email, new_email);                           //assigns profiles with all the newly obtained values
        strcpy(new_people->first_name, new_first);
        strcpy(new_people->last_name, new_last);
        new_people->age = atoi(new_age_string);                         //this converts new_age_string to an int before assignment
        strcpy(new_people->hometown, new_home);
        strcpy(new_people->hobby, new_hobby);
        
        if (items_scanned == 1) {                                       //checks if the scan was successful
            push(new_people, sizeof(profile));                          //adds new_people to the list of profiles
        }
        
    } while (items_scanned == 1);                                       //loop ends when scan is unsuccessful, meaning file is empty
    
    fclose(proFile);                                                    //closes file
    
    FILE* connectFile;
    connectFile = fopen(connectionsFilename, "r");
    int scanItem = 0;
    
    do {
        char people[150];
        char* friend1;
        char *friend2;
        
        scanItem = fscanf(connectFile, " %[^\n]s", people);
        
        friend1 = strtok(people,", ");
        friend2 = strtok(NULL, ", ");
        
        if(scanItem == 1){
            connect_friend(friend1, friend2);
        }
        
        
    }while (scanItem == 1);
    
}






//MAIN EXECUTION
int main(int argc, const char * argv[]) {
    head_p = malloc(sizeof(node*));             //initializes profile list
    friend_head = malloc(sizeof(friendship*));  //initializes friendship list
    if (head_p == NULL) {
        printf("head_p is NULL");
    }
    if (friend_head == NULL) {
        printf("friend_head is NULL");
    }
    
    char menu_selection = 'B';              //user enters char to choose action
    
    while(menu_selection != 'Q') {          //while loop executes until user chooses to quit
        //Menu Text
        printf("Please make your selection then hit enter:\n");
        printf(" -Press A to add a new profile\n -Press R to remove a profile\n -Press E to edit an existing profile\n");
        printf(" -Press C to connect two profiles\n -Press D to display all users and their friends\n -Press U to unfriend 2 profiles\n");
        printf(" -Press G to see one person's friends\n -Press S to save the profiles to a text file\n -Press L to load profiles from a text file\n");
        printf(" -Press Q to quit\n");
        
        scanf(" %c", &menu_selection);      //asks user for menu seleciton
        
        //If statements
        if (menu_selection == 'A') {        //calls add
            char new_email[50];
            printf("Please enter an email address for the new profile: ");
            scanf("%s", new_email);
            add(new_email);
        }
        else if (menu_selection == 'R') {   //calls remove_friend
            char old_email[50];
            printf("Please enter the email address of the profile you'd like to delete: ");
            scanf("%s", old_email);
            remove_friend(old_email);
        }
        else if (menu_selection == 'E') {   //calls edit
            char edit_email[50];
            printf("Please edit the email address of the profile you'd like to edit: ");
            scanf("%s", edit_email);
            edit(edit_email);
        }
        else if (menu_selection == 'C') {   //calls connect_friend
            char first_email[50];
            char second_email[50];
            printf("Please enter the first email you'd like to connect: ");
            scanf("%s", first_email);
            printf("Please enter the second email you'd like to connect: ");
            scanf("%s", second_email);
            connect_friend(first_email, second_email);
        }
        else if (menu_selection == 'D') {   //calls display
            display();
        }
        else if (menu_selection == 'U') {   //calls disconnect
            char first_email[50];
            char second_email[50];
            printf("Please enter the first email you'd like to disconnect: ");
            scanf("%s", first_email);
            printf("Please enter the second email you'd like to disconnect: ");
            scanf("%s", second_email);
            disconnect(first_email, second_email);
        }
        else if (menu_selection == 'G') {   //calls get_friends
            char check_email[50];
            printf("Please enter the email of the person whose friends you'd like to see: ");
            scanf("%s", check_email);
            getFriends(check_email);
        }
        else if (menu_selection == 'S') {   //calls saveNetwork
            char proFile_name[50];
            char network_name[50];
            printf("Please enter the name of the file you'd like to store the profile information in (it should end in \".txt\" and have no spaces):");
            scanf("%s", proFile_name);
            printf("Please enter the name of the file you'd like to store the friendship information in (it should end in \".txt\" and have no spaces):");
            scanf("%s", network_name);
            saveNetwork(proFile_name, network_name);
        }
        else if (menu_selection == 'L') {   //calls saveNetwork
            char proFile_name[50];
            char network_name[50];
            printf("Please enter the name of the file you'd like to retrieve the profile information from:");
            scanf("%s", proFile_name);
            printf("Please enter the name of the file you'd like to retrieve the friendship information from:");
            scanf("%s", network_name);
            retrieveNetwork(proFile_name, network_name);
        }
    }
    
    return 0;
}
