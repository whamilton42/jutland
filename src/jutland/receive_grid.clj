(ns jutland.receive_grid
  (:require
    [jutland.game_repository]
    [jutland.grid_valid]
  )
  (use [datomic.api :only [connect db squuid transact] :as datomic])
)

(def uri "datomic:free://localhost:4334/jutland")
(def conn (datomic/connect uri))

(defn- grid-field-to-update [game program-name]
  (if (= (:game/program_1_name game) program-name)
    :game/program_1_grid
    :game/program_2_grid
  )
)

(defn- save-grid [game program-name grid]
  @(datomic/transact conn [
    {:db/id #db/id[:db.part/user]
      :game/uuid (:game/uuid game)
      (grid-field-to-update game program-name) grid
    }
  ])
)

(defn call [game-uuid program-name grid]
  (if (jutland.grid_valid/call grid)
    (let [[game] [(jutland.game_repository/find-by-uuid game-uuid)]]
      (save-grid game program-name grid)
    )
    false
  )
)
