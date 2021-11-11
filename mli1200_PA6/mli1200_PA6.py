"""
Created on Fri Apr 24 11:34:48 2020
Matthew Irvine
1001401200
4/24/2020
Windows 10
"""
import os
    
def getFullSize(path):
    #variables used to determine if the path is a file or a directory
    isFile = os.path.isfile(path)
    isDir = os.path.isdir(path)
    
    #if it is a file
    if isFile:
        #calculate file size
        tmpsize = os.path.getsize(path)
        #printout the file and its corrosponding size
        print(path, end = ':\t')
        print(tmpsize)
        #return the size of the file
        return tmpsize
    elif isDir:     #otherwise if it is a directory
        #list all of the file and subdirectories
        listd = os.listdir(path)
        #used to keep track of the size of the entire directory
        fullSize = 0
        for l in listd:
            #new absolute path for the entry
            newpath = path + '\\' + l
            #add the size of subdirectory or file of each entry
            fullSize += getFullSize(newpath)
            
    return fullSize

def main():
    #get absolute path to current working directory
    size = getFullSize(os.getcwd())
    print("The full size of " + os.getcwd() + " is:")
    print(size, end = ' ')
    print("bytes")
    
if __name__ == '__main__':
    main()
