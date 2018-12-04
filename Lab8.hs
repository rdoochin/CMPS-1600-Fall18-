-- Rebekah Doochin and Ellen Waller
-- Lab 8 part 1
-- 

-- 1. List Comprehensions 

-- a)
n = isEven 7
main = print n 
a = ["jeans", "khakis"]
b = ["white shirt", "grey turtleneck", "pink polo", "green hoodie"]
c = ["brogues", "converse", "sandals"]
combo x y z = [(x, y, z)| x <- a, y <- b, z <- c] 

outf = combo a b c

-- You increase the maximum number of outfits when you either add 
-- one item to the pants list and one item to the shoes list, or 
-- when you add two items to the pants list.


-- b)
d = ["paisley scarf", "knitloop"]

outfits2 = [(x, y, z, w)| x <- a, y <- b, z <- c, w <- d]

-- outfits2 generates 48 outfit options


-- c)
p = ["Mr.", "Ms.", "Mrs.", "Dr.", "Prof.", "Rev."]
honor = [h | h <- p] ++ [h ++ " and " ++ g | h <- p, g <-p]


-- d)
bet = ['A'..'Z']
nato1 = ["Alpha","Bravo","Charlie","Delta","Echo","Foxtrot","Golf","Hotel","India","Juliett","Kilo","Lima","Mike","November","Oscar","Papa","Quebec","Romeo","Sierra","Tango","Uniform","Victor","Whiskey","Xray","Yankee","Zulu"]
natobet = zip bet nato1

translate str = [snd y | x <- str, y <- natobet, x == fst y]


-- 2. Functions 

-- a)
remainder :: Int -> Int -> Int
remainder j k = j `mod` k 

-- b)
isEven :: Int -> Bool
isEven a = if a `mod` 2 == 0
then True 
else False 

-- c)
merge :: [Int] -> [Int] -> [Int]
merge (x:xs) (y:ys) = if x < y
then x:(merge xs (y:ys))
else y:(merge (x:xs) ys)
merge [] xs = xs
merge xs [] = xs
g = [1,5,7,8,13]
f = [3,9,22]
r = merge g f


-- d)
removeMultiple:: [Int] -> Int -> [Int]
removeMultiple lst i = [x | x <- lst, x `mod` i /= 0] 


-- 3. List Functions 

-- a) 
-- what we did for combo in part 1
outfits x y z = [(x, y, z)| x <- a, y <- b, z <- c] 

-- b)
honorifics lst = [h | h <- lst] ++ [h ++ " and " ++ g | h <- lst, g <-lst]

-- c)
nato wdr = [snd y | x <- wdr, y <- natobet, x == fst y]

