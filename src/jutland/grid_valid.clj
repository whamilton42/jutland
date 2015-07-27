(ns jutland.grid_valid)

(defn- squares [grid]
  (count grid))

(defn- carrier-squares [grid]
  (count (re-seq #"C" grid)))

(defn- battleship-squares [grid]
  (count (re-seq #"B" grid)))

(defn- submarine-squares [grid]
  (count (re-seq #"S" grid)))

(defn- destroyer-squares [grid]
  (count (re-seq #"D" grid)))

(defn- patrol-boat-squares [grid]
  (count (re-seq #"P" grid)))

(defn- empty-squares [grid]
  (count (re-seq #" " grid)))


(defn rows [grid]
  (map clojure.string/join
    (partition 10 grid)))

(defn columns [grid]
  (map
    (fn [column_num]
      (clojure.string/join
        (map
          (fn [row] (nth row column_num))
          (rows grid)
        )
      )
    )
    (range 0 10)
  )
)

(def ships
  [
    {:name "Aircraft Carrier", :symbol "C" :length 5}
    {:name "Battleship", :symbol "B" :length 4}
    {:name "Submarine", :symbol "S" :length 3}
    {:name "Destroyer", :symbol "D" :length 3}
    {:name "Patrol Boat", :symbol "P" :length 2}
  ]
)

(defn- find-ship [key value]
  (first
    (filter
      (fn [ship] (= value (key ship)))
      ships
    )
  )
)

(defn- ship-regex [ship]
  (re-pattern (str (:symbol ship) "{" (:length ship) "}"))
)

(defn ship-in-line? [line ship]
  (boolean (re-find (ship-regex ship) line))
)

(defn ship-valid? [grid ship]
  (boolean
    (some
      true?
      (map
        (fn [line] (ship-in-line? line ship))
        (concat (rows grid) (columns grid))
      )
    )
  )
)

(defn all-ships-valid? [grid]
  (every?
    (fn [ship] (ship-valid? grid ship))
    ships
  )
)


(defn call [grid]
  (every?
    true?
    [
      (= (squares grid) 100)
      (= (empty-squares grid) 83)
      (all-ships-valid? grid)
    ]
  )
)
