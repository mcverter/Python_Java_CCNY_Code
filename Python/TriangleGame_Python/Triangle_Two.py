from PyQt4 import QtGui 
from PyQt4 import QtCore
import PyQt4
import sys
import random

SCALE=50
line_hash={}
triangle_hash={}
triangle_to_lines={}
line_to_triangles={}

class Line:
    def __init__ (self, id_num, sr, sc, er, ec):
        self.id=id_num
        self.sr=sr
        self.sc=sc
        self.er=er
        self.ec=ec
        self.drawn=False
        self.drawable=True

    def draw(self, qp):
        if self.drawn==True:
            qp.setPen(QtGui.QPen(QtCore.Qt.blue,5))
            qp.drawLine(self.sc*SCALE, self.sr*SCALE, 
                    self.ec*SCALE, self.er*SCALE)


class Triangle:
    def __init__ (self, id_num, e1, e2, e3):    
        self.id=id_num
        self.e1 = e1
        self.e2 = e2
        self.e3 = e3
        self.winner = None
        

    def draw(self, qp):
        if (self.winner==None):
            return
        if (self.winner=="Player1"):
            tricolor= QtCore.Qt.green
        else:
            tricolor=QtCore.Qt.magenta
        sides=[line_hash[self.e1],line_hash[self.e2],line_hash[self.e3]]
        all_points=[]
        for side in sides:
            all_points.append(QtCore.QPointF(0+side.sc*SCALE, 0+side.sr*SCALE))
            all_points.append(QtCore.QPointF(0+side.ec*SCALE, 0+side.er*SCALE))

        trianglePath = QtGui.QPainterPath() 
        trianglePath.moveTo(all_points[0])
        if (all_points[1] != all_points[0]):
                trianglePath.lineTo(all_points[1])
        if (all_points[2] != all_points[0] and all_points[2] != all_points[1] ):
                trianglePath.lineTo(all_points[2])
        if (all_points[3] != all_points[0] and all_points[3] != all_points[1] 
            and all_points[3] != all_points[2] ):
                trianglePath.lineTo(all_points[3])
        if (all_points[4] != all_points[0] and all_points[4] != all_points[1] 
            and all_points[4] != all_points[2] and all_points[4] != all_points[3] ):
            trianglePath.lineTo(all_points[4])
        if (all_points[5] != all_points[0] and all_points[5] != all_points[1] 
            and all_points[5] != all_points[2] and all_points[5] != all_points[3] 
            and all_points[5] != all_points[4] ):
            trianglePath.lineTo(all_points[5])
        trianglePath.closeSubpath()
        qp.fillPath(trianglePath,  tricolor)


class Board(QtGui.QWidget):
    def __init__(self):
        super(Board, self).__init__()
        self.setGeometry(0,0,250,250)
        self.show()
        self.initialize_lines()
        self.initialize_triangles()
        self.remaining_lines=[]


    def temp_line_draw(self, line_num):
        self.all_lines[line_num-1].drawn = True
        all_triangles = line_to_triangles[line_num]
        for triangle_num in all_triangles:
            if self.all_triangles[triangle_num-1].winner==None:
                tri_lines = triangle_to_lines[triangle_num]
                if (self.all_lines[tri_lines[0]-1].drawn and 
                    self.all_lines[tri_lines[1]-1].drawn and 
                    self.all_lines[tri_lines[2]-1].drawn):
                    self.all_triangles[triangle_num-1].winner="Temp"
    
    def erase_line(self,line_num):
        self.all_lines[line_num-1].drawn = False
        all_triangles = line_to_triangles[line_num]
        for triangle_num in all_triangles:
            if self.all_triangles[triangle_num-1].winner=="Temp":
                self.all_triangles[triangle_num-1].winner=None



    def check_for_completed_triangle(self, line_num,player_name=None):
        all_triangles = line_to_triangles[line_num]
        for triangle_num in all_triangles:
            if self.all_triangles[triangle_num-1].winner==None:
                tri_lines = triangle_to_lines[triangle_num]
                if (self.all_lines[tri_lines[0]-1].drawn and 
                    self.all_lines[tri_lines[1]-1].drawn and 
                    self.all_lines[tri_lines[2]-1].drawn):
                    self.all_triangles[triangle_num-1].winner=player_name

    def count_completed_triangles(self):
        completed = 0
        for tri in self.all_triangles:
            if tri.winner != None:
                completed += 1
        return completed

    def test_line_addition(line):
        ''' find the number of current triangles on board'''
        ''' draw the line '''
        ''' find the number of triangles now'''
        ''' undraw the line'''
        return 1

    def get_scores(self):
        p1 = 0
        p2=0
        print len(self.all_triangles)
        for triangle in self.all_triangles:
            if triangle.winner == "Player1":
                p1+= 1
            elif triangle.winner == "Player2":
                p2+= 1
        return (p1,p2)
            
            
        
    def make_lines_undrawable(self, line_num):
        self.all_lines[line_num-1].drawable=False
        
