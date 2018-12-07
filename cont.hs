-- Rebekah Doochin and Ellen Waller
-- Lab 8 part 2
-- 

-- 3. Validating Credit Card Numbers 


-- Exercise 1 -----------------------------------------

-- Get the last digit from a number
lastDigit :: Integer -> Integer
lastDigit x = x `mod` 10

-- Drop the last digit from a number
dropLastDigit :: Integer -> Integer
dropLastDigit y = if y < 10 
then 0
else (y - lastDigit y) `div` 10


-- Exercise 2 -----------------------------------------

toRevDigits :: Integer -> [Integer] 
toRevDigits x = if x == 0 
then []
else let y = dropLastDigit x in lastDigit x : toRevDigits y


-- Exercise 3 -----------------------------------------

-- Double every second number in a list starting on the left.
doubleEveryOther :: [Integer] -> [Integer]
doubleEveryOther [] = []
doubleEveryOther [x] = [2*x]
doubleEveryOther (x:y:xs) = x:(2*y):(doubleEveryOther xs)


-- Exercise 4 -----------------------------------------

-- Calculate the sum of all the digits in every Integer.
sumDigits :: [Integer] -> Integer
sumDigits [] = 0
sumDigits (x:xs) = if x<10
then x + sumDigits xs 
else 1 + x`mod`10 + sumDigits xs



-- Exercise 5 -----------------------------------------

-- Validate a credit card number using the above functions.
luhn :: Integer -> Bool
luhn x = let y = sumDigits (doubleEveryOther (toRevDigits x)) `mod` 10 in if y == 0
then True
else False




