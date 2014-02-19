#2.	Implement the adaptive arithmetic encoding and decoding procedure: start from uniform distribution and re-adjust the probability on the fly.

import string


frequencies = {
    'a' : 1,
    'b' : 1,
    'c' : 1,
    'd' : 1,
}
total_count=4

aInterval=[0.00,0.25]
bInterval=[0.25,0.50]
cInterval=[0.50,0.75]
dInterval=[0.75,1.00]
code_interval = [0, 1.00]


aProbability = 0.25 
bProbability = 0.25 
cProbability = 0.25 
dProbability = 0.25 


def print_interval():
    print "code interval: "
    print code_interval
    print "a probability"
    print aProbability 
    print "aInterval: "
    print aInterval
    print "b probability"
    print bProbability 
    print "bInterval: "
    print bInterval
    print "c probability"
    print cProbability 
    print "cInterval: "
    print cInterval
    print "d probability"
    print dProbability 
    print "dInterval: "
    print dInterval


def determine_symbol(value):    
    global aProbability   
    global bProbability   
    global cProbability   
    global dProbability   
    global total_count

#    print_interval() 

    if (aInterval[0] <= value< aInterval[1]):
        symbol  = 'a'
        code_interval[0]=aInterval[0]        
        code_interval[1]=aInterval[1]        
    elif (bInterval[0] <= value< bInterval[1]):
        symbol = 'b'
        code_interval[0]=bInterval[0]        
        code_interval[1]=bInterval[1]        
    elif (cInterval[0] <= value< cInterval[1]):
        symbol = 'c'
        code_interval[0]=cInterval[0]        
        code_interval[1]=cInterval[1]        
    elif (dInterval[0] <= value<= dInterval[1]):
        symbol = 'd'
        code_interval[0]=dInterval[0]        
        code_interval[1]=dInterval[1]        
    else:
        print "something has gone terribly wrong"

    freq=frequencies[symbol]      
    frequencies[symbol] = freq+1
    total_count += 1
    
    aProbability = 1.0 * frequencies['a']/total_count
    bProbability = 1.0 * frequencies['b']/total_count
    cProbability = 1.0 * frequencies['c']/total_count
    dProbability = 1.0 * frequencies['d']/total_count

    newRange = code_interval[1]-code_interval[0]
    aRange = newRange * aProbability 
    bRange = newRange * bProbability
    cRange = newRange * cProbability 
    dRange = newRange * dProbability 

    aInterval[0]=code_interval[0]
    aInterval[1]=aInterval[0]+aRange
    bInterval[0]=aInterval[1]
    bInterval[1]=bInterval[0]+bRange
    cInterval[0]=bInterval[1]
    cInterval[1]=cInterval[0]+cRange
    dInterval[0]=cInterval[1]
    dInterval[1]=dInterval[0]+dRange


    return symbol

def calculate_interval(char):
    # we could have short-circuited this code to make it more efficient
    # but, for debugging purposes, it is better to calculate all the results

    # calculate probability of each symbol
    Pa = (1.0 * frequencies['a'])/total_count 
    Pb = (1.0 * frequencies['b'])/total_count 
    Pc = (1.0 * frequencies['c'])/total_count 
    Pd = (1.0 * frequencies['d'])/total_count 

    # find interval of each symbol
    aInterval=[0, Pa]
    bInterval=[aInterval[1], aInterval[1]+Pb]
    cInterval=[bInterval[1], bInterval[1]+Pc]
    dInterval=[cInterval[1], cInterval[1]+Pd]

    # determine which is the relevant interval
    if (char=='a'):
        char_interval=aInterval
    elif(char=='b'):
        char_interval=bInterval
    elif(char=='c'):
        char_interval=cInterval
    elif(char=='d'):
        char_interval=dInterval


    # calculate the new subinterval
    code_space = code_interval[1]-code_interval[0]
    code_start = code_interval[0]
    code_interval[0]= code_start+(char_interval[0]*code_space)
    code_interval[1]= code_start+(char_interval[1]*code_space)

def decode(input_length, encoded_value):
    decoded =''
    for iteration in range(input_length):
        symbol = determine_symbol(encoded_value)
        decoded = decoded + symbol
    return decoded

def main():
    input_length=int(raw_input("Please enter the length of the string:  "))
    encoded_value=float(raw_input("Please enter the decimal value of the encoded string:  "))
    if ((encoded_value >1) or (encoded_value<0)):
        print "The encoded value must be between 0 and 1"
        return
    else:
        decoded_string=decode(input_length, encoded_value)
    print "The decoded string is %s" % decoded_string

if __name__ == '__main__':
    main()
