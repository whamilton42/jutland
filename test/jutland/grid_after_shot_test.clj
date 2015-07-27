(ns jutland.grid_after_shot-test
  (:require [clojure.test :refer :all]
            [jutland.grid_after_shot :refer :all]))

(deftest test-column
  (testing "."
    (is (= 1 (jutland.grid_after_shot/row "B10")))
  )
)

(deftest test-row
  (testing "."
    (is (= 9 (jutland.grid_after_shot/column "B10")))
  )
)

(deftest test-grid-index
  (testing "."
    (is (= 19 (jutland.grid_after_shot/grid-index "B10")))
  )
)

(deftest test-value-of-square
  (testing "."
    (is (= \B (jutland.grid_after_shot/value-of-square " B        " "A2")))
  )
)

(deftest test-replace-at-index
  (testing "."
    (is (= "xxBzz" (jutland.grid_after_shot/replace-at-index "xxYzz" 2 "B")))
  )
)

(deftest test-downcase-at-index
  (testing "."
    (is (= "xxyZZ" (jutland.grid_after_shot/downcase-at-index "xxYZZ" 2)))
  )
)

(deftest test-call
  (let [[grid] [" CCCCC    "]]
    (testing "miss"
      (is (= grid (jutland.grid_after_shot/call grid "A1")))
    )

    (testing "hit"
      (is (= " cCCCC    " (jutland.grid_after_shot/call grid "A2")))
    )
  )
)
