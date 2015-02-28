(ns zach-ebooks.phrases
	(:require [zach-ebooks.corpus :as corpus]))

(def phrase-length 20)

(defn wrand
	"given a vector of slice sizes, returns the index of a slice given a                                                                                                                                      
	random spin of a roulette wheel with compartments proportional to                                                                                                                                         
	slices."
	[slices]
	(let [total (count slices)
        	r (rand total)]
	    (loop [i 0 sum 0]
	      (if (< r (+ (slices i) sum))
	        i
	        (recur (inc i) (+ (slices i) sum))))))

(defn add-text
	[text]
	(corpus/learn-text text))

(defn start-chain
	[corpus]
	(let [total (count corpus)
		  	r (rand total)]
		 (nth (keys corpus) r)))

(defn get-next-word
	[corpus word]
	(let [corpus-entry (get corpus word)
			v (vec (vals corpus-entry))
			next-words (vec (keys corpus-entry))]
			(->> (wrand v)
				(nth next-words))))

(defn gen-sentence
	[]
	(let [corpus (deref corpus/data)]
		(loop [phrase [] word (start-chain corpus) n 0]
			(if (> n phrase-length)
				(clojure.string/join " " phrase)
			(let [next-word (get-next-word corpus word)]
				(recur (conj phrase next-word) next-word (inc n)))))))
	