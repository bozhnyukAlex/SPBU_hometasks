#include <stdio.h>

int bitAnd();
int bitOr();
int bitXor();
int thirdBits();
int fitsBits();


int main(){
    int option = -1;
    printf("Hello, User! Select option. Press number from 1 to 12\n");
    printf(" 1 - bitAnd\n 2 - bitXor\n 3 - thirdBits\n 4 - fitsBits\n 5 - sign\n 6 - getByte\n 7 - logicalShift\n 8 - addOk\n 9 - bang\n 10 - conditional\n 11 - isPower2\n 12 - Exit Program");
    while(1){
        printf("\nYour option: ");
        scanf("%d", &option);
        switch(option){
            case 1:{
                printf("Input two numbers: ");
                int parA, parB, res;
                scanf("%i %i", &parA, &parB);
                res = bitAnd(parA, parB);
                printf("Result: %i \n", res);
                break;
            }
            case 2:{
                printf("Input two numbers: ");
                int parA, parB, res;
                scanf("%i %i", &parA, &parB);
                res = bitXor(parA, parB);
                printf("Result: %i \n", res);
                break;
            }
            case 3:{
                int res = thirdBits();
                printf("Result: %i \n", res);
                break;
            }
            case 4:{
                int num, btCount;
                printf("Input number and bits count: ");
                scanf("%i %i", &num, &btCount);
                int res = fitsBits(num, btCount);
                printf("Result: %i \n", res);
                break;
            }
            case 5:{
                break;
            }
            case 6:{
                break;
            }
            case 7:{
                break;
            }
            case 8:{
                break;
            }
            case 9:{
                break;
            }
            case 10:{
                break;
            }
            case 11:{
                break;
            }
            case 12:{
                return 0;
                break;
            }


        }
    }

    return 0;
}

int bitAnd(int parA, int parB){
    return ~(~parA | ~parB);
}
int bitOr(int parA, int parB){
    return ~(~parA & ~parB);
}
int bitXor(int parA, int parB){
    return bitOr(parA & ~parB, ~parA & parB);
}
int thirdBits(){
    int res = 36, step = 6, first = 36;
    int step2 = step + step; //I can't use * operator;
    int step4 = step2 + step2;
    res |= (res << step);
    res |= (res << step2);
    res |= (first << step4);
    return res;
}
int fitsBits(int num, int btCount){
    int shift = 32 + (~btCount + 1);
    int newNum = num << shift;
    newNum >>= shift;
    return !(num ^ newNum);
}
