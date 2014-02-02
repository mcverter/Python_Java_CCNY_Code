'''
Strategy:
(a) Draw if two edges
(b) Draw if no edge
(c) Do not draw if  one edge

Possible triangles:
(a) Corners: 2 triangles
(b) Sides:  4 triangles
(c) Center:  8 triangles
'''

import sys
import random
import re 

GRID_SIZE = 4
grid = [[GRID_SIZE],[GRID_SIZE],[GRID_SIZE],[GRID_SIZE]]
lines = []


def computer_find_move(sx, sy):
    for row in range (GRID_SIZE-2):
        for column in range(GRID_SIZE-2):
            1



class LineSet():
    def __init__(self):
        self.AllEdges=[]
        
    def add_edge(edge):
        AllEdges.append(edge)
  
    def get_next_randm_edge():
        index = random.randint(0,  AllEdges.len)  
        return AllEdges.pop(index)
        
    def length():
        return AllEdges.len
    

class Line():
    def __init__(self, sx, sy, ex, ey):
        self.sx = min(sx,ex)
        self.sy = min(sy,ey)
        self.ex = max(ex,sx)
        self.ey = max(ey,sy)
        
    def has_coordinates(self, sx, sy, ex, ey):
        if (self.sx == sx and
            self.sy == sy and
            self.ex == ex and
            self.ey == ey):
            return True
        return False

def check_for_triangle():
    for row in range(GRID_SIZE):
        for column in range(GRID_SIZE):
            1

def check_horizontal(row, column):
    for line in (lines):
        if line.has_coordinates(row, column, row, column+1):
            return True
    return False

def check_vertical(row, column):
    for line in (lines):
        if line.has_coordinates(row, column, row+1, column):
            return True
    return False

def check_southeast(row,  column):
    for line in (lines):
        if line.has_coordinates(row, column, row+1, column+1):
            return True
    return False

def check_southwest(row,  column):
    for line in (lines):
        if line.has_coordinates(row-1, column, row, column+1):
            return True
    return False



def draw_grid ():
    print
    for row in range (GRID_SIZE):
        for column in range (GRID_SIZE):
            sys.stdout.write ("*")
            if (column < GRID_SIZE):
                if (check_horizontal(row, column)):
                    sys.stdout.write(" --- ")
                else:
                    sys.stdout.write("     ")
        print
        for column in range (GRID_SIZE):
            if (row<GRID_SIZE):
                if (check_vertical(row, column)):
                    sys.stdout.write("|  ")
                else:
                    sys.stdout.write("   ")
                    
                if (column < GRID_SIZE):
                    if (check_southeast(row, column)):
                        sys.stdout.write("\\  ")
                    else:
                        if (column > 0):
                            if (check_southwest(row, column)):
                                sys.stdout.write("/  ")
                            else:
                                sys.stdout.write("   ")
                        else:
                            sys.stdout.write("   ")
                elif (column==GRID_SIZE) :
                    if (check_southwest(row, column)):
                        sys.stdout.write("/  ")
                    else:
                        sys.stdout.write("   ")
        print
        

def checkConnectors(row,column):
    if (row==0):
        if (column==0):
            1         # corner -- two triangles
        elif (column==GRID_SIZE-1):
            1    #corner -- two triangles
        else:
            1   # side -- four triangles
    elif (row==GRID-SIZE-1):
        if (column==0):
            1         # corner -- two triangles
        elif (column==GRID_SIZE-1):
            1    #corner -- two triangles
        else:
            1   # side -- four triangles
    elif (column==0):
        1  #  side 4 triangles -- we already took care of corners
    elif (column==GRID_SIZE-1):
        1  #  side 4 triangles -- we already took care of corners
    else:
        1  # center 8 triangles



def sweet_add_a_line(sx, sy, ex, ey):
    lines.append(Line(sx,sy,ex,ey))
    
def check_for_line(sx, sy, ex, ey):
    return False
    
def check_for_diagonal():
    return False
    
def check_user_move (sx, sy, ex, ey):
    if (sx<0 or sx>= GRID_SIZE or
        sy<0 or sy>= GRID_SIZE or
        ex<0 or ex>= GRID_SIZE or
        sy<0 or sy>= GRID_SIZE or
        (sx-ex==0 and sy-ey==0) or
        abs(sx-ex)>1 or abs(sx-sy)>1):
        return False
    elif (check_for_line(sx, sy, ex, ey)):
        return False
    elif (abs(sx-ex)==1 and abs(sx-sy)==1 and
          check_for_diagonal(sx, sy, ex, ey)):
        return False
    else:
        return True

def get_user_move():
    valid_move = False
    while (valid_move == False):
        start = raw_input("Please enter the start coordinate: [row, column] ")
        s = re.search("(\d),\s*(\d)",  start)
        sx = int(s.group(1))
        sy=int(s.group(2))
        end = raw_input("Please enter the end coordinate: [row, column] ")
        e=re.search("(\d),\s*(\d)",  end)
        ex = int(e.group(1))
        ey= int(e.group(2))
        print "(%d, %d) to (%d, %d)" %  (sx, sy, ex, ey)
        valid_move = check_user_move (sx, sy, ex, ey)
        
def computer_move():
    1
# return mEdgeSet.getNextRemainingEdge()


def other_computer_move():
    return computer_move()
    
def draw_board_1():
    sweet_add_a_line(0,0,0,1)
    sweet_add_a_line(0,0,1,0)
    sweet_add_a_line(2, 2, 2, 3)
    sweet_add_a_line(1, 1, 2, 1)
    sweet_add_a_line(1, 1, 2, 2)
    sweet_add_a_line(2, 2, 1, 1)
    draw_grid()

def check_board():
        return False




#mEdgeSet = EdgeSet()
mLineSet = LineSet()

def main():
    if (random.randint(1, 10) %2):
        computer_move()
    
    done = False
    
    while (done==False):
        other_computer_move()
        done=check_board()
        if (done==False):
            computer_move()
            done=check_board()
        done=True
        
    draw_board_1()

if __name__ == '__main__':
    main()
