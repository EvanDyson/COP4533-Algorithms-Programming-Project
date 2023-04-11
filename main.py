import random

f = open("matrix.txt", "w")

stocks = 5
days = 5

for m in range(stocks):
    f.write('{ ')
    for n in range(days):
        if (n == days - 1):
            f.write(str(random.randrange(20)))
        else:
            f.write(str(random.randrange(20)))
            f.write(', ')
    else:
        if (m == stocks - 1):
            f.write(' }\n')
        else :
            f.write(' }, \n')