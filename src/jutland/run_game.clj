(ns jutland.run_game
  (:require [jutland.grid_valid :as grid_valid])
  (:require [jutland.grid_after_shot :as grid_after_shot])
  (:require [jutland.program_runner :as program_runner])
)

(defn- print-grid [grid]
  (doseq [row (grid_valid/rows grid)]
    (println (str "|" row "|"))
  )
)

(defn- get-grid [opts program]
  (println (str "Fetching " program " grid.."))
  (let [[grid] [(program_runner/call (:folder opts) (program opts) "grid")]]
    (print-grid grid)
    (println
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


(defn call [opts]
  (let [[grids]
        [{
          :program-one (get-grid opts :program-one)
          :program-two (get-grid opts :program-two)
        }]
    ]

    (loop [[grids program]
           [grids :program-one]]

      (println (str program " takes its turn.."))

      (let [[shot] [(get-shot opts program)]]
        (println (str "Shoot! " shot))

        (let [[new-grid]
              [(grid_after_shot/call ((opponent program) grids) shot)]]
          (print-grid new-grid)

          (if (game-over? new-grid)
            (println "yay")
            (recur [
              (merge grids {(opponent program) new-grid})
              (opponent program)
            ])
          )
        )
      )
    )
  )
)
