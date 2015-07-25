(ns jutland.program_runner
  (use [clojure.java.shell :only [sh]])
)

(defn- file [folder name]
  (str folder "/" name))

(defn- run [file]
  (sh "sh" "-c" file))

(defn- output_of_run [file]
  (:out (run file)))


(defn call [folder name]
  (let [[file] [(file folder name)]]
    (output_of_run file)
  )
)