#diagonals
        if (line_num==5):
            self.all_lines[5].drawable=False
        elif (line_num==6):
            self.all_lines[4].drawable=False
        elif (line_num==8):
            self.all_lines[8].drawable=False
        elif (line_num==9):
            self.all_lines[7].drawable=False
        elif (line_num==11):
            self.all_lines[11].drawable=False
        elif (line_num==12):
            self.all_lines[10].drawable=False
        elif (line_num==18):
            self.all_lines[18].drawable=False
        elif (line_num==19):
            self.all_lines[17].drawable=False
        elif (line_num==21):
            self.all_lines[21].drawable=False
        elif (line_num==22):
            self.all_lines[20].drawable=False
        elif (line_num==24):
            self.all_lines[24].drawable=False
        elif (line_num==25):
            self.all_lines[23].drawable=False
        elif (line_num==31):
            self.all_lines[31].drawable=False
        elif (line_num==32):
            self.all_lines[30].drawable=False
        elif (line_num==34):
            self.all_lines[34].drawable=False
        elif (line_num==35):
            self.all_lines[33].drawable=False
        elif (line_num==37):
            self.all_lines[37].drawable=False
        elif (line_num==38):
            self.all_lines[36].drawable=False

    def sweet_add_a_line(self, line_num, player_name):
        self.make_lines_undrawable(line_num)
        self.all_lines[line_num-1].drawn=True
        self.check_for_completed_triangle(line_num, player_name)
        self.repaint()

    def check_if_valid_line(self, line_num):
        line = self.all_lines[line_num-1]
        return line.drawable

    def paintEvent(self, e):
        qp = QtGui.QPainter()
        qp.begin(self)
        self.drawVisiblePoints(qp)
        self.drawVisibleTriangles(qp) 
        self.drawVisibleLines(qp)
 
        qp.end()


  
    def drawVisiblePoints(self, qp):      
        qp.setPen (QtGui.QPen(QtCore.Qt.red, 5))
        for r in range(4):
            for c in range(4):
                qp.drawPoint(r*SCALE,c*SCALE)

    def drawVisibleLines(self, qp):
        for line in self.all_lines:
            line.draw(qp)
        
    def drawVisibleTriangles(self, qp):
        for triangle in self.all_triangles:
            triangle.draw(qp)
        
    def initialize_lines(self):
        l1 =  Line(1 ,0,0,0,1)
        l2  = Line(2 ,0,1,0,2)
        l3  = Line(3 ,0,2,0,3)
        l4  = Line(4 ,0,0,1,0)
        l5  = Line(5 ,0,0,1,1)
        l6  = Line(6 ,0,1,1,0)
        l7  = Line(7 ,0,1,1,1)
        l8  = Line(8 ,0,1,1,2)
        l9  = Line(9 ,0,2,1,1)
        l10 = Line(10,0,2,1,2)
        l11 = Line(11,0,2,1,3)
        l12 = Line(12,0,3,1,2)
        l13 = Line(13,0,3,1,3)
        # Row 2
        l14 = Line(14,1,0,1,1)
        l15 = Line(15,1,1,1,2)
        l16 = Line(16,1,2,1,3)
        l17 = Line(17,1,0,2,0)
        l18 = Line(18,1,0,2,1)
        l19 = Line(19,1,1,2,0)
        l20 = Line(20,1,1,2,1)
        l21 = Line(21,1,1,2,2)
        l22 = Line(22,1,2,2,1)
        l23 = Line(23,1,2,2,2)
        l24 = Line(24,1,2,2,3)
        l25 = Line(25,1,3,2,2)
        l26 = Line(26,1,3,2,3)

        
        # Row 2
        l27 = Line(27,2,0,2,1)
        l28 = Line(28,2,1,2,2)
        l29 = Line(29,2,2,2,3)
        l30 = Line(30,2,0,3,0)
        l31 = Line(31,2,0,3,1)
        l32 = Line(32,2,1,3,0)
        l33 = Line(33,2,1,3,1)
        l34 = Line(34,2,1,3,2)
        l35 = Line(35,2,2,3,1)
        l36 = Line(36,2,2,3,2)
        l37 = Line(37,2,2,3,3)
        l38 = Line(38,2,3,3,2)
        l39 = Line(39,2,3,3,3)
        l40 = Line(40,3,0,3,1)
        l41 = Line(41,3,1,3,2)
        l42 = Line(42,3,2,3,3)
        self.all_lines=[l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,
                        l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35,l36,l37,l38,l39,
                        l40,l41,l42]
        for line in self.all_lines:
            line_hash[line.id]=line
                        
    def get_remaining_lines(self):
        self.remaining_lines=[]
        for line in self.all_lines:
            if line.drawable==True:
                self.remaining_lines.append(line.id)
        return (self.remaining_lines)
                
    def get_remaining_line(self, choice_num):
        return self.remaining_lines[choice_num]

    def is_game_over(self):
        return len(self.get_remaining_lines())==0

    def initialize_triangles(self):        
        Triangle1 = Triangle(1,1,4,6)
        Triangle2 = Triangle(2,1,5,7)
        Triangle3 = Triangle(3,2,7,9)
        Triangle4 = Triangle(4,2,8,10)
        Triangle5 = Triangle(5,3,10,12)
        Triangle6 = Triangle(6,3,11,13)
        Triangle7 = Triangle(7,4,5,14)
        Triangle8 = Triangle(8,6,7,14)
        Triangle9 = Triangle(9,7,8,15)
        Triangle10 = Triangle(10,9,10,15)
        Triangle11 = Triangle(11,10,11,16)
        Triangle12 = Triangle(12,12,13,16)
        Triangle13 = Triangle(13,14,17,19)
        Triangle14 = Triangle(14,14,18,20)
        Triangle15 = Triangle(15,15,20,22)
        Triangle16 = Triangle(16,15,21,23)
        Triangle17 = Triangle(17,16,23,25)
        Triangle18 = Triangle(18,16,24,26)
        Triangle19 = Triangle(19,17,18,27)
        Triangle20 = Triangle(20,19,20,27)
        Triangle21 = Triangle(21,20,21,28)
        Triangle22 = Triangle(22,22,23,28)
        Triangle23 = Triangle(23,23,24,29)
        Triangle24 = Triangle(24,25,26,29)
        Triangle25 = Triangle(25,27,30,32)
        Triangle26 = Triangle(26,27,31,33)
        Triangle27 = Triangle(27,28,33,35)
        Triangle28 = Triangle(28,28,34,36)
        Triangle29 = Triangle(29,29,36,38)
        Triangle30 = Triangle(30,29,37,39)
        Triangle31 = Triangle(31,30,31,40)
        Triangle32 = Triangle(32,32,33,40)
        Triangle33 = Triangle(33,33,34,41)
        Triangle34 = Triangle(34,35,36,41)
        Triangle35 = Triangle(35,36,37,42)
        Triangle36 = Triangle(36,38,39,42)
        self.all_triangles=[Triangle1,Triangle2,Triangle3,Triangle4,Triangle5,Triangle6,
                            Triangle7,Triangle8,Triangle9,Triangle10,Triangle11,Triangle12,
                            Triangle13,Triangle14,Triangle15,Triangle16,Triangle17,Triangle18,
                            Triangle19,Triangle20,Triangle21,Triangle22,Triangle23,Triangle24,
                            Triangle25,Triangle26,Triangle27,Triangle28,Triangle29,Triangle30,
                            Triangle31,Triangle32,Triangle33,Triangle34,Triangle35,Triangle36]
        for index in range (1,37):
            triangle_to_lines[index]=[]
        for index in range (1,43):
            line_to_triangles[index]=[]
        for triangle in self.all_triangles:
            triangle_hash[triangle.id]=triangle
            triangle_to_lines[triangle.id]=[triangle.e1, triangle.e2,triangle.e3]
            line_to_triangles[triangle.e1].append(triangle.id)
            line_to_triangles[triangle.e2].append(triangle.id)
            line_to_triangles[triangle.e3].append(triangle.id)

