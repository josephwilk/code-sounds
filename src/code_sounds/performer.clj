(ns code-sounds.performer
  (:use [overtone.live])
  (:require
   [overtone.music.pitch :as pitch]
   [code-sounds.instruments :as inst]))

(def happy (pitch/scale-field :c :major))
(def sad   (pitch/scale-field :c :minor))

(defn pick-scale [{score :score}] (if (< score 0.5) sad happy))

(def transpose 10)
(def numbers             [50 53 56 59 62 65 68 71 74 77])
(def punctuation-numbers [46 44 39 33 63 40 41 91 93 123 125 35 61 95 60 62 34 45 43 47 92])

(defn- code-as-number [character-code]
  (Integer. (str (char character-code))))

(defn- find-pitch [character-code scale]
  (cond
   (<= 65 character-code 90)
   (+ character-code transpose)

   (<= 97 character-code 122)
   (nth scale (- character-code 80))

   (= character-code 32)
   32

   (<= 48 character-code 57)
   (+ transpose (nth numbers (code-as-number character-code)))

   (some #{character-code} punctuation-numbers)
   60))

(defn sonify-character [character props]
  (let [character-code (int character)
        scale (pick-scale props)
        pitch (find-pitch character-code scale)]
    {:pitch pitch
     :out character}))

(defn sonify [string props]
  (doall (map #(sonify-character % props) (str string))))

(defn play [{code :code :as props}]
  (doseq [event (mapcat #(sonify % props) code)]
    (print (:out event))
    (flush)
    (inst/supersaw2 :freq (midi->hz (:pitch event)))
    ;;Lazy
    (Thread/sleep 200)))

(comment
  (play {:sad 1 :code ["hello"]})
  (play {:sad 0 :code ["hello"]})

  (sonify-character \c {:score 1})
  (sonify-character \c {:score 0})
  )
