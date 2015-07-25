(ns jutland.program_runner
  (use [clojure.java.shell :only [sh]])
  (:gen-class))

(defn- program_file [name]
  (str (System/getenv "BATTLESHIP_PROGRAMS_FOLDER") name))

(defn- run_program [name]
  (sh "sh" "-c" (program_file name)))

(defn- run_program_and_capture_output [name]
  (:out (run_program name)))


(defn call [name]
  (run_program_and_capture_output name))
