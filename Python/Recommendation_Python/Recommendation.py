'''
This file fulfills question #2, filling in the missing values


'''
#import pdb; pdb.set_trace()
import numpy as np
import matplotlib.pyplot as plt



# failed attempt to load array directly from file
# could not figure out how to do masking
#import numpy.ma as ma

DATAFILE= "/home/mitchell/ComputerScience/479_DigitalLibraries/wei_groupproject/Problem_2/roster_sample.txt"
grades = np.loadtxt(fname=DATAFILE,  dtype=np.int,  skiprows=1,   
                         converters= {
                                    0: lambda s: 95 if  s=='A' 
                                        else 85 if s=='B' 
                                        else 75 if s=='C'
                                        else  -1 if s== '?'
                                        else -555 ,  
                                    1: lambda s : -1 if s=="?" else s,
                                    2: lambda s : -1 if s=="?" else s,
                                    3: lambda s : -1 if s=="?" else s, 
                                    }) 
#    >>> mx = ma.masked_array(x, mask=[0, 0, 0, 1, 0])


# just hardcoded the values instead

all_values = [
#        [--, 15, 28, 28],
#       [--, 27, 47, 45],
#      [--, 16, 62, 32],
#      [85, 8, 52, --],
#       [95, 25, 51, --],
#        [75, 21, 33, --],
#        [75, 14, --, 40],
        [95, 36, 48, 45],
        [85, 20, 23, 36],
        [95, 21, 45, 51],
        [95, 33, 50, 39],
        [85, 36, 40, 29],
        [75, 12, 41, 27],
        [75, 19, 32, 29],
        [85, 20, 55, 39],
        [95, 18, 66, 48],
        [75, 25, 42, 36],
        [85, 25, 30, 39],
        [75, 21, 37, 56],
        [95, 39, 69, 54],
        [95, 38, 49, 51],
        [85, 33, 57, 38],
        [85, 30, 35, 33],
        [85, 38, 43, 40],
        ]

complete_results = np.array([
        [95, 36, 48, 45],
        [85, 20, 23, 36],
        [95, 21, 45, 51],
        [95, 33, 50, 39],
        [85, 36, 40, 29],
        [75, 12, 41, 27],
        [75, 19, 32, 29],
        [85, 20, 55, 39],
        [95, 18, 66, 48],
        [75, 25, 42, 36],
        [85, 25, 30, 39],
        [75, 21, 37, 56],
        [95, 39, 69, 54],
        [95, 38, 49, 51],
        [85, 33, 57, 38],
        [85, 30, 35, 33],
        [85, 38, 43, 40],
        ])

finals_incomplete = np.array([
        [85, 8, 52],
        [95, 25, 51],
        [75, 21, 33],
])

midterms_incomplete = np.array([
    [75, 14, 40],
])


course_grade_incomplete = np.array([
    [15, 28, 28],
    [27, 47, 45],
    [16, 62, 32],
])


# different regression models have different exponents
powers = [ 
    [1,1,1],
    [2,1,1],
    [1,2,1],
    [1,1,2],
    [2,2,1],
    [2,1,2],
    [1,2,2],
    [2,2,2],
    [0.5,1,1],
    [1,0.5,1],
    [1,1,0.5],
    [0.5,0.5,1],
    [0.5,1,0.5],
    [1,0.5,0.5],
    [0.5,0.5,0.5]
]


def find_error(arrays, coefficients, ex):
    '''
    Finds error of a statistical model by comparing the
    result predicted by the model with the actual result 
    given in the chart

    INPUTS:  
    (1) Array of actual results
    (2) Coefficients of statistical model
    (3) Exponents of statistical model

    OUTPUT:
    (1) SUM(expected_distance_from_actual) / SUM(actual)
    '''
    total_difference=0
    total_result=0
    for array in arrays:
        expected_result = 1.0* (
            coefficients[0]*pow(array[0],powers[ex][0])+
            coefficients[1]*pow(array[1],powers[ex][1])+
            coefficients[2]*pow(array[2],powers[ex][2]))
        actual_result = array[3]
        difference = abs(expected_result-actual_result)
        total_difference += difference
        total_result+= actual_result
    return 1.0 * total_difference/total_result


