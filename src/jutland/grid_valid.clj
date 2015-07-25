(ns jutland.grid_valid)

(defn- squares [grid]
  (count grid)
)

(defn- ship-squares [grid]
  (count (re-seq #"x" grid))
)

(defn- empty-squares [grid]
  (count (re-seq #" " grid))
)


(defn call [grid]
  (every?
    true?
    [
      (= (squares grid) 100)
      (= (ship-squares grid) 17)
      (= (empty-squares grid) 83)
    ]
  )
)
