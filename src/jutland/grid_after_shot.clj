(ns jutland.grid_after_shot
  (:require [jutland.grid_valid :as grid_valid])
)

(defn column [square]
  (dec
    (Integer.
      (clojure.string/replace-first square #"." "")
    )
  )
)

(defn row [square]
  (- (int (first square)) 65)
)

(defn grid-index [square]
  (+
    (* 10 (row square))
    (column square)
  )
)

(defn value-of-square [grid square]
  (get
    (get (grid_valid/rows grid) (row square))
    (column square)
  )
)

(defn replace-at-index [string index char]
  (str
    (subs string 0 index)
    char
    (subs string (inc index))
  )
)

(defn downcase-at-index [string index]
  (replace-at-index
    string
    index
    (clojure.string/lower-case (get string index))
  )
)

(defn- downcase-at-square [grid square]
  (downcase-at-index grid (grid-index square))
)


(defn call [grid square]
  (if (= (value-of-square grid square) \space)
    grid
    (downcase-at-square grid square)
  )
)
