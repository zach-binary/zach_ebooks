(ns zach-ebooks.phrases)

(def test-phrase "We will focus on the technique mostly used with 3D fighters like Virtua Fighter. It is following a certain pattern of high/low blocking that will effectively block a large part of the high/low mix-ups that you have to deal with in a match. This concept is not unique to 3D fighters, and it is also found in 2D fighters. It plays an important role in the King of Fighters because it forces the player to react to high-speed mix-ups more frequently than most other 2D fighters.")

(def phrase-length 10)

(defn gen-corpus
	"Generates a corpus of data needed to start a markov chain.
	 - accepts a text string"
	[text]
	(->> (clojure.string/split text #"\s") 
		(partition 2 1)
		(reduce (fn [acc [w next-w]]
			(update-in acc [w next-w] 
				(fnil inc 0)))
			{})))

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

(defn start-chain
	[data]
	(let [total (count data)
		  	r (rand total)]
		 (nth (keys data) r)))

(defn get-next-word
	[data word]
	(let [data-entry (get data word)
			v (vec (vals data-entry))
			next-words (vec (keys data-entry))]
			(->> (wrand v)
				(nth next-words))))

(defn test-markov
	[]
	(let [data (gen-corpus test-phrase)
			start (start-chain data)]
		(loop [phrase [] word (start-chain data) n 0]
			(if (> n phrase-length)
				(clojure.string/join " " phrase)
			(let [next-word (get-next-word data word)]
				(recur (conj phrase next-word) next-word (inc n)))))))
	