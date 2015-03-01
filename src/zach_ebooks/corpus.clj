(ns zach-ebooks.corpus
	(:use [clojure.java.io]))

(def data (atom {}))

(def test-phrase "We will focus on the technique mostly used with 3D fighters like Virtua Fighter. It is following a certain pattern of high/low blocking that will effectively block a large part of the high/low mix-ups that you have to deal with in a match. This concept is not unique to 3D fighters, and it is also found in 2D fighters. It plays an important role in the King of Fighters because it forces the player to react to high-speed mix-ups more frequently than most other 2D fighters.")

(defn add-to-corpus
	"Generates a corpus of data needed to start a markov chain.
	 - accepts a text string"
	[text]
	(->> (clojure.string/split text #"\s") 
		(partition 2 1)
		(reduce (fn [acc [w next-w]]
			(update-in acc [w next-w] 
				(fnil inc 0)))
			@data)))

(defn learn-text
	[text]
	(reset! data (add-to-corpus text)))

(defn learn-file
	[fileptr]
	(if (.isDirectory fileptr)
		(println "Skipping Directory" (.getPath fileptr))
	(reset! data (add-to-corpus 
		(slurp (.getPath fileptr))))))

(defn learn-files
	[dirpath]
	(let [directory (file dirpath)
			files (file-seq directory)]
		(map learn-file files))

	"Complete") ; adding here so all the data doesn't get dumped to the repl

(defn persist-data
	[]
	(spit "data" @data))

(defn load-data
	[]
	(reset! data (read-string (slurp "data"))))

(if (.exists (as-file "data"))
	(load-data)
	(->> (learn-text test-phrase)
		(spit "data")))