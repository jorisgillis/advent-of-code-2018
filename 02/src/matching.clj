(ns matching
  (:require [clojure.string :as s]))

(defn- cartesian-product [xs ys]
  (mapcat (fn [x] (map (fn [y] [x y]) ys)) xs))

(defn- count-characters-differing [x y]
  (->> (map (fn [xc yc] (= xc yc)) x y)
       (filter (complement identity))
       count))

(defn- differ-by-one-character [x y]
  (= 1 (count-characters-differing x y)))

(defn- find-matching-ids [lines]
  (->> (cartesian-product lines lines)
       (filter (fn [[x y]] (< (compare x y) 0)))
       (filter (fn [[x y]] (differ-by-one-character x y)))
       first))

(defn- extract-common [xs ys]
  (->> (map (fn [x y] [x y]) xs ys)
       (filter (fn [[x y]] (= x y)))
       (map (fn [[x _]] x))))

(defn -main []
  (with-open [f (clojure.java.io/reader "input.txt")]
    (let [lines (line-seq f)
          [x y] (find-matching-ids lines)]
      (println (s/join (extract-common x y))))))