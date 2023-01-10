import csv
import names
import random

num = 0
data = []
database = "Tarskereso_db.csv"

while( num < 1 or num > 1000):
    try:
        num = int(input("Number of users to generate: "))
    except ValueError:
        print("Invalid input. Please enter an integer.")

for i in range(num):
    g = "male"
    g_hu = "ferfi"
    like = False
    
    like_rand = random.randrange(0,4)
    g_rand = random.randrange(0,2)
    
    if g_rand is 0:
        g = "female"
        g_hu = "no"
        
    if like_rand is 0:
        like = True
    
    name = names.get_full_name(gender=g)
    age = random.randrange(18,100)
    a_data = [i, g_hu, name, age, "bio placeholder", like]
    data.append(a_data)

with open(database, 'w', newline='') as csvfile:
    csvwriter = csv.writer(csvfile, quoting=csv.QUOTE_ALL)
    csvwriter.writerows(data)