(ns jutland.core
  (require [clojure.tools.cli :refer [cli]])
  (:require [jutland.opts_parser :as opts_parser])
  (:require [jutland.run_game :as run_game])
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
      (System/exit 1)
    )

    (run_game/call opts)
    (shutdown-agents)
  )
)
