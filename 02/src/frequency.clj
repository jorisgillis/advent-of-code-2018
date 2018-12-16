(ns frequency)

(defn- find-duplicate [deltas]
  (loop [acc {:seen    #{}
              :current 0}
         l   deltas]
    (let [current (:current acc)
          seen    (:seen acc)
          delta   (first l)
          next    (+ delta current)]
      (if (contains? seen next)
        next
        (recur {:seen    (conj seen next)
                :current next}
               (rest l))))))

(defn -main []
  (with-open [f (clojure.java.io/reader "input.txt")]
    (println
     (->> (line-seq f)
          (map #(Integer/parseInt %))
          cycle
          find-duplicate))))