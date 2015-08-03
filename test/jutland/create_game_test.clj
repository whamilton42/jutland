(ns jutland.create_game-test
  (:require
    [clojure.test :refer :all]
    [jutland.create_game :refer :all]
    [jutland.game_repository]
  )
)

(deftest test-call
  (testing "returns UUID of game now in DB"
    (def created-game-uuid (jutland.create_game/call "Jellicoe" "Scheer"))
    (def saved-game (jutland.game_repository/find-by-uuid created-game-uuid))

    (is (= "Jellicoe" (:game/program_1_name saved-game)))
    (is (= "Scheer" (:game/program_2_name saved-game)))
  )
)

