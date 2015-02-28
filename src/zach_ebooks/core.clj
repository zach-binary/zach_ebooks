(ns zach-ebooks.core
  (:gen-class)
  (:require [zach-ebooks.tasks :as tasks]
  			[zach-ebooks.phrases :as phrases]))

(defn -main
	"Entry point I suppose.."
	[& args]
	(println "Starting Bot")
	(->> (phrases/gen-sentence)
		(tasks/update-status)
		(println)))



