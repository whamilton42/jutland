(ns jutland.integration-test
  (:require [clojure.test :refer :all]
            [jutland.create_game])
)

(deftest test-integration
  (testing "the whole thing works"
    (def new-game-uuid (jutland.create_game/call "Jellico" "Scheer"))
    ; ? message about new game published
    ; p1 submits grid
    ; p2 submits grid
    ; ? message about game ready published
    ; shots fired
    ; chaos
  )
)

