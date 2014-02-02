#3.	Implement the component labeling procedure for a binary image.


from copy import deepcopy
import re
import sys

data_matrix=[]
label_matrix=[]
equivalency_sets=[]
region_id=2
rows=0
columns=0
connectedness=0

def printLabelMatrix():
    print "=========================="
    print"LABEL MATRIX"
    for r in range(rows):
        for c in range(columns):
            int_label=label_matrix[r][c]
            char_label=chr(int_label+96)
            sys.stdout.write(char_label)
        sys.stdout.write("\n")

def firstPass():
    for r in range (rows):
        for c in range (columns):
            cell = data_matrix[r][c]
            if (cell == 1):
                checkSurroundingCells(r, c)
    print "after first pass:"
    printLabelMatrix()

def checkSurroundingCells(row, column):
    match = False
    if (column>0):
        match = checkWest(row, column)
    if (column>0 and row>0):
        match=compareNorthAndWest(row, column)
    if  ( row>0):
        match=checkNorth(row, column)
    if ( row>0 and column > 0):
        match = compareNorthAndWestWithSelf(row, column)
    if (connectedness == 8):
        check_diagonals(row, column)
    if(match==False):
        new_label = getNewLabel()
        label_matrix[row][column]=new_label
        
'''
Connectivity checks are carried out by checking the labels of pixels that are North-East, North, North-West and West of the current pixel (assuming 8-connectivity). 4-connectivity uses only North and West neighbors of the current pixel. The following conditions are checked to determine the value of the label to be assigned to the current pixel (4-connectivity is assumed)
'''
def check_northwest(row, column):
    northwest= data_matrix[row-1][column-1]
    if (northwest==1):
        if (label_matrix[row][column] == 0):
            label_matrix[row][column]=label_matrix[row-1][column-1]
        if (label_matrix[row-1][column-1] != label_matrix[row][column]):
            storeEquivalenceRelationship(label_matrix[row-1][column-1],
                                         label_matrix[row][column])

def check_northeast(row, column):
    northeast= data_matrix[row-1][column+1]
    if (northeast==1):
        if (label_matrix[row][column] == 0):
            label_matrix[row][column]=label_matrix[row-1][column+1]
        if (label_matrix[row-1][column+1] != label_matrix[row][column]):
            storeEquivalenceRelationship(label_matrix[row-1][column+1],
                                         label_matrix[row][column])

def check_diagonals(row, column):
    if (row >0):
        if (column<columns-1):
            check_northeast(row, column)
        if (column>0):
            check_northwest(row, column)

def getNewLabel():
    global region_id
    region_id += 1
    return region_id

'''
Once the initial labeling and equivalence recording is completed, the second pass merely replaces each pixel label with its equivalent disjoint-set representative element.
'''

def secondPass():
    for r in range (rows):
        for c in range (columns):
            cell = data_matrix[r][c]
            if (cell == 1):
                replaceLabelWithLowestEquivalence(r, c)
    print "After second pass:"
    printLabelMatrix()


def  replaceLabelWithLowestEquivalence(row, column):
    label = label_matrix[row][column]
    for zet in (equivalency_sets):
        if (label in zet):
            label = min(zet)
            label_matrix[row][column] = label


'''
    Does the pixel to the left (West) have the same value?
        Yes - We are in the same region. 
        Assign the same label to the current pixel
        No - Check next condition
'''
def checkWest(row, column):
    western = data_matrix[row][column-1]
    if western==1:
        label_matrix[row][column]=label_matrix[row][column-1]
        return True
    return False
        
    


'''
    Do the pixels to the North and West of the current pixel have the
     same value but not the same label?
       
        Yes - We know that the North and West pixels belong 
        to the same region 
        and must be merged. 
        Assign the current pixel the minimum of the 
        North and West labels, and record their equivalence relationship
        No - Check next condition
'''

def storeEquivalenceRelationship(label_1, label_2):
    minimum = min(label_1, label_2)
    maximum = max(label_1, label_2)
    added=False
    for zet in (equivalency_sets):
        if (minimum in zet):
            zet.add(maximum)
            added=True
    if (added==False):
        for zet in (equivalency_sets):
            if (maximum in zet):
                zet.add(minimum)
                added=True
    if (added==False):
        zet = set([minimum, maximum])
        equivalency_sets.append(zet)
        
    '''
    if (label_1 not in equivalencies):
        equivalencies[label_1]=[]
    if (label_2 not in equivalencies):
        equivalencies[label_2]=[]
        
    label_1_equivalencies=equivalencies[label_1]
    label_2_equivalencies=equivalencies[label_2]
    if (label_2 not in (label_1_equivalencies)):
        label_1_equivalencies.append(label_2)
    if (label_1 not in (label_1_equivalencies)):
        label_1_equivalencies.append(label_1)   
    for val in (label_2_equivalencies):
        if (val not in label_1_equivalencies):
            label_1_equivalencies.append(val)  
    equivalencies[label_2]=equivalencies[label_1]
 '''
