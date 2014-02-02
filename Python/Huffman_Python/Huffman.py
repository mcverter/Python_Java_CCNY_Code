'''
1.	For any input string, ignoring non-letter characters, construct the binary Huffman code for those lower case characters (upper case ones are put to lower cases).

'''


import string
import re

class TreeNode:
    def __init__(self):
        self.left = None
        self.right = None
        self.data = None
    
    def setData(self, data):
        self.data=data

    def setLeft(self, left):
        self.left=left

    def setRight(self, right):
        self.right=right

    def getRight(self):
        return self.right

    def getLeft(self):
        return self.left

    def getData(self):
        return self.data
    

freq={}
codes={}


'''
from cormen, leiserson, rivest, stein:

n = |lenght of C characters|
Queue = C

for i=1 to n-1
    allocate a new node z
    z.left= x = extractMin(Q)
   z.right = y=extractMin(Q)
    z.frequency = x.frequency+y.frequency
    insert(Q,z)
endfor
return extract=min(q) // return root of tree
'''
def findMin():
    freq.values().sort()

def construct_huffman():
    last_iteration = len(freq)
    for i in range (1, last_iteration):
        sorted_freq = sorted(freq.items(), key=lambda x: x[1])
        one_min_sorted=sorted_freq[0]
        one_min_node=one_min_sorted[0]
        one_min_freq=one_min_sorted[1]
     
        two_min_sorted=sorted_freq[1]
        two_min_node=two_min_sorted[0]
        two_min_freq=two_min_sorted[1]
     
        combined_freq = one_min_freq + two_min_freq
        z = TreeNode()
        z.setLeft(one_min_node)
        z.setRight(two_min_node)
        
        del(freq[one_min_node])
        del(freq[two_min_node])

        freq[z] = combined_freq
        1;

'''
    Start with as many leaves as there are symbols.
    Enqueue all leaf nodes into the first queue (by probability in increasing order so that the least likely item is in the head of the queue).
    While there is more than one node in the queues:
        Dequeue the two nodes with the lowest weight by examining the fronts of both queues.
        Create a new internal node, with the two just-removed nodes as children (either node can be either child) and the sum of their weights as the new weight.
        Enqueue the new node into the rear of the second queue.
    The remaining node is the root node; the tree has now been generated.

Althoug
'''

def generateCode(node, prefix):
    if (node.getData()):
        codes[node.getData()]=prefix
    else:
        generateCode(node.getLeft(), prefix+'0')
        generateCode(node.getRight(), prefix+'1')
        

def read_huffman():
    head = freq.keys()[0]
    generateCode(head, '')

def printCodes():
    for key in codes.keys():
        print key + ":  " + codes[key]
    
def printHuffmanTranslation(plaintext):
    encoded = plaintext
    for key in codes.keys():
        encoded = re.sub(key, codes[key], encoded)
    print "Original: " + plaintext
    print "Encoded:  "+ encoded

def main():
    plaintext = raw_input("Please enter some text and hit return and I will generate beautiful Huffman code!:")
    plaintext=string.lower(plaintext)
    for ltr in string.ascii_lowercase:
        count = string.count(plaintext, ltr)
        if (count>0):
            node = TreeNode();
            node.setData(ltr)
            freq[node] = count
    print "These are the number of occurences of each letter\n"
    for key in freq.keys():
        print "%s : %d\n" % (key.getData(), freq[key] )
    print"\n\n"
    construct_huffman()
    read_huffman()
    printCodes()
    printHuffmanTranslation(plaintext)

if __name__ == '__main__':
    main()

                      