def chart_results(names, array, coefficients, exes, subplot_num):
    equation_definition = "(%f *( %s ** %d )) + \n (%f *( %s ** %d )) + \n  (%f *( %s ** %d )) \n = %s \n" % (coefficients[0], names[0], exes[0], coefficients[1], names[1], exes[1],  coefficients[2], names[2], exes[2],names[3])
    all_expected=[]
    print "EQUATION IS:" 
    print equation_definition
    for actual_scores in array:
        expected_result = 1.0* (
            coefficients[0]*pow(actual_scores[0],exes[0])+
            coefficients[1]*pow(actual_scores[1],exes[1])+
            coefficients[2]*pow(actual_scores[2],exes[2]))
        all_expected.append(expected_result)
        actual_result = actual_scores[3]
        difference = abs(expected_result-actual_result)
        relative_difference = difference/actual_result
        print "\n\nFOR ROW:"
        print actual_scores
        print "Expected Result: %f.  Actual Result: %d " % (expected_result, actual_result)  
        print "Difference:  %f.  Weighted Differene: %f" % (difference, relative_difference)
    graph_results(equation_definition, array[:,3], all_expected, subplot_num)

def graph_results(title, actual, expected, subplot_num):
    plt.hold(True)
    length = len(actual)    
#    plt.subplot(subplot_num)
    plt.xlabel("Trial Number")
    plt.ylabel("Results")
    plt.title(title)
    plt.axis([0, length+2, 0, 120])
    for index in range(length):
        plt.plot(index+1, actual[index], 'ro', label="Actual")
        plt.plot(index+1, expected[index], 'g*', label="Expected")
        if (index==0):
            plt.legend("AE", "Expected")
    plt.show()
     
     
    
    
    # header = value is this,  x1 is this x2 is this , x3 is that
    # coefficients[0]*(x0**powers[0]) + coefficients[1]*(x1**powers[1]) + coefficients[2]*(x2**powers[2])     
   # sort array by 4th column
    # for each value
    # caluculate expected value
    # graph againstreceived value 
    # calculate numerical error
    # add bth points to plot 
    # x axis is just the value 
    # print out the equation 
    # equation string =
    # value 

    
def cross_validate(xarray, all_coefficients):
    '''
    Determines the error produced by an array of statistical 
    models and returns the index of the model with the smallest error


    INPUTS:
    (1) Array of actual values used for cross-validation
    (2) Array of coefficients used by various statistical models

    OUTPUT:
    '''
    all_errors=[]
    for r in range (8):
        error = find_error(xarray, all_coefficients[r], r)
        all_errors.append(error)
    min_error= min(all_errors)
    min_index=all_errors.index(min_error)
    return min_index

def find_training_coefficients(training):
    '''
    '''
    all_coefficients=[]
    for r in range(8):
        co=get_coefficients(training,r)
        all_coefficients.append(co)
    return all_coefficients

    
def get_missing_value(coefficients, arr, ex):
    '''
    '''
    missing_value = 1.0 * (
        coefficients[0]*pow(arr[0],powers[ex][0])+
        coefficients[1]*pow(arr[1],powers[ex][1])+
        coefficients[2]*pow(arr[2],powers[ex][2]))
    return missing_value



def get_coefficients (arr, ex):
    '''
    '''
    first_three = np.array([
            pow(arr[:,0], powers[ex][0]),
            pow(arr[:,1], powers[ex][1]),
            pow(arr[:,2], powers[ex][2])
                ]).T
    last=arr[:,3]
    co=np.dot(np.linalg.pinv(first_three),last)
    return co


def randomize(arr):   
    '''
    '''
    return np.random.shuffle(arr)
    
def partition_data(arr):
    '''
    '''
    count=len(arr)
    twenty_percent=int(count*0.2)
    cross_validation=arr[:twenty_percent]
    testing=arr[twenty_percent:(2*twenty_percent)]
    training=arr[(2*twenty_percent):]
    return (cross_validation, testing, training)


