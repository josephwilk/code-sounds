(ns code-sounds.code)

(def metrics {:score 0.0})

(defn with-metrics [code]
  (conj metrics {:code code}))
