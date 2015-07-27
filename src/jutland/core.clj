(ns jutland.core
  (:require [jutland.program_runner :as program_runner])
  (:require [jutland.opts_parser :as opts_parser])
  (:require [jutland.grid_valid :as grid_valid])
  (:require [jutland.grid_after_shot :as grid_after_shot])
  (require [clojure.tools.cli :refer [cli]])
  (:gen-class))

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

(defn -main
  "Runs battles between two Battleship programs."
  [& args]
  (let
    [[opts args banner] (cli args
      ["-p1" "--program-one" "First program to battle with"]
      ["-p2" "--program-two" "Second program to battle with"]
      ["-f" "--folder" "Folder where programs reside"]
    )]

    (when (opts_parser/missing-required? opts)
      (println banner)
      (System/exit 1))

    (let [[grid-one grid-two]
        [
          (get-grid opts :program-one)
          (get-grid opts :program-two)
        ]
      ]

      (loop [[grid     shot]
             [grid-one (get-shot opts :program-one)]]

        (println (str "Shoot! " shot))

        (let [[new-grid] [(grid_after_shot/call grid shot)]]
          (print-grid new-grid)

          (if (game-over? new-grid)
            (println "yay")
            (recur [new-grid (get-shot opts :program-one)])
          )
        )
      )
    )

    (shutdown-agents)
  )
)