def find_missing_course_grades(): 
    '''
    '''
    grades=complete_results.copy()
    grades=  np.vstack((grades[:,1],grades[:,2],grades[:,3],grades[:,0])).T
    results_are_bad = True
    best_model=-1
    all_coefficients=[]
    testing=[]
    while(results_are_bad):
        randomize(grades)
        all_partitions = partition_data(grades)
        training=all_partitions[2]
        all_coefficients = find_training_coefficients(training)
        cross_validation=all_partitions[1]
        best_fit_index=cross_validate(cross_validation, all_coefficients)
        testing=all_partitions[0]
        test_error = find_error(testing, all_coefficients[best_fit_index],
                                best_fit_index)
        if (test_error < 0.1):
            results_are_bad=False
            best_model = best_fit_index


    all_found = grades.copy()
    for unknown in course_grade_incomplete:
        course_grade = get_missing_value(
            all_coefficients[best_model],
            unknown, best_model)
        known = np.append(unknown, course_grade)
        all_found = np.vstack([all_found, known])
        
    names = ("quiz_grade","final_exam_grade","midterm_grade", "course_grade")
    print "OVERALL RESULTS for COURSE GRADES"
    print "Testing Set:"
    print testing
    print "test error is %f" % test_error
    print "Complete Chart (Unknowns at bottom)"
    print "%s\t%s\t%s\t%s" % names
    print all_found
    print "Global Relative Error for Knowns:"
    print find_error(all_found, all_coefficients[best_model], best_model)
    print "\n\n"
    print "INDIVIDUAL RESULTS:"
    chart_results(names, grades, all_coefficients[best_model], powers[best_model], 311)
 #   x=np.arange(0,110)
#    plt.plot(x, all_found, 'o')


def find_missing_final_grades():
    '''
    '''
    grades=complete_results.copy()
    results_are_bad = True
    best_model=-1
    testing=[]
    while(results_are_bad):
        randomize(grades)
        all_partitions = partition_data(grades)
        training=all_partitions[2]
        all_coefficients = find_training_coefficients(training)
        cross_validation=all_partitions[1]
        best_fit_index=cross_validate(cross_validation, all_coefficients)
        testing=all_partitions[0]
        test_error = find_error(testing, all_coefficients[best_fit_index],
                                best_fit_index)

        if (test_error < 0.1):
            results_are_bad=False
            best_model = best_fit_index

    all_found = grades.copy()
    for unknown in finals_incomplete:
        course_grade = get_missing_value(
            all_coefficients[best_model],
            unknown, best_model)
        known = np.append(unknown, course_grade)
        all_found = np.vstack([all_found, known])



    names = ("course_grade","quiz_grade","midterm_grade","final_exam_grade")
    print "\n\n\n\n*********************************************************************************"
    print "OVERALL RESULTS for FINAL EXAM GRADES"
    print "Testing Set:"
    print testing
    print "test error is %f" % test_error
    print "Complete Chart (Unknowns at bottom)"
    print "%s\t%s\t%s\t%s" % names
    print all_found
    print "Global Relative Error for Knowns:"
    print find_error(all_found, all_coefficients[best_model], best_model)
    print "\n\n"
    print "INDIVIDUAL RESULTS:"
    chart_results(names, grades, all_coefficients[best_model], powers[best_model], 312)


def find_missing_midterm_grades():
    '''
    '''
    grades=complete_results.copy()
    grades=  np.vstack((grades[:,0],grades[:,1],grades[:,3],grades[:,2])).T
    results_are_bad = True
    best_model=-1
    testing=[]
    while(results_are_bad):
        randomize(grades)
        all_partitions = partition_data(grades)
        training=all_partitions[2]
        all_coefficients = find_training_coefficients(training)
        cross_validation=all_partitions[1]
        best_fit_index=cross_validate(cross_validation, all_coefficients)
        testing=all_partitions[0]
        test_error = find_error(testing, all_coefficients[best_fit_index],
                                best_fit_index)

        if (test_error < 0.1):
            results_are_bad=False
            best_model = best_fit_index
            print "test error is %f" % test_error

    all_found = grades.copy()
    for unknown in midterms_incomplete:
        course_grade = get_missing_value(
            all_coefficients[best_model],
            unknown, best_model)
        known = np.append(unknown, course_grade)
        all_found = np.vstack([all_found, known])
    print all_found
    names = ("course_grade","quiz_grade","final_exam_grade","midterm_grade")
    print "\n\n\n\n*********************************************************************************"
    print"OVERALL RESULTS for MIDTERM GRADES"
    print "Testing Set:"
    print testing
    print "test error is %f" % test_error
    print "Complete Chart (Unknowns at bottom)"
    print "%s\t%s\t%s\t%s" % names
    print all_found
    print "Global Relative Error for Knowns:"
    print find_error(all_found, all_coefficients[best_model], best_model)
    print "\n\n"
    print "INDIVIDUAL RESULTS:"
    chart_results(names, grades, all_coefficients[best_model], powers[best_model],313)  

def main():
    '''
    '''
    find_missing_course_grades()
    find_missing_final_grades()
    find_missing_midterm_grades()

    

if __name__ == '__main__':
    main()
