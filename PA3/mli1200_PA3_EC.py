# -*- coding: utf-8 -*-
"""
recursively finds the deepth of braces
    perhaps overengineered:
        accounts for:
            before a quotation
            after quotation
            before a quotation before comment
            after quotation before comment
            before comment
            curly braces pair on the same line {}
            

s is the string
braceCounter is braceCounter from main
start is where the search begins
end is only used when there is a comment in the line
    or if there is quotation mark to account for
"""
def braceChecker(s, braceCounter, start=0, end = -1):
    """
    if it does not have a comment then it checks for quotations
            if it does not have quotations then it just looks for any braces without worry
            if it does have quotations it only looks for braces outside of the quotations
        if it does have comment then it checks for quotations
            if it does not have quotations then it looks for any braces before the comment
            if it does have quotations then it looks for any brace before the comment and not inside the quotations
    """  
    if(s.find('{',start) == -1 and s.find('}', start)== -1):
        return braceCounter
    p = 0
    m = 0
    if(end == -1): #no comment
        i = s.find('\"', start)
        #checks for beginning quotation
        if(i != -1):# their is beginning quotation
            #finds braces before first quotation
            m = s.find('{', start, i)
            if(m != -1):
                return 1 + braceChecker(s, braceCounter, m+1, i)
                
            m = s.find('}', start, i) 
            if(m != -1):
                return -1 + braceChecker(s, braceCounter, m+1, i)
                
            p = s.find('\"', i+1)
            #find  braces after second quotation
            m = s.find('{', p)
            if(m != -1):
                return 1 + braceChecker(s, braceCounter, m+1, end)
                
            m = s.find('}', p) 
            if(m != -1):
                return -1 + braceChecker(s, braceCounter, m+1, end)
        else:#no quotation
            m = s.find('{', start, end)
            if(m != -1):
                return 1 + braceChecker(s, braceCounter, m+1, end)
                
            m = s.find('}', start, end)
            if(m != -1):
                return -1 + braceChecker(s, braceCounter, m+1, end)
    else:
        i = s.find('\"', start, end)
        #checks if a quotation occurs not inside the comment
        if(i != -1):
            #checks for any braces before first quotation, first quotation already accounts for comment.
            m = s.find('{', start, i) 
            if (m != -1):
                return 1 + braceChecker(s, braceCounter, m+1, i)
            m = s.find('}', start, i)
            if(m != -1):
                return -1 + braceChecker(s, braceCounter, m+1, i)
                
            p = s.find('\"', i+1, end) 
            #checks for any braces after second quotation but before the comment.
            m = s.find('{', p, end) 
            if(m != -1):
                return 1 + braceChecker(s, braceCounter, m+1, end)
            m = s.find('}', p, end) 
            if(m != -1):
                return -1 + braceChecker(s, braceCounter, m+1, end)
        else:
            #if no quotations then just make sure the braces are before the comment.
            m = s.find('{', start, end)
            if(m != -1):
                return 1 + braceChecker(s, braceCounter, m+1, end)
            m = s.find('}', start, end)
            if(m != -1):
                return -1 + braceChecker(s, braceCounter, m+1, end)
             
    return braceCounter

#returns the braceCounter and the multiline flag state.
def siftLine(braceCounter, s, flag):
    """
    Begins by checking for multiline comments
    if /* is found then sets flag to 1
    if */ is found then sets flag to 0
    if flag is equal to 1 then return without doing anything except maybe the change to flag.
    """
    if(s.find('/*') != -1):
        flag = 1
    if(s.find('*/') != -1):
        flag = 0;
        
    if(flag == 1):
        return braceCounter, flag
    """
    first if statements check to see if the line has a single line comment
    """
    c = s.find('//')
    #checks for single line comments
    if(c == -1):
        braceCounter = braceChecker(s,braceCounter, 0, c)
    else: # if a comment does occur
        braceCounter = braceChecker(s, braceCounter, 0, c)
    return braceCounter, flag

def main():
    
    #try to open the file, currently only set to input_EC.txt
    try:
        f = open('input_EC.txt','r')
    except IOError:
        print("Could not open file!")
        return        
    with f:
        #flag is used to skip parts of code that occur in a multiline comment
        flag = 0
        #brace counter
        braceCounter = 0
        #s is a list of strings of the extracted file
        s = f.readlines()
        """
        checks each element of s for braces
            then prints the brace counter separately since python doesn't like multityped print statements
            then prints the actual line from the file
                line from file already includes a newline so set end to nothing.
        """
        for line in s:
            braceCounter, flag = siftLine(braceCounter, line, flag)
            print(braceCounter, end = " ")
            print(line, end = "")
            
        print()
        if(braceCounter != 0):
            print("Braces do not match up!")
    f.close()
    
    
if __name__ == '__main__':
    main()