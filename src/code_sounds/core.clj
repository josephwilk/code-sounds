(ns code-sounds.core
  (:require [clojure.tools.cli]
            [clojure.java.io :as io])
  (:import [clojure.lang LineNumberingPushbackReader]))

(def ^:private default-args {:init-ns    'user})

(defn- careful-refer [ns]
  (binding [*ns* ns]
    (refer 'clojure.core :exclude (or (keys (ns-interns ns)) ())))
  ns)

(def eof (Object.))

(defn read-file
  "Generate a lazy sequence of top level forms from a
   LineNumberingPushbackReader"
  [^LineNumberingPushbackReader r init-ns]
  (let [do-read (fn do-read [ns]
                  (lazy-seq
                   (let [form (binding [*ns* ns]
                                (read r false eof))
                         [ns? new-ns k] (when (sequential? form) form)
                         ns (if (and (symbol? new-ns)
                                     (or (= ns? 'ns) (= ns? 'in-ns)))
                              (careful-refer (create-ns new-ns))
                              ns)]
                     (when-not (= form eof)
                       (cons form (do-read ns))))))]
    (do-read (careful-refer (create-ns init-ns)))))

(defn check-reader [reader]
  (read-file (LineNumberingPushbackReader. reader) (:init-ns default-args)))

(defn check-file [source-file]
  (with-open [reader (io/reader source-file)]
    (check-reader reader)))

(defn run [source-file & args] (check-file (io/file source-file)))

(comment
  (run  "/Users/josephwilk/Workspace/josephwilk/clojure/code-sounds/test/fixtures/bad_code.clj")
  )
