(ns jutland.opts_parser)

(def required-opts #{:program-one :program-two :folder})

(defn missing-required?
  "Returns true if opts is missing any of the required-opts"
  [opts]
  (not-every? opts required-opts))
