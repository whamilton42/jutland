(ns jutland.program_runner
  (use [clojure.java.shell :only [sh]])
)

(defn- program [folder name]
  (str folder "/" name))

(defn- run [program script]
  (println (str program " bin/" script))
  (sh "sh" "-c" (str program "/bin/" script)))

(defn- output_of_run [program script]
  (:out (run program script)))


(defn call [folder name script]
  (output_of_run (program folder name) script)
)
