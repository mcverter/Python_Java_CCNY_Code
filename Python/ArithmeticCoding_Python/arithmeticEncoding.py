#2.	Implement the adaptive arithmetic encoding and decoding procedure: start from uniform distribution and re-adjust the probability on the fly.

import string


frequencies = {
    'a' : 1,
    'b' : 1,
    'c' : 1,
    'd' : 1,
}
total_count=4

aProbability   = 0.25
bProbability   = 0.25
cProbability   = 0.25
dProbability    = 0.25


aInterval=[0.00,0.25]
bInterval=[0.25,0.50]
cInterval=[0.50,0.75]
dInterval=[0.75,1.00]

code_interval=[0,1]

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

def calculate_interval(char):
    global aInterval
    global bInterval
    global cInterval
    global dInterval
    global aProbability   
    global bProbability   
    global cProbability   
    global dProbability   
    global total_count
    global code_interval

    aProbability = (1.0 * frequencies['a'])/total_count 
    bProbability = (1.0 * frequencies['b'])/total_count 
    cProbability = (1.0 * frequencies['c'])/total_count 
    dProbability = (1.0 * frequencies['d'])/total_count 

    code_space = code_interval[1]-code_interval[0]

    aRange = code_space * aProbability 
    bRange = code_space * bProbability
    cRange = code_space * cProbability 
    dRange = code_space * dProbability 

    aInterval[0]=code_interval[0]
    aInterval[1]=aInterval[0]+aRange
    bInterval[0]=aInterval[1]
    bInterval[1]=bInterval[0]+bRange
    cInterval[0]=bInterval[1]
    cInterval[1]=cInterval[0]+cRange
    dInterval[0]=cInterval[1]
    dInterval[1]=dInterval[0]+dRange

#    print_interval()


    # determine which is the relevant interval
    if (char=='a'):
        code_interval[0]=aInterval[0]
        code_interval[1]=aInterval[1]
    elif(char=='b'):
        code_interval[0]=bInterval[0]
        code_interval[1]=bInterval[1]
    elif(char=='c'):
        code_interval[0]=cInterval[0]
        code_interval[1]=cInterval[1]
    elif(char=='d'):
        code_interval[0]=dInterval[0]
        code_interval[1]=dInterval[1]


def encode_string(s):
    global total_count
    stringSoFar=""
    for char in s:
        try: 
            stringSoFar  += char
            calculate_interval(char)
            freq= frequencies[char]      
            frequencies[char] = freq+1
            total_count += 1

        except KeyError:
            print "Invalid input character in input: %s" % char
            print "Exiting program"
            return
    encoded=(code_interval[0]+code_interval[1])/2
    return encoded

def main():
    print "In our language, we only have four letters: [a,b,c,d]"
    input_string = string.lower(raw_input("Enter a string with these four letters:"))
    encoded = encode_string(input_string)
    print "The encoded value is %f" % encoded

if __name__ == '__main__':
    main()