def compareNorthAndWest(row, column):
    western = data_matrix[row][column-1]
    northern = data_matrix[row-1][column]
    if ((western==1 and northern==1)):
        if (label_matrix[row][column-1] != label_matrix[row-1][column]):
            label_matrix[row][column] = min(label_matrix[row][column-1],
                                      label_matrix[row-1][column])
            storeEquivalenceRelationship(label_matrix[row][column-1],
                                      label_matrix[row-1][column])
        else:
            label_matrix[row][column] = label_matrix[row][column-1]
        return True
    return False
                                    


'''
    Does the pixel to the left (West) have a different value and 
     the one to the North the same value?
        Yes - Assign the label of the North pixel to the current pixel
        No - Check next condition
        
        NOTE:  we don't really need to compare West with North
            because we are operating with binary values
'''
def checkNorth(row,column):
    northern = data_matrix[row-1][column]
    if (northern == 1):
        label_matrix[row][column]=label_matrix[row-1][column]
        return True
    return False


'''

    Do the pixel's North and West neighbors have different pixel values?
        Yes - Create a new label id and assign it to the current pixel
'''

def compareNorthAndWestWithSelf(row,column):
    western = data_matrix[row][column-1]
    northern = data_matrix[row-1][column]
    if (data_matrix[row][column] != western and 
        data_matrix[row][column] != northern):
        label_matrix[row][column] = getNewLabel()
        return True


'''
0000000000000
0000000001110
0001110000110
0001110000010
0000100000010
0111111100010
0101110100010
0101110100010
0001110000010
0001010000010
0001010000010
0001010000010
0001010000010

'''
def testConfigurationTwo():
    global data_matrix
    global label_matrix
    global rows
    global columns
    global region_id
    region_id -= 14

    rows = 13 
    columns = 13
    data_matrix=[]
    label_matrix=[]
    equivalency_sets=[]
    line=[]
    line.append('0000000000000')
    line.append('0000000001110')
    line.append('0001110000110')
    line.append('0001110000010')
    line.append('0000100000010')
    line.append('0111111100010')
    line.append('0101110100010')
    line.append('0101110100010')
    line.append('0001110000010')
    line.append('0001010000010')
    line.append('0001010000010')
    line.append('0001010000010')
    line.append('0001010000010')

    for r in range (rows):
        data_matrix.append([])
        input_line=line[r]
        for c in range(columns):
            cell = int(input_line[c])
            data_matrix[r].append(cell)
    label_matrix = deepcopy(data_matrix)
    firstPass()
    secondPass()
    
'''
00000000000000000
00110011001100110
01111111100111100
00011110001111000
00111100011100110
01110011000111000
00110000011000110
00000011110011110
00000000000000000
'''
def testConfigurationOne():
    global data_matrix
    global label_matrix
    global rows
    global columns
    global region_id
    region_id-=2
    rows = 9 
    columns = 17
    data_matrix=[]
    label_matrix=[]
    equivalency_sets=[]
    line=[]
    line.append('00000000000000000')
    line.append('00110011001100110')
    line.append('01111111100111100')
    line.append('00011110001111000')
    line.append('00111100011100110')
    line.append('01110011000111000')
    line.append('00110000011000110')
    line.append('00000011110011110')
    line.append('00000000000000000')
    for r in range (9):
        data_matrix.append([])
        input_line=line[r]
        for c in range(17):
            cell = int(input_line[c])
            data_matrix[r].append(cell)
    label_matrix = deepcopy(data_matrix)
    firstPass()
    secondPass()



def main():
    global rows
    global columns
    global data_matrix
    global label_matrix
    global equivalency_sets
    global connectedness
    global region_id

    print " FOUR CONNECTEDNESS"
    connectedness  = 4
    testConfigurationOne()
    testConfigurationTwo()

    print " EIGHT CONNECTEDNESS"
    connectedness = 8
    testConfigurationOne()
    testConfigurationTwo()

    data_matrix=[]
    label_matrix=[]
    equivalency_sets=[]
    
    region_id -=5
    connectedness = int(raw_input("Please enter the degree of connectedness (4 or 8)"))
    
    columns = int(raw_input("Please enter the number of columns: "))
    rows = int(raw_input("Please enter the number of rows: "))
    for r in range (rows):
        data_matrix.append([])
        row = raw_input("Please enter the data for row %d  :" % r)
        row = re.sub("\s*", '', row)
        for c in range(columns):
            cell = int(row[c])
            if (cell!=0 and cell!=1):
                print "Error.  This must be a binary matrix.  Only 0s and 1s are allowed"
                return
            data_matrix[r].append(cell)
    label_matrix = deepcopy(data_matrix)
    firstPass()
    secondPass()

if __name__ == '__main__':
    main()
