(ns filtering.core
  (:require clojure.set))

(def records [{:id 10 :cat 12 :div 16}
              {:id 10 :cat 11 :div 15}
              {:id 11 :cat 12 :div 18}
              {:id 11 :cat 22 :div 19}])

(defn has-all? [criteria records]
  (letfn [(take-vals [criteria records]
            (reduce conj #{} (map (key criteria) records)))]
    (clojure.set/subset? (val criteria) (take-vals criteria records))))

(defn satisfy? [criteria records-id]
  (if (has-all? criteria records-id)
    records-id
    []))

(defn filter-by-criteria [criteria records]
  (letfn [(get-id [records]
            (reduce conj #{} (map :id records)))
          (take-by-id [id records]
            (filter #(= (:id %) id) records))]
    (reduce into
            (pmap #(satisfy? (first criteria) (take-by-id % records))
                 (get-id records)))))

;; Esempi
(filter-by-criteria {:div #{18 19}} records)
(filter-by-criteria {:cat #{12}} records)
(filter-by-criteria {:div #{11 12}} records)

(defn random-records
  "Genera molt record per num id."
  [num molt]
  (let [take-random (fn [] (inc (rand-int 100)))]
    (map #(hash-map :id % :cat (take-random) :div (take-random))
         (reduce into (map #(repeat molt %) (range num))))))

;; Esempio con timing
;(time (filter-by-criteria {:div #{18 19}} (random-records 1000 10)))


