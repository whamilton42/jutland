(ns jutland.receive_grid-test
  (:require
    [clojure.test :refer :all]
    [jutland.receive_grid :refer :all]
    [jutland.create_game]
  )
)

(defn- create-game []
  (jutland.create_game/call "Jellicoe" "Scheer")
)

(deftest test-call
  (testing "valid grid"
    (testing "saved in DB"
      (let [[grid game-uuid] [" D  BBBB   D         DC         C   P     C   P     C  SSS    C                                     " (create-game)]]

        (jutland.receive_grid/call game-uuid "Jellicoe" grid)

        (let [[game] [(jutland.game_repository/find-by-uuid game-uuid)]]
          (is (= (:game/program_1_grid game) grid))
        )
      )
    )

    (testing "invalid program name")
  )

  (testing "invalid grid"
    (let [[grid] ["lol"]]
      (testing "returns false"
        (is (= false (jutland.receive_grid/call (:game/uuid (create-game)) "Jellicoe" grid)))
      )
    )
  )
)
