'''
2. Implement the adaptive arithmetic encoding/decoding algorithm 
with results similar to the midterm question 2. 
Users are first asked to provide prior knowledge 
such as number and frequencies of symbols and confidence: 
with the same prior probability, a more confident prior belief
 asks for larger initial counts assignment 
so that the encoded symbols will not affect the probability too much. 
Users are also allowed to choose to update probabilities for
 every incoming symbol or do it every 10 or 100 symbols encoded. 


'''

import string
from decimal import *
symbol_counts = {}
ordered_symbols = []
total_count=0
certainties = {}
getcontext().prec=100
total_weighted_certainty=Decimal()
code_interval=[Decimal(0),Decimal(1)]


def calculate_interval(char):
    global total_count
    global code_interval
    global total_weighted_certainty

    code_space = code_interval[1]-code_interval[0]
    start_value=code_interval[0]
    end_value= start_value
    
    for sym in (ordered_symbols):
        sym_cnt = symbol_counts[sym]
 #       probability=1.0 * sym_cnt/total_count
        certainty=certainties[sym]
        probability = sym_cnt*certainty/total_weighted_certainty
        range=code_space*probability
        end_value=start_value+range
        if (char==sym):
            code_interval[0]=start_value
            code_interval[1]=end_value
            symbol_counts[sym] = sym_cnt+1
            total_weighted_certainty += certainty
            total_count+=1
            return
        else:
            start_value=end_value

def recalculate_certainties():
    global total_weighted_certainty
    global total_count
    new_weighted_certainty = Decimal()
    for sym in (ordered_symbols):
        sym_cnt = symbol_counts[sym]
        probability=Decimal (100.0 *  sym_cnt/total_count) 
        print "Given the inputs so far, the probability for letter %s is estimated to be  %f " % (sym, probability )
        percent_certainty=Decimal(0.01 * int(raw_input("What percent certainty do you have about this ratio? (0-100):  ")))
        certainties[sym] = percent_certainty
        new_weighted_certainty += (percent_certainty  * sym_cnt)
    total_weighted_certainty = new_weighted_certainty
     
def main():
    global total_length
    global total_count
    global total_weighted_certainty
    total_length=int(raw_input("How long is your string ?  "))
    cont = 'n'
    letter = 'a'
    letter_count = 0
    letter_total = 0
    while (raw_input("You have entered " + str(letter_total) + 
                     " letters. \n Your string is " + str(total_length) + 
                     " long\nDo you have more letters to enter? (y/n) [y]  ")
                     != 'n'):
        letter_count =int(raw_input("How many " + letter + "?  "))
        letter_total += letter_count
        if (letter_total > total_length):
            print (" The number of letters is greater than the length of the string:  ")
            print ("Please try to enter your data again")
            print ("Exiting ...")
            return (-1)
        symbol_counts[letter] = letter_count
        percent_certainty=Decimal(0.01 * int(raw_input("What percent certainty do you have (0-100):  ")))
        certainties[letter] = percent_certainty
        total_weighted_certainty += (percent_certainty  * letter_count)
        ordered_symbols.append(letter)
        letter = chr(ord(letter)+1)
        
    if (letter_total != total_length):
        print (" The number of letters does not equal the length of the string.")
        print ("Please try to enter your data again")
        print ("Exiting ...")
        return (-1)
    rawtext=raw_input("Please enter your string ?")
    if (len(rawtext) != total_length):
        print ("Your string is not the length you said it was.")
        print ("Please try to enter your data again")
        print ("Exiting ...")
        return (-1)
    
    total_count=total_length
    for i in range (total_length):
        calculate_interval(rawtext[i])
        if (i%10 == 9):
            recalculate_certainties()
    encoded=(code_interval[0]+code_interval[1])/2
    print "Encoded Value is " 
    print encoded
    

    
'''
    encoded = encode_string(input_string)
    print "The encoded value is %f" % encoded
'''

if __name__ == '__main__':
    main()
