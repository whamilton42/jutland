(ns jutland.create_game
  (use [datomic.api :only [connect db squuid transact] :as datomic])
)

(def uri "datomic:free://localhost:4334/jutland")
(def conn (datomic/connect uri))
(defn- generate-uuid []
  (str (datomic/squuid))
)

(defn call [program_1_name program_2_name]
  (let [[game-uuid] [(generate-uuid)]]
    @(datomic/transact conn [
      {:db/id #db/id[:db.part/user]
        :game/uuid game-uuid
        :game/program_1_name program_1_name
      }
      {:db/id #db/id[:db.part/user]
        :game/uuid game-uuid
        :game/program_2_name program_2_name
      }
    ])

    game-uuid
  )
)