def get_player_move(board):
    line_picked=""
    valid_move = False 
    while (valid_move == False):
        try:
            line_picked = int(raw_input("Please select a line:  "))
            valid_move = board.check_if_valid_line(line_picked)
        except ValueError:
            valid_move=False
    return line_picked


def make_computer_move(board):
    return make_computer_move_lookahead_two(board)




def make_computer_move_lookahead_two(board):
    choices = board.get_remaining_lines()
    num_triangles_completed = board.count_completed_triangles()
    
    best_net_gain=-1000
    best_gain_set=[]
    for choice in choices:
        board.temp_line_draw(choice)
        test_completed = board.count_completed_triangles()
        my_triangles_gained = test_completed-num_triangles_completed
        
        other_player_choices=choices[:]
        other_player_choices.remove(choice)
        
        max_other_gain=0
        for other_choice in other_player_choices:
            board.temp_line_draw(other_choice)
            other_triangle_completed=board.count_completed_triangles()
            other_triangle_gain=other_triangle_completed-test_completed
            max_other_gain=max(other_triangle_gain, max_other_gain)
            board.erase_line(other_choice)
        
        net_gain=my_triangles_gained-max_other_gain

        if (net_gain>best_net_gain):
            best_net_gain=net_gain
            best_gain_set=[choice]
        elif (net_gain==best_net_gain):
            best_gain_set.append(choice)
        board.erase_line(choice)

    choice = random.randint(0,len(best_gain_set)-1)
    move=best_gain_set[choice]
    return move



