(ns jutland.core
  (:require [jutland.program_runner :as program_runner])
  (:require [jutland.opts_parser :as opts_parser])
  (:require [jutland.grid_valid :as grid_valid])
  (require [clojure.tools.cli :refer [cli]])
  (:gen-class))

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

    (let [[grid_one grid_two]
        [
          (program_runner/call (:folder opts) (:program-one opts) "--command=grid")
          (program_runner/call (:folder opts) (:program-two opts) "--command=grid")
        ]
      ]

      (println grid_one)
      (println (grid_valid/call grid_one))

      (println grid_two)
      (println (grid_valid/call grid_two))
    )

    (shutdown-agents)
  )
)