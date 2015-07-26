(ns jutland.grid_valid-test
  (:require [clojure.test :refer :all]
            [jutland.grid_valid :refer :all]))

(deftest test-columns
  (testing "columns"
    (let [[grid] ["C         C         C         C         C                                                           "]]
      (is (= (jutland.grid_valid/columns grid) ["CCCCC     " "          " "          " "          " "          " "          " "          " "          " "          " "          "]))
    )
  )
)

(deftest test-ship-in-line?
  (let [[ship] [{:name "Aircraft Carrier", :symbol "C" :length 5}]]
    (testing "ship continuous in line"
      (let [[line] ["CCCCC      "]]
        (is (= (jutland.grid_valid/ship-in-line? line ship) true))
      )
    )

    (testing "ship not non-continuous in line"
      (let [[line] ["CCC C     "]]
        (is (= (jutland.grid_valid/ship-in-line? line ship) false))
      )
    )
  )
)

(deftest test-ship-valid?
  (let [[ship] [{:name "Aircraft Carrier", :symbol "C" :length 5}]]
    (testing "ship continuous in row"
      (let [[grid] [" CCCCC     "]]
        (is (= (jutland.grid_valid/ship-valid? grid ship) true))
      )
    )

    (testing "ship not non-continuous in row"
      (let [[grid] [" CCCC C    "]]
        (is (= (jutland.grid_valid/ship-valid? grid ship) false))
      )
    )

    (testing "ship continuous in column"
      (let [[grid] ["C         C         C         C         C         "]]
        (is (= (jutland.grid_valid/ship-valid? grid ship) true))
      )
    )

    (testing "ship not non-continuous in column"
      (let [[grid] ["C         C         C         C                    C         "]]
        (is (= (jutland.grid_valid/ship-valid? grid ship) false))
      )
    )
  )
)

(deftest call_function
  (testing "not enough squares"
    (let [[grid] ["CCCCC BBBB SSS DDD PP"]]
      (is (= (jutland.grid_valid/call grid) false))
    )
  )

  (testing "missing carrier"
    (let [[grid] ["      BBBB SSS DDD PP"]]
      (is (= (jutland.grid_valid/call grid) false))
    )
  )

  (testing "invalid carrier"
    (let [[grid] ["CCCC      BBBB SSS DDD PP C"]]
      (is (= (jutland.grid_valid/call grid) false))
    )
  )

  (testing "complex valid grid"
    (let [[grid] [" D  BBBB   D         DC         C   P     C   P     C  SSS    C                                     "]]
      (is (= (jutland.grid_valid/call grid) true))
    )
  )

  (testing "complex slightly invalid grid"
    (let [[grid] [" D  BBBB   D         DC         C   P     C   P     C   SS    C   S                                 "]]
      (is (= (jutland.grid_valid/call grid) false))
    )
  )
)