def make_computer_move_lookahead_one(board):
    choices = board.get_remaining_lines()
    num_triangles_completed = board.count_completed_triangles()
    best_gain=0
    best_gain_set=[]
    for choice in choices:
        board.temp_line_draw(choice)
        test_completed = board.count_completed_triangles()
        triangles_gained = test_completed-num_triangles_completed
        if (triangles_gained>best_gain):
            best_gain=triangles_gained
            best_gain_set=[choice]
        elif (triangles_gained==best_gain):
            best_gain_set.append(choice)
        board.erase_line(choice)

    choice = random.randint(0,len(best_gain_set)-1)
    move=best_gain_set[choice]
    return move

    
    

def main():
    app=QtGui.QApplication(sys.argv)
    board=Board()

    print '''
========================================
========================================

STARTING TRIANGLES GAAAAAAAAME


'''
    if (random.randint(1,10)%2==0):
        print "PLAYER TWO:"
        move = make_computer_move(board)
        board.sweet_add_a_line(move, "Player2")
        print "player 2 moves to %d" % move
        
    while (board.is_game_over()==False):
        print "PLAYER ONE:"
#        move = make_computer_move(board)
#        board.sweet_add_a_line(move, "Player1")
        move = board.sweet_add_a_line(get_player_move(board), "Player1")
#        print "player 1 moves to %d" % move
        if (board.is_game_over()==False):
            print "PLAYER TWO:"
            move = make_computer_move(board)
            board.sweet_add_a_line(move, "Player2")
            print "player 2 moves to %d" % move
 #           board.sweet_add_a_line(get_player_move(board), "Player2")
    scores = board.get_scores()
    print "Player 1: %d .  Player 2: %d" % (scores[0],scores[1])
    if (scores[0] > scores[1]):
        print "PLAYER ONE IS THE WINNNNNNAAAAAHHHHH!!!!"
    elif (scores[1] > scores[0]):
        print "PLAYER TWO IS THE WINNNNNNAAAAAHHHHH!!!!"
    else:
        print "Tie Game!!!"
    moo = raw_input("Press Any Key to Quit")

if __name__ == '__main__':
    main()
    
