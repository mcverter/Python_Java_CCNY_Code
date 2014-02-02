import random


def diagonalize(two_d, columns, rows):
    last_sum = columns+rows-2
    one_d_index = 0
    one_d_diagonalized=[]
    if (columns > rows):
        max_dimension = columns
        min_dimension = rows
    else: 
        max_dimension = rows
        min_dimension = columns

    for sum_of_indices in range(last_sum+1):
    
        if sum_of_indices < min_dimension:
            diag_length=sum_of_indices+1
        elif sum_of_indices >= max_dimension:
            diag_length=(min_dimension+max_dimension) - (sum_of_indices +1)
        else:
            diag_length=min_dimension
    
# go up right

        if sum_of_indices % 2 == 0:
            if (sum_of_indices < rows):
                diagonal_row=sum_of_indices
                diagonal_column=0
                
                for x in range(diag_length):
                    one_d_diagonalized.append(two_d[diagonal_row][diagonal_column])
                    one_d_index+=1 
                    diagonal_column+=1 
                    diagonal_row-=1


            else:
                diagonal_row=rows-1
                diagonal_column=sum_of_indices-diagonal_row

                for x in range(diag_length):
                    one_d_diagonalized.append(two_d[diagonal_row][diagonal_column])
                    one_d_index+=1 
                    diagonal_column+=1 
                    diagonal_row-=1
        #go down left
        else:
            if (sum_of_indices<columns):
                diagonal_row=0
                diagonal_column=sum_of_indices

                for x in range(diag_length):
                    one_d_diagonalized.append(two_d[diagonal_row][diagonal_column])
                    one_d_index+=1 
                    diagonal_column-=1 
                    diagonal_row+=1

            else:
                diagonal_column=columns-1
                diagonal_row=sum_of_indices-diagonal_column

                for x in range(diag_length):
                    one_d_diagonalized.append(two_d[diagonal_row][diagonal_column])
                    one_d_index+=1 
                    diagonal_column-=1 
                    diagonal_row+=1
    return one_d_diagonalized


def initialize_array(columns, rows):
    arr = [[random.randint(10,99) for c in range(columns)] for r in range(rows)]
    return arr

def main():
    columns = int(raw_input('Enter number of columns:'))
    rows = int(raw_input('Enter number of rows:'))
    two_d = initialize_array(columns, rows)
    print "Original Array is:"
    for r in range(rows):
        print two_d[r]
    diag = diagonalize(two_d, columns, rows)
    print "Diagonalized is:"
    print diag

if __name__ == '__main__':
    main()
