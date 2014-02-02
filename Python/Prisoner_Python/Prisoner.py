import random


class Convict():
    def __init__(self):
        self.time_served = 0
        self.plea_history=""

    def add_time(self, years):
        self.time_served += years

    def get_time(self):
        return self.time_served

    def get_plea_history(self):
        return self.plea_history

    def get_plea(self):
        claim = random.randint(1,100) % 2
        if claim == 0:
            self.plea_history += "c"
            return "c"
        elif claim==1:
            self.plea_history += "d"
            return "d"
        else:
            print "something horrible happened"
            assert(0)
        
class GeneticConvict(Convict):
    def __init__(self, plea_history):
        self.time_served = 0
        self.plea_history=plea_history
        self.plea_num=0

    def get_plea(self):
        plea=self.plea_history[self.plea_num]
        self.plea_num += 1
        return plea

def accept_testimony(con_1, con_2):
    c1_test = con_1.get_plea()
    c2_test = con_2.get_plea()
    if (c1_test=="c" and
        c2_test=="c"):
        con_1.add_time(1)
        con_2.add_time(1)
    elif (c1_test=="c" and
          c2_test=="d"):
        con_1.add_time(3)
        con_2.add_time(0)
    elif (c1_test=="d" and
          c2_test=="c"):
        con_1.add_time(0)
        con_2.add_time(3)
    elif (c1_test=="d" and
          c2_test=="d"):
        con_1.add_time(2)
        con_2.add_time(2)
    else:
        print "something terrible happened"




def one_hundred_trials(convict1, convict2):
    for i in range (100):
        accept_testimony(convict1, convict2)

    time1=convict1.get_time()
    time2=convict2.get_time()
    pleas1 = convict1.get_plea_history()
    pleas2=convict2.get_plea_history()
        
    return (time1, time2, pleas1, pleas2)
 

def get_best_random_trial():
    best_time = 1000000000000
    second_best_time = 10000000000000000
    best_strategy = "foobar"
    second_best_strategy = "boofar"
    
    for i in range (100):
        (time1, time2, pleas1,pleas2) = one_hundred_trials()
        if (time1<time2):
            if (time1<best_time):
                best_time=time1
                best_strategy=pleas1
                if (time2<second_best_time):
                    second_best_time=time2
                    second_best_strategy=pleas2
            elif (time1<second_best_time):
                    second_best_time=time1
                    second_best_strategy=pleas1
        else:
            if (time2<best_time):
                best_time=time2
                best_strategy=pleas2
                if (time1<second_best_time):
                    second_best_time=time1
                    second_best_strategy=pleas1
            elif (time2<second_best_time):
                second_best_time=time2
                second_best_strategy=pleas2
    print "Best:  Time:  " + str(best_time) + ".   Strategy:  " +  best_strategy
    print "Second:  Time:  " + str(second_best_time) + ".   Strategy:  " +  second_best_strategy

def make_next_generation(ancestors):
    enemy = Convict()
    best_time = 1000000000000
    second_best_time = 10000000000000000
    best_strategy = "foobar"
    second_best_strategy = "boofar" 
    for ancestor in ancestors:
        time = one_hundred_trials(ancestor,enemy)[0]
        if time < 50:
            1
        if (time<best_time):
            second_best_time=best_time
            second_best_strategy=best_strategy
            best_time=time
            best_strategy=ancestor.get_plea_history()
        elif (time<second_best_time):
            second_best_time=time
            second_best_strategy=ancestor.get_plea_history()
    next_generation = breed(best_strategy, second_best_strategy)
    return next_generation

def breed(anc1, anc2):
    new_generation=[]
    for i in range (10):
        split = random.randint(1,100)
        new_strategy=anc1[0:split]+anc2[split:100]
        for j in range (100):
            if random.randint(1,100) == 77:
                if (new_strategy[j]=='d'):
                    new_strategy=new_strategy[0:j]+'c'+new_strategy[j+1:100]
                else:
                    new_strategy=new_strategy[0:j]+'d'+new_strategy[j+1:100]
        new_generation.append(GeneticConvict(new_strategy))
    return new_generation
        
def create_ten_random_ancestors():
    ancestors=[]
    '''
    plea_history=""
    for i in range (100):
            plea_history += "c"
        ancestors.append(GeneticConvict(plea_history))

        plea_history=""
        for i in range (100):
            plea_history += "d"
        ancestors.append(GeneticConvict(plea_history))

        plea_history=""
        for i in range (50):
            plea_history += "d"
        for i in range (50):
            plea_history += "c"
        ancestors.append(GeneticConvict(plea_history))


        plea_history=""
        for i in range (50):
            plea_history += "c"
        for i in range (50):
            plea_history += "d"
        ancestors.append(GeneticConvict(plea_history))
        '''
    plea_history=""
    for i in range (25):
        plea_history += "c"
    for i in range (25):
        plea_history += "d"
    for i in range (25):
        plea_history += "c"
    for i in range (25):
        plea_history += "d"
    ancestors.append(GeneticConvict(plea_history))

    plea_history=""
    for i in range (25):
        plea_history += "d"
    for i in range (25):
        plea_history += "c"
    for i in range (25):
        plea_history += "d"
    for i in range (25):
        plea_history += "c"
    ancestors.append(GeneticConvict(plea_history))

    for j in range(8):
        plea_history=""
        for i in range (100):
            if random.randint(1,100) % 2 == 0:
                plea_history+= "c"
            else:
                plea_history+=  "d"
        ancestors.append(GeneticConvict(plea_history))

    return ancestors
            
def main():
    ancestors=create_ten_random_ancestors()
    for i in range (100):
        ancestors=make_next_generation(ancestors)
    best_time = 1000000000000    
    best_strategy="foobar"
    enemy = Convict()
    for ancestor in ancestors:
        time = one_hundred_trials(ancestor,enemy)[0]
        strategy=ancestor.get_plea_history()
        if (time<best_time):
            best_time=time
            best_plea_history = strategy
    print "Best Time:  " + str(best_time)
    print "Best Strategy: " + best_plea_history
        

if __name__ == '__main__':
    main()
