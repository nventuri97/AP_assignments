module Main where

import MultiSet
import Data.Char (toLower, isAlpha)
import Data.List (sort)

-- | Compute ciao of a string: lowercase, keep only letters, sort
ciao :: String -> String
ciao str = sort [ toLower c | c <- str, isAlpha c ]

-- | Read a file into a multiset of ciao strings
readMSet :: FilePath -> IO (MSet String)
readMSet fname = do
  contents <- readFile fname
  let ws = words contents
  return (foldl add empty (map ciao ws))

-- | Write multiset to file, one line per element with multiplicity
writeMSet :: Show a => MSet a -> FilePath -> IO ()
writeMSet (MS xs) fname = do
  let linesOut = [ show v ++ " - " ++ show n | (v,n) <- xs ]
  writeFile fname (unlines linesOut)

-- | Main test
main :: IO ()
main = do
  -- load multisets
  m1 <- readMSet "../aux_files/anagram.txt"
  m2 <- readMSet "../aux_files/anagram-s1.txt"
  m3 <- readMSet "../aux_files/anagram-s2.txt"
  m4 <- readMSet "../aux_files/margana2.txt"

  -- check conditions
  putStrLn $ "i.a Multisets m1 and m4 are not equal: " ++ show (m1 /= m4)
  putStrLn $ "i.b Multisets m1 and m4 have the same elements: " ++ show (elems m1 == elems m4)
  putStrLn $ "ii. Multiset m1 is equal to the union of multisets m2 and m3: " ++ show (m1 == m2 `union` m3)

  -- write outputs
  writeMSet m1 "output/anag-out.txt"
  writeMSet m4 "output/gana-out.txt"
