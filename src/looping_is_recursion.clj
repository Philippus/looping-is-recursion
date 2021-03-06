(ns looping-is-recursion)

(defn power [base exp]
  (let [helper (fn [acc n k]
                 (if (zero? k)
                   acc
                   (recur (* acc n) n (dec k))))]
    (helper 1 base exp)))

(defn last-element [a-seq]
  (let [helper (fn [curlast a-seq]
                 (if (empty? a-seq)
                   curlast
                   (recur (first a-seq) (rest a-seq))))]
    (helper nil a-seq)))

(defn seq= [seq1 seq2]
  (let [helper (fn [curr seq1 seq2]
                 (if (and (empty? seq1) (empty? seq2))
                   curr
                   (recur (and curr (== (first seq1) (first seq2))) (rest seq1) (rest seq2))))]
    (cond
      (not= (count seq1) (count seq2)) false
      :else (helper true seq1 seq2))))

(defn find-first-index [pred a-seq]
  (loop [index 0
         seq a-seq]
    (cond
      (empty? seq) nil
      (pred (first seq)) index
      :else (recur (+ index 1) (rest seq)))))

(defn avg [a-seq]
  (loop [running-total 0
         number-of-items 0
         seq a-seq]
    (cond
      (empty? seq) (/ running-total number-of-items)
      :else (recur (+ running-total (first seq)) (+ number-of-items 1) (rest seq)))))

(defn list-contains? [coll value]
  (let [s (seq coll)]
    (if s
      (if (= (first s) value) true (recur (rest s) value))
      false)))

(defn toggle [a-set elem]
  (if (list-contains? a-set elem)
    (remove #{elem} a-set)
    (cons elem (seq a-set))))

(defn parity [a-seq]
  (loop [odd-set []
         seq a-seq]
    (if (empty? seq)
      odd-set
      (recur (toggle odd-set (first seq)) (rest seq)))))

(defn fast-fibo [n]
  (loop [a 0
         b 1
         n n]
    (cond
      (< n 0) a
      (== n 0) a
      :else (recur b (+ a b) (- n 1)))))

(defn cut-at-repetition [a-seq]
  (loop [curset []
         seq a-seq]
    (cond
      (empty? seq) curset
      (list-contains? curset (first seq)) curset
      :else (recur (conj curset (first seq)) (rest seq)))))
