"""
Matthew Irvine
1001401200
4/23/2020
Windows 10
"""
import os

def RPN(str):
    #declare some variables for later use
    stack = []
    space = 0
    i = 0
    
    #originally used for statement, moved to while because for statement would reset 'i' each iteration
    #while statement gave me more freedom as to what 'i' is
    while i in range(0, len(str)):
        #if position is whitespace skip it
        if str[i].isspace():
            #if whitespace move to next position
            i = i + 1       
            continue
        
        #otherwise
        #find the next space
        space = str.find(" ", i)
        
        #if no more spaces check for newline
        if space == -1:   
            space = str.find("\n",i)
            #if no newline at the end of the line, set space equal to the length of the string
            if space == -1:
                space = len(str)
            
        #get the full number from i to the next space
        substring = str[i:space]
        
        #if the substring is a string of digits
        if substring.isdigit():  
            #push the number unto the stack while converting to int
            stack.append(int(substring))
        else:                           
            #if not whitespace or number then it must be operator
            #so we will pop two items for the operands
            op1 = stack.pop()
            op2 = stack.pop()
            operator = substring    #just for clarity
            result = None
            
            #do the operation
            if operator == '+':
                result = op1 + op2
            elif operator == '-':
                result = op1 - op2
            elif operator == '*':
                result = op1 * op2
            elif operator == '/':
                result = op1/op2
                
            #push result onto stack for later operation
            stack.append(result)
        
        #skip the portion that was in substring, we already evaluated it
        i = space  
    #stack should only hold 1 value, the result
    return stack.pop()

def main():
    
    print("Hello GTA I just want to let you know I am using python 3.7.3.")
    print("Reason being is the python IDE (spyder through anaconda) I used has it set to this version and I don't know how to change it.")
    print("back to the program...")
    print()
    
    #get the absolute path of the file
    path = os.path.abspath('input_RPN.txt')
    try:
        #open the file using absolute path
        f = open(path,'r')
    except IOError:
        #check for error
        print("Could not open file!")
        return        
    with f:
        #s is the list of Reverse Polish Notation strings
        s = f.readlines()
        #line is a single entry in s
        for line in s:
            #result is the output of the RPN
            result = RPN(line)
            #print it out
            print("result of ( ", end = ' ')
            print(line.rstrip(), end = ' ) is: ')
            print(result)
    f.close()
    
if __name__ == '__main__':
    main()