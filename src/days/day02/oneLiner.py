p2 = sum([3*(ord(i[2])-88) + (ord(i[0])-65 + ord(i[2]) -
                              88 + 2) % 3 + 1 for i in open("input.txt", "r")])
p1 = sum([3*((4+(ord(i[2])-88)-(ord(i[0])-65)) % 3)+ord(i[2])-87 for i in open("input.txt", "r")])


print(p1, p2)
