-- Rebekah Doochin and Ellen Waller
-- Lab 8 part 1
-- 

-- 1. List Comprehensions 

-- a)
main = print outfits 
a = ["jeans", "khakis"]
b = ["white shirt", "grey turtleneck", "pink polo", "green hoodie"]
c = ["brogues", "converse", "sandals"]
combo x y z = [(x, y, z)| x <- a, y <- b, z <- c] 

outfits = combo a b c

-- You increase the maximum number of outfits when you either add 
-- one item to the pants list and one item to the shoes list, or 
-- when you add two items to the pants list.


-- b)
d = ["paisley scarf", "knitloop"]

outfits2 = [(x, y, z, w)| x <- a, y <- b, z <- c, w <- d]

-- outfits2 generates 48 outfit options


-- c)
p = ["Mr.", "Ms.", "Mrs.", "Dr.", "Prof.", "Rev."]
honorifics = [h | h <- p] ++ [h ++ " and " ++ g | h <- p, g <-p]


-- d)
bet = ['A'..'Z']
nato = ["Alpha","Bravo","Charlie","Delta","Echo","Foxtrot","Golf","Hotel","India","Juliett","Kilo","Lima","Mike","November","Oscar","Papa","Quebec","Romeo","Sierra","Tango","Uniform","Victor","Whiskey","Xray","Yankee","Zulu"]
natobet = zip bet nato

translate str = [snd y | x <- str, y <- natobet, x == fst y]
