(ns jutland.core
  (:require [jutland.program_runner :as program_runner])
  (:gen-class))

(def program_names
  [
    (System/getenv "PROGRAM_1")
    (System/getenv "PROGRAM_2")
  ])

(defn -main
  "Runs battles between two Battleship binaries."
  [& args]
  (println (program_runner/call (first program_names)))
  (shutdown-agents)
)
