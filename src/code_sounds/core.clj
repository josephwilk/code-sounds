(ns code-sounds.core
  (:require [clojure.tools.cli]
            [clojure.java.io :as io])
  (:import [clojure.lang LineNumberingPushbackReader]))

(def init-ns 'user)

(defn- careful-refer [ns]
  (binding [*ns* ns]
    (refer 'clojure.core :exclude (or (keys (ns-interns ns)) ())))
    ns)

(def eof (Object.))

(defn expr-seq
  "Given an expression (any piece of Clojure data), return a lazy (depth-first)
  sequence of the expr and all its sub-expressions"
  [expr]
  (tree-seq sequential?
            seq
            expr))

(defn file->seq [source-file]
  (with-open [reader (io/reader source-file)]
    (let [^LineNumberingPushbackReader r (LineNumberingPushbackReader. reader)
          do-read (fn do-read [ns]
                    (let [form (binding [*ns* ns] (read r false eof))
                          [ns? new-ns k] (when (sequential? form) form)
                          ns (if (and (symbol? new-ns)
                                      (or (= ns? 'ns) (= ns? 'in-ns)))
                               (careful-refer (create-ns new-ns))
                               ns)]
                      (when-not (= form eof) (cons form (do-read ns)))))]

      (do-read (careful-refer (create-ns init-ns))))))

(defn run [source-file & args]
  (-> source-file
      io/file
      file->seq
      expr-seq))

(println (run "/Users/josephwilk/Workspace/josephwilk/clojure/code-sounds/test/fixtures/bad_code.clj"))
