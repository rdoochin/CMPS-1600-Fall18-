//
//  main.c
//  Lab 6: guessing game
//
//  Created by Rebekah on 11/4/18.
//  Copyright © 2018 Rebekah. All rights reserved.
//

#include <stdio.h>
#include "GuessingGameHeader.h"
#include <stdlib.h>         //defines rand() and srand()
#include <time.h>           //for get_random


/*
 Function to make sure that a number is within
 a specified range.
 */
int ask_in_range(int min, int max){
    
    int x;
    printf("%s", "Please enter a number: ");
    
    scanf("%d", &x);
    if (x >= min && x <= max){
        return x;
    }
    else{
        printf("%s", "Your number is outside of [");
        printf("%d", min);
        printf("%s", ",");
        printf("%d", max);
        printf("%s", "] range.");
        
        ask_in_range(min, max);
    }
    
    return x;
}

/*
 Function to get a random integer.
 */
int get_random(int rangemin, int rangemax){
    int random_num = 0;
    //srand(time(NULL));      //seeding with current time.
    srand( (unsigned int) time(NULL) );
    random_num = rand()%rangemax;
    if(random_num < rangemin){
        random_num += rangemin;
    }
    return random_num;
}

/*
 A function to play a guessing game!
 */
int guessing_game(int rangemin, int rangemax){
    char user_name = '\0';
    int count = 0;
    int askAnswer;
    printf("%s", "Hello and welcome to the game.");
    printf("\n");
    printf("%s", "Please enter your name: ");
    scanf("%s", user_name);
    printf("%s", "You need to guess a number between ");
    printf("%d", rangemin);
    printf("%s"," and ");
    printf("%d", rangemax);
    printf("%s",".");
    
    int num = get_random(rangemin, rangemax);
    
    askAnswer = ask_in_range(rangemin, rangemax);
    
    while(askAnswer != num){
    if(askAnswer > num){
        count += 1;
        printf("%s", "Too high! ");
        askAnswer = ask_in_range(rangemin, rangemax);
    }
    if(askAnswer < num){
        count += 1;
        printf("%s", "Too low! ");
        askAnswer = ask_in_range(rangemin, rangemax);
    }
    }
    if(askAnswer == num){
        count += 1;
        printf("%s", "Good job! You took ");
        printf("%d", count);
        printf("%s", " guesses. \n");
    }
    
    // create a FILE typed pointer
    FILE *file_pointer;
    
    // open the file for writing                                //idk how to do this part :(
    file_pointer = fopen("high_scores.txt", "w");
    
    // Writes to the file
    fprintf(file_pointer, "%c", user_name);
    fprintf(file_pointer, "%d", count);
    
    //scanning for a high score
    
    
    // Close the file
    fclose(file_pointer);
    
    return 0;
    
}

int main(int argc, const char * argv[]) {
    guessing_game( -100, 200);
    return 0;
}
