(ns jutland.program_runner
  (use [clojure.java.shell :only [sh]])
)

(defn- file [folder name]
  (str folder "/" name))

(defn- run [file opts]
  (sh "sh" "-c" (str file " " opts)))

(defn- output_of_run [file opts]
  (:out (run file opts)))


(defn call [folder name opts]
  (output_of_run (file folder name) opts)
)
