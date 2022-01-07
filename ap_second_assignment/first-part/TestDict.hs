import Dictionary
import Data.List (sort, (\\))
import GHC.Unicode (toLower)
import Text.Printf (printf)

-- Convert a string into a lower case string.
lower :: [Char] -> String
lower = map toLower

-- Two dictionaries have the same key set if their difference is empty.
-- Since the given dictionaries are well-formed, each set does not have a duplicate key
sameKeys :: Eq a => Dictionary a b1 -> Dictionary a b2 -> Bool
sameKeys d1 d2 = null (keys d1 \\ keys d2)

-- A "ciao" of a word is a string in lower case and alphabetical order
-- of th original one. We can see the ciaoWord as a function composition
-- of lower and sort functions.
ciaoWord :: String -> String
ciaoWord = lower . sort

-- 1. Define a function readDict that reads a text file whose name is passed as argument (as a string), 
-- and returns a new Dictionary after adding each word of the file using its ciao as key.
readDict :: FilePath -> IO (Dictionary String String)
readDict filename = do
    contents <- readFile filename
    -- Using foldl add the pair (ciao(word), word) inside a new empty dictionary.
    return (foldl (\acc word -> insert acc (ciaoWord word) word) (empty ()) (words contents))

-- 2. Define a function writeDict that given a dictionary and a file name, writes in the file, one per line, 
-- each key of the dictionary together with the length of the list of values associated with the key.
writeDict :: Dictionary String String -> FilePath -> IO ()
writeDict d1 filename = do
    -- For each key inside the dictonary, compute a string "k,length(d[k])" and merge the obtained
    -- strings into single one
    let content = concatMap (\k -> case Dictionary.lookup d1 k of
          Nothing -> ""
          Just values -> k ++ "," ++ show (length values) ++ "\n") (keys d1)
    -- Write the content inside the file
    writeFile filename content

-- 3. Define a function main :: IO() which does the following:
--  a.  Using readDict, from directory aux_files it loads files anagram.txt, anagram_s1.txt, anagram_s2.txt and margana2.txt in
--      corresponding dictionaries, that we call d1, d2, d3 and d4 respectively;
--  b. Exploiting also the functions imported from Dictionary.hs, it checks the following facts and prints a corresponding comment:
--      i. Dictionaries d1 and d4 are not equal, but they have the same keys (ignoring the ordering);
--      ii. Dictionary d1 is equal to the merge of dictionaries d2 and d3;
--  c. Finally, using writeDict it writes dictionaries d1 and d4 to files anag- out.txt and gana-out.txt, respectively.
main :: IO ()
main = do
    -- Initially, crate dictionaries reading words from the given auxiliar files.
    d1 <- readDict "./aux_files/anagram.txt"
    d2 <- readDict "./aux_files/anagram-s1.txt"
    d3 <- readDict "./aux_files/anagram-s2.txt"
    d4 <- readDict "./aux_files/margana2.txt"
    -- Prints: Dictionaries d1 and d4 are not equal, but they have the same keys (ignoring the ordering);
    printf "d1 /= d4: %s, same keys: %s\n" (show (d1 /= d4)) (show (sameKeys d1 d4))
    -- Prints: Dictionary d1 is equal to the merge of dictionaries d2 and d3;
    printf "d1 == (d2 ∪ d3): %s\n" (show (d1 == merge d2 d3))
    -- Finally, write d1 and d4 to the respective files.
    writeDict d1 "./anag-out.txt"
    writeDict d4 "./gana-out.txt"