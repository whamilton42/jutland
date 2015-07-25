(ns jutland.grid_valid-test
  (:require [clojure.test :refer :all]
            [jutland.grid_valid :refer :all]))

(deftest call_function
  (testing "not enough squares"
    (let [[grid] ["xxxxx xxxxxxx xxx xx"]]
      (is (= (jutland.grid_valid/call grid) false))
    )
  )

  (testing "not enough ship squares"
    (let [[grid] ["                                                                                xxxxx xxxxxxx xxx   "]]
      (is (= (jutland.grid_valid/call grid) false))
    )
  )

  (testing "not correct type of ships"
    (let [[grid] ["         xxx                                                                    xxxxx xxxx    xxx xx"]]
      (is (= (jutland.grid_valid/call grid) false))
    )
  )

  (testing "enough ship squares"
    (let [[grid] ["                                                                                xxxxx xxxxxxx xxx xx"]]
      (is (= (jutland.grid_valid/call grid) true))
    )
  )
)
