(ns jutland.game_repository
  (use [datomic.api :only [db entity q] :as datomic])
)

(def uri "datomic:free://localhost:4334/jutland")
(def conn (datomic/connect uri))

(defn find-by-uuid [uuid]
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
