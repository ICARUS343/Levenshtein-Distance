# Levenshtein-Distance
Note that my program calculates the levenshtein distance between the query word and every single word in the dictionary. Since the length of the dictionary is constant it does not affect the program's Big O runtime. The program total runtime is O(ab) where a is the length of the query word and b is the average length of a word from the dictionary. 

Another solution would be to come up with all words which are one edit away from the query word. This would take O(am) time where a is the lenth of the query word and m is the length of the english alphabet. If we stored the dictionary in a hashtable, we could look up in constant time whether each of the words we came up with was in the dictionary and the program's total runtime would be O(am).
