(ns filtering.core)

;; Pre: records are sorted by id
(def records [{:id 10 :cat 12 :div 16}
              {:id 10 :cat 11 :div 15}
              {:id 11 :cat 12 :div 18}
              {:id 11 :cat 22 :div 19}])

(def criteria {:cat #{11 12}})

(defn take-by-id [id records]
  (filter #(= (:id %) id) records))

(defn take-vals [criteria records]
  (reduce conj #{} (map (key criteria) records)))

(defn has-all? [criteria records]
  (clojure.set/subset? (val criteria) (take-vals criteria records)))

(defn get-id [records]
  (reduce conj #{} (map :id records)))

(defn filter-by [criteria records]
  (loop [ids (get-id records)
         recs (take-by-id (first ids) records)
         filtered []]
    (if (empty? recs)
      filtered
      (recur (disj ids (first ids))
             (take-by-id (first (rest ids)) records)
             (if (has-all? criteria recs)
               (conj filtered recs)
               filtered)))))
