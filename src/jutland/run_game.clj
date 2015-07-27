(ns jutland.run_game
  (:require [jutland.grid_valid :as grid_valid])
  (:require [jutland.grid_after_shot :as grid_after_shot])
  (:require [jutland.program_runner :as program_runner])
)

(defn- output [opts string]
  (if-not (:quiet opts)
    (println string)
  )
)

(defn- printable-grid [grid]
  (clojure.string/join
    "\n"
    (map
      (fn [row] (str "|" row "|"))
      (grid_valid/rows grid)
    )
  )
)

(defn- get-grid [opts program]
  (output opts (str "Fetching " program " grid.."))
  (let [[grid] [(program_runner/call (:folder opts) (program opts) "grid")]]
    (output opts (printable-grid grid))
    (output opts
      (if (grid_valid/call grid)
        "valid!"
        "invalid!"
      )
    )
    grid
  )
)

(defn- get-shot [opts program]
  (program_runner/call (:folder opts) (program opts) "shoot")
)

(defn- game-over? [grid]
  (boolean (re-find #"[[a-z]\s]{100}" grid))
)

(defn- opponent [program]
  (case program
    :program-one :program-two
    :program-two :program-one
  )
)

(defn- grids [opts]
  {
    :program-one (get-grid opts :program-one)
    :program-two (get-grid opts :program-two)
  }
)


(defn call [opts]
  (loop [[grids        program      turn]
         [(grids opts) :program-one 1]]

    (output opts (str program " takes its turn.."))

    (let [[shot] [(get-shot opts program)]]
      (output opts (str "Shoot! " shot))

      (let [[new-grid]
            [(grid_after_shot/call ((opponent program) grids) shot)]]
        (output opts (printable-grid new-grid))

        (if (game-over? new-grid)
          (output opts (str "Victory for " program " in " turn " turns!"))
          (recur [
            (merge grids {(opponent program) new-grid})
            (opponent program)
            (inc turn)
          ])
        )
      )
    )
  )
)
