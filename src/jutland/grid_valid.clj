(ns jutland.grid_valid)

(defn- squares [grid]
  (count grid))

(defn- ship-squares [grid]
  (count (re-seq #"x" grid)))

(defn- empty-squares [grid]
  (count (re-seq #" " grid)))

(defn rows [grid]
  (map clojure.string/join
    (partition 10 grid)
  )
)

(defn- columns [grid]
  (map
    (fn [column]
      (map
        (fn [row]
          (nth (nth (rows grid) row) column)
        )
        (range 0 10)
      )
    )
    (range 0 10)
  )
)

(defn line-without-five [line column-num]
  (let [[preceeding, five, following]
    [
      (subs line 0 column-num)
      (subs line column-num (+ column-num 5))
      (subs line (+ column-num 5) (min (+ column-num 10) 10))
    ]]

    (if (= five "xxxxx")
      (str preceeding "     " following)
    )
  )
)

(defn line-without-fives [line]
  (filter
    (complement nil?)
    (map
      (fn [column-num] (line-without-five line column-num))
      (range 0 6)
    )
  )
)

(defn grids-without-five [grid]
  (flatten
    (clojure.string/join
      (map
        line-without-fives
        (rows grid)
      )
    )
  )
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
