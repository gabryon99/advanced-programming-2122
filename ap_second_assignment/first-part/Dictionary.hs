-- Export Dictionary module with all the desired functions.
module Dictionary (
    Dictionary,     
    Dictionary.empty,
    Dictionary.insert,
    Dictionary.lookup,
    Dictionary.keys,
    Dictionary.values,
    Dictionary.merge
) where

-- Declare a new type for Dictionary as told by the specification.
data Dictionary a b = Dict [(a, [b])] deriving (Show)

-- According to the specification: two dictionaries are equal if they contain the same keys, possibly not in the same order, 
-- and if for each key k the two sets of values associated with k are equal (that is, the corresponding lists contain 
-- the same values, disregarding the ordering and possible repetitions).
instance (Eq a, Eq b) => Eq (Dictionary a b) where
  (==) (Dict b) (Dict b') =
    let lb = length b in
    let lb' = length b' in
    -- If the keys sets have different length then the two dictionaries are different.
    lb == lb' && aux b (Dict b')
    where
        eq' [] _ = True
        eq' (h:t) l2 = elem h l2 && eq' t l2
        aux [] _ = True
        aux ((k, lv):t) d2 = case Dictionary.lookup d2 k of
            Nothing -> False -- the dictonary d2 doesn not contain the key k in d1, so the equality is false
            Just lv' -> eq' lv lv' && aux t d2 -- If the values contained in the other dictionary are not the same then return false


-- Return an empty Dictonary.
empty :: () -> Dictionary a b
empty () = Dict []

-- Insert an element of key k inside the dictonary. The function ensure the well-formed 
-- Dictionary property.
insert :: Eq a => Dictionary a b -> a -> b -> Dictionary a b
insert (Dict buckets) k v = Dict (insert' buckets)
    where
        insert' [] = [(k, [v])]
        insert' ((k', l):t) = if k == k' then (k, v:l):t else (k', l) : insert' t

-- Return a value of type Maybe [b], which is the list of elements
-- with key k, if such list exists in dict, and Nothing otherwise.
lookup :: Eq a => Dictionary a b -> a -> Maybe [b]
lookup (Dict buckets) k = lookup' buckets
    where
        lookup' [] = Nothing
        lookup' ((k', l):t) = if k == k' then Just l else lookup' t

-- Returns a list of keys return inside a Dictonary.
keys :: Dictionary a b -> [a]
keys (Dict buckets) = map fst buckets

-- Returns a single list containing all the values inside the dictionary.
values :: Dictionary a b -> [b]
values (Dict buckets) = concatMap snd buckets

-- Return the Dictionary obtained by merging the contents of the two dictionaries.
merge :: Eq a => Dictionary a b -> Dictionary a b -> Dictionary a b
merge (Dict b) (Dict b') =
    -- Flat the buckets dictionary lists into and concatenate them.
    let flat = (flat' b [] ++ flat' b' []) in
    -- Using foldl, create a new empty dictionary from the couple list.
    foldl (\acc (k, v) -> insert acc k v) (empty ()) flat
    where
        -- For each pair inside the bucket list, concatenate to an accumulator
        -- a list made of tuple (k, element) using the zip function.
        -- The final list will be something like: [(k1, e10), (k1, e11), ..., (kn, emn)]
        flat' [] acc = acc
        flat' ((k, elements):t) acc = flat' t (zip (repeat k) elements ++ acc)
    