(ns checksum)

(defn- count-characters [id]
  (->>
   id
   (into #{})
   (map (fn [c] [c (count (filterv #(= c %) id))]))
   (into {})))

(defn- contains-character-times? [n count-map]
  (->> count-map
       vals
       (filter #(= n %))
       empty?
       not))

(defn- id-contains-multiple? [n id]
  (->> id
       count-characters
       (contains-character-times? n)))

(defn- count-ids-with-multiple [n ids]
  (->> ids
       (map (partial id-contains-multiple? n))
       (filterv identity)
       count))

(defn -main []
  (with-open [f (clojure.java.io/reader "input.txt")]
    (let [lines      (line-seq f)
          num-double (count-ids-with-multiple 2 lines)
          num-triple (count-ids-with-multiple 3 lines)]
      (println (* num-double num-triple)))))