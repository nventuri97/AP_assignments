module MultiSet ( MSet(..)
  , empty
  , add
  , occs
  , elems
  , subeq
  , union
  , mapMSet
  ) where
import Data.Maybe (fromMaybe)

-- | Multiset type: elements with multiplicities
data MSet a = MS [(a, Int)] deriving (Show)

{-| Ensure multiset is well-formed:
    - multiplicities > 0
    - no duplicate elements
-}
mergeDuplicates :: Eq a => [(a, Int)] -> [(a, Int)]
mergeDuplicates = foldr insert []
  where
    insert (x,n) acc
      | n <= 0    = acc
      | otherwise = addOrUpdate x n acc

    addOrUpdate x n [] = [(x,n)]
    addOrUpdate x n ((y,m):ys)
      | x == y    = (y,m+n):ys                  -- if the element exists, update its count
      | otherwise = (y,m):addOrUpdate x n ys    -- otherwise, keep looking

-- | Empty multiset constructor
empty :: MSet a
empty = MS []

{-| Add one occurrence of a value avoinding duplicates
    @xs@ is the MultiSet
    @v@ is the value to add
 -}
add :: Eq a => MSet a -> a -> MSet a
add (MS xs) v = MS (mergeDuplicates ((v,1):xs))

{-| Number of occurrences of a value
    @xs@ is the MultiSet
    @v@ is the value to check
    fromMaybe is used to handle the case where the element is not found
-}
occs :: Eq a => MSet a -> a -> Int
occs (MS xs) v = fromMaybe 0 (lookup v xs)

{-| Return all elements, each repeated by multiplicity
    @xs@ is the MultiSet
-}
elems :: MSet a -> [a]
elems (MS xs) = map fst xs

{-| True if mset1 ⊆ mset2 (multiplicity-wise)
    @mset1@ is the first MultiSet
    @mset2@ is the second MultiSet  
-}
subeq :: Eq a => MSet a -> MSet a -> Bool
subeq (MS xs) (MS ys) = all (\(v,n) -> occs (MS ys) v >= n) xs

{-| Union of multisets (summing multiplicities)
    @mset1@ is the first MultiSet
    @mset2@ is the second MultiSet

    This function concatenates the internal lists of both multisets
    and then merges duplicates to ensure the result is well-formed.
-}
union :: Eq a => MSet a -> MSet a -> MSet a
union (MS xs) (MS ys) = MS (mergeDuplicates (xs ++ ys))

{-| Equality instance for MultiSet: must have same elements with same multiplicities
    @mset1@ is the first MultiSet
    @mset2@ is the second MultiSet

    The function uses the subeq function to check if each multiset is a subset of the other,
    if both conditions hold, the multisets are equal.
-}
instance Eq a => Eq (MSet a) where
  (==) :: Eq a => MSet a -> MSet a -> Bool
  (MS xs) == (MS ys) = subeq (MS xs) (MS ys) && subeq (MS ys) (MS xs)

-- | Foldable instance for MultiSet: fold over elements ignoring multiplicity
instance Foldable MSet where
  foldr :: (a -> b -> b) -> b -> MSet a -> b
  foldr f z (MS xs) = foldr f z (elems (MS xs))

{-| Map a function over elements
    @f@ is the function to apply
    @mset@ is the MultiSet

    This function applies the function to each element of the multiset,
    then merges duplicates to ensure the result is well-formed.
-}
mapMSet :: (Eq b) => (a -> b) -> MSet a -> MSet b
mapMSet f (MS xs) =
  MS (mergeDuplicates [ (f v, n) | (v,n) <- xs ])

{- Why MSet cannot be a Functor:
   The Functor laws require fmap id = id and fmap (f . g) = fmap f . fmap g.

   When applying a function that maps two different elements to the same
   result, their multiplicities must be merged. This breaks the structure
   expected of Functor, because multiplicity merging is not transparent.
   Therefore, we cannot make MSet an instance of Functor.
-}
