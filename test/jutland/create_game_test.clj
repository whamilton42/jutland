(ns jutland.create_game-test
  (use [datomic.api :only [db entity q] :as datomic])
  (:require [clojure.test :refer :all]
            [jutland.create_game :refer :all])
)

(defn- find-game [uuid]
  (def results
    (datomic/q
      '[
        :find ?e
        :in $ ?uuid
        :where [?e :game/uuid ?uuid]
      ]
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
    (def created-game-uuid (jutland.create_game/call "Jellicoe" "Scheer"))
    (def saved-game (find-game created-game-uuid))

    (is (= "Jellicoe" (:game/program_1_name saved-game)))
    (is (= "Scheer" (:game/program_2_name saved-game)))
  )
)

