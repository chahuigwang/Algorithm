letters = input()

croatia_letters = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']
for croatia_letter in croatia_letters:
    letters = letters.replace(croatia_letter, '*')
    
print(len(letters))