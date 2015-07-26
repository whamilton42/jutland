(ns jutland.grid_valid-test
  (:require [clojure.test :refer :all]
            [jutland.grid_valid :refer :all]))

; (deftest test-rows
;   (testing "full example"
;     (let [[grid] ["                                                                                xxxxx xxxxxxx xxx xx"]]
;       (is
;         (=
;           [
;             "          "
;             "          "
;             "          "
;             "          "
;             "          "
;             "          "
;             "          "
;             "          "
;             "xxxxx xxxx"
;             "xxx xxx xx"
;           ]
;         (jutland.grid_valid/rows grid))
;       )
;     )
;   )
; )

; (deftest line_without_five
;   (testing "full example"
;     (let [[line] ["    xxxxxx"]]
;       (is (= "         x" (jutland.grid_valid/line-without-five line 4)))
;       (is (= "    x     " (jutland.grid_valid/line-without-five line 5)))
;     )
;   )
; )

; (deftest line_without_fives
;   (testing "full example"
;     (let [[line] ["    xxxxxx"]]
;       (is
;         (=
;           [
;             "         x"
;             "    x     "
;           ]
;         (jutland.grid_valid/line-without-fives line))
;       )
;     )
;   )
; )

(deftest grids_without_5
  (testing "full example"
    (let [[grid] [
      ; "                                                                                xxxxx xxxxxxx xxx xx"
      "   xxxxx  "
      ]]
      (is
        (=
          [
            "          "
            ; "                                                                                      xxxxxxx xxx xx"
            ; "                                                                                xxxxx      xx xxx xx"
            ; "                                                                                xxxxx x     x xxx xx"
            ; "                                                                                xxxxx xx      xxx xx"
          ]
        (jutland.grid_valid/grids-without-five grid))
      )
    )
  )
)

; (deftest call_function
;   (testing "not enough squares"
;     (let [[grid] ["xxxxx xxxxxxx xxx xx"]]
;       (is (= (jutland.grid_valid/call grid) false))
;     )
;   )

;   (testing "not enough ship squares"
;     (let [[grid] ["                                                                                xxxxx xxxxxxx xxx   "]]
;       (is (= (jutland.grid_valid/call grid) false))
;     )
;   )

  ; (testing "5-ship not present"
  ;   (let [[grid] ["xxxx                                                                            xxxx     xxxx xxx xx"]]
  ;     (is (= (jutland.grid_valid/call grid) false))
  ;   )
  ; )

;   (testing "enough ship squares"
;     (let [[grid] ["                                                                                xxxxx xxxxxxx xxx xx"]]
;       (is (= (jutland.grid_valid/call grid) true))
;     )
;   )
; )
