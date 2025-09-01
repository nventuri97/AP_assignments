module MultiSet where
import Data.Maybe (fromMaybe)

-- | Multiset type: elements with multiplicities
data MSet a = MS [(a, Int)] deriving (Show)

-- | Ensure multiset is well-formed:
--   - multiplicities > 0
--   - no duplicate elements
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

-- | Empty multiset
empty :: MSet a
empty = MS []

-- | Add one occurrence of a value avoinding duplicates
add :: Eq a => MSet a -> a -> MSet a
add (MS xs) v = MS (mergeDuplicates ((v,1):xs))

-- | Number of occurrences of a value
occs :: Eq a => MSet a -> a -> Int
occs (MS xs) v = fromMaybe 0 (lookup v xs)

-- | Return all elements, each repeated by multiplicity
elems :: MSet a -> [a]
elems (MS xs) = map fst xs

-- | True if mset1 ⊆ mset2 (multiplicity-wise)
subeq :: Eq a => MSet a -> MSet a -> Bool
subeq (MS xs) (MS ys) = all (\(v,n) -> occs (MS ys) v >= n) xs

-- | Union of multisets (summing multiplicities)
union :: Eq a => MSet a -> MSet a -> MSet a
union (MS xs) (MS ys) = MS (mergeDuplicates (xs ++ ys))

-- | Equality of multisets: same elements, same multiplicities
instance Eq a => Eq (MSet a) where
  (MS xs) == (MS ys) =
    let nx = mergeDuplicates xs
        ny = mergeDuplicates ys
    in all (\(v,n) -> occs (MS ny) v == n) nx
       && all (\(v,n) -> occs (MS nx) v == n) ny

-- | Foldable instance: fold over elements ignoring multiplicity
instance Foldable MSet where
  foldr f z (MS xs) = foldr f z (elems (MS xs))

-- | Map a function over elements
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
