(ns jutland.create_game-test
  (use [datomic.api :only [db entity q] :as datomic])
  (:require [clojure.test :refer :all]
            [jutland.create_game :refer :all])
)

(defn- find-game [uuid]
  (def results
    (datomic/q
      '[:find ?e :where [?e :game/uuid ?uuid]]
      (datomic/db conn)
      uuid
    )
  )
  (def id (ffirst results))
  (def game (-> conn db (datomic/entity id)))
  game
)

(deftest test-call
  (testing "returns UUID of game now in DB"
    (def created-game-uuid (jutland.create_game/call "Banana" "Orange"))
    (def saved-game (find-game created-game-uuid))

    (is (= "Banana" (:game/program_1_name saved-game)))
    (is (= "Orange" (:game/program_2_name saved-game)))
  )
)

