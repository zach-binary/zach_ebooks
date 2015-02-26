(ns zach-ebooks.core
  (:gen-class)
  (:require [zach-ebooks.tasks :as tasks]
  			[clj-time.core :as t]
  			[clj-time.periodic :refer [periodic-seq]]
  			[chime :refer [chime-at]]))

(def schedule (periodic-seq (t/now)
							(-> 10 t/seconds)))

(defn run-task
	"Runs every 10 seconds"
	[time]
	(println "Chiming at" time))

(defn -main
	"Entry point I suppose.."
	[& args]
	(println "Starting Bot")
	(chime-at schedule run-task))



