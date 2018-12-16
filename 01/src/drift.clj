(ns drift)

(defn -main []
  (with-open [f (clojure.java.io/reader "input.txt")]
    (println
     (->> (line-seq f)
          (mapv #(Integer/parseInt %))
          (reduce + 0)))))
